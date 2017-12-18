package UtilsAutomationConfigRelated;

/**
 * template-automation Created by dhruba.jyoti on 10/24/17.
 */
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;

import java.io.IOException;
import java.util.Properties;


public class ConfigFileManager {

    static Properties commonConfigProp;
    private static ConfigFileManager configFileManager = null;
    final String commonConfigPath = "Config.properties";

    private ConfigFileManager() throws IOException {
        commonConfigProp = new Properties();
        commonConfigProp.load(ConfigFileManager.class.getClassLoader().getResourceAsStream(commonConfigPath));
    }

    public static ConfigFileManager getInstance()
    {
        try {
            if (configFileManager == null)
                configFileManager = new ConfigFileManager();
            return configFileManager;
        }
        catch (IOException e) {
            LoggerUtils.error(e);
            return null;
        }
    }


    public String getAppiumLocation() {
        return commonConfigProp.getProperty("Appium");
    }

    public String getNodeServerPath() {
        return commonConfigProp.getProperty("Node");
    }

    public String getChromedriverLocation() {
        return commonConfigProp.getProperty("chromedriverExecutable");
    }

    public String getDeviceInfo() {
        return commonConfigProp.getProperty("Device");
    }


    public String getResultsFileConfig() {
        return commonConfigProp.getProperty("ScreenShots_folder");
    }

    public String getTestDataConfig() { return commonConfigProp.getProperty("NativeTestMetadata"); }

    public String getDataDownloaderConfig() { return commonConfigProp.getProperty("ResponseDownloaderUrl"); }


    public String getProxyServerPortConfig() {
        return commonConfigProp.getProperty("ProxyServerPort");
    }

    public String getProxyServerLogConfig() {
        return commonConfigProp.getProperty("ProxyServerLogPath");
    }

    public String getBeaconBoxConfig() {
        return commonConfigProp.getProperty("BeaconBox");
    }

    public String getBeaconPublisherBoxConfig() {
        return commonConfigProp.getProperty("PublisherFillBox");
    }

    public String getMoatBoxConfig() {
        return commonConfigProp.getProperty("MoatBox");
    }

    public boolean getValidateMoatBoxConfig() {
        return true;
    }

    public String getIASBoxConfig() {
        return commonConfigProp.getProperty("IASBox");
    }

    public boolean getValidateIASBoxConfig() {
        return true;
    }


    public String getCrashLogConfig() {
        return commonConfigProp.getProperty("CrashLogPath");
    }

    public String getCrashReportConfig() { return commonConfigProp.getProperty("CrashReportPath"); }

    public String getAppLogConfig() {
        return commonConfigProp.getProperty("AppLogPath");
    }

}


