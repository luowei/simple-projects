package com.other.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 14-3-28
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
public class PdfView<T> extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> stringObjectMap, Document document,PdfWriter pdfWriter,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        Font f = null;
        Image logo = null;
        try {
            response.setContentType("application/pdf");

            document.setPageSize(PageSize.A4);
//        BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\simsun.ttc,1", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            f = new Font(baseFont,10, Font.NORMAL);

            String logoPath =  getConfigPath() + "header.jpg";
            logo = Image.getInstance(logoPath);
            logo.setAbsolutePosition(0,0);
            logo.setAlignment(Image.ALIGN_CENTER);
//            logo.scaleAbsolute(30,30);
            logo.scalePercent(36);

//            PdfContentByte contentByte = pdfWriter.getDirectContent();
//            PdfTemplate tp = contentByte.createTemplate(30, 30);
//            tp.addImage(logo);
//            contentByte.addTemplate(tp, 0, 0);

//            HeaderFooter的第2个参数为非false时代表打印页码,页眉页脚中也可以加入图片，并非只能是文字
//            HeaderFooter header = new HeaderFooter(new Phrase(contentByte+"上海隆众信息技术有限公司",f),false);
            HeaderFooter header = new HeaderFooter(new Phrase(new Chunk(logo, 0, -20)), false);
            //设置是否有边框等
            header.setBorder(Rectangle.NO_BORDER);
            header.setAlignment(1);
//            header.setBorderColor(Color.black);




            document.setHeader(header);

            HeaderFooter footer=new HeaderFooter(new Phrase("-",f),new Phrase("-",f));
            // 0是靠左,1是居中, 2是居右
            footer.setAlignment(1);
            footer.setBorderColor(Color.black);
            footer.setBorder(Rectangle.NO_BORDER);
            document.setFooter(footer);

            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String,Phrase> revenueData = new HashMap<String,Phrase>();
        revenueData.put("1/20/2010", new Phrase("你好",f));
        revenueData.put("1/21/2010", new Phrase("我你好",f));
        revenueData.put("1/22/2010", new Phrase("$100,000",f));
        revenueData.put("1/23/2010", new Phrase("$100,000",f));
        revenueData.put("1/24/2010", new Phrase("$100,000",f));


        Table table = new Table(2);
        table.addCell("Month");
        table.addCell("Revenue");

        for (Map.Entry<String, Phrase> entry : revenueData.entrySet()) {
            table.addCell(entry.getKey());
            table.addCell(entry.getValue());
        }

        document.add(table);
//        document.add(logo);

        //添加注释,注释有标题和内容,注释可以是文本，内部链接，外部链接，图片等
//        Annotation annotation=new Annotation("what's this?","it's a tree and it is not a big");
//        document.add(annotation);

        document.close();
    }

    private static String getConfigPath() {
        String configFilePath = PdfView.class.getClassLoader().getResource("resource").getPath().substring(1);
        // 判断系统 linux，windows
        if ("\\".equals(File.separator)) {
            configFilePath = configFilePath.replace("%20", " ");
        } else if ("/".equals(File.separator)) {
            configFilePath = "/" + configFilePath.replace("%20", " ");
        }
        return configFilePath;
    }
}
