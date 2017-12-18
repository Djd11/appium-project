package UtilsTestRelated.UtilsBeaconRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * template-automation Created by dhruba.jyoti on 11/22/17.
 */
public class BeaconValidations {

    public static String listOfBeacon(String testcase) throws IOException {
        String beacons = "";
        BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + ConfigFileManager.getInstance().getProxyServerLogConfig()));
        Pattern regPattern = Pattern.compile("GET\\s" + ConfigFileManager.getInstance().getBeaconBoxConfig() + ".+\\?m=(\\d+).*\\sHTTP");
        String s;
        while ((s = in.readLine()) != null) {
            Matcher matcher = regPattern.matcher(s);
            if (matcher.find()) {
                beacons = beacons.equals("") ? matcher.group(1) : String.join(",", beacons, matcher.group(1));
            }
        }
        if (in != null) {
            in.close();
        }
        return beacons;
    }


    public static JSONObject listOfBeaconInterceptedOnProxyServer() throws IOException {
        String beacons = "";
        JSONObject beaconSeen = new JSONObject();
        BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + ConfigFileManager.getInstance().getProxyServerLogConfig()));
        String s,key,value;
        String[] postSplit;
        while ((s = in.readLine()) != null) {
            if(s.contains(":")) {
                postSplit = s.split(":");
                if (postSplit.length >= 1) {
                    beaconSeen.put(postSplit[0].trim(), postSplit[1].trim());
                }
            }
        }
        if (in != null) {
            in.close();

        }
        return beaconSeen;
    }

//    public static boolean validateBeacons(String adType, String adFormat) throws IOException {
//        boolean result = false;
//        JSONObject beaconIntercepted = listOfBeaconInterceptedOnProxyServer();
//        String beaconInterceptedString = beaconIntercepted.toString();
//        LoggerUtils.debug("Beacons " + beaconInterceptedString);
//
//        result = validateBeacon(adType, adFormat, beaconInterceptedString);
//
//        if (ConfigFileManager.getInstance().getValidateMoatBoxConfig()) {
//            boolean moatResult = validateMoatBeacon(adType, adFormat, beaconInterceptedString);
//            result = result && moatResult;
//        }
//
//        if (ConfigFileManager.getInstance().getValidateIASBoxConfig()) {
//            boolean iasResult = validateIASBeacon(adType, adFormat, beaconInterceptedString);
//            result = result && iasResult;
//        }
//
//        return result;
//    }

    public static boolean validateBeacons(String adType, String adFormat) throws IOException {

        boolean result = false;
        JSONObject beaconIntercepted = listOfBeaconInterceptedOnProxyServer();
        String beaconInterceptedString = beaconIntercepted.toString();
        LoggerUtils.debug("Beacons " + beaconInterceptedString);

        if (adFormat.contains("Infeed")) {

            if(adType.startsWith("Video")) {

                if(beaconInterceptedString.contains("10") && beaconInterceptedString.contains("18") &&
                        beaconInterceptedString.contains("q=1") && beaconInterceptedString.contains("q=2") &&
                        beaconInterceptedString.contains("q=3") && beaconInterceptedString.contains("13") &&
                        beaconInterceptedString.contains("2") && beaconInterceptedString.contains("8") &&
                        beaconInterceptedString.contains("120")) {
                    result = true;
                }
            }
            else if (adType.startsWith("Static")) {
                if(beaconInterceptedString.contains("8") && beaconInterceptedString.contains("18") &&
                        beaconInterceptedString.contains("120")) {
                    result = true;
                }
            }
        }
        else if(adType.startsWith("Video")){

            if(beaconInterceptedString.contains("10") && beaconInterceptedString.contains("18") &&
                    beaconInterceptedString.contains("q=1") && beaconInterceptedString.contains("q=2") &&
                    beaconInterceptedString.contains("q=3") && beaconInterceptedString.contains("13") &&
                    beaconInterceptedString.contains("120")) {
                result = true;
            }
        }

        return result;
    }

    public static boolean validateMoatBeacon(String adType, String adFormat) throws IOException {

        boolean result = false;
        JSONObject beaconIntercepted = listOfBeaconInterceptedOnProxyServer();
        String beaconInterceptedString = beaconIntercepted.toString();
        LoggerUtils.debug("Beacons " + beaconInterceptedString);

        if(beaconInterceptedString.contains("e=0") && beaconInterceptedString.contains("e=17")) {
            result = true;
        }

        return result;
    }

    public static boolean validateIASBeacon(String adType, String adFormat) throws IOException {

        boolean result = false;
        JSONObject beaconIntercepted = listOfBeaconInterceptedOnProxyServer();
        String beaconInterceptedString = beaconIntercepted.toString();
        LoggerUtils.debug("Beacons " + beaconInterceptedString);

        if(beaconInterceptedString.contains("pingTime:2") && beaconInterceptedString.contains("pingTime:5")) {
            result = true;
        }

        return result;
    }

    public static void clearBeaconLogs() throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/" + ConfigFileManager.getInstance().getProxyServerLogConfig();
        LoggerUtils.debug("Beacon log file path: " + filePath);

        try {
            if(Files.exists(Paths.get(filePath))) {
                FileOutputStream writer = new FileOutputStream(filePath);
                writer.write(("").getBytes());
                writer.close();
                LoggerUtils.debug("Cleaned beacon log file");
            }
        }
        catch (Exception e) {
            LoggerUtils.error("Failed to clean beacon log file");
            LoggerUtils.error(e);
        }
    }

}
