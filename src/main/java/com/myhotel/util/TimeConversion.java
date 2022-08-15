package com.myhotel.util;





import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.platform.commons.util.StringUtils;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeConversion {

    /**
     * 输入字符串时间指定结束时间
     * 2022-08-03T16:00:00.000Z
     *
     * @return
     */
    public String setTime(String str, String dt) throws ParseException {
        //        定义日期格式
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        将字符串转化为日期
        java.util.Date date = df.parse(str);
        //获取时间
        Calendar cal = Calendar.getInstance();
        //        设置时间
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);//这里改为1

        System.out.println("时间：" + new SimpleDateFormat(dt).format(cal.getTime()));

        return new SimpleDateFormat(dt).format(cal.getTime());
    }

    /**
     * 输入字符串时间转化为时间格式
     * 2022-08-03T16:00:00.000Z => 2022-08-04 13:16:10
     *
     * @return
     */
    public String timeInvert(String timeStr) {
       if (!StringUtils.isBlank(timeStr)){
           DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
           LocalDateTime ldt = LocalDateTime.parse(timeStr, df);
           ZoneId currentZone = ZoneId.of("UTC");
           ZoneId newZone = ZoneId.of("Asia/Shanghai");
           timeStr = ldt.atZone(currentZone).withZoneSameInstant(newZone).toLocalDateTime().toString();
           java.util.Date date = (Date) df.parse(timeStr);
           System.out.println("时间1："+timeStr.substring(0, 10));
           return timeStr.substring(0, 10);
       }
       return null;
    }


    /**
     * 根据身份证的号码算出当前身份证持有者的年龄
     *
     * @return
     **/
    public int countAge(String idNumber) {
        if (idNumber.length() != 18 && idNumber.length() != 15) {
            throw new IllegalArgumentException("身份证号长度错误");
        }
        String year;
        String yue;
        String day;
        if (idNumber.length() == 18) {
            year = idNumber.substring(6).substring(0, 4);// 得到年份
            yue = idNumber.substring(10).substring(0, 2);// 得到月份
            day = idNumber.substring(12).substring(0, 2);//得到日
        } else {
            year = "19" + idNumber.substring(6, 8);// 年份
            yue = idNumber.substring(8, 10);// 月份
            day = idNumber.substring(10, 12);//日
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
        String fday = format.format(date).substring(8, 10);//
        int age = 0;
        if (Integer.parseInt(yue) == Integer.parseInt(fyue)) {//如果月份相同
            if (Integer.parseInt(day) <= Integer.parseInt(fday)) {//说明已经过了生日或者今天是生日
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            }
        } else {

            if (Integer.parseInt(yue) < Integer.parseInt(fyue)) {
                //如果当前月份大于出生月份
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            } else {
                //如果当前月份小于出生月份,说明生日还没过
                age = Integer.parseInt(fyear) - Integer.parseInt(year) - 1;
            }
        }
        return age;
    }

}
