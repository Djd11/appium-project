package UtilsSetupEnvRelated.UtilsAppsRelated;

/**
 * template-automation Created by dhruba.jyoti on 10/24/17.
 */
public class KitchenSinkVersionSpecificActions {

        private String Monetization;
        private String AdNetwork;
        private String Settings;
        private String Reload;
        private String AppId;
        private String EditAdSize;
        private String Interstitial;
        private String EditAdServerUrl;
        private String ExtraParameter;
        private String Done;
        private String ShowInterstitial;
        private String AlertCancel;
        private String BackToApp;
        private String ctaDone;
        private String InmobiNativeReboot;
        private String InmobiNativeRebootReload;

    public KitchenSinkVersionSpecificActions(String version)
        {
            int v=Integer.parseInt(version);
            switch (v)
            {
                case 451:
                case 452:
                case 453:
                case 454:
                {
                    Monetization = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
                    AdNetwork = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
                    Settings = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[3]";
                    Reload = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]";
                    AppId = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]";
                    EditAdSize = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]";
                    Interstitial = " //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]";
                    ExtraParameter= "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[1]";
                    EditAdServerUrl = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIATextField[1]";
                    Done = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]";
                    ShowInterstitial = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[4]";
                    AlertCancel="//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]";
                    BackToApp="//UIAApplication[1]/UIAWindow[3]/UIAStatusBar[1]/UIAButton[1]";
                    break;
                }

                case 510:
                case 511:
                case 520:
                {
                    Monetization = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
                    AdNetwork = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
                    Settings = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[3]";
                    Reload = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]";
                    AppId = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]";
                    EditAdSize = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]";
                    Interstitial = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]";
                    ExtraParameter= "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[1]";
                    EditAdServerUrl = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIATextField[1]";
                    Done = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]";
                    ShowInterstitial = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[4]";
                    AlertCancel = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]";
                    BackToApp="//UIAApplication[1]/UIAWindow[3]/UIAStatusBar[1]/UIAButton[1]";
                    ctaDone="//UIAApplication[1]/UIAWindow[1]/UIAButton[2]";
                    break;
                }

                case 530:
                case 540:
                case 600:
                {
                    Monetization = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
                    AdNetwork = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
                    Settings = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[3]";
                    Reload = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]";
                    AppId = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIATextField[1]";
                    EditAdSize = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]";
                    Interstitial = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]";
                    ExtraParameter= "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[1]";
                    EditAdServerUrl = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIATextField[1]";
                    Done = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]";
                    ShowInterstitial = "//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[4]";
                    AlertCancel = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]";
                    BackToApp="//UIAApplication[1]/UIAWindow[3]/UIAStatusBar[1]/UIAButton[1]";
                    ctaDone="//UIAApplication[1]/UIAWindow[1]/UIAButton[2]";
                    break;
                }
                case 700:
                {
                    Monetization ="//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]";
                    AdNetwork = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]";
                    Settings = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeToolbar[1]/XCUIElementTypeButton[3]";
                    Reload = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeIcon[1]";
                    AppId = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeTextField[1]";
                    EditAdSize = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[2]";
                    Interstitial = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[4]/XCUIElementTypeStaticText[1]";
                    ExtraParameter= "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[5]";
                    EditAdServerUrl = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[3]/XCUIElementTypeTextField[1]";
                    Done = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeButton[2]";
                    ShowInterstitial = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeToolbar[1]/XCUIElementTypeButton[4]";
                    AlertCancel = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]";
                    BackToApp="//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeButton[1]";
                    ctaDone="//UIAApplication[1]/UIAWindow[1]/UIAButton[2]";
                    break;
                }
                case 701:
                {
                    Monetization ="//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]";
                    AdNetwork = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]";
                    InmobiNativeReboot = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[3]/XCUIElementTypeStaticText[1]";
                    Settings = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[3]";
                    InmobiNativeRebootReload = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[2]";
                    Reload = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeIcon[1]";
                    AppId = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeTextField[1]";
                    EditAdSize = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[2]";
                    Interstitial = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[4]/XCUIElementTypeStaticText[1]";
                    ExtraParameter= "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[5]";
                    EditAdServerUrl = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[3]/XCUIElementTypeTextField[1]";
                    Done = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeButton[2]";
                    ShowInterstitial = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeToolbar[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[4]";
                    AlertCancel = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]";
                    BackToApp="//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeButton[1]";
                    ctaDone="//UIAApplication[1]/UIAWindow[1]/UIAButton[2]";
                    break;
                }
            }
        }

    public String getMonetization() { return Monetization; }

    public String getAdNetwork() { return AdNetwork; }

    public String getSettings() { return Settings; }

    public String getReload() { return Reload; }

    public String getAppId() { return AppId; }

    public String getEditAdSize() { return EditAdSize; }

    public String getInterstitial() { return Interstitial; }

    public String getExtraParameter() { return ExtraParameter; }

    public String getEditAdServerUrl() { return EditAdServerUrl; }

    public String getDone() { return Done; }

    public String getShowInterstitial() { return ShowInterstitial; }

    public String getAlertCancel() { return AlertCancel; }

    public String getBackToApp() { return BackToApp; }

    public String getctaDone()
    {
        return ctaDone;
    }

    public String getInmobiNativeReboot(){return InmobiNativeReboot;}

    public String getInmobiNativeRebootReload() {return InmobiNativeRebootReload;}

}
