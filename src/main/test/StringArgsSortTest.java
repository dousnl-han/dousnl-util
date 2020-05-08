import java.util.Arrays;

/**
 * 数组的几种排序方式
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/25 10:34
 */
public class StringArgsSortTest {

    public static void main(String[] args) {

        int [] arr={5,4,2,1,9,0,6,3,10,-1};
        //第一种
        Arrays.sort(arr);
        //System.out.println(Arrays.toString(arr));
        //第二种--冒泡排序
        //maoPao(arr);
        //maoPao2(arr);
        //第三种--选择排序
        //xuanZe(arr);
        //第四种--反转排序
        fanZuan(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void fanZuan(int[] arr) {
        for(int i=0;i<arr.length/2;i++) {
            int temp=arr[i];
            arr[i]=arr[arr.length-1-i];
            arr[arr.length-1-i]=temp;
        }
    }

    private static void xuanZe(int[] arr) {
        for (int i=0;i<arr.length;i++){
            int temp=i;
            for (int j=i+1;j<arr.length;j++){
                if (arr[temp]>arr[j]){
                    temp=j;
                }
            }
            int temp1=arr[i];
            arr[i]=arr[temp];
            arr[temp]=temp1;
        }
    }

    private static void maoPao2(int[] arr) {
        for (int i=0;i<arr.length;i++){
            int temp=0;
            for (int j=i+1;j<arr.length;j++){
                if (arr[i]>arr[j]){
                    temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;

                }
            }
        }
    }

    private static void maoPao(int[] arr) {

        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                if (arr[i]>arr[j]){
                    arr[i]=arr[i]+arr[j];
                    arr[j]=arr[i]-arr[j];
                    arr[i]=arr[i]-arr[j];

                }
            }
        }
    }
}
