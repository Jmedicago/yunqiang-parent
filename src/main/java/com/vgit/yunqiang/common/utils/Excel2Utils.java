package com.vgit.yunqiang.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class Excel2Utils {

    public static void writeExcel(String filepath, String sheetName, List<String> titles, List<Map<String, Object>> values) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 650);

        XSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //声明列对象
        XSSFCell cell;

        //创建标题
        Map<String, Integer> titleOrder = new HashMap<String, Integer>();
        for (int i = 0; i < titles.size(); i++) {
            sheet.setColumnWidth(i, 6000);
            cell = row.createCell(i);
            String title = titles.get(i);
            cell.setCellValue(title);
            XSSFFont font = workbook.createFont();
            //设置excel数据字体颜色
            font.setColor(Font.COLOR_NORMAL);
            //设置excel数据字体大小
            font.setFontHeightInPoints((short) 15);
            headStyle.setFont(font);
            cell.setCellStyle(headStyle);
            titleOrder.put(title, i);
        }

        XSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //自动换行
        dataStyle.setWrapText(true);

        // 写入正文
        Iterator<Map<String, Object>> iterator = values.iterator();
        // 行号
        int index = 1;
        while (iterator.hasNext()) {
            //填充数据
            row = sheet.createRow(index);
            row.setHeight((short) 2000);
            Map<String, Object> value = iterator.next();
            for (Map.Entry<String, Object> map : value.entrySet()) {
                // 获取列名
                String title = map.getKey();
                // 根据列名获取序号
                int i = titleOrder.get(title);
                // 在指定序号处创建cell
                cell = row.createCell(i);
                // 获取列的值
                Object object = map.getValue();
                // 判断object的类型
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (object instanceof Double) {
                    cell.setCellValue((Double) object);
                } else if (object instanceof Date) {
                    String time = simpleDateFormat.format((Date) object);
                    cell.setCellValue(time);
                } else if (object instanceof Calendar) {
                    Calendar calendar = (Calendar) object;
                    String time = simpleDateFormat.format(calendar.getTime());
                    cell.setCellValue(time);
                } else if (object instanceof Boolean) {
                    cell.setCellValue((Boolean) object);
                } else {
                    if (object != null) {
                        cell.setCellValue(object.toString());
                    }
                }

                if ("图片".equals(title)) {
                    if (object != null && StringUtils.isNotBlank(object.toString())) {
                        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                        System.out.println("图片" + index + " : " + object.toString());
                        BufferedImage image = ImageIO.read(new URL(object.toString()));
                        ImageIO.write(image, "jpg", byteArrayOut);
                        XSSFClientAnchor anchor = new XSSFClientAnchor(
                                0, 0, 0, 0,
                                (short) 1, index, (short) 2 , 1 + index
                        );
                        // 插入图片
                        patriarch.createPicture(
                                anchor,
                                workbook.addPicture(
                                        byteArrayOut.toByteArray(),
                                        XSSFWorkbook.PICTURE_TYPE_JPEG
                                )
                        );
                        byteArrayOut.close();
                    }
                }
            }
            index++;
        }
        //输出至文件
        workbook.write(new FileOutputStream(filepath));
    }

    public static void exportXlsx() throws IOException {
        List<String> titleList = Arrays.asList("品名", "图片");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("商品信息");
        // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 650);

        XSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //声明列对象
        XSSFCell cell;

        //创建标题
        for (int i = 0; i < titleList.size(); i++) {
            sheet.setColumnWidth(i, 6000);
            cell = row.createCell(i);
            cell.setCellValue(titleList.get(i));
            XSSFFont font = workbook.createFont();
            //设置excel数据字体颜色
            font.setColor(Font.COLOR_NORMAL);
            //设置excel数据字体大小
            font.setFontHeightInPoints((short) 15);
            headStyle.setFont(font);
            cell.setCellStyle(headStyle);
        }

        XSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //自动换行
        dataStyle.setWrapText(true);

        //填充数据
        row = sheet.createRow(1);
        row.setHeight((short) 2000);
        // ----- 姓名
        cell = row.createCell(0);
        cell.setCellValue("11");
        // ----- 证件照
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(new URL("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1400063385,2378282079&fm=26&gp=0.jpg"));
        ImageIO.write(image, "jpg", byteArrayOut);
        XSSFClientAnchor anchor = new XSSFClientAnchor(
                0, 0, 0, 0,
                (short) 1, 1, (short) 2, 2
        );
        // 插入图片
        patriarch.createPicture(
                anchor,
                workbook.addPicture(
                        byteArrayOut.toByteArray(),
                        XSSFWorkbook.PICTURE_TYPE_JPEG
                )
        );
        byteArrayOut.close();

        //输出至文件
        workbook.write(new FileOutputStream(new File("D:\\测试.xlsx")));
    }

    public static void exportXls() throws IOException {
        List<String> titleList = Arrays.asList("1", "2");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("11");
        // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 650);

        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //声明列对象
        HSSFCell cell;

        //创建标题
        for (int i = 0; i < titleList.size(); i++) {
            sheet.setColumnWidth(i, 6000);
            cell = row.createCell(i);
            cell.setCellValue(titleList.get(i));
            HSSFFont font = workbook.createFont();
            //设置excel数据字体颜色
            font.setColor(Font.COLOR_NORMAL);
            //设置excel数据字体大小
            font.setFontHeightInPoints((short) 15);
            headStyle.setFont(font);
            cell.setCellStyle(headStyle);
        }

        HSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //自动换行
        dataStyle.setWrapText(true);

        //填充数据
        row = sheet.createRow(1);
        row.setHeight((short) 2000);
        // ----- 姓名
        cell = row.createCell(0);
        cell.setCellValue("11");
        // ----- 证件照
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(new URL("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2778527992,3607430593&fm=26&gp=0.jpg"));
        ImageIO.write(image, "jpg", byteArrayOut);
        HSSFClientAnchor anchor = new HSSFClientAnchor(
                0, 0, 0, 0,
                (short) 1, 1, (short) 2, 2
        );
        // 插入图片
        patriarch.createPicture(
                anchor,
                workbook.addPicture(
                        byteArrayOut.toByteArray(),
                        HSSFWorkbook.PICTURE_TYPE_JPEG
                )
        );
        byteArrayOut.close();

        //输出至文件
        workbook.write(new FileOutputStream(new File("D:\\测试.xls")));
    }


    public static void main(String[] args) throws IOException {
        String filePath = "D:\\excel\\导入.xlsx";
        String picStorePath = "D:\\excel\\";

        File file = new File(filePath);
        String fileName = file.getName();
        FileInputStream fileInputStream = new FileInputStream(file);
        importExcelWithImage(fileInputStream, fileName, picStorePath);
    }

    public static void importExcelWithImage(InputStream in, String fileName, String picStorePath) throws IOException {
        Workbook wookbook;
        Sheet sheet;
        //图片数据
        Map<String, PictureData> pictureDataMap = null;

        //得到工作簿
        try {
            if (fileName.endsWith(".xls")) {
                //2003版本的excel，用.xls结尾
                wookbook = new HSSFWorkbook(in);
            } else {
                //2007版本的excel，用.xlsx结尾
                wookbook = new XSSFWorkbook(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // ----- 获取图片数据 -----
        sheet = wookbook.getSheetAt(0);
        // 判断用07还是03的方法获取图片
        if (fileName.endsWith(".xls")) {
            pictureDataMap = getXlsPictures((HSSFSheet) sheet);
        } else {
            pictureDataMap = getXlsxPictures((XSSFSheet) sheet);
        }

        // ----- 获得标题 -----
        Row rowHead = sheet.getRow(0);
        int rowHeadPhysicalNumberOfCells = rowHead.getPhysicalNumberOfCells();
        for (int i = 0; i < rowHeadPhysicalNumberOfCells; i++) {
            Cell cell = rowHead.getCell(i);
            System.out.println(cell.getStringCellValue());
        }

        // ----- 获取其他数据 -----
        int totalRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= totalRowNum; i++) {
            Row row = sheet.getRow(i);
            int physicalNumberOfCells = row.getPhysicalNumberOfCells();
            for (int j = 0; j < physicalNumberOfCells; j++) {
                Cell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }
        }

        // ----- 输出图片 -----
        try {
            printImg(pictureDataMap, picStorePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片和位置 (xls)
     *
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getXlsPictures(HSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<>();
        List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
                PictureData pdata = picture.getPictureData();
                // 行号-列号
                String key = cAnchor.getRow1() + "-" + cAnchor.getCol1();
                map.put(key, pdata);
            }
        }
        return map;
    }

    /**
     * 获取图片和位置 (xlsx)
     *
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getXlsxPictures(XSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<>();
        List<POIXMLDocumentPart> list = sheet.getRelations();
        for (POIXMLDocumentPart part : list) {
            if (part instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) part;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture picture = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = picture.getPreferredSize();
                    CTMarker marker = anchor.getFrom();
                    String key = marker.getRow() + "-" + marker.getCol();
                    map.put(key, picture.getPictureData());
                }
            }
        }
        return map;
    }

    public static void printImg(Map<String, PictureData> sheetList, String path) throws IOException {
        Object[] key = sheetList.keySet().toArray();
        for (int i = 0; i < sheetList.size(); i++) {
            // 获取图片流
            PictureData pic = sheetList.get(key[i]);
            // 获取图片索引
            String picName = key[i].toString();
            // 获取图片格式
            String ext = pic.suggestFileExtension();

            byte[] data = pic.getData();

            //图片保存路径
            String imgPath = path + picName + "." + ext;
            FileOutputStream out = new FileOutputStream(imgPath);
            System.out.println(imgPath);
            out.write(data);
            out.close();
        }
    }

}
