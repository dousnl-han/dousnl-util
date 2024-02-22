import com.alibaba.fastjson.JSON;
import com.dousnl.utils.IntegerEncryptTool;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.util.StringUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

    public static void main(String[] args) throws ParseException {
        Date d=new Date();
        Date c=d;
        Integer u=null;
        if (u==null ||u == 0){
            System.out.println(u);
        }
        Integer a=null;
        boolean after = d.after(c);
        //screenList(1);
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

        String content1="<h3 style=\"text-align:center;\"><strong>评论话题，赢知识礼包</strong></h3><p>&nbsp;</p><p style=\"text-align:center;\">你对哪位学术大师的故事最有感触？</p><p style=\"text-align:center;\">在评论区分享，即有机会赢取知识礼包1份！共送出10份</p><p>&nbsp;</p><h5 class=\"customH5\"><span style=\"color:hsl(0,0%,60%);\">活动时间和规则：2022年11月26日-2022年12月2日，12月5日中奖用户名单+收件信息收集入口将在本页面公布。礼包包含2本精选解读实体书。</span></h5><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>荐 语</strong></h3></blockquote><p>&nbsp;</p><p>本书讲述的是1920年代中国考古初创的故事。作者以地质调查所、清华国学研究院、中央研究院史语所和营造学社四大机构的发展为主线，讲述了诸如李济、赵元任、梁思成、林徽因等考古先行者们，在内忧外患的时代，如何凭热血和专业为国效力，让中国学术发扬光大，让文化泽被后世。他们在逆境中前行，不仅为中国开创了学术的新纪元，更改变了世界对中国的认知。</p><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>你 将 获 得</strong></h3></blockquote><p>&nbsp;</p><ul><li>穿越百年，了解中国考古四座高峰</li><li>看一代学人如何开创中国学术新纪元</li><li>感受大师们逆境中前行的勇气和毅力</li></ul><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>作 者 简 介</strong></h3></blockquote><p>&nbsp;</p><h4><span style=\"color:#fdd000;\"><strong>☉ </strong></span><strong>张泉</strong></h4><p>&nbsp;</p><p>毕业于复旦大学中文系</p><p>原《生活月刊》主编</p><p>著有《城殇：晚清民国十六城记》《中华文明访谈录》等作品</p><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>精 彩 选 段</strong></h3></blockquote><p>&nbsp;</p><p>1.诞生于内忧外患之时，考古发掘不仅是科学领域的探索，甚至关乎民族尊严。这一代学人始终被强烈的民族情感激励着，鼓动着，急切地试图向世界证明，中国不仅有黄帝和尧、舜、禹的传说，不仅有夏、商、周以降的代际传承，在远古时代，中国大地上同样孕育过远古人类的一支。P.72</p><p>&nbsp;</p><p>2. 梁启超、王国维和陈寅恪都没有亲身参与过考古发掘，但是，作为中国举足轻重的两代学界领袖，他们对“地下之新材料”的认同与提倡、对考古发掘的推崇与畅想，无疑为中国第一代考古学家的田野考察减轻了舆论压力，铺平了路。清华国学研究院存世时间虽不长，却因这些交集与相互砥砺，氤氲出新的文化图景。P.139</p><p>&nbsp;</p><p>3.1920 年代，一些知识分子逐渐意识到，中国欲求真正的复兴，必须追本溯源，理清文明嬗变的轨迹，才能以古鉴今，重塑文明的新格局。在现代语境之下，家国意识的苏醒催生了对民族身份的探寻，学界首当其冲。P.170</p><h5 class=\"customH5\"><br><span style=\"color:hsl(0,0%,60%);\">注：上述页码为句子在实体书中所在的页码。</span><br>&nbsp;</h5><div class=\"swipe-box\"><div class=\"swipe-content\"><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;1669169740dd6608d3144a71fc89eac9941d271eedu5ewer\"></figure><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;1669169745092f30558b42bc1d54eca0e840fb1b46lb427w\"></figure><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;1669169747ef3d5db4e99fe92af75b7106947b34a5jarceq\"></figure><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;1669169750c9f5af64d9b447acd9de1779e6ed9344d56nzf\"></figure></div></div><p>&nbsp;</p><h5 class=\"customH5\" style=\"text-align:center;\"><span style=\"color:hsl(0,0%,60%);\">滑动可查看更多知识点~</span></h5><p style=\"text-align:center;\">&nbsp;</p><figure class=\"image\"><img src=\"image://image/png;1669169783c6273a54cebe84e12ac7ab3b683f5841j0k5a8\"></figure>";
        String str = content1.replaceAll("\\r|\\n", "").replaceAll("<p></p>", "").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
        System.out.println(content1);
        System.out.println(content1.contains("荐 语") && content1.contains("动可查看更多知识点"));
        String recommendInfo = content1.substring(content1.indexOf("你 将 获 得")+7,content1.indexOf("作 者 简 介"));
        System.out.println("recommendInfo:"+recommendInfo);

        String[] split = recommendInfo.split("<li>");
        for (String s : split) {
            String s1 = s.replaceAll("\\r|\\n", "").replaceAll("<p></p>", "").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
            if (!StringUtils.isBlank(s1)){
                System.out.println(s1);
            }
        }


        System.out.println("=============");
        String intros = content1.substring(content1.indexOf("精 彩 选 段") + 7, content1.indexOf("注：上述页码为句子在实体书中所在的页码。"));
        String[] split1 = intros.split("<p>");
        for (String s : split1) {
            String s1 = s.replaceAll("\\r|\\n", "").replaceAll("<p></p>", "").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
            if (!StringUtils.isBlank(s1)){
                if (s1.contains("P.")){
                    String[] split2 = s1.split("P.");
                    for (String s2 : split2) {
                        System.out.println(s2);
                    }
                }else{
                    System.out.println(s1);
                }

            }
        }
        System.out.println("=============");
        System.out.println(intros);

        String con="<h3 style=\"text-align:center;\"><strong>评论话题，赢知识礼包</strong></h3><p>&nbsp;</p><p style=\"text-align:center;\">咖啡和茶，你更喜欢哪一个，为什么？</p><p style=\"text-align:center;\">在评论区带话题关键词<span style=\"color:rgb(36,91,219);\">#咖啡派#</span>或<span style=\"color:rgb(36,91,219);\">#茶派#</span>分享，即有机会赢取“东坡好友茶”或“情动班其挂耳咖啡”一份，共抽取6份！</p><p>&nbsp;</p><p>&nbsp;</p><h5 class=\"customH5\"><span style=\"color:hsl(0,0%,60%);\">活动时间和规则：2022年12月17日-2022年12月24日，12月27日中奖用户名单+收件信息收集入口将在本页面公布。礼包为“东坡茶”或“情动班其挂耳咖啡”一份。</span></h5><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>荐 语</strong></h3></blockquote><p>&nbsp;</p><p>俗语说：“开门七件事，柴米油盐酱醋茶。”一炊一饭之外的茶，是如何走进了我们的生活，与前六种生活必需品并列的？川藏背夫的背上，俄罗斯的大篷车中，英国的快剪船内，这样一片普通的叶子，如何走过了这般波澜壮阔的路程，才成为我们手中的一盏清茗？</p><p>&nbsp;</p><p>在本书中，著名汉学家梅维恒和郝也麟以独树一帜的广阔视角，合力考察了全球每一个茶叶出没的角落，将散落在历史中的一个个关于茶叶故事串联起来，从社会、文化、历史、经济和文学等各个方面，讲述了这部关于茶叶的大历史，让我们看见关于这片树叶的传奇。</p><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>你 将 获 得</strong></h3></blockquote><p>&nbsp;</p><ul><li>从中国开始，看茶怎样征服世界各地的味蕾</li><li>从茶的起源到创新茶饮，茶叶如何走到今天</li><li>从茶到文化，展现跨学科视野下的茶史全图</li></ul><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>作 者 简 介</strong></h3></blockquote><p>&nbsp;</p><h4><span style=\"color:#fdd000;\"><strong>☉</strong></span><strong>&nbsp;[美] 梅维恒</strong></h4><p>&nbsp;</p><p>著名汉学家 北美敦煌学第一人</p><p>宾夕法尼亚大学中国语言文学教授</p><p>编纂了《哥伦比亚中国文学史》等著作</p><p>《道德经》《庄子》等作品的英译者</p><p>&nbsp;</p><h4><span style=\"color:#fdd000;\"><strong>☉</strong></span><strong> &nbsp;[瑞典] 郝也麟</strong></h4><p>&nbsp;</p><p>记者，为多种刊物撰写中国历史和文化方面的文章</p><p>&nbsp;</p><p>&nbsp;</p><blockquote><h3><strong>精 彩 选 段</strong></h3></blockquote><p>&nbsp;</p><p>1.在品茗的间隙，感受茶汤丰富的色彩，想象茶叶经过的时空路程，回味茶的厚重历史，体会茶带来的力量和自信，思索茶引起的文化变革，体会茶带来的平和心，或许是很有裨益的。毕竟，这个星球上数以百万计的人们，无论是在办公室、茶馆，还是在沙漠，都和你一样，正撇开日常琐事的烦恼，享受只有茶才能带来的片刻清新和宁静。前言P.06</p><p>&nbsp;</p><p>2.漫漫数百年中，微不入眼的茶在诸多人类历史的十字路口扮演了略带神秘色彩的、匪夷所思的角色。在波士顿倾茶这一独幕剧中，茶只不过是个道具。这一舶来的暴利商品不经意间却成了统治者强加于劳苦大众的苛政、贪婪和不公的象征。P.188</p><h5 class=\"customH5\"><br><span style=\"color:hsl(0,0%,60%);\">注：上述页码为句子在实体书中所在的页码。</span><br>&nbsp;</h5><div class=\"swipe-box\"><div class=\"swipe-content\"><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;1669688476e1018d3c95ea95f7eddceecdbe08ad75ztb2fw\"></figure><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;166968848117757d2aa8355b8485d233a8a14bbce05r6200\"></figure><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;1669688486d8706907977337cdce9139cc5ccdc124igkcxk\"></figure><figure class=\"image image_resized\" style=\"width:75%;\"><img src=\"image://image/png;1669688491b532cc8a9a16d9cf2b3325565409887c10t8cw\"></figure></div></div><p>&nbsp;</p><h5 class=\"customH5\" style=\"text-align:center;\"><span style=\"color:hsl(0,0%,60%);\">滑动可查看更多知识点~</span></h5><p>&nbsp;</p><figure class=\"image\"><img src=\"image://image/png;1670829814034f1978bebd67410bc0afcd06c2972bzm3f6d\"></figure>";
        if (con.contains("精 彩 选 段") && con.contains("注：上述页码为句子在实体书中所在的页码。")) {
            try {
                String intros1 = con.substring(con.indexOf("精 彩 选 段") + 7, con.indexOf("注：上述页码为句子在实体书中所在的页码。"));
                String[] split2 = intros.split("<p>");
                for (String s : split1) {
                    try {
                        String s1 = s.replaceAll("\\r|\\n", "").replaceAll("<p></p>", "").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "")
                                .replaceAll("[(/>)<]", "");
                        if (!StringUtils.isBlank(s1)) {
                            System.out.println("s1:"+s1);
                            if (s1.contains("P.")) {
                                String[] split22 = s1.split("P.");
                                System.out.println("split22[0]:"+split22[0]);
                                if (split2.length > 1) {
                                    System.out.println("split22[0]:"+split22[1]);
                                }
                            } else {
                                System.out.println("s1:"+s1);
                            }
                        }
                    } catch (Exception e) {
                        logger.warn("365精彩片段异常:{}", e.getMessage());
                    }
                }
            } catch (Exception e) {
                logger.warn("365精彩片段异常:{}", e);
            }
        }

        String str111="1.2.3.尊重彼此，认可每个人都是独一无二、有价值的个体，是建立一段稳固、相爱的关系的基础。你得到对方的珍视和重视，并且你也以同样的方式待人。P.209";
        String s1 = str111.replaceAll("\\r|\\n", "").replaceAll("<p></p>", "").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "")
                .replaceAll("[(/>)<]", "")
                .replace("1.","")
                .replace("2.","")
                .replace("3.","")
                .replace("4.","");
        System.out.println(s1);

        Integer aa=3;
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        if (integers.contains(aa)){
            integers.remove(aa);
        }
        System.out.println(integers);
        Date dateStartTime1 =
                new DateTime(DateUtil.parse("2024-01-01 00:02:00 000")).minusMonths(1).dayOfMonth().withMinimumValue()
                        .withTimeAtStartOfDay().millisOfDay().withMinimumValue().toDate();
        Date dateEndtTime2 =
                new DateTime(DateUtil.parse("2024-01-01 00:02:00 000")).minusMonths(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay()
                        .millisOfDay().withMaximumValue().toDate();

        Date dateStartTime = DateTime.now().minusMonths(1).dayOfMonth().withMinimumValue().withTimeAtStartOfDay().millisOfDay().withMinimumValue().toDate();
        Date dateEndTime = DateTime.now().minusMonths(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay().millisOfDay().withMaximumValue().toDate();

        System.out.println(DateUtil.dateToString(dateStartTime1,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.dateToString(dateEndtTime2,"yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateUtil.dateToString(dateStartTime,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.dateToString(dateEndTime,"yyyy-MM-dd HH:mm:ss"));
    }

    private static void screenList(Boolean i) {
        if (Boolean.FALSE.equals(i)){
            System.out.println(false);
        }
        return;
    }
    private static boolean registerYearSource(String source) {
        if (!StringUtil.isEmpty(source) && Objects.equals(source,"registerYear")){
            return true;
        }
        return false;
    }

}
