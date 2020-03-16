package com.dx340.fileinterconnectionweb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CommandUtil {
    private static byte[] buf = new byte[1024 * 10];

    public static int getPidByPort(int port) {
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec("netstat -ano");
            InputStream inputStream = p.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));
            String result;
            int index = 0;
            while ((result = read.readLine()) != null) {
                if (index++ == 3) {
                    continue;
                }
                String[] res = result.replaceAll(" +", "%_%").split("%_%");
                if (res.length > 2) {
                    if (res[2].split(":")[1].equals(port + "")) {
                        return Integer.parseInt(res[5]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void kill(int pid) {
        Runtime r = Runtime.getRuntime();
        try {
            r.exec("taskkill /pid " + pid + " -f");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
