import com.dousnl.utils.date.DateUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/2/27 19:43
 */
public class DateTest {
    private static Logger logger = LoggerFactory.getLogger(DateTest.class);

    public static void main(String[] args) {
        Date d=new Date();
        Date c=d;

        Integer a=null;
        boolean after = d.after(c);
        screenList(1);
        System.out.println(after);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR,1991);

        Date time = calendar.getTime();

        System.out.println(DateUtil.dateToString(time,"yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateUtil.dateToString(time,"yyyy"));

        System.out.println(DateUtil.dateToString(new Date(1609727383405l),"yyyy-MM-dd HH:mm:ss"));

        logger.info("周年日期：{},当前日期：{},周年弹框日期：{}", d, d, d);

        registerYearSource(null);

        System.out.println(HashUtil.string2Hash("418328859"));

        System.out.println(418327855/64);

        //Date n=DateUtil.parseDate("2020-11-11 17:41:30");
        //Date reg=DateUtil.parseDate("2020-11-11 18:41:40");

        //int minutesBetween = DateUtil.getMinutesBetween(n, reg);
        //if (minutesBetween>=0 && minutesBetween <= 30) {
        //    System.out.println(minutesBetween);
        //}

        System.out.println(DateUtil.formatTime(1608631052000l,"yyyy-MM-dd HH:mm:ss"));
        String content="<p>看完这本书后，请记得“联盟”这个词。这意味着你换了崭新的眼光来看待你和员工的关系。在全民创业的中国，意识到这一点尤为重要。</p>\\r\\n\\r\\n<p style=\\\"text-align:center\\\"><img src=\\\"image://image/jpeg;158262175508121a4a3d75289cd0b3e0ddaa87732600553w\\\" style=\\\"box-sizing:border-box !important; color:rgb(62, 62, 62); text-align:center; visibility:visible !important; white-space:normal; width:auto !important; word-wrap:break-word !important\\\"></p>\\r\\n\\r\\n<p><span style=\\\"color:rgb(0, 176, 240)\\\"><strong><span style=\\\"font-size:20px\\\">你还在为员工的离去而纠结吗？</span></strong></span></p>\\r\\n\\r\\n<hr>\\r\\n<p>每个老板几乎都会担心员工离职，而每个员工心中又都有一个创业梦想。所以无论一段合作如何开始，结局却大都令人心寒。这本书给了我们一个展新的视角，既然员工的离开是迟早的事，为什么不直面这件事？坦率的讨论员工在离职后的职业方向，才能够激发员工在任期内努力进步。互联网放大了每个人的能力，因此，未来的企业中不再是雇佣的关系，而是联盟的关系！</p>\\r\\n\\r\\n<p style=\\\"text-align:center\\\"><img src=\\\"image://image/jpeg;1582621756d958b4fb7653d96570b68786a742f0aa8rcbvi\\\" style=\\\"box-sizing:border-box !important; color:rgb(62, 62, 62); text-align:center; visibility:visible !important; white-space:normal; width:auto !important; word-wrap:break-word !important\\\"></p>\\r\\n\\r\\n<p><span style=\\\"color:rgb(0, 176, 240)\\\"><strong><span style=\\\"font-size:20px\\\">通过联盟重建信任与忠诚</span></strong></span></p>\\r\\n\\r\\n<hr>\\r\\n<p>传统的雇佣关系中，雇主一直倡导长期合作，但是却会突然开除你；员工不管在面试时如何表忠心，一有机会就会跳槽或者创业。因为双方的对话都是建立在不诚实的基础上的。</p>\\r\\n\\r\\n<p>如果我们不能回到终身雇佣的年代，就要学会重建雇主与员工的关系。这种新的忠诚观允许公司和员工对彼此做出承诺，把过去的商业交易转变为互惠关系的框架。雇佣关系可以转换为一个联盟：一份由独立的双方达成的，有明确条款的互惠协议。</p>\\r\\n\\r\\n<p>（本节选为<strong>《联盟》</strong>精华解读第1段，想了解更多内容，请点击本页面右上角封面图进行获取）</p>";
        content = content.replaceAll("\\r|\\n", "").replaceAll("<p></p>", "").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
        //String s2 = HtmlUtils.html2Text(content);
        //System.out.println(s2);
        System.out.println(content);
        String s = content.substring(0, 110).replaceAll("\r|\n","");
        System.out.println(s);
        String s1 = s.replaceAll("\\r|\\n", "");
        System.out.println(s1);
        System.out.println("str1".substring(0,3));
        System.out.println(Arrays.asList(1,4).contains(null));
        System.out.println(String.format("https://card.dushu.io/app/auditNotification.html?informType=%s&id=%s",1,1));
        String rkey = "appnotifykey_" + HashUtil.string2Hash("418279610");
        System.out.println(rkey);

        System.out.println(DateUtil.formatTime(31557052800000l,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
    }

    private static void screenList(int i) {
        return;
    }
    private static boolean registerYearSource(String source) {
        if (!StringUtil.isEmpty(source) && Objects.equals(source,"registerYear")){
            return true;
        }
        return false;
    }

}
