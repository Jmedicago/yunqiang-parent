package com.vgit.yunqiang.common.utils;

import com.vgit.yunqiang.common.utils.encrypt.MD5;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class StrUtils {

    public static Long[] splitToLong(String str) {
        return splitToLong(str, ",");
    }

    public static Long[] splitToLong(String str, String separator) {
        if (null == str || "".equals(str))
            return null;
        String[] strArr = str.split(separator);
        Long[] longArr = new Long[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            longArr[i] = Long.parseLong(strArr[i]);
        }
        return longArr;
    }

    public static String getRandomString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();

    }

    public static String getComplexRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String convertPropertiesToHtml(String properties) {
        if (StringUtils.isBlank(properties)) {
            return "";
        }
        StringBuilder sBuilder = new StringBuilder();
        String[] propArr = properties.split("_");
        for (String props : propArr) {
            String[] valueArr = props.split(":");
            sBuilder.append(valueArr[1]).append(":").append(valueArr[3]).append("<br>");
        }
        return sBuilder.toString();
    }

    public static void main(String[] args) {
        String password = "admin";
        String salt = StrUtils.getComplexRandomString(10);
        System.out.println("salt:" + salt);
        //通过盐值进行MD5散列
        String md5Password = MD5.getMD5(password + salt);
        System.out.println("password:" + md5Password);
    }

}
