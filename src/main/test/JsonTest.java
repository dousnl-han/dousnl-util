import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/5
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/5       hll    新增              1001
 ********************************************************************/
public class JsonTest {


    public static void main(String[] args) {
        System.out.println("insertTargetRule=======================================================");
        String json = "{insertType\": 1,\n" +
                "\t\t\t\t\t\t\t\t\"tableRule\": {\n" +
                "\t\t\t\t\t\t\t\t\t\"datasourceName\": \"${datasourceName}\",\n" +
                "\t\t\t\t\t\t\t\t\t\"tableName\": \"${tableName}\"\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t}";

        String dateSource = "sam";
        String table = "t_cms_menu";

        json = json.replace("${datasourceName}",dateSource);
        json = json.replace("${tableName}",table).replace("\t", "").replace("\n", "");
        System.out.println(JSON.toJSONString(json));
        System.out.println("insertParam=======================================================");
        String [] tableFieldArray = {"id","name","age"};
        InsertParam insertParam = new InsertParam();
        insertParam.setTableInsertDTO(Lists.newArrayList());
        for (String s : tableFieldArray) {
            TableInsertArray tableInsertArray = new TableInsertArray();
            tableInsertArray.setTableField(s);
            tableInsertArray.setInsertField(s);
            insertParam.getTableInsertDTO().add(tableInsertArray);
        }
        System.out.println(JSON.toJSONString(insertParam));
        System.out.println("onlyOneCheck=======================================================");
        String[] oneCheck = "[name/=,del_flag/=]：名称已存在：100001，[url/=,del_flag/=]：菜单url已存在：100001".split("，");
        System.out.println(JSON.toJSONString(oneCheck));
        //OnlyOneCheckRule onlyOneCheckRule = JSON.parseObject(oneCheck, OnlyOneCheckRule.class);

        OnlyOneCheckRule oneCheckRule = new OnlyOneCheckRule();
        oneCheckRule.setFields(Lists.newArrayList());
        oneCheckRule.setErrorCode("100005");
        oneCheckRule.setErrorMsg("名称已存在");

        OnlyOneCheck onlyOneCheck = new OnlyOneCheck();
        onlyOneCheck.setDatasourceName(dateSource);
        onlyOneCheck.setTableName(table);
        onlyOneCheck.setFields(Lists.newArrayList());


    }


    @Data
    public static class InsertParam {
        private List<TableInsertArray> tableInsertDTO;
    }

    @Data
    public static class TableInsertArray {
        private  String tableField;
        private String insertField;

    }

    @Data
    public static class OnlyOneCheck {
        private String datasourceName;
        private String tableName;
        private List<TableInsertArray> fields;
        private String errorCode;
        private String errorMsg;
    }

    @Data
    public static class OnlyOneCheckFieldArray {
        private  String tableField;
        private String insertField;
        private String linker;

    }

    @Data
    public static class OnlyOneCheckRule {
        private List<OnlyOneCheckRuleData> fields;
        private String errorCode;
        private String errorMsg;
    }
    @Data
    public static class OnlyOneCheckRuleData {
        private String tableField;
        private String checkField;
        private String linker;
    }
}


