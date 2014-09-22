package com.other.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-12-13
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class PdfTest {

    public static void main(String[] args) throws IOException, DocumentException {
//        Document doc = null;
//        try {
//            doc = new Document();
//            PdfWriter.getInstance(doc, new FileOutputStream("C:/itext.pdf"));
//            doc.open();
//            doc.add(new Paragraph("Hello World"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } finally {
//            doc.close();
//        }

//==================================================================

//        Document d = new Document();// 建立文档
//        try {
//            PdfWriter.getInstance(d, new FileOutputStream("e:/Hello World.pdf"));// 建立一个PDF格式的书写器
////			HtmlWriter.getInstance(d, new FileOutputStream(
////					"e:/Hello World.html"));// 建立一个HTML格式的书写器
//            // 下面是解决中文的问题
//            BaseFont bf = BaseFont.createFont(
//                    "c:/windows/fonts/simsun.ttc,1", BaseFont.IDENTITY_H,
//                    BaseFont.EMBEDDED);
//
//			/*
//             * 下面分别是设置PDF文件的属性:标题,主题,关键字
//			 */
//            d.addTitle("java生成pdf文件");
//            d.addSubject("主题");
//            d.addKeywords("关键字");
//            d.addAuthor("创建者---XXXX");
//            d.open();// 打开文档
//            d.add(new Paragraph("你好 World!", new Font(bf)));// 想pdf文件中添加内容
//            d.close();// 关闭文档
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

//======================================================


//        Document doc = null;
//        try {
//            String pdfFilePath = "f:/itext-demo.pdf";
//            doc = new Document(PageSize.A4);
//            // 第二 步： Get a PdfWriter instance.
//            PdfWriter.getInstance(doc, new FileOutputStream(pdfFilePath));
//            // 第三步：Open the Document.
//            doc.open();
//            // 添加 中文信息
//            BaseFont bfCN = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", false);
//            // 设置字体大小
//            Font fontCN = new Font(bfCN, 12, Font.NORMAL, BaseColor.RED);
//            doc.add(new Paragraph("使用STSongStd-Light字体输出中文。", fontCN));
//
//
//            BaseFont bfHei = BaseFont.createFont("c:/Windows/fonts/SIMHEI.TTF",
//                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            Font font = new Font(bfHei, 32);
//            String text = "这是黑体字测试！";
//            doc.add(new Paragraph(text, font));
//
//
//            bfHei = BaseFont.createFont("c:/Windows/fonts/SIMKAI.TTF",
//                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            font = new Font(bfHei, 32);
//            text = "这是楷体字测试！";
//            doc.add(new Paragraph(text, font));
//
//            BaseFont bfSun=BaseFont.createFont("c:/Windows/fonts/SIMSUN.TTC,1"
//                    , BaseFont.IDENTITY_H
//                    , BaseFont.NOT_EMBEDDED);
//
//            font = new Font(bfSun, 16);
//            text = "这是字体集合中的新宋体测试！";
//            doc.add(new Paragraph(text, font));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            doc.close();
//        }

          generateAshwinFriends();
    }

    private static void generateAshwinFriends() throws IOException,
            FileNotFoundException, DocumentException {
        PdfReader pdfTemplate = new PdfReader("C:/Users/Administrator/Desktop/template/测试pdf生成.pdf");
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Administrator/Desktop/template/aaaa.pdf");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
        stamper.setFormFlattening(true);

        stamper.getAcroFields().setField("name", "维唯为为aaa");
        stamper.getAcroFields().setField("sex", "男aaabbb");
        stamper.getAcroFields().setField("age", "25");

        stamper.close();
        pdfTemplate.close();

    }

}
