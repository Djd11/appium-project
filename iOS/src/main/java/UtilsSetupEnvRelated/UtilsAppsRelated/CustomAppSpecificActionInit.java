package UtilsSetupEnvRelated.UtilsAppsRelated;

import io.appium.java_client.TouchAction;
import org.openqa.selenium.*;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CustomAppSpecificActionInit extends AppSpecificActionInit {

    private CustomAppVersionSpecificActions app;

    public CustomAppSpecificActionInit(String sdkversion) throws IOException, InterruptedException {
        super(sdkversion);
        app = new CustomAppVersionSpecificActions(sdkversion);
    }


    public void clickGetBacktoApp() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getBackToApp()));
        ele.click();
    }


    public void clickBanner() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getBannerAd()));
        ele.click();
    }


    public void showSettingAlertForBanner() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getBannerAd()));
        new TouchAction(driver).longPress(ele, 2000).perform();
    }


    public void clickInterstitial() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getInterstitalAd()));
        ele.click();
    }


    public void showSettingAlertForInterstitial() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getInterstitalAd()));
        new TouchAction(driver).longPress(ele, 2000).perform();
    }


    public void clickInfeedVideo() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getInfeedVideoAd()));
        ele.click();
    }


    public void showSettingAlertForInfeedVideo() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getInfeedVideoAd()));
        new TouchAction(driver).longPress(ele, 2000).perform();
    }


    public void clickAndEditPlacementId(String placementId) throws InterruptedException,IOException {
        ele = driver.findElement(By.xpath(app.getEditPlacementId()));
        ele.click();
        if(!ele.getText().equals(placementId)) {
            ele.clear();
            ele.sendKeys(placementId);
        }
    }


    public void clickAndEditAdServerUrl(String AdServerUrl) throws InterruptedException,IOException {
        ele = driver.findElement(By.xpath(app.getEditAdServerUrl()));
        ele.click();
        if(!ele.getText().equals(AdServerUrl)) {
            ele.clear();
            ele.sendKeys(AdServerUrl);
        }
    }


    public void clickSettingDone() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getSettingsDone()));
        ele.click();
    }


    public void clickSettingCancel() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getSettingsCancel()));
        ele.click();
    }


    public void clickLoadAd() throws InterruptedException {
        ele = driver.findElement(By.xpath(app.getLoadAdButton()));
        ele.click();
    }


    public void clickShowAd() throws InterruptedException {
        for (int attempt = 0; attempt < 30; attempt++) {
            try {
                ele = driver.findElement(By.xpath(app.getShowAdButton()));
                if (ele != null) {
                    ele.click();
                }
                break;
            } catch (Exception e) {
                driver.manage().timeouts().implicitlyWait(1, SECONDS);
            }
        }
    }

    @Override
    public void setAppForInterstitial(String placementId,String adserveUrl) throws InterruptedException, IOException {
        super.setAppForInterstitial(placementId,adserveUrl);
        driver.closeApp();
        driver.launchApp();
        driver.rotate(ScreenOrientation.PORTRAIT);
        clickAllowFirstTimeLaunch();
        Thread.sleep(3000);
        showSettingAlertForInterstitial();
        clickAndEditPlacementId(placementId);
        clickAndEditAdServerUrl(adserveUrl);
        clickSettingDone();
        clickInterstitial();
        clickLoadAd();
        clickShowAd();
    }

    @Override
    public void restartAppForInterstitial() throws InterruptedException, IOException {
        super.restartAppForInterstitial();
        driver.closeApp();
        driver.launchApp();
        driver.rotate(ScreenOrientation.PORTRAIT);
        clickInterstitial();
        clickLoadAd();
        clickShowAd();
    }

    @Override
    public void setAppForInfeed(String placementId,String adserveUrl) throws InterruptedException, IOException {
        super.setAppForInfeed(placementId, adserveUrl);
        driver.closeApp();
        driver.launchApp();
        driver.rotate(ScreenOrientation.PORTRAIT);
        showSettingAlertForInfeedVideo();
        clickAndEditPlacementId(placementId);
        clickAndEditAdServerUrl(adserveUrl);
        clickSettingDone();
        clickInfeedVideo();
    }

}
