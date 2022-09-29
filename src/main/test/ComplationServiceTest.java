import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/8/15
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/8/15       hanliangliang     新增              1001
 ********************************************************************/
public class ComplationServiceTest {

    private volatile static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ArrayList<Object> objects = Lists.newArrayList();
        int j = 1;
        for (; j <= 100; j++) {
            objects.add(j);
        }
        CompletionService service = new ExecutorCompletionService(Executors.newFixedThreadPool(4));
        int size = 10;
        List<List<Object>> partition = Lists.partition(objects, size);
        for (int i = 0; i < size; i++) {
            List<Object> objects1 = partition.get(i);
            service.submit(new Callable() {
                @Override public Object call() throws Exception {
                    //atomicInteger.incrementAndGet();
                    return objects1;
                }
            });
        }
        for (int i = 0; i < size; i++) {
            Object o = service.take().get();
            System.out.println(o);
        }
        ArrayList<Integer> param = Lists.newArrayList(1, 2, 3,4,5);

        List<String> send = send(param, e -> {
            List collect = (List) e.stream().map(String::valueOf).collect(Collectors.toList());
            return collect;
        });
        System.out.println(send);

    }

    private static <T,P> List<T> send(List<P> list,FuntionTest funtionTest) {
        List<List<P>> partition = Lists.partition(list, 2);
        List<T> result=new ArrayList<>();
        for (List<P> ps : partition) {
            List<T> list1 = funtionTest.getList(ps);
            result.addAll(list1);
        }
        return result;
    }

    @FunctionalInterface
    public interface FuntionTest<T,P>{
        List<T> getList(List<P> list);
    }

}
