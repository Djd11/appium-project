package UtilsSetupEnvRelated.UtilsResponseRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ResponseMetaDataDownloader {

    private static ResponseMetaDataDownloader responseDownloader = null;
    private static ArrayList<String> xlsxDataArrayList = new ArrayList<String>();

    public static ResponseMetaDataDownloader getInstance() {
        if (responseDownloader == null)
            responseDownloader = new ResponseMetaDataDownloader();
        return responseDownloader;
    }

    public void downloadResponseMetaData() throws IOException {
        String directory = System.getProperty("user.dir") + "/src/main/resources/TestData";
        String fileName = "Unified_Response.txt";

        try {
            File file = FileUtils.getFile(directory, fileName);
            if (file.exists()) {
                //Compare version and then download.
            }
            else {
                downloadAndWriteToXLSXFile(ConfigFileManager.getInstance().getDataDownloaderConfig(), directory + "/" + fileName);
            }
        }
        catch (IOException e) {
            LoggerUtils.error(e);
        }
    }

    public void downloadAndWriteToXLSXFile(String fileUrl, String filePath) throws IOException {
        try {
            File file = new File(filePath);
            FileUtils.copyURLToFile(new URL(fileUrl), file);
            readFromTextFile(filePath);
            writeToXLSX(System.getProperty("user.dir") + ConfigFileManager.getInstance().getTestDataConfig());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromTextFile(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            String paragraphsString = null;

            for (String para : lines) {
                paragraphsString = para;
            }

            String[] split = paragraphsString.split(",,], ");
            for (String arrayString:split) {
                arrayString = arrayString.replace("[[", "[");
                arrayString = arrayString.replace(",,", "");
                xlsxDataArrayList.add(arrayString);
            }
            LoggerUtils.debug("xlsx data" + xlsxDataArrayList);
        }
        catch (Exception e) {
            LoggerUtils.error(e);
        }

    }

    public void writeToXLSX(String filePath) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();
            final List<String> dataArrayList = new ArrayList<String>();
            Map<String, String[]> data = new TreeMap<String, String[]>();

            for (String arrays : xlsxDataArrayList) {
                dataArrayList.add(Arrays.toString(new String[]{arrays}));
            }
            //This data needs to be written (Object[])
            for (int count=0;count<dataArrayList.size();count++) {
                String rowValues = dataArrayList.get(count);
                String [] splitObject = rowValues.split(",");
                data.put(String.valueOf(count), splitObject);
            }
            //Iterate over data and write to sheet

            for (int rowCount = 0; rowCount< data.size(); rowCount++){
                //create a row of excelsheet
                Row row = sheet.createRow(rowCount);
                //get object array of prerticuler key
                String[] objArr = data.get(String.valueOf(rowCount));
                for (int cellCount = 0; cellCount< objArr.length; cellCount++){
                    String obj = objArr[cellCount];
                    Cell cell = row.createCell(cellCount);
                    cell.setCellValue(obj);
                }
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filePath));
            workbook.write(out);
            out.close();
            LoggerUtils.debug("xlsx written successfully on disk.");
        }
        catch (Exception e) {
            LoggerUtils.error(e);
        }
    }

}
