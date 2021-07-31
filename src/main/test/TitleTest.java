import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/9/8
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/9/8       hanliangliang     新增              1001
 ********************************************************************/
public class TitleTest {

    private static Pattern titlePattern = Pattern.compile("\\s*|\t|\r|\n");
    public static void main(String[] args) {
        String str="1111\n  \r  a  \t  b";

        System.out.println(replaceBlank(str));
        System.out.println(str);
        System.out.println("".substring(0,"".length()));
        if (Boolean.FALSE.equals(null)){
            System.out.println("fsdfd");
        }
    }

    private static String replaceBlank(String str){
        String dest = "";
        if (str != null) {
            Matcher m = titlePattern.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
