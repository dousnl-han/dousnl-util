/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/24 18:06
 */
public class PaixuTest {

    public static void main(String[] args) {
        int [] arr={3,4,1,2,5,9,0,7};

        System.out.println("排序前");
        for (int i=0;i<arr.length;i++){
            System.out.print(" "+arr[i]);
        }
        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                if (arr[i]>arr[j]){
                    arr[i]=arr[i]+arr[j];
                    arr[j]=arr[i]-arr[j];
                    arr[i]=arr[i]-arr[j];
                }
            }
        }
        System.out.println();
        System.out.println("排序后");
        for (int i=0;i<arr.length;i++){
            System.out.print(" "+arr[i]);
        }
    }
}
