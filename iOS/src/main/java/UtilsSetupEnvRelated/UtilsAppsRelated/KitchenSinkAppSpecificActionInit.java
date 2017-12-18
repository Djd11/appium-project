package UtilsSetupEnvRelated.UtilsAppsRelated;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import java.io.IOException;

public class KitchenSinkAppSpecificActionInit extends AppSpecificActionInit {

    private KitchenSinkVersionSpecificActions app;

    public KitchenSinkAppSpecificActionInit(String sdkversion) throws IOException, InterruptedException {
        super(sdkversion);
        app = new KitchenSinkVersionSpecificActions(sdkversion);
    }


    public void clickGetBacktoApp() throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getBackToApp()));
        ele.click();
    }


    public  void clickMonetization() throws InterruptedException
    {
        driver.tap(1,0,0,2);
        driver.context("NATIVE_APP");
        ele = driver.findElement(By.xpath(app.getMonetization()));
        ele.click();
    }


    public  void clickNativeRebootReload() throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getInmobiNativeRebootReload()));
        ele.click();
    }


    public  void clickNativeReboot() throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getInmobiNativeReboot()));
        ele.click();
    }


    public  void clickAdNetwork() throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getAdNetwork()));
        ele.click();
    }


    public  void clickSettings() throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getSettings()));
        ele.click();
    }


    public void clickReload() throws InterruptedException
    {
        waitForClickable(By.xpath(app.getReload()),5000);
        ele = driver.findElement(By.xpath(app.getReload()));
        ele.click();
    }


    public void clickAppId(String AppID) throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getAppId()));
        ele.click();
        if(!ele.getText().equals(AppID)) {
            ele.clear();
            ele.sendKeys(AppID + "\n");
        }
    }


    public void clickEditAdSize() throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getEditAdSize()));
        ele.click();
    }


    public void clickInterstitial() throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getInterstitial()));
        ele.click();
    }


    public void clickExtraParameter(String ExtraParams) throws InterruptedException
    {
        ele = driver.findElement(By.xpath(app.getExtraParameter()));
        ele.click();
        ele.clear();
        ele.sendKeys(ExtraParams);
    }


    public void clickEditAdServerUrl(String AdServerUrl) throws InterruptedException,IOException
    {
        ele = driver.findElement(By.xpath(app.getEditAdServerUrl()));
        ele.click();
        if(!ele.getText().equals(AdServerUrl)) {
            ele.clear();
            ele.sendKeys(AdServerUrl);
        }
    }


    public  void clickDone() throws InterruptedException
    {
        ele=driver.findElement(By.xpath(app.getDone()));
        ele.click();
    }


    public  void clickShowInterstitial() throws InterruptedException
    {
        ele = driver.findElementByAccessibilityId("Show Interstitial");
        ele.click();
    }


    public boolean closeOnCTA() throws InterruptedException
    {
        if(driver.findElements(By.id(app.getSettings())).isEmpty())
            return false;
        else
            return true;
    }


    public void clickAllowFirstTimeLaunch(){

        String xpath_allowButton = "//*[@name=\"Allow\"]";
        try {
            ele = driver.findElement(By.xpath(xpath_allowButton));

            if (ele != null)
                ele.click();
            return;
        }
        catch (org.openqa.selenium.NoSuchElementException e){}
    }

    @Override
    public void setAppForInterstitial(String placementId,String adserveUrl) throws InterruptedException, IOException
    {
        super.setAppForInterstitial(placementId, adserveUrl);
        driver.closeApp();
        driver.launchApp();
        driver.rotate(ScreenOrientation.PORTRAIT);
        clickAllowFirstTimeLaunch();
        clickMonetization();
        clickAdNetwork();
        clickSettings();
        clickAppId(placementId);
        clickEditAdSize();
        clickInterstitial();
        clickGetBacktoApp();
        clickEditAdServerUrl(adserveUrl);
        clickDone();
        clickShowInterstitial();
    }

    @Override
    public void restartAppForInterstitial() throws InterruptedException, IOException {
        super.restartAppForInterstitial();
        driver.closeApp();
        driver.launchApp();
        driver.rotate(ScreenOrientation.PORTRAIT);
        clickMonetization();
        clickAdNetwork();
        clickShowInterstitial();
        driver.wait(3000);
    }

    @Override
    public void setAppForInfeed(String placementId, String adserveUrl) throws InterruptedException, IOException
    {
        super.setAppForInfeed(placementId, adserveUrl);
        driver.closeApp();
        driver.launchApp();
        driver.rotate(ScreenOrientation.PORTRAIT);
        clickMonetization();
        clickNativeReboot();
        clickSettings();
        clickAppId(placementId);
        clickEditAdServerUrl(adserveUrl);
        clickDone();
        clickNativeRebootReload();
    }
}
