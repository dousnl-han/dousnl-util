import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/1 11:44
 */
public class StringUtilJoinTest {

    public static void main(String[] args) {
        String join = StringUtils.join("a1", "a2");
        System.out.println(join);

        String join1 = StringUtils.join(new Object[]{"a1","a2"},"|");
        System.out.println(join1);

        Integer dectrcAid=1,did=0;
        if (dectrcAid==0 || (did=5)==0){
            System.out.println("did:"+did);
        }
        System.out.println("did:"+did);
    }
}
