package UtilsSetupEnvRelated.UtilsLogger;

import UtilsAutomationConfigRelated.ConfigFileManager;
import org.apache.log4j.Logger;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoggerUtils {

    private static Logger LOGGER = Logger.getLogger("TemplateAutomation");

    public static void debug(Object object) {
        LOGGER.debug(object.toString());
    }

    public static void error(Object object) {
        LOGGER.error(object.toString());
        if (object instanceof Exception) {
            Exception e = (Exception)object;
            e.printStackTrace();
        }
    }

    public static void cleanLogFile() throws Exception {
        String filePath = System.getProperty("user.dir") + "/" + ConfigFileManager.getInstance().getAppLogConfig();
        LoggerUtils.debug("Log file path: " + filePath);

        try {
            if(Files.exists(Paths.get(filePath))) {
                FileOutputStream writer = new FileOutputStream(filePath);
                writer.write(("").getBytes());
                writer.close();
                LoggerUtils.debug("Cleaned log file");
            }
        }
        catch (Exception e) {
            LoggerUtils.error("Failed to clean log file");
            LoggerUtils.error(e);
        }
    }
}
