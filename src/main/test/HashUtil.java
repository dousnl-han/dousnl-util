/**
 * Created by William on 2018/11/13.
 */

import java.util.zip.CRC32;

/**
 * <br>
 * Description: HashUtil<br>
 * Company    : 上海黄豆网络科技有限公司 <br>
 * Author     : WangLei<br>
 * Date       : 2018/11/13 16:16<br>
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号<br>
 * v1.0.0      2018/11/13             WangLei         新增              1001<br>
 ********************************************************************/
public class HashUtil {

    /**
     * 对字符串做hash
     * @param s key
     * @return hash结果
     */
    public static Integer string2Hash(String s){
        CRC32 crc32 = new CRC32();
        crc32.update(s.getBytes());
        return Math.toIntExact(crc32.getValue() % 1000);
    }
    
    
    public static void main(String[] args) {
        System.out.println(string2Hash("418329052"));
    }

}
