package UtilsTestRelated.UtilsBeaconRelated;

import UtilsAutomationConfigRelated.ConfigFileManager;
import UtilsSetupEnvRelated.UtilsLogger.LoggerUtils;
import com.google.common.collect.ImmutableMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * template-automation Created by dhruba.jyoti on 11/22/17.
 */
public class ProxyServer {
    private static ProxyServer mInstance;
    private boolean serverStarted = false;

    private ProxyServer() {
        String hostAddress = "";

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    hostAddress = addr.getHostAddress();
                    LoggerUtils.debug(iface.getDisplayName() + " " + hostAddress);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        if(!serverStarted) {
            server = DefaultHttpProxyServer.bootstrap()
                        .withAddress(new InetSocketAddress(hostAddress, Integer.parseInt(ConfigFileManager.getInstance().getProxyServerPortConfig())))
                            .withFiltersSource(new HttpFiltersSourceAdapter() {
                                public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
                                    return new HttpFiltersAdapter(originalRequest) {
                                        @Override
                                        public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                                            LoggerUtils.debug(httpObject);
                                            String filePath = System.getProperty("user.dir") + "/" + ConfigFileManager.getInstance().getProxyServerLogConfig();
                                            Path newFilePath = Paths.get(filePath);
                                            try {
                                                if(!Files.exists(newFilePath))
                                                    Files.createFile(newFilePath);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                            try {
                                                Pattern beaconRegex = Pattern.compile("GET\\s" + ConfigFileManager.getInstance().getBeaconBoxConfig() + ".+\\?m=(\\d+).*\\sHTTP");
                                                Pattern quarterRegex = Pattern.compile("GET\\s" + ConfigFileManager.getInstance().getBeaconBoxConfig() + ".+(q=\\d+).*\\sHTTP");
                                                Pattern actionRegex = Pattern.compile("GET\\s" + ConfigFileManager.getInstance().getBeaconBoxConfig() + ".+(action=[a-zA-Z]+).*\\sHTTP");
                                                Pattern cwBeaconRegex = Pattern.compile("c\\.w\\.inmobi\\.com");
                                                Pattern publisherFillRegex = Pattern.compile("GET\\s" + ConfigFileManager.getInstance().getBeaconPublisherBoxConfig() + ".+\\?m=(\\d+).*\\sHTTP");
                                                Pattern moatRegex = Pattern.compile("GET\\s" + ConfigFileManager.getInstance().getMoatBoxConfig() + ".+\\?e=(\\d+).*\\sHTTP");
                                                Pattern iasRegex = Pattern.compile("GET\\s" + ConfigFileManager.getInstance().getIASBoxConfig() + ".+\\?pingTime:(\\d+).*\\sHTTP");

                                                String httpRequest = httpObject.toString();
                                                Matcher beconMatcher = beaconRegex.matcher(httpRequest);
                                                Matcher quarterMatcher = quarterRegex.matcher(httpRequest);
                                                Matcher actionMatcher = actionRegex.matcher(httpRequest);
                                                Matcher cwMatcher = cwBeaconRegex.matcher(httpRequest);
                                                Matcher publisherMatcher = publisherFillRegex.matcher(httpRequest);
                                                Matcher moatMatcher = moatRegex.matcher(httpRequest);
                                                Matcher iasMatcher = iasRegex.matcher(httpRequest);

                                                if (beconMatcher.find()) {
                                                    String beacon = beconMatcher.group(1);
                                                    switch (beacon) {
                                                        case "99":
                                                            if (actionMatcher.find())
                                                                beacon += " " + actionMatcher.group(1);
                                                            break;
                                                        case "12":
                                                            if (quarterMatcher.find())
                                                                beacon += " " + quarterMatcher.group(1) + " : (Quartile Beacon)";
                                                            break;
                                                    }

                                                    Files.write(newFilePath, (beacon + " " + (beaconsMap.containsKey(beacon) ? ": (" + beaconsMap.get(beacon) + ")" : "") + "\n").getBytes(), StandardOpenOption.APPEND);
                                                }

                                                if (cwMatcher.find()) {
                                                    Files.write(newFilePath, (cwMatcher.group() + "\n").getBytes(), StandardOpenOption.APPEND);
                                                }

                                                if (publisherMatcher.find()) {
                                                    String beacon = publisherMatcher.group(1);
                                                    Files.write(newFilePath, (beacon + " " + (beaconsMap.containsKey(beacon) ? ": (" + beaconsMap.get(beacon) + ")" : "") + "\n").getBytes(), StandardOpenOption.APPEND);
                                                }

                                                if (moatMatcher.find()) {
                                                    String beacon = publisherMatcher.group(1);
                                                    Files.write(newFilePath, (beacon + " " + (moatMap.containsKey(beacon) ? ": (" + moatMap.get(beacon) + ")" : "") + "\n").getBytes(), StandardOpenOption.APPEND);
                                                }

                                                if (iasMatcher.find()) {
                                                    String beacon = publisherMatcher.group(1);
                                                    Files.write(newFilePath, (beacon + " " + (iasMap.containsKey(beacon) ? ": (" + iasMap.get(beacon) + ")" : "") + "\n").getBytes(), StandardOpenOption.APPEND);
                                                }

                                            }
                                            catch (IOException e) {
                                                LoggerUtils.error(e);
                                            }
                                            return null;
                                        }
                                    };
                                }
                            })
                            .start();
        }

        if (server != null) {

            serverStarted = true;
        }
        LoggerUtils.debug("Proxy Server Started on: " + hostAddress + ":" + ConfigFileManager.getInstance().getProxyServerPortConfig());
    }

    public static ProxyServer getInstance() throws IOException {
        if (mInstance == null) {
            mInstance = new ProxyServer();
        }
        return mInstance;
    }

    private HttpProxyServer server = null;
    private static Map<String,String> beaconsMap= ImmutableMap.<String,String>builder()
            .put("1", "Creative View")
            .put("18", "Impression Beacon")
            .put("14", "Media Paused")
            .put("17", "Media Unpaused")
            .put("10", "Media Start")
            .put("13", "Complete")
            .put("8", "Click")
            .put("2", "Expand")
            .put("12", "Quartile Beacon")
            .put("120", "Publisher Fill")
            .build();

    private static Map<String,String> moatMap= ImmutableMap.<String,String>builder()
            .put("0", "e=0")
            .put("17", "e=17")
            .build();

    private static Map<String,String> iasMap= ImmutableMap.<String,String>builder()
            .put("2", "pingTime:2")
            .put("5", "pingTime:5")
            .build();

    public HttpProxyServer getProxyServer() {
        return server;
    }

    private String getChangedOutputPath(String httpObject) {
        Pattern pattern = Pattern.compile("GET[\\s]+\\/changeTheOutputPathWithNewPath[\\s]+HTTP\\/[\\S\\s]*PATH:[\\s]*"
                + "(.*)[\\s]+[\\S\\s]*");
        Matcher matcher = pattern.matcher(httpObject);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    public void closeServer() throws Exception {
        if (server != null)
            server.abort();
    }

}
