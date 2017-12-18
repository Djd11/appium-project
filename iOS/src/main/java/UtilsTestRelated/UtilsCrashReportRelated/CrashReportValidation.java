package UtilsTestRelated.UtilsCrashReportRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsDeviceOrSimulatorRelated.ExtractDeviceInfo;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import org.apache.commons.io.FileUtils;
import java.io.*;

public class CrashReportValidation {

    public static String crashReportFilePath() throws IOException, InterruptedException {
        String userDirectory = System.getProperty("user.home");
        String filePath = userDirectory + ConfigFileManager.getInstance().getCrashReportConfig();
        return filePath;
    }

    public static void cleanCrashDiagnosticReportsFolder() throws IOException, InterruptedException {
        try {
            File directory = new File(crashReportFilePath());
            FileUtils.cleanDirectory(directory);
        }
        catch (Exception e) {
            LoggerUtils.error(e);
        }
    }

    public static boolean validateCrashReports() throws IOException, InterruptedException {
        boolean result = true;
        try {
            File directory = new File(crashReportFilePath());
            int count = directory.listFiles().length;
            if (count > 0) {
                result = false;
            }
        }
        catch (Exception e) {
            LoggerUtils.error(e);
        }

        return result;
    }


    /*
    * Helper methods to check crash log in system.log file of simulator
    */
    public static String crashLogFilePath() throws IOException, InterruptedException {
        String userDirectory = System.getProperty("user.home");
        String deviceUDID = new ExtractDeviceInfo().getDeviceUDID(ConfigFileManager.getInstance().getDeviceInfo());
        String filePath = userDirectory + ConfigFileManager.getInstance().getCrashLogConfig() + "/" + deviceUDID + "/system.log";
        return filePath;
    }

    public static void startMonitoringCrashLog() throws Exception {
        try {
            FileOutputStream writer = new FileOutputStream(crashReportFilePath());
            writer.write(("").getBytes());
            writer.close();
        }
        catch (Exception e) {
            LoggerUtils.error(e);
        }
    }

    public static boolean validateCrashLogReports() {
        boolean result = true;
        BufferedReader br = null;
        String strLine = "";
        try {
            br = new BufferedReader( new FileReader("fileName"));
            while( (strLine = br.readLine()) != null){
                LoggerUtils.debug(strLine);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
        return result;
    }

}
