package com.dousnl.domain.builder;


/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/1/19
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/1/19       hanliangliang     新增              1001
 ********************************************************************/
public class OutClass {

    public InnerIn destionation(String str){
        class PDestionation implements InnerIn{
            private String label;
            private PDestionation(String whereTo){
                label = whereTo;
            }
            public String readLabel(){
                return label;
            }
        }
        return new PDestionation(str);
    }

    public static void main(String[] args) {
        OutClass parcel5 = new OutClass();
        InnerIn d = parcel5.destionation("chenssy");
    }
}
