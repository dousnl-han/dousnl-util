package com.dousnl.facebook;

import com.facebook.ads.sdk.*;

import java.util.Arrays;


public class FacebookInsightsFetcher {
    public static void main(String[] args) throws APIException {
        // 初始化API上下文
        APIContext context = new APIContext("EAATRqGuZCEC4BPuWsYoTke2GyFZAr9JvsWOMHGNAD9raSx4ZBdLJaGHB6P4T40RtCOs7lks8VcohkvfaeTW9agCeVSfTJDGAxBlZBTEFDfoZA1O2jtZCA3TsJaeFZBjdvUgcyZA9czNc0OJkAwqnCmEbMR9j9fxpQ2LV77pLhQArewr9PYhzqkYaAF46DI2yFbCzOiA73z8THUF8DIKk1yclhR7hHY9uqqAl")
                .enableDebug(true)
                .setLogger(System.out);

        // 指定广告账户
        AdAccount account = new AdAccount("act_123456789", context);

        // 构建查询参数
        APINodeList<AdsInsights> insights = account.getInsights()
                .setDatePreset("last_30d")
                .setLevel(AdsInsights.EnumLevel.VALUE_CAMPAIGN)
                .setFields(Arrays.asList(
                        "campaign_name", "campaign_id",
                        "spend", "impressions", "clicks",
                        "cpc", "ctr", "actions"
                ))
                .requestAllFields()
                .execute();

        // 处理返回数据
        for (AdsInsights insight : insights) {
            System.out.println("Campaign: " + insight.getFieldCampaignName());
            System.out.println("Spend: " + insight.getFieldSpend());
            System.out.println("Impressions: " + insight.getFieldImpressions());
            System.out.println("----------------------");
        }
    }
}
