/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/1/13
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/1/13       hanliangliang     新增              1001
 ********************************************************************/
public class Html2Text {

    public static void main(String[] args) {
        String content="<p>作者将男孩成长过程分为三个显著的阶段，讨论了睾丸激素对男孩心理状态的影响，分享了他们对男孩大脑结构的新发现，揭示了父亲和母亲在男孩成长中的作用并提供了相应的建议，分析运动对男孩的意义以及不利的影响，提出了对男孩性教育的有益做法。当然，这本书的核心是帮助父母如何让男孩健康地成为一个男人，你从中可以学习到很多的做法。</p>\\r\\n\\r\\n<p>这本书的结构化程度其实不高，而且存在一些对男孩问题的夸大，也许它描述的问题在您的孩子身上并不存在，但提供的警示却足够值得借鉴。</p>\\r\\n\\r\\n<p>（本段为<strong>《养育男孩》</strong>音频第1段，想了解更多内容，请点击本页面右上角封面图进行获取）</p>";
        content = content.replaceAll("\\r|\\n", "").replaceAll("</p>", "\r\n").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
        //String s2 = HtmlUtils.html2Text(content);
        //System.out.println(s2);
        System.out.println(content);
    }
}
