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
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private Integer money;
    private Boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getMoney() {
        return (money==null || money==0)? null:money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
