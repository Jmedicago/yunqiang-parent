package com.vgit.yunqiang.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    /**
     * 根据地址获得数据的输入流
     *
     * @param strUrl 网络连接地址
     * @return url的输入流
     */
    public static InputStream getInputStreamByUrl(String strUrl) {
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

    public static Workbook readExcel(String fileName, InputStream is) {
        Workbook wb = null;
        try {
            if (".xls".equals(fileName)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(fileName)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
            return wb;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    // 读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
            return wb;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
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

    public static List<Map<String, String>> getColumns(Workbook wb, String... columns) {
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        // 获取第一个sheet
        sheet = wb.getSheetAt(0);
        // 获取最大行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        // 获取第一行
        row = sheet.getRow(0);
        // 获取最大列数
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = 1; i < rowNum; i++) {
            Map<String, String> map = new LinkedHashMap<String, String>();
            row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < colNum; j++) {
                    cellData = (String) getCellFormatValue(row.getCell(j));
                    map.put(columns[j], cellData);
                }
            } else {
                break;
            }
            list.add(map);
        }
        return list;
    }
	
	/*public static void main(String[] args) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		List<Map<String, String>> list = null;
		String cellData = null;
		String filePath = "http://47.101.150.9:81/1570679909855941.xlsx";
		String columns[] = { "库存类别", "商品类别", "图片", "品种名称", "商品型号", "颜色", "成本价", "出厂价", "单件体积", "备注", "包装形态", "货柜日期"};
		wb = readExcel(filePath);
		if (wb != null) {
			// 用来存放表中数据
			list = new ArrayList<Map<String, String>>();
			// 获取第一个sheet
			sheet = wb.getSheetAt(0);
			// 获取最大行数
			int rownum = sheet.getPhysicalNumberOfRows();
			// 获取第一行
			row = sheet.getRow(0);
			// 获取最大列数
			int colnum = row.getPhysicalNumberOfCells();
			for (int i = 1; i < rownum; i++) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				row = sheet.getRow(i);
				if (row != null) {
					for (int j = 0; j < colnum; j++) {
						cellData = (String) getCellFormatValue(row.getCell(j));
						map.put(columns[j], cellData);
					}
				} else {
					break;
				}
				list.add(map);
			}
		}
		// 遍历解析出来的list
		for (Map<String, String> map : list) {
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.print(entry.getKey() + " : " + entry.getValue() + ", ");
			}
			System.out.println();
		}

	}*/

    public static void main(String[] args) throws Exception {
        String uri = "http://47.101.150.9:81/1570679909855941.xlsx";
        Workbook workbook = ExcelUtils.readExcel(".xlsx", ExcelUtils.getInputStreamByUrl(uri));
        String columns[] = {"库存类别", "商品类别", "图片", "品种名称", "商品型号", "颜色", "成本价", "出厂价", "单件体积", "备注", "包装形态", "货柜日期"};
        List<Map<String, String>> list = ExcelUtils.getColumns(workbook, columns);
        // 遍历解析出来的list
        for (Map<String, String> map : list) {
            for (Entry<String, String> entry : map.entrySet()) {
                System.out.print(entry.getKey() + " : " + entry.getValue() + ", ");
            }
            System.out.println();
        }
    }

}
