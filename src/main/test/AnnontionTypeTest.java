import com.alibaba.fastjson.JSON;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/7 16:22
 */
public class AnnontionTypeTest {
    public static volatile boolean flag=false;

    public static void main(String[] args) throws UnsupportedEncodingException {
        Integer showLimit=0;
        if (Objects.equals(0, showLimit)) {
            System.out.println("showLimit");
        }
        if (!flag){
            flag=true;
        }
        System.out.println("flag:"+flag);
        System.out.println("json:"+JSON.toJSONString(null));
        Set set= new CopyOnWriteArraySet();
        set.add(1);set.add(2);set.add(3);
        Set set1= new HashSet();
        set1.add(1);set1.add(2);
        set.addAll(set1);

        System.out.println("set:"+set);
        Integer i=1;
        if (set.contains(i)){
            System.out.println(JSON.toJSONString(set));
            System.out.println(true);
        }
        String str="+13162505297";
        System.out.println(str.substring(0,5));
        String encode="";
        try {
            encode = URLEncoder.encode("https://card.dushu.io/app/auditNotification.html?auditType=1", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(encode);
        String decode = URLDecoder.decode(encode, "utf-8");
        System.out.println(decode);
        Calendar c = Calendar.getInstance();
        //c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND, 0);
        System.out.println(DateUtil.dateToString(c.getTime(),"yyyy-MM-HH hh:mm:ss"));

        //System.out.println(Arrays.asList(null));
        List<Integer> albumIds = Arrays.asList(1,3,4,5,6);
        List<Integer> configAlbumIds= Arrays.asList(1,2,3);
        Iterator<Integer> iterator = configAlbumIds.iterator();
        List<Integer> configAlbumIdsNew=new ArrayList<>();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (!albumIds.contains(next)){
                configAlbumIdsNew.add(next);
            }
        }
        System.out.println(configAlbumIdsNew);
        //configAlbumIds.removeAll(albumIds);
        System.out.println(c);
        int al=50;
        int il=0;
        Map<Integer,Integer> set12 =Maps.newHashMap();
        set12.put(1,2);
        set12.put(1,3);
        set12.put(1,4);
        System.out.println(set12);


        int[][] a1 = { { 1, 2 }, { 3, 4 } };
        int[][] a2 = { { 4, 3 }, { 2, 1 } };
        System.out.println(Arrays.deepToString(a1));// [[1, 2], [3, 4]]
        System.out.println(Arrays.deepToString(a2));// [[3, 4], [1, 2]]
        int [] a3={1,3};
        System.out.println("a1:"+a1.length);
        //System.out.println(Arrays.binarySearch(a1,a3));
        List<Set<Integer>> list=Lists.newArrayList();
        list.add(Sets.newHashSet(1,2));
        list.add(Sets.newHashSet(1,3));
        list.add(Sets.newHashSet(1,4));
        list.add(Sets.newHashSet(2,4));

        System.out.println("lists:"+JSON.toJSONString(list));
        //List<Set> sets = JSON.parseArray(JSON.toJSONString(list), Set.class);
        List<Set> sets = JSON.parseArray("[[1, 2], [1, 3], [1, 4], [2, 4]]", Set.class);
        System.out.println("sets:"+sets);

        Set set2=Sets.newHashSet(1,4);
        boolean flag=false;
        for (Set s : list) {
            if (s.size() != set2.size()) {
                System.out.println("不合法");
            }
            if (s.containsAll(set2)){
                flag=true;
                break;
            }
        }
        if (flag){
            System.out.println("合法");
        }
        if (!flag){
            System.out.println("不合法");
        }
        BigDecimal bigInteger = new BigDecimal(100);
        BigDecimal balanceBig = new BigDecimal("-156");
        BigDecimal decimal = balanceBig.divide(bigInteger);
        System.out.println(decimal);
    }
}
