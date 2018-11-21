package web.common;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 所有对excel表格的操作都在该类里定义
 * Created by Merry as on 2016/11/2.
 */
public class ExcelOperation {
    private static final Logger logger = LoggerFactory.getLogger(ExcelOperation.class);

    public static Object[][] getData(String secondPath, String fileName) {
        Object[][] data = new Object[100][100];
        String caseName = null;
        int m = 0, n = 0;
        int i = 0, j = 0;
        boolean isE2007 = false;    //判断是否是excel2007格式
        String curPath = System.getProperty("user.dir");
//        读取文件的存放路径
        fileName = curPath + "/testData/" + secondPath + fileName;
        if (fileName.endsWith("xlsx"))
            isE2007 = true;
        try {
            InputStream input = new FileInputStream(fileName);  //建立输入流  
            Workbook wb = null;
            //根据文件格式(2003或者2007)来初始化  
            if (isE2007) {
                wb = new XSSFWorkbook(input);
            } else {
                wb = new HSSFWorkbook(input);
            }
            Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器
            int rowIndex = 0;
            int executeRow = 0;
            while (rows.hasNext()) {
                Row row = rows.next();  //获得行数据
                if (row.getRowNum() == 0) {
                    continue;
                }
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器
                int columnIndex = 0;
                if (executeRow != 0)
                    rowIndex = executeRow;
                else
                    rowIndex = 0;
                while (cells.hasNext()) {
                    Cell cell = cells.next();
//                    getColumnIndex获取指定列名称，如果不存在返回-1，列数从0开始
                    if (cell.getColumnIndex() == 0) {
                        caseName = cell.getStringCellValue();
                        continue;
                    }
//                    Excel中第一sheet的第二列值=N，跳过，不取
                    if (cell.getColumnIndex() == 1 && cell.getStringCellValue().toUpperCase().equals("N")) break;
//                    Excel中第一sheet的第二列值为空，跳过，不取
                    if (cell.getColumnIndex() == 1 && cell.getStringCellValue().toUpperCase().equals("")) break;
//                    Excel中第一sheet的第二列值=Y，读取
                    if (cell.getColumnIndex() == 1 && cell.getStringCellValue().toUpperCase().equals("Y")) {
                        i = rowIndex;
                        j = columnIndex;
                        data[i][j] = caseName;
                        executeRow++;
                        columnIndex++;
                        continue;
                    }
                    i = rowIndex;
                    j = columnIndex;
                    data[i][j] = null;

                    if (cell != null) {
                        //判断cell类型
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC: {
                                data[i][j] = String.valueOf(cell.getNumericCellValue());
                                break;
                            }
                            case Cell.CELL_TYPE_FORMULA: {
                                //判断cell是否为日期格式
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    //转换为日期格式YYYY-mm-dd
                                    data[i][j] = cell.getDateCellValue();
                                } else {
                                    //数字
                                    data[i][j] = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            }
                            case Cell.CELL_TYPE_STRING: {
                                data[i][j] = cell.getRichStringCellValue().getString();
                                break;
                            }
                            default:
                                data[i][j] = "";
                        }
                    } else {
                        data[i][j] = "";
                    }
                    if (data[i][j].toString().contains("<$>")) {
                        String sheetName = data[i][j].toString().replace("<$>", "").trim();
                        Sheet subSheet = wb.getSheet(sheetName);
                        data[i][j] = getSubSheetValue(subSheet, caseName);
                    }
                    columnIndex++;
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        m = i + 1;
        n = j + 1;

        Object[][] returnData = new Object[m][n];
        for (int l = 0; l < m; l++) {
            for (int p = 0; p < n; p++) {
                returnData[l][p] = data[l][p];
            }
        }
        return (returnData);
    }

    private static String getSubSheetValue(Sheet subSheet, String caseName) {
        Iterator<Row> rows1 = subSheet.rowIterator();
        Boolean findTestCase = false;
        String returnData = "initial";
        Object cellValue = null;
        while (rows1.hasNext()) {
            if (findTestCase == true) break;
            Row row = rows1.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                if (cell.getColumnIndex() == 0 && cell.getStringCellValue().equals(caseName)) {
                    findTestCase = true;
                    continue;
                }
                if (cell.getColumnIndex() == 0 && !cell.getStringCellValue().equals(caseName)) {
                    break;
                }
                switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        cellValue = String.valueOf(cell.getStringCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: {
                        //判断cell是否为日期格式
                        if (DateUtil.isCellDateFormatted(cell)) {
                            //转换为日期格式YYYY-mm-dd
                            cellValue = cell.getDateCellValue();
                        } else {
                            //数字
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    }
                    default:
                        System.out.println("unsupported cell type");
                        break;
                }

                if (cellValue == null) {
                    cellValue = "";
                }
                returnData = returnData + "," + cellValue;
            }


        }
        returnData = returnData.replace("initial,", "");

        return returnData;

    }
}
