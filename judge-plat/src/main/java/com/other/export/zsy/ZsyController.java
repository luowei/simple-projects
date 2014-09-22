package com.other.export.zsy;

import com.other.user.UserInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by luowei on 2014/9/18.
 */
@Controller
public class ZsyController {

    @Autowired
    ZsyRepository zsyRepository;

    @RequestMapping("/download/guolei")
    public ResponseEntity<byte[]> downloadGuolei(HttpServletRequest request, String priceDate) throws FileNotFoundException {
        if(isBlank(priceDate)){
            return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "guolei_" + priceDate + ".xls");

//        System.out.println("=======:" + request.getServletContext().getRealPath("xls/aaa.xls"));
//        InputStream in = request.getServletContext().getResourceAsStream("xls/aaa.xls");

        UserInfo user = (UserInfo) request.getSession().getAttribute("gujiauser");
        if (user == null || isBlank(user.getUsername())) {
            return null;
        }
        InputStream in = request.getServletContext().getResourceAsStream("xls/" + user.getUsername() + ".xls");

        byte[] data = handleExcel(in, priceDate);

        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
    }

    private byte[] handleExcel(InputStream in, String priceDate) {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();

            //从excel中取得eList
            HSSFWorkbook wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);

            //内容从第三行开如
            int rowIdx = 2;
            HSSFRow row = row = sheet.getRow(rowIdx);
            while (row != null && row.getCell(6) != null) {

                if (row.getCell(6) == null || row.getCell(6).getCellType() == Cell.CELL_TYPE_STRING) {
                    break;
                }

                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(4).setCellType(Cell.CELL_TYPE_NUMERIC);
                row.getCell(5).setCellType(Cell.CELL_TYPE_NUMERIC);
                row.getCell(6).setCellType(Cell.CELL_TYPE_NUMERIC);

                row.getCell(0).setCellValue(priceDate);

                int id = (int) row.getCell(6).getNumericCellValue();
                Zsy zsy = zsyRepository.findByIdAndPriceDate(id, priceDate);
                if(zsy==null) {
                    rowIdx++;
                    row = sheet.getRow(rowIdx);
                    continue;
                }

                BigDecimal diJia =  zsy.getDiJia();
                BigDecimal gaoJia = zsy.getGaoJia();


                row.getCell(4).setCellValue(diJia.doubleValue());
                row.getCell(5).setCellValue(gaoJia.doubleValue());

                rowIdx++;
                row = sheet.getRow(rowIdx);
            }
            wb.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
