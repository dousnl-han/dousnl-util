import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/18 11:06
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private Integer money;
    private Boolean status;
}
