import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/12/30 13:31
 */
public class StreamToMap {

    public static void main(String[] args) {
        List<String> list=Lists.newArrayList("11","22","33","11","22");
        List<String> collect = list.stream().map(String::toString).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }
}
