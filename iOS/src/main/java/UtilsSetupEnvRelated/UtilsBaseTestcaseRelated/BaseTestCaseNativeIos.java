package UtilsSetupEnvRelated.UtilsBaseTestcaseRelated;

import UtilsSetupEnvRelated.UtilsAppsRelated.AppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsAppsRelated.CustomAppSpecificActionInit;
import UtilsSetupEnvRelated.UtilsAppsRelated.KitchenSinkAppSpecificActionInit;
import UtilsTestRelated.UtilsBeaconRelated.ProxyServer;
import UtilsTestRelated.UtilsNativeRelated.NativeAdSpecificUtils;

import java.io.IOException;

/**
 * template-automation Created by dhruba.jyoti on 10/27/17.
 */
public class BaseTestCaseNativeIos {


    private AppSpecificActionInit appInit;
    private static BaseTestCaseNativeIos mInstance = null;


    private BaseTestCaseNativeIos(String Sdkversion, boolean isKitchenSinkApp) throws IOException, InterruptedException {
        BaseTestCaseIosInit(Sdkversion, isKitchenSinkApp);
    }


    public void BaseTestCaseIosInit (String sdkVersion, boolean isKitchenSinkApp) throws IOException, InterruptedException {
        if (isKitchenSinkApp) {
            //For kitchenSink app
            appInit = new KitchenSinkAppSpecificActionInit(sdkVersion);
        } else {
            //For Automation App
            appInit = new CustomAppSpecificActionInit(sdkVersion);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    ProxyServer.getInstance().getProxyServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public static BaseTestCaseNativeIos getInstance(String sdkverion, boolean isKitchenSinkApp) throws IOException, InterruptedException {
        if(mInstance == null){
            return new BaseTestCaseNativeIos(sdkverion, isKitchenSinkApp);
        }
        return mInstance;
    }


    public void appInterstitialSetup(String placementId, String responseUrl) throws IOException, InterruptedException {
        appInit.setAppForInterstitial(placementId,responseUrl);
    }


    public void appInfeedSetup(String placementId, String responseUrl) throws IOException, InterruptedException {
        appInit.setAppForInfeed(placementId,responseUrl);
    }


    public AppSpecificActionInit getAppSpecificActionInstance() {
        return appInit;
    }

}
