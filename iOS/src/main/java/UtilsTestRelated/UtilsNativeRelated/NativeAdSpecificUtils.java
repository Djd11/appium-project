package UtilsTestRelated.UtilsNativeRelated;

import UtilsSetupEnvRelated.UtilsAppiumServerRelated.AppiumDriverInstance;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.HashMap;

import static java.util.concurrent.TimeUnit.SECONDS;

public class NativeAdSpecificUtils {
    protected AppiumDriver<MobileElement> driver;
    private Dimension windowsize;

    public  NativeAdSpecificUtils(String sdkversion) throws IOException, InterruptedException {
        driver = AppiumDriverInstance.getinstance(sdkversion).getDriver();
        windowsize = getWidthAndHeight();
    }


    public boolean isUiElementFound(final String accessibilityId,final int waitTime){

        boolean result = false;
        MobileElement element = null;

        for (int attempt = 0; attempt < waitTime; attempt++) {
            try {
                element= driver.findElementByAccessibilityId(accessibilityId);
                if(element!=null){
                    result = true;
                }
                break;
            }
            catch (Exception e) {
                driver.manage().timeouts().implicitlyWait(1, SECONDS);
                LoggerUtils.error(e);
            }
            finally {
                if(element != null){
                    LoggerUtils.debug("\n\n\n\nElement " + accessibilityId + " Found\n\n\n\n");
                } else {
                    LoggerUtils.error("\n\n\n\nDid not find " + accessibilityId + "\n\n\n\n");
                }
            }
        }
        return result;
    }

    public boolean navigateTillInFeedElement(String frameElement, int startx, int starty, int endx, int endy) throws Exception {
        boolean result = false;
        result = scrollTillElementLocation(frameElement,startx,starty,endx,endy);
        return result;
    }

    public boolean scrollTillElementLocation(String element, int startx, int starty, int endx, int endy ) throws Exception {
     /*
     Please use this method when scroll needs to position based
      */
        boolean result=false;
        while(!result)
        {
            try
            {
                MoveDown(startx,starty,endx,endy);
                MobileElement ele = driver.findElementByName(element);
                if(ele!=null) {
                    result = true;
                    break;
                }
            }
            catch(Exception e) {
                driver.manage().timeouts().implicitlyWait(1, SECONDS);
                LoggerUtils.error(e);
            }
        }
        return result;
    }

    public void clickOnElement(String element) throws Exception {
        try
        {
            MobileElement ele = driver.findElementByName(element);
            if(ele!=null) {
                ele.click();
            }
        }
        catch(Exception e) {
            LoggerUtils.error(e);
        }
    }

    public void MoveDown(int startx,int starty,int endx,int endy){
        int startX = getX(startx); int endX = getX(endx);
        int startY = getY(starty); int endY = getY(endy);
        TouchAction touchAction4 = new TouchAction(driver);
        touchAction4.press(startX, startY).moveTo(endX, -endY).release();
        driver.performTouchAction(touchAction4);
    }

    private  int getX (int x) {
        //percentage of width
        return (int) ((windowsize.width * x)/100);
    }

    private  int getY (int y) {
        return (int) ((windowsize.height * y)/100);
    }

    private Dimension getWidthAndHeight(){
        return driver.manage().window().getSize();
    }
}
