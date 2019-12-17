package com.vgit.yunqiang.common.utils;

import com.vgit.yunqiang.service.BisStockShuntService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    /**
     * 生成订单编号
     *
     * @param id: 用户id
     *            <p>
     *            订单编号规则：(10位)：(年末尾*月，取后2位)+（用户ID%3.33*日取整后2位）+(timestamp*10000以内随机数，取后6位)
     * @return
     */
    public static String generateOrderSn(long id) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        year = year % 10;
        if (year == 0) year = 10;
        int month = calendar.get(Calendar.MONTH) + 1;
        int yearMonth = year * month;
        String yearMonthPart = "0" + yearMonth;
        yearMonthPart = yearMonthPart.substring(yearMonthPart.length() - 2);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayNum = (int) ((id % 3.33) * day);
        String dayPart = "0" + dayNum;
        dayPart = dayPart.substring(dayPart.length() - 2);

        String timestampPart = "" + (Math.random() * 10000) * (System.currentTimeMillis() / 10000);
        timestampPart = timestampPart.replace(".", "").replace("E", "");
        timestampPart = timestampPart.substring(0, 6);
        return yearMonthPart + dayPart + timestampPart;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            System.out.println(generateShuntSn(1050L));
    }

    public static String generateShuntSn(Long stockId) {
        String code = "";
        if (BisStockShuntService.SOUTH_STOCK.equals(String.valueOf(stockId))) {
            code += "N";
        } else if (BisStockShuntService.NORTH_STOCK.equals(String.valueOf(stockId))) {
            code += "B";
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 6);
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String dateString = formatter.format(cal.getTime());
        code += dateString;
        return code;
    }

}
