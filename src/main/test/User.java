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
@Data
@NoArgsConstructor
public class User {
    private String name;
    private int age;
    private Integer money;
    private Boolean status;
    private Integer num;
    private long aaa;

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

    public User(String name, int age, Integer money, Boolean status) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.status = status;
    }

    public User(String name, int age, Integer money, Boolean status, Integer num) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.status = status;
        this.num = num;
    }
}
