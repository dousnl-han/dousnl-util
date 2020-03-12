import java.util.Date;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/2/27 19:43
 */
public class DateTest {


    public static void main(String[] args) {
        Date d=new Date();
        Date c=d;


        boolean after = d.after(c);

        System.out.println(after);
    }
}
