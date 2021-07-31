package com.dousnl.component.convert;

import com.dousnl.domain.wechat.WeChatData;
import com.dousnl.domain.wechat.WxTemplateData;
import com.dousnl.domain.wechat.WxTemplateMessage;
import lombok.Data;

/**
 * 365会期续费提醒
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/13 15:14
 */
@Data
public class RenewalsOf365 implements WxMsgConverter<WxTemplateMessage> {

    private String number;
    private String nickName;
    private String expireTime;
    private static final String FIRST_EXPIRE_NAME = "尊敬的会员，您的VIP已到期";
    private static final String FIRST_ABOUT_TO_EXPIRE_NAME = "尊敬的会员，您的VIP即将到期";
    private static final String REMARK = "打开樊登读书APP立即续费，继续畅听好书";

    private static final String COLOR = "#173177";

    public RenewalsOf365(String number, String nickName, String expireTime) {
        this.number = number;
        this.nickName = nickName;
        this.expireTime = expireTime;
    }

    @Override
    public void convert(WxTemplateMessage msg) {
        WxTemplateData data = new WxTemplateData();
        data.setFirst(new WeChatData("收到一条新订单", COLOR));
        data.setKeyword1(new WeChatData("测试商家", COLOR));
        data.setKeyword2(new WeChatData("13162505297", COLOR));
        data.setKeyword3(new WeChatData("AT000001", COLOR));
        data.setKeyword4(new WeChatData("成功", COLOR));
        data.setKeyword5(new WeChatData("100", COLOR));
        data.setRemark(new WeChatData("测试订单状态", COLOR));
        msg.setData(data);
    }

    public void convertAboutToExpire(WxTemplateMessage msg) {
        WxTemplateData data = new WxTemplateData();
        data.setFirst(new WeChatData(FIRST_ABOUT_TO_EXPIRE_NAME, COLOR));
        setCommonData(data);
        msg.setData(data);
    }

    public void convertExpire(WxTemplateMessage msg) {
        WxTemplateData data = new WxTemplateData();
        data.setFirst(new WeChatData(FIRST_EXPIRE_NAME, COLOR));
        setCommonData(data);
        msg.setData(data);
    }

    private void setCommonData(WxTemplateData data) {
        data.setKeyword1(new WeChatData(number, COLOR));
        data.setKeyword2(new WeChatData(nickName, COLOR));
        data.setKeyword3(new WeChatData(expireTime, COLOR));
        data.setRemark(new WeChatData(REMARK, COLOR));
    }

}
