package UtilsTestRelated.UtilsNativeRelated.UtilsVideoRelated;

import UtilsSetupEnvRelated.UtilsAppiumServerRelated.AppiumDriverInstance;
import UtilsSetupEnvRelated.UtilsAppsRelated.AppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsAppsRelated.CustomAppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsAppsRelated.KitchenSinkAppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import UtilsTestRelated.UtilsNativeRelated.NativeAdSpecificUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.IOException;

/**
 * template-automation Created by dhruba.jyoti on 11/7/17.
 */
public class VideoInsterstitialValidationUtils {


    private NativeAdSpecificUtils nativeAdSpecificUtils ;
    private AppSpecificActionInit appSpecificActionInit;
    private AppiumDriver<MobileElement> driver;

    public  VideoInsterstitialValidationUtils(String sdkVersion, Boolean isKitchenSinkApp) throws IOException, InterruptedException {
        nativeAdSpecificUtils = new NativeAdSpecificUtils(sdkVersion);
        driver = AppiumDriverInstance.getinstance(sdkVersion).getDriver();

        if (isKitchenSinkApp) {
            //For kitchenSink app
            appSpecificActionInit = new KitchenSinkAppSpecificActionInit(sdkVersion);
        } else {
            //For Automation App
            appSpecificActionInit = new CustomAppSpecificActionInit(sdkVersion);
        }
    }


    public boolean ValidateNative20VideoElements(int attempt, String validations) throws Exception
    {
        /*
            UITesting_CrossImageButton
            UITesting_SkipImageButton
            UITesting_ReplayImageButton
            UITesting_TimerImageButton
            UITesting_CTAImageButton
            UITesting_MuteImageButton
         */

        boolean result = false;
        boolean crossButton = false;
        boolean ctaButton = false;
        boolean muteButton = false;
        boolean replayButton = false;
        boolean skipButton = false;
        boolean timerAsset = false;

        // check post click
        String[] validationParams = validations.trim().split(" ");
        LoggerUtils.debug("validationParams : " + validations);

        for (String validation : validationParams) {

            if(validation.contains("cta") || validation.contains("companion") || validation.contains("learn"))
                ctaButton = true;
            else if (validation.contains("close"))
                crossButton = true;
            else if (validation.contains("skip"))
                skipButton = true;
            else if (validation.contains("replay"))
                replayButton = true;
            else if (validation.contains("mute"))
                muteButton = true;
            else if (validation.contains("timer"))
                timerAsset = true;
        }

        if(nativeAdSpecificUtils.isUiElementFound("UITestingVideoViewCreated",attempt)) {

            result = true;

            if (crossButton) {
                boolean cross = nativeAdSpecificUtils.isUiElementFound("UITesting_CrossImageButton", attempt);
                result = result && cross;
            }

            if (ctaButton) {
                boolean cta = nativeAdSpecificUtils.isUiElementFound("UITesting_CTAImageButton", attempt);
                boolean learnMore = nativeAdSpecificUtils.isUiElementFound("learn_more", attempt);
                result = result && (cta || learnMore);
            }

            if (skipButton) {
                boolean skip = nativeAdSpecificUtils.isUiElementFound("UITesting_SkipImageButton", attempt);
                result = result && skip;
            }

            if (timerAsset) {
                boolean timer = nativeAdSpecificUtils.isUiElementFound("UITesting_TimerImageButton", attempt);
                result = result && timer;
            }

            if (muteButton) {
                boolean mute = nativeAdSpecificUtils.isUiElementFound("UITestingUnMuteButton", attempt);
                result = result && mute;
            }

            if (replayButton) {
                boolean replay = nativeAdSpecificUtils.isUiElementFound("UITesting_ReplaymageButton", attempt);
                result = result && replay;
            }
        }
        else {
            // check if video is not played try playing it attempt 5
            int tryitFor = 0;

            while(tryitFor<=5 && !(nativeAdSpecificUtils.isUiElementFound("UITestingVideoViewCreated",2))) {
                try {
                    appSpecificActionInit.restartAppForInterstitial();
                    driver.wait(3000);
                    tryitFor++;
                }
                catch (Exception e){e.printStackTrace();}
            }
        }

        return result;
    }


    public boolean ValidateNative20VideoEndCardElements(int attempt,String validations) throws Exception {
        /*
            UITesting_CrossImageButton
            UITesting_SkipImageButton
            UITesting_ReplayImageButton
            UITesting_TimerImageButton
            UITesting_CTAImageButton
            UITesting_MuteImageButton
         */

        boolean result = false;
        boolean crossButton = false;
        boolean ctaButton = false;
        boolean replayButton = false;

        // check post click
        String[] validationParams = validations.split(" ");
        LoggerUtils.debug("validationParams : " + validations);

        for (String validation : validationParams) {
            if(validation.contains("cta") || validation.contains("companion") || validation.contains("learn"))
                ctaButton = true;
            else if (validation.contains("close"))
                crossButton = true;
            else if (validation.contains("replay"))
                replayButton = true;
        }

        if (nativeAdSpecificUtils.isUiElementFound("UITesting_RootConatiner", attempt)) {
            result = true;

            if (crossButton) {
                boolean cross = nativeAdSpecificUtils.isUiElementFound("UITesting_CrossImageButton", attempt);
                result = result && cross;
            }

            if (replayButton) {
                boolean replay = nativeAdSpecificUtils.isUiElementFound("UITesting_ReplayImageButton", attempt);
                result = result && replay;
            }

            if (ctaButton) {
                boolean cta = nativeAdSpecificUtils.isUiElementFound("UITesting_CTAImageButton", attempt);
                boolean learnMore = nativeAdSpecificUtils.isUiElementFound("learn_more", attempt);
                result = result && (cta || learnMore);
                if (cta) {
                    nativeAdSpecificUtils.clickOnElement("UITesting_CTAImageButton");
                }
                else if (learnMore) {
                    nativeAdSpecificUtils.clickOnElement("learn_more");
                }
            }
        }
        return result;
    }


}
