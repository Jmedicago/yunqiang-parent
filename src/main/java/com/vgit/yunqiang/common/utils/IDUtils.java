package com.vgit.yunqiang.common.utils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * ID生成策略
 */
public class IDUtils {

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 获取商品编码
     * 商品编码规则：nanoTime(后5位)*5位随机数(10000~99999)
     *
     * @return
     */
    public static String generateProductCode() {
        long nanoPart = System.nanoTime() % 100000L;
        if (nanoPart < 10000L) {
            nanoPart += 10000L;
        }
        long randomPart = (long) (Math.random() * (90000) + 10000);
        String code = "0" + String.valueOf((new BigDecimal(nanoPart).multiply(new BigDecimal(randomPart))));
        return code.substring(code.length() - 10);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            System.out.println(generateProductCode());
    }

}
