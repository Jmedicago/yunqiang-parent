package com.vgit.yunqiang.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import com.vgit.yunqiang.common.consts.GlobalSettingNames;
import com.vgit.yunqiang.common.exception.BisException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

/**
 * Excel操作工具类
 */
public class ExcelUtils {

    public static final String OFFICE_EXCEL_XLS = "xls";
    public static final String OFFICE_EXCEL_XLSX = "xlsx";

    /**
     * 根据文件路径获取Workbook对象
     *
     * @param filepath 文件全路径
     */
    public static Workbook getWorkbook(String filepath, boolean isUrl)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        InputStream is = null;
        Workbook wb = null;
        if (StringUtils.isBlank(filepath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        } else {
            String suffix = getSuffix(filepath);
            if (StringUtils.isBlank(suffix)) {
                throw new IllegalArgumentException("文件后缀不能为空");
            }
            if (OFFICE_EXCEL_XLS.equals(suffix) || OFFICE_EXCEL_XLSX.equals(suffix)) {
                try {
                    if (isUrl) {
                        is = getInputStreamByUrl(filepath);
                    } else {
                        is = new FileInputStream(filepath);
                    }
                    wb = WorkbookFactory.create(is);
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (wb != null) {
                        wb.close();
                    }
                }
            } else {
                throw new IllegalArgumentException("该文件非Excel文件");
            }
        }
        return wb;
    }

    /**
     * 根据地址获得数据的输入流
     *
     * @param strUrl 网络连接地址
     * @return url的输入流
     */
    private static InputStream getInputStreamByUrl(String strUrl) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(), output);
            return new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取后缀
     *
     * @param filepath filepath 文件全路径
     */
    private static String getSuffix(String filepath) {
        if (StringUtils.isBlank(filepath)) {
            return "";
        }
        int index = filepath.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return filepath.substring(index + 1, filepath.length());
    }

    /**
     * 读取指定Sheet也的内容
     *
     * @param filepath filepath 文件全路径
     * @param sheetNo  sheet序号,从0开始,如果读取全文sheetNo设置null
     */
    public static List<List<Map<String, String>>> readExcel(String filepath, boolean isUrl, Integer sheetNo)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        List<List<Map<String, String>>> sheets = new ArrayList<List<Map<String, String>>>();
        Workbook workbook = getWorkbook(filepath, isUrl);
        if (workbook != null) {
            if (sheetNo == null) {
                int numberOfSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numberOfSheets; i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    if (sheet == null) {
                        continue;
                    }
                    sheets.add(readExcelSheet(sheet));
                }
                return sheets;
            } else {
                Sheet sheet = workbook.getSheetAt(sheetNo);
                if (sheet != null) {
                    sheets.add(readExcelSheet(sheet));
                    return sheets;
                }
            }
        }
        return sheets;
    }

    private static List<Map<String, String>> readExcelSheet(Sheet sheet) throws IOException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String cellData = null;
        if (sheet != null) {
            // 单元格数据
            int rowTotal = sheet.getPhysicalNumberOfRows(); // 得到Excel的总记录条数
            for (int i = 1; i < rowTotal; i++) { // 遍历行
                Map<String, String> map = new LinkedHashMap<String, String>();
                Row row = sheet.getRow(i);
                if (row != null) {
                    int cellTotal = row.getPhysicalNumberOfCells(); // 表头总共的列数
                    for (int j = 0; j < cellTotal; j++) {
                        try {
                            cellData = (String) getCellFormatValue(row.getCell(j));
                        } catch (Exception e) {
                            Map<String, Object> result = new HashMap<String, Object>();
                            result.put("row", i + 1); // 行
                            result.put("col", j + 1); // 列
                            result.put("value", row.getCell(j));
                            throw new BisException().setData(result).setInfo("内容格式不正确");
                        }

                        Row titles = ExcelUtils.readTitle(sheet);
                        Cell cell = titles.getCell(j);
                        map.put(cell.getStringCellValue(), cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }

    private static Object getCellFormatValue(Cell cell) throws Exception {
        Object cellValue = null;
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        // 数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    /**
     * 读取指定Sheet页的表头
     *
     * @param filepath filepath 文件全路径
     * @param sheetNo  sheet序号,从0开始,必填
     */
    public static Row readTitle(String filepath, boolean isUrl, int sheetNo)
            throws IOException, EncryptedDocumentException, InvalidFormatException {
        Row returnRow = null;
        Workbook workbook = getWorkbook(filepath, isUrl);
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(sheetNo);
            returnRow = readTitle(sheet);
        }
        return returnRow;
    }

    /**
     * 读取指定Sheet页的表头
     */
    public static Row readTitle(Sheet sheet) throws IOException {
        Row returnRow = null;
        int totalRow = sheet.getLastRowNum(); // 得到excel的总记录条数
        for (int i = 0; i < totalRow; i++) { // 遍历行
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            returnRow = sheet.getRow(0);
            break;
        }
        return returnRow;
    }

    public static List<Map<String, PictureData>> getSheetPictures(String fileName, boolean isUrl) throws IOException, InvalidFormatException {
        Workbook workbook = getWorkbook(fileName, isUrl);
        // 获取文件后缀名
        String suffix = getSuffix(fileName);
        ;
        // 创建sheet
        Sheet sheet = null;
        //获取excel sheet总数
        int sheetNumbers = workbook.getNumberOfSheets();
        // sheet list
        List<Map<String, PictureData>> sheetList = new ArrayList<Map<String, PictureData>>();
        // 循环sheet
        for (int i = 0; i < sheetNumbers; i++) {
            sheet = workbook.getSheetAt(i);
            Map<String, PictureData> sheetIndexPicMap;
            // 判断用XLS还是XLSX的方法获取图片
            if (suffix.equals(OFFICE_EXCEL_XLS)) {
                sheetIndexPicMap = getSheetPicturesFromHSSF(i, (HSSFSheet) sheet, (HSSFWorkbook) workbook);
            } else {
                sheetIndexPicMap = getSheetPicturesFromXSSF(i, (XSSFSheet) sheet, (XSSFWorkbook) workbook);
            }
            // 将当前sheet图片map存入list
            sheetList.add(sheetIndexPicMap);
        }
        return sheetList;
    }

    /**
     * 获取XLS图片
     *
     * @param sheetNum 当前sheet编号
     * @param sheet    当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流 PictureData
     * @throws IOException
     */
    private static Map<String, PictureData> getSheetPicturesFromHSSF(int sheetNum,
                                                                     HSSFSheet sheet, HSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        List<HSSFPictureData> pictures = workbook.getAllPictures();
        if (pictures.size() != 0) {
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;
                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = pictures.get(pictureIndex);
                   /* String picIndex = String.valueOf(sheetNum) + "_"
                            + String.valueOf(anchor.getRow1()) + "_"
                            + String.valueOf(anchor.getCol1());*/
                    String picIndex = String.valueOf(anchor.getRow1());
                    sheetIndexPicMap.put(picIndex, picData);
                }
            }
            return sheetIndexPicMap;
        } else {
            return null;
        }
    }


    /**
     * 获取XLSX图片
     *
     * @param sheetNum 当前sheet编号
     * @param sheet    当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流 PictureData
     */
    private static Map<String, PictureData> getSheetPicturesFromXSSF(int sheetNum,
                                                                     XSSFSheet sheet, XSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    /*String picIndex = String.valueOf(sheetNum) + "_"
                            + ctMarker.getRow() + "_" + ctMarker.getCol();*/
                    String picIndex = String.valueOf(ctMarker.getRow());
                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
                }
            }
        }
        return sheetIndexPicMap;
    }

    /**
     * 创建Excel文件
     *
     * @param filepath  filepath 文件全路径
     * @param sheetName 新Sheet页的名字
     * @param titles    表头
     * @param values    每行的单元格
     */
    public static boolean writeExcel(String filepath, String sheetName, List<String> titles,
                                     List<Map<String, Object>> values) throws IOException {
        boolean success = false;
        OutputStream outputStream = null;
        if (StringUtils.isBlank(filepath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        } else {
            String suffix = getSuffix(filepath);
            if (StringUtils.isBlank(suffix)) {
                throw new IllegalArgumentException("文件后缀不能为空");
            }
            Workbook workbook;
            if (OFFICE_EXCEL_XLS.equals(suffix.toLowerCase())) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = new XSSFWorkbook();
            }
            // 生成一个表格
            Sheet sheet;
            if (StringUtils.isBlank(sheetName)) {
                // name 为空则使用默认值
                sheet = workbook.createSheet();
            } else {
                sheet = workbook.createSheet(sheetName);
            }
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成样式
            Map<String, CellStyle> styles = createStyles(workbook);
            // 创建标题行
            Row row = sheet.createRow(0);
            // 存储标题在Excel文件中的序号
            Map<String, Integer> titleOrder = new HashMap<String, Integer>();
            for (int i = 0; i < titles.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(styles.get("header"));
                String title = titles.get(i);
                cell.setCellValue(title);
                titleOrder.put(title, i);
            }
            // 写入正文
            Iterator<Map<String, Object>> iterator = values.iterator();
            // 行号
            int index = 1;
            while (iterator.hasNext()) {
                row = sheet.createRow(index);
                Map<String, Object> value = iterator.next();
                for (Map.Entry<String, Object> map : value.entrySet()) {
                    // 获取列名
                    String title = map.getKey();
                    // 根据列名获取序号
                    int i = titleOrder.get(title);
                    // 在指定序号处创建cell
                    Cell cell = row.createCell(i);
                    // 设置cell的样式
                    if (index % 2 == 1) {
                        cell.setCellStyle(styles.get("cellA"));
                    } else {
                        cell.setCellStyle(styles.get("cellB"));
                    }
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
                }
                index++;
            }

            try {
                outputStream = new FileOutputStream(filepath);
                workbook.write(outputStream);
                return true;
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            }
        }
    }

    /**
     * 设置格式
     */
    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        // 标题样式
        XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平对齐
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
        titleStyle.setLocked(true); // 样式锁定
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleStyle.setFont(titleFont);
        styles.put("title", titleStyle);

        // 文件头样式
        XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // 前景色
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 颜色填充方式
        headerStyle.setWrapText(true);
        headerStyle.setBorderRight(BorderStyle.THIN); // 设置边界
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        titleFont.setFontName("微软雅黑");
        headerStyle.setFont(headerFont);
        styles.put("header", headerStyle);

        Font cellStyleFont = wb.createFont();
        cellStyleFont.setFontHeightInPoints((short) 12);
        cellStyleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
        cellStyleFont.setFontName("微软雅黑");

        // 正文样式A
        XSSFCellStyle cellStyleA = (XSSFCellStyle) wb.createCellStyle();
        cellStyleA.setAlignment(HorizontalAlignment.CENTER); // 居中设置
        cellStyleA.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleA.setWrapText(true);
        cellStyleA.setBorderRight(BorderStyle.THIN);
        cellStyleA.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderLeft(BorderStyle.THIN);
        cellStyleA.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderTop(BorderStyle.THIN);
        cellStyleA.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderBottom(BorderStyle.THIN);
        cellStyleA.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setFont(cellStyleFont);
        styles.put("cellA", cellStyleA);

        // 正文样式B:添加前景色为浅黄色
        XSSFCellStyle cellStyleB = (XSSFCellStyle) wb.createCellStyle();
        cellStyleB.setAlignment(HorizontalAlignment.CENTER);
        cellStyleB.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleB.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyleB.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleB.setWrapText(true);
        cellStyleB.setBorderRight(BorderStyle.THIN);
        cellStyleB.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderLeft(BorderStyle.THIN);
        cellStyleB.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderTop(BorderStyle.THIN);
        cellStyleB.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderBottom(BorderStyle.THIN);
        cellStyleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setFont(cellStyleFont);
        styles.put("cellB", cellStyleB);

        return styles;
    }

    /**
     * 将源文件的内容复制到新Excel文件(可供理解Excel使用,使用价值不大)
     *
     * @param srcFilepath 源文件全路径
     * @param desFilepath 目标文件全路径
     */
    public static void writeExcel(String srcFilepath, String desFilepath)
            throws IOException, EncryptedDocumentException, InvalidFormatException {
        FileOutputStream outputStream = null;
        String suffix = getSuffix(desFilepath);
        if (StringUtils.isBlank(suffix)) {
            throw new IllegalArgumentException("文件后缀不能为空");
        }
        Workbook workbook_des;
        if (OFFICE_EXCEL_XLS.equals(suffix.toLowerCase())) {
            workbook_des = new HSSFWorkbook();
        } else {
            workbook_des = new XSSFWorkbook();
        }

        Workbook workbook = getWorkbook(srcFilepath, false);
        if (workbook != null) {
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int k = 0; k < numberOfSheets; k++) {
                Sheet sheet = workbook.getSheetAt(k);
                Sheet sheet_des = workbook_des.createSheet(sheet.getSheetName());
                if (sheet != null) {
                    int rowNos = sheet.getLastRowNum();
                    for (int i = 0; i <= rowNos; i++) {
                        Row row = sheet.getRow(i);
                        Row row_des = sheet_des.createRow(i);
                        if (row != null) {
                            int col = row.getLastCellNum();
                            for (int j = 0; j < col; j++) {
                                Cell cell = row.getCell(j);
                                Cell cell_des = row_des.createCell(j);
                                if (cell != null) {
                                    cell.setCellType(CellType.STRING);
                                    cell_des.setCellType(CellType.STRING);
                                    cell_des.setCellValue(cell.getStringCellValue());
                                }
                            }
                        }
                    }
                }

            }
        }
        try {
            outputStream = new FileOutputStream(desFilepath);
            workbook_des.write(outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (workbook != null) {
                workbook_des.close();
            }
        }
    }

    /**
     * 保存图片
     *
     * @param sheetList
     * @throws IOException
     */
    public static void uploadImage(Map<String, PictureData> sheetList) throws IOException {
        Object key[] = sheetList.keySet().toArray();
        for (int i = 0; i < sheetList.size(); i++) {
            // 获取图片流
            PictureData pic = sheetList.get(key[i]);
            // 获取图片索引
            String picName = key[i].toString();
            // 获取图片格式
            String ext = pic.suggestFileExtension();
            byte[] data = pic.getData();
            // FTP上传
            boolean flag = FtpUtils.uploadFile(
                    GlobalSetting.get(GlobalSettingNames.FTP_ADDRESS),
                    Integer.parseInt(GlobalSetting.get(GlobalSettingNames.FTP_PORT)),
                    GlobalSetting.get(GlobalSettingNames.FTP_USERNAME),
                    GlobalSetting.get(GlobalSettingNames.FTP_PASSWORD),
                    GlobalSetting.get(GlobalSettingNames.FTP_BASE_PATH),
                    "/2019/10/08", picName + "." + ext, new ByteArrayInputStream(data));
            System.out.println(flag);
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://47.101.150.9:81/1570679909855941.xlsx";
        String filePath = "C:\\Users\\Administrator\\Downloads\\1570679909855941.xlsx";

        /*List<Map<String, String>> list = ExcelUtils.readExcel(url, columns);
        // 遍历解析出来的list
        for (Map<String, String> map : list) {
            for (Entry<String, String> entry : map.entrySet()) {
                System.out.print(entry.getKey() + " : " + entry.getValue() + ", ");
            }
            System.out.println();
        }*/

        List<Map<String, PictureData>> list = ExcelUtils.getSheetPictures(filePath, false);
        for (Map<String, PictureData> map : list) {
            //ExcelUtils.printImg(map);
            System.out.println(map.toString());
        }

        /*List<List<Map<String, String>>> sheets = ExcelUtils.readExcel(filePath, false, null);
        System.out.print(sheets.size());
        for (int i = 0; i < sheets.size(); i++) {
            // 遍历解析出来的list
            for (Map<String, String> map : sheets.get(i)) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    System.out.print(entry.getKey() + " : " + entry.getValue() + ", ");
                }
                System.out.println();
            }
        }*/

        /*Row row = ExcelUtils.readTitle(filePath, false, 1);
        if (row != null) {
            int cellTotal = row.getPhysicalNumberOfCells(); // 表头总共的列数
            for (int j = 0; j < cellTotal; j++) {
               *//* cellData = (String) getCellFormatValue(row.getCell(j));
                map.put(columns[j], cellData);*//*
                System.out.print(row.getCell(j) + "  ");
            }
        }*/


    }

}
