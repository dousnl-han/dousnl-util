import com.alibaba.fastjson.JSON;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/7 16:22
 */
public class AnnontionTypeTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Set set= Sets.newLinkedHashSet();
        set.add(1);set.add(2);
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
        while(al==50){
            System.out.println("第"+al+"次循环");
            il++;
            if (il==2){
                al--;
            }
        };

    }
}
