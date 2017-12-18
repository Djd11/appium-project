package UtilsTestRelated.UtilsNativeRelated.UtilsStaticRelated;

import UtilsSetupEnvRelated.UtilsAppiumServerRelated.AppiumDriverInstance;
import UtilsSetupEnvRelated.UtilsAppsRelated.AppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsAppsRelated.CustomAppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsAppsRelated.KitchenSinkAppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import UtilsTestRelated.UtilsNativeRelated.NativeAdSpecificUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.IOException;

public class InFeedStaticValidationUtils {

    private NativeAdSpecificUtils nativeAdSpecificUtils ;
    private AppSpecificActionInit appSpecificActionInit;
    private AppiumDriver<MobileElement> driver;

    public  InFeedStaticValidationUtils(String sdkVersion, Boolean isKitchenSinkApp) throws IOException, InterruptedException {
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


    public boolean ValidateNativeInFeedStaticEndCardElements(int attempt, String validations) throws Exception {
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

        for (String validation : validationParams){

            if(validation.contains("cta") || validation.contains("companion") || validation.contains("learn"))
                ctaButton = true;
            else if (validation.equals("close"))
                crossButton = true;
            else if (validation.equals("skip"))
                skipButton = true;
            else if (validation.equals("replay"))
                replayButton = true;
            else if (validation.equals("mute"))
                muteButton = true;
            else if (validation.equals("timer"))
                timerAsset = true;
        }


        if(nativeAdSpecificUtils.navigateTillInFeedElement("UITesting_RootConatiner",30, 80, 82, 90))
        {
            if (nativeAdSpecificUtils.isUiElementFound("UITesting_RootConatiner", attempt))
            {
                result = true;
                nativeAdSpecificUtils.clickOnElement("UITesting_RootConatiner");

                if (timerAsset) {
                    boolean timer = nativeAdSpecificUtils.isUiElementFound("UITesting_TimerImageButton", attempt);
                    result = result && timer;
                }

                if (muteButton) {
                    boolean unMuteButton = nativeAdSpecificUtils.isUiElementFound("UITestingUnMuteButton", attempt);
                    result = result && unMuteButton;
                }

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

                if (replayButton) {
                    boolean skip = nativeAdSpecificUtils.isUiElementFound("UITesting_ReplaymageButton", attempt);
                    result = result && skip;
                }
            }
        }
        return result;
    }
}
