import com.dousnl.utils.date.DateUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/12/20 11:31
 */
public class LocalDate {


    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        Date a=new Date();
        System.out.println(DateUtil.dateToString(a,"yyyy-MM-dd HH:mm:ss sss"));

        long start = System.currentTimeMillis();
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(1);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date b = Date.from(instant);
        long end = System.currentTimeMillis();
        System.out.println("end-start:"+(end-start)+"ms");

        System.out.println(DateUtil.dateToString(b,"yyyy-MM-dd HH:mm:ss sss"));
        long end1 = System.currentTimeMillis();
        new Date(System.currentTimeMillis()+1000);
        System.out.println("end1-start1:"+(end1-start1)+"ms");

        long start2 = System.currentTimeMillis();
        long start2q000=start2+1000;
        System.out.println("start2:"+start2);
        System.out.println("start2+1000:"+start2q000);
        Date e = new Date(start2);
        Date c = new Date(start2q000);
        long end2 = System.currentTimeMillis();
        System.out.println(DateUtil.dateToString(c,"yyyy-MM-dd HH:mm:ss sss"));
        System.out.println(DateUtil.dateToString(e,"yyyy-MM-dd HH:mm:ss sss"));
        System.out.println("end2-start2:"+(end2-start2)+"ms");
        long start3 = System.currentTimeMillis();
        System.out.println("start3:"+start3);
        //1576822993891

        //1576823017896
        Date b1 = new Date(1546272000000L);
        Date b2 = new Date(1576823017896l);
        System.out.println(DateUtil.dateToString(b1,"yyyy-MM-dd HH:mm:ss sss"));
        System.out.println(DateUtil.dateToString(b2,"yyyy-MM-dd HH:mm:ss sss"));

        System.out.println("user:screen:popup:418279610".getBytes().length);
        System.out.println("1".getBytes().length);
        System.out.println("promo:link:030a1656b506f56fca3e9eaaaf2af2bce0e925fedb5d5cec83102df814f8e2af".getBytes().length);
        System.out.println("http://test4.99bs.club/3wjgqXh".getBytes().length);
    }
}
