package UtilsSetupEnvRelated.UtilsDeviceOrSimulatorRelated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * template-automation Created by dhruba.jyoti on 10/24/17.
 */
public class ExtractDeviceInfo {

    private String output(InputStream inputStream, String string) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null)
            {
                if(line.contains(string))
                    sb.append(line + System.getProperty("line.separator"));
            }
        }
        finally
        {
            br.close();
        }
        return sb.toString();
    }



    public String getDeviceUDID(String deviceInfo) throws IOException, InterruptedException
    {

        ProcessBuilder pb = new ProcessBuilder("instruments" , "-s" , "devices","grep",deviceInfo,"tail -n1","awk '{print $5}'");
        Process pc = pb.start();
        pc.waitFor();
        String out = output(pc.getInputStream(), deviceInfo);
        return (out.split("\\[")[1].split("]")[0]);
    }

//    public String getOS() throws IOException, InterruptedException
//    {
//        ProcessBuilder pb = new ProcessBuilder("instruments" , "-s" , "devices");
//        Process pc = pb.start();
//        pc.waitFor();
////        String out = output(pc.getInputStream(), "51D3C3D6-B735-4796-901A-14D653049BBF"); // 5s
//        String out = output(pc.getInputStream(), "8B21A85C-F98B-4074-AC63-3C9DD3BCEF2E"); // 6
////        String out = output(pc.getInputStream(), "Video");
//        return (out.split("\\(")[1].split("\\)")[0]);
//    }
}
