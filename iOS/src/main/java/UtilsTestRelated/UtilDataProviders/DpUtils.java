package UtilsTestRelated.UtilDataProviders;

import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * template-automation Created by dhruba.jyoti on 11/14/17.
 */
public class DpUtils {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static Object[][] getTableArray(String adFormat, String filePath, String sheetName) throws Exception {

        String[][] tabArray = null;

        try {

            FileInputStream ExcelFile = new FileInputStream(filePath);

            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            Iterator<Row> iterator = ExcelWSheet.iterator();

            ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();

            int ci = 0, cj = 0;
            int totalRows = 0, totalCols = 7;

            int sdkVersionColumnIndex = 0, adTypeColumnIndex = 0, adFormatColumnIndex = 0;
            int placementIdColumnIndex = 0, adServerUrlColumnIndex = 0;
            int videoAssetsColumnIndex = 0, endCardAssetsColumnIndex = 0;

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                int rowIndex = currentRow.getRowNum();

                if (rowIndex == 0) {
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        String cellValue = currentCell.getStringCellValue();
                        int columnIndex = currentCell.getColumnIndex();

                        if (cellValue.trim().equals("SDK Version")) {
                            sdkVersionColumnIndex = columnIndex;
                        }
                        else if (cellValue.trim().equals("AdType")) {
                            adTypeColumnIndex = columnIndex;
                        }
                        else if (cellValue.trim().equals("AdFormat")) {
                            adFormatColumnIndex = columnIndex;
                        }
                        else if (cellValue.trim().equals("SitePlacement ID")) {
                            placementIdColumnIndex = columnIndex;
                        }
                        else if (cellValue.trim().equals("AdServer URL")) {
                            adServerUrlColumnIndex = columnIndex;
                        }
                        else if (cellValue.trim().equals("VideoAssetElement]")) {
                            videoAssetsColumnIndex = columnIndex;
                        }
                        else if (cellValue.trim().equals("EndCardAssetElement")) {
                            endCardAssetsColumnIndex = columnIndex;
                        }
                    }
                }
                else {
                    //(String SdkVersion,String AdType,String AdFormat,String Placement,String ResponseUrl ,String VideoElementValidations,String EndCardElementValidations)
                    String adFormatString = getCellData(rowIndex, adFormatColumnIndex).trim();

                    if (adFormatString.contains(adFormat))
                    {
                        String sdkVersion = getCellData(rowIndex, sdkVersionColumnIndex).trim();
                        String adType = getCellData(rowIndex, adTypeColumnIndex).trim();
                        String placementId = getCellData(rowIndex, placementIdColumnIndex).trim();
                        String adServerUrl = getCellData(rowIndex, adServerUrlColumnIndex).trim();
                        String videoAssets = getCellData(rowIndex, videoAssetsColumnIndex).replace("-", "").replace("]","").trim();
                        String endCardAssets = getCellData(rowIndex, endCardAssetsColumnIndex).replace("-", "").replace("]","").trim();

                        ArrayList<String> rowData = new ArrayList<String>();
                        rowData.add(sdkVersion);
                        rowData.add(adType);
                        rowData.add(adFormatString);
                        rowData.add(placementId);
                        rowData.add(adServerUrl);
                        rowData.add(videoAssets);
                        rowData.add(endCardAssets);

                        dataList.add(rowData);
                        totalRows += 1;
                    }

                }
            }

            tabArray = new String[totalRows][totalCols];

            for (ArrayList<String> rowData : dataList) {
                for (String data: rowData) {
                    tabArray[ci][cj] = data;
                    cj += 1;
                }
                ci += 1;
                cj = 0;
            }
        }
        catch(Exception e){
            LoggerUtils.error("Could not read the Excel sheet" + e);
            LoggerUtils.error(e);
        }


        return(tabArray);
    }


    public static String getCellData(int RowNum, int ColNum) throws Exception {
        final DataFormatter df = new DataFormatter();

        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            return df.formatCellValue(Cell);
        }
        catch (Exception e){
            LoggerUtils.error(e);
            throw (e);
        }
    }

}
