package TemplateResponseTypeTestRelated.NativeTestRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsAppsRelated.AppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsBaseTestcaseRelated.BaseTestCaseNativeIos;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import UtilsSetupEnvRelated.UtilsResponseRelated.ResponseMetaDataDownloader;
import UtilsTestRelated.UtilDataProviders.DpUtils;
import UtilsTestRelated.UtilsBeaconRelated.BeaconValidations;
import UtilsTestRelated.UtilsCrashReportRelated.CrashReportValidation;
import UtilsTestRelated.UtilsNativeRelated.UtilsStaticRelated.InFeedStaticValidationUtils;
import UtilsTestRelated.UtilsNativeRelated.UtilsVideoRelated.InFeedVideoValidationUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NativeInlineResponseTest {

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
        Object[][] responseData =  DpUtils.getTableArray("Infeed",filePath,"sheet0");
        return (responseData);
    }

    @Test(dataProvider = "Responses")
    @Parameters({"SdkVersion","AdType","AdFormat","Placement","ResponseUrl","VideoElementValidations","EndCardElementValidations"})
    public void Test_InFeedElements(String SdkVersion,String AdType,String AdFormat,String Placement,String ResponseUrl,String VideoElementValidations ,String EndCardElementValidations) throws Exception
    {
        LoggerUtils.debug("Placement id : " + Placement);
        LoggerUtils.debug("Ad server url : " + ResponseUrl);

        if (AdFormat.contains("Infeed"))
        {
            boolean isKitchenSinkApp = false;
            String testCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
            BaseTestCaseNativeIos testCaseNativeIos = BaseTestCaseNativeIos.getInstance(SdkVersion, isKitchenSinkApp);
            testCaseNativeIos.appInfeedSetup(Placement,ResponseUrl);


            if (AdType.startsWith("Video")) {
                InFeedVideoValidationUtils validationUtils = new InFeedVideoValidationUtils(SdkVersion, isKitchenSinkApp);

                if (!EndCardElementValidations.isEmpty()) {
                    Assert.assertTrue(validationUtils.ValidateNativeInFeedVideoEndCardElements(30, EndCardElementValidations));
                    testCaseNativeIos.getAppSpecificActionInstance().takeScreenShots(testCaseName, Placement, "video");
                    Thread.sleep(20000);
                    Assert.assertTrue(BeaconValidations.validateBeacons(AdType, AdFormat));
                }
            }
            else if (AdType.startsWith("Static")) {
                InFeedStaticValidationUtils validationUtils = new InFeedStaticValidationUtils(SdkVersion, isKitchenSinkApp);

                if (!EndCardElementValidations.isEmpty()) {
                    Assert.assertTrue(validationUtils.ValidateNativeInFeedStaticEndCardElements(30, EndCardElementValidations));
                    testCaseNativeIos.getAppSpecificActionInstance().takeScreenShots(testCaseName, Placement,"static");
                    Thread.sleep(5000);
                    Assert.assertTrue(BeaconValidations.validateBeacons(AdType, AdFormat));
                }
            }
        }
    }

}
