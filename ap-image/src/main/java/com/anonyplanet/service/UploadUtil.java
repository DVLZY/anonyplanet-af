package com.anonyplanet.service;

/**
 * @author 邓启航
 */
public class UploadUtil {

    public static String getUploadPath(String fileName, String upload) {

        //根据文件名称,生成hash字符串,截取前8位
        String hash = Integer.toHexString(fileName.hashCode());
        while (hash.length() < 8) {
            hash += "0";
        }
        for (int i = 0; i < hash.length(); i++) {
            upload += "/" + hash.charAt(i);
        }

        return upload;
    }
}
