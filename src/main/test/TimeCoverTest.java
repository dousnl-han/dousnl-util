import com.dousnl.utils.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/1/2 15:05
 */
public class TimeCoverTest {


    public static void main(String[] args) throws ParseException {
        long start1 = System.currentTimeMillis();
        Date a=new Date();
        Date b=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String appointmentTimeStart = format.format(a) + " 00:00:00";
        formatDate.parse(appointmentTimeStart);
        String appointmentTimeEnd = format.format(b) + " 23:59:59";
        formatDate.parse(appointmentTimeEnd);

        long end = System.currentTimeMillis();
        System.out.println("end-start:"+(end-start1)+"ms");
    }
}
