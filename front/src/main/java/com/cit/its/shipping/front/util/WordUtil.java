package com.cit.its.shipping.front.util;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;

/**
 * @author 杨贺龙
 * @name WordUtil
 * @create 2019-12-06 10:22
 * @description:
 */
public class WordUtil {
    public static XWPFDocument outWord(String title, String[] header, String[][] values) throws Exception {
        XWPFDocument doc = new XWPFDocument();
        //输出位置
        // FileOutputStream out = new FileOutputStream(new File("E:\\"));

        //添加标题
        XWPFParagraph titleParagraph = doc.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText(title);
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);

        //段落
        XWPFParagraph firstParagraph = doc.createParagraph();
        XWPFRun firstRun = firstParagraph.createRun();
        firstRun.setText(title);
        firstRun.setColor("696969");
        firstRun.setFontSize(16);


        //设置段落背景色
        CTShd ctShd = firstRun.getCTR().addNewRPr().addNewShd();
        ctShd.setVal(STShd.CLEAR);
        ctShd.setFill("97FFFF");

        //换行
        XWPFParagraph paragraph1 = doc.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");

        //基本信息表格
        XWPFTable infoTable = doc.createTable();
        //去表格边框
        infoTable.getCTTbl().getTblPr().unsetTblPrChange();

        //列宽自动分隔
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));
        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        for (String str : header) {
            //infoTableRowOne.getCell(0).setText(str);
            infoTableRowOne.addNewTableCell().setText(str);
        }

        for (int i = 0; i < values.length; i++) {
            XWPFTableRow infoTableRowTwo = infoTable.createRow();
            for (int j = 0; j < values[i].length; j++) {
                infoTableRowTwo.getCell(j).setText(values[i][j]);
            }
        }
        /*//表格第三行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("生日");
        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");

        //表格第四行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("性别");
        infoTableRowFour.getCell(1).setText(": 男");

        //表格第五行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("现居地");
        infoTableRowFive.getCell(1).setText(": xx");*/

        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(doc, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "ctpHeader";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, doc);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "ctpFooter";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, doc);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

        return doc;

    }
}
