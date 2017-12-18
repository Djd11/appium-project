package UtilsTestRelated.UtilsWebViewRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsDeviceOrSimulatorRelated.ExtractDeviceInfo;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class iOSWebKitProxy
{
    ExtractDeviceInfo iOSDevice;
    public void connect() throws IOException, InterruptedException
    {
        iOSDevice = new ExtractDeviceInfo();
        final Process p = Runtime.getRuntime().exec("ios_webkit_debug_proxy -c "+ iOSDevice.getDeviceUDID(ConfigFileManager.getInstance().getDeviceInfo()) +":27753");


        new Thread(new Runnable() {
            public void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;

                try {
                    while ((line = input.readLine()) != null)
                        LoggerUtils.debug(line);
                } catch (IOException e) {
                    LoggerUtils.error(e);
                }
            }
        }).start();

        p.waitFor(30, TimeUnit.SECONDS);
    }
}
