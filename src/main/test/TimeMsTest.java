import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/28 10:42
 */
public class TimeMsTest {

    public static void main(String[] args) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        long b=1596609725346l;
        String format1 = format.format(new Date(b));
        System.out.println("format1:"+format1);

        long c=1596609741849l;
        String format2 = format.format(new Date(c));
        System.out.println("format2:"+format2);


        long d=1596610776532l;
        String format3 = format.format(new Date(d));
        System.out.println("format3:"+format3);

        long e=1596609769648l;
        String format4 = format.format(new Date(e));
        System.out.println("format4:"+format4);

        String myCommentId = null;
        if ((1==1) && true) {
            myCommentId = "1";
        }
        System.out.println(myCommentId);


        String jump=String.format("https://deeplink.dushu.io/link/index.html?op=jump&view=topicDiscussReplyDetail&topicId=%s&commentId" +
                "=%s&replyCommentId=%s",11,"214234dwqf","456745gfe");
        String str="https://deeplink.dushu.io/link/index.html?op=jump&view=topicDetail&topicId=" + 1;

        System.out.println(jump);
        System.out.println(str);

        Boolean flag=false;
        //flag= null;

        if (Boolean.FALSE.equals(flag)){
            System.out.println("111eww");
        }
    }
}
