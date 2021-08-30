import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/8 19:17
 */
public class sss {

    public static void main(String[] args) {
        User user=new User();
        Optional<User> user1 = Optional.of(user);

        System.out.println(user1.map(User::getAge).orElse(1));
        System.out.println(user1.map(User::getName).orElse("1"));
        List<BannerBO> banner= JSON.parseArray("[{\"bannerId\":108071,\"score\":1.9332882165908813,\"sort\":1},{\"bannerId\":106512,\"score\":1.9332882165908813,\"sort\":2},{\"bannerId\":108041,\"score\":1.8903146982192993,\"sort\":3},{\"bannerId\":108072,\"score\":1.8903146982192993,\"sort\":4},{\"bannerId\":108073,\"score\":1.859147548675537,\"sort\":5},{\"bannerId\":108051,\"score\":1.7570786476135254,\"sort\":6},{\"bannerId\":107974,\"score\":1.7570786476135254,\"sort\":7},{\"bannerId\":108052,\"score\":1.7105618715286255,\"sort\":8},{\"bannerId\":108053,\"score\":1.6933988332748413,\"sort\":9},{\"bannerId\":106530,\"score\":1.3678251504898071,\"sort\":10}]",BannerBO.class);

        List<Integer> bannerIds = banner.stream().map(BannerBO::getBannerId).collect(Collectors.toList());
        System.out.println(bannerIds);

        List<Integer> bannerArray = Arrays.asList(106512, 106513, 106514, 108071, 108052, 108053, 106530, 108041, 108072, 108073, 108051, 107974);
        List bannerSort=new ArrayList();
        for (Integer id:bannerIds){
            if (bannerArray.contains(id)){
                bannerSort.add(id);
            }
        }

        System.out.println(bannerSort);

        List<Integer> collect = bannerArray.stream().filter(o -> bannerIds.contains(o)).collect(Collectors.toList());

        System.out.println(collect);
    }
}

 class BannerBO {
    private Integer bannerId;
    private Integer sort;

    public BannerBO() {
    }

    public Integer getBannerId() {
        return this.bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
