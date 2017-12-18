package UtilsSetupEnvRelated.UtilsAppsRelated;

public class CustomAppVersionSpecificActions {

    private String BannerAd;
    private String InterstitalAd;
    private String InfeedVideoAd;
    private String EditPlacementId;
    private String EditAdServerUrl;
    private String SettingsCancel;
    private String SettingsDone;
    private String LoadAdButton;
    private String ShowAdButton;
    private String BackToApp;


    public String getBannerAd() {
        return BannerAd;
    }

    public String getInterstitalAd() {
        return InterstitalAd;
    }

    public String getInfeedVideoAd() {
        return InfeedVideoAd;
    }

    public String getEditPlacementId() {
        return EditPlacementId;
    }

    public String getEditAdServerUrl() {
        return EditAdServerUrl;
    }

    public String getSettingsDone() {
        return SettingsDone;
    }

    public String getSettingsCancel() {
        return SettingsCancel;
    }

    public String getLoadAdButton() {
        return LoadAdButton;
    }

    public String getShowAdButton() {
        return ShowAdButton;
    }

    public String getBackToApp() {
        return BackToApp;
    }

    public CustomAppVersionSpecificActions(String version)
    {
        int v=Integer.parseInt(version);
        switch (v)
        {
            case 700:
            case 701: {
                BannerAd = "//XCUIElementTypeCell[@name=\"banner_ads\"]";
                InterstitalAd = "//XCUIElementTypeCell[@name=\"interstitial_ads\"]";
                InfeedVideoAd = "//XCUIElementTypeCell[@name=\"infeed_video_ads\"]";
                EditPlacementId = "//XCUIElementTypeTextField[@name=\"placement_id\"]";
                EditAdServerUrl = "//XCUIElementTypeTextField[@name=\"ad_server_url\"]";
                SettingsDone = "//XCUIElementTypeButton[@name=\"done_button\"]";
                SettingsCancel = "//XCUIElementTypeButton[@name=\"cancel_button\"]";
                LoadAdButton = "//XCUIElementTypeButton[@name=\"load_ad_button\"]";
                ShowAdButton = "//XCUIElementTypeButton[@name=\"show_ad_button\"]";
                BackToApp = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeNavigationBar[1]/XCUIElementTypeButton[1]";
                break;
            }
        }
    }
}
