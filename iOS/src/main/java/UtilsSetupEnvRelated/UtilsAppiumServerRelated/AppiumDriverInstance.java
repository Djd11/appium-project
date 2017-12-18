package UtilsSetupEnvRelated.UtilsAppiumServerRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsDeviceOrSimulatorRelated.ExtractDeviceInfo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

/**
 * template-automation Created by dhruba.jyoti on 11/15/17.
 */
public class AppiumDriverInstance {


    private boolean isDriverConnected = false;
    private static AppiumDriverInstance mInstance;

    private AppiumDriver<MobileElement> driver;
    private AppiumDriverInstance(String sdkversion) throws IOException, InterruptedException {

        AppiumDriverLocalService service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File(ConfigFileManager.getInstance().getNodeServerPath()))
                        .withAppiumJS(
                                new File(
                                        ConfigFileManager.getInstance().getAppiumLocation()))
                        .withIPAddress("127.0.0.1").usingPort(4723));

        service.start();

        if(service.isRunning() && !isDriverConnected ){
            driver = new IOSDriver<MobileElement>(service.getUrl(), iosCapabilities(sdkversion));
            isDriverConnected = true;
        }

    }

    public static AppiumDriverInstance getinstance(String sdkversion) throws IOException, InterruptedException {
        if(null == mInstance)
            mInstance = new AppiumDriverInstance(sdkversion);
        return mInstance;
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    private static DesiredCapabilities iosCapabilities(String sdkversion) throws IOException, InterruptedException {
        String Appname = sdkversion;
        final String dir = System.getProperty("user.dir");
        File appDir = new File(dir + "/Resource/iOS/");
        File app = new File(appDir, Appname + ".app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Test Device");
        capabilities.setCapability("udid", new ExtractDeviceInfo().getDeviceUDID(ConfigFileManager.getInstance().getDeviceInfo()));
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("chromedriverExecutable", ConfigFileManager.getInstance().getChromedriverLocation());
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("clearSystemFiles",true);
        capabilities.setCapability("newCommandTimeout", 200);

        return capabilities;
    }

}
