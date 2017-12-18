package TemplateResponseTypeTestRelated.NativeTestRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsAppsRelated.AppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsBaseTestcaseRelated.BaseTestCaseNativeIos;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import UtilsSetupEnvRelated.UtilsResponseRelated.ResponseMetaDataDownloader;
import UtilsTestRelated.UtilDataProviders.DpUtils;
import UtilsTestRelated.UtilsBeaconRelated.BeaconValidations;
import UtilsTestRelated.UtilsCrashReportRelated.CrashReportValidation;
import UtilsTestRelated.UtilsNativeRelated.UtilsVideoRelated.VideoInsterstitialValidationUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * template-automation Created by dhruba.jyoti on 10/27/17.
 */
public class NativeVideoReponseTest {


    @BeforeSuite
    public void cleanLogs() throws Exception {
        LoggerUtils.cleanLogFile();
    }

    @BeforeClass
    public void cleanTestRelatedFolders() throws Exception {
        ResponseMetaDataDownloader.getInstance().downloadResponseMetaData();
        CrashReportValidation.cleanCrashDiagnosticReportsFolder();
        AppSpecificActionInit.cleanScreenshotsFolder();
    }

    @AfterClass
    public void crashReportValidations() throws IOException, InterruptedException {
        Assert.assertTrue(CrashReportValidation.validateCrashReports());
    }

    @BeforeTest
    public void clearBeaconLogs() throws FileNotFoundException {
        BeaconValidations.clearBeaconLogs();
    }

    @DataProvider(name = "Responses")
    public  Object[][] testData() throws Exception {
        String responseDataPath = ConfigFileManager.getInstance().getTestDataConfig();
        String dir = System.getProperty("user.dir");
        String filePath = dir + responseDataPath;
        Object[][] responseData =  DpUtils.getTableArray("Video20",filePath,"sheet0");
        return (responseData);
    }


    @Test(dataProvider = "Responses")
    @Parameters({"SdkVersion","AdType","AdFormat","Placement","ResponseUrl","VideoElementValidations","EndCardElementValidations"})
    public void Test_Video20Elements(String SdkVersion,String AdType,String AdFormat,String Placement,String ResponseUrl ,String VideoElementValidations,String EndCardElementValidations) throws Exception
    {
        LoggerUtils.debug("Placement id : " + Placement);
        LoggerUtils.debug("Ad server url : " + ResponseUrl);

        if (AdFormat.startsWith("Native") && AdType.startsWith("Video"))
        {
            boolean isKitchenSinkApp = false;
            String testCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
            BaseTestCaseNativeIos testCaseNativeIos = BaseTestCaseNativeIos.getInstance(SdkVersion, isKitchenSinkApp);
            testCaseNativeIos.appInterstitialSetup(Placement,ResponseUrl);

            VideoInsterstitialValidationUtils validationUtils = new VideoInsterstitialValidationUtils(SdkVersion, isKitchenSinkApp);

            if(!VideoElementValidations.isEmpty()) {
                Assert.assertTrue(validationUtils.ValidateNative20VideoElements(20, VideoElementValidations));
                testCaseNativeIos.getAppSpecificActionInstance().takeScreenShots(testCaseName, Placement, "video");
                Thread.sleep(10000);
            }

            if(!EndCardElementValidations.isEmpty()) {
                Assert.assertTrue(validationUtils.ValidateNative20VideoEndCardElements(20, EndCardElementValidations));
                testCaseNativeIos.getAppSpecificActionInstance().takeScreenShots(testCaseName, Placement, "endcard");
                Assert.assertTrue(BeaconValidations.validateBeacons(AdType, AdFormat));
            }
        }
    }

}
