/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/25 20:44
 */
public class ShuSuTest {
    public static void main(String[] args) {

        for (int i=101;i<200;i++){
            boolean flag=true;
            for (int j=2;j<i;j++){
                if (i%j==0){
                    flag=false;
                    break;
                }
            }
            if (flag==true){
                System.out.println(""+i);
            }
        }
    }
}
