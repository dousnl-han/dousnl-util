package com.dousnl.component;

import com.dousnl.component.chanjar.WxType;
import java.io.IOException;
import java.util.Map;

/**
 * 微信模板接口
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 11:40
 */
public interface WxMpService {
    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
     *
     * @param postData 请求参数json值
     * @param url      请求接口地址
     * @return 接口响应字符串
     * @throws WxErrorException 异常
     */
    String post(WxMpApiUrl url, String postData) throws WxErrorException;

    /**
     * 获取WxMpConfigStorage 对象.
     *
     * @return WxMpConfigStorage
     */
    WxMpConfigStorage getWxMpConfigStorage();

    /**
     * <pre>
     * Service没有实现某个API的时候，可以用这个，
     * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
     * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
     * </pre>
     *
     * @param data     参数数据
     * @param url      接口地址
     * @return 结果
     * @throws WxErrorException 异常
     */
    <T, E> T execute(String url, E data) throws WxErrorException;

    /**
     * <pre>
     * 获取access_token，本方法线程安全.
     * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
     *
     * 另：本service的所有方法都会在access_token过期时调用此方法
     *
     * 程序员在非必要情况下尽量不要主动调用此方法
     *
     * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
     * </pre>
     *
     * @param forceRefresh 是否强制刷新
     * @return token
     * @throws WxErrorException .
     */
    String getAccessToken(boolean forceRefresh) throws WxErrorException;


    <T, E> T doExecute(String uri, E postEntity, WxType wxType) throws IOException, WxErrorException;

    /**
     * 设置 {@link WxMpConfigStorage} 的实现. 兼容老版本
     *
     * @param wxConfigProvider .
     */
    void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider);

    /**
     * 注入多个 {@link WxMpConfigStorage} 的实现. 并为每个 {@link WxMpConfigStorage} 赋予不同的 {@link String label} 值
     *
     * @param configStorages WxMpConfigStorage map
     * @param defaultMpId    设置一个{@link WxMpConfigStorage} 所对应的{@link String mpId}进行Http初始化
     */
    void setMultiConfigStorages(Map<String, WxMpConfigStorage> configStorages, String defaultMpId);
}
