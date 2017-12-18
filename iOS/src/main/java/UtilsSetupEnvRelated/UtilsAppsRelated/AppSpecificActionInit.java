package UtilsSetupEnvRelated.UtilsAppsRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsAppiumServerRelated.AppiumDriverInstance;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * template-automation Created by dhruba.jyoti on 10/27/17.
 */
public  class AppSpecificActionInit {

    AppiumDriver<MobileElement> driver;
    WebElement ele;
    private static Log log = LogFactory.getLog(AppSpecificActionInit.class);

    public AppSpecificActionInit(String sdkversion) throws IOException, InterruptedException {
        driver = AppiumDriverInstance.getinstance(sdkversion).getDriver();
    }

    public void clickAllowFirstTimeLaunch() {
        String xpath_allowButton = "//*[@name=\"Allow\"]";
        try {
            ele = driver.findElement(By.xpath(xpath_allowButton));

            if (ele != null)
                ele.click();
            return;
        }
        catch (org.openqa.selenium.NoSuchElementException e){}
    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
    public  void waitForClickable(final By by, final int waitTime) {
        Wait wait = new WebDriverWait(driver, waitTime);
        for (int attempt = 0; attempt < waitTime; attempt++) {
            try {
                driver.findElement(by);
                LoggerUtils.debug("Found Element "+by.toString()+" looking for before operating on it");
                break;
            } catch (Exception e) {
                driver.manage().timeouts().implicitlyWait(1, SECONDS);
                LoggerUtils.error(e);
            }
        }
    }


    public void setAppForInterstitial(String placementId,String adserveUrl) throws InterruptedException, IOException {
        //Sub class should implement its functionality
    }

    public void restartAppForInterstitial() throws InterruptedException, IOException {
        //Sub class should implement its functionality
    }

    public void setAppForInfeed(String placementId,String adserveUrl) throws InterruptedException, IOException {
        //Sub class should implement its functionality
    }


    public void takeScreenShots(String testCase, String placementId, String assets)
    {
        String screenShotPath = ConfigFileManager.getInstance().getResultsFileConfig()  + "/";
        String fileName = testCase + "_" + placementId + "_" + assets;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dir = System.getProperty("user.dir");
        File results = new File(dir +"/"+ screenShotPath + fileName + ".jpg");
        try {
            LoggerUtils.debug("Screen "+results);
            FileUtils.copyFile(scrFile,results);
            FileUtils.forceDelete(scrFile);
        }
        catch (IOException e) {
            LoggerUtils.error("ScreenShot failed to take for "+results);
            LoggerUtils.error(e);
        }
    }

    public static void cleanScreenshotsFolder() throws Exception {
        String folderPath = System.getProperty("user.dir") + ConfigFileManager.getInstance().getResultsFileConfig();
        LoggerUtils.debug("Screenshot folder path:" + folderPath);
        try {
            File directory = new File(folderPath);
            if (directory.exists()) {
                FileUtils.cleanDirectory(directory);
                LoggerUtils.debug("Cleaned Screenshot folder");
            }
            else {
                FileUtils.forceMkdir(directory);
                LoggerUtils.debug("Created Screenshot folder");
            }

        }
        catch (Exception e) {
            LoggerUtils.error("Failed to clean Screenshot folder");
            LoggerUtils.error(e);
        }
    }
}
