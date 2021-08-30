package com.dousnl.utils;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/5/17
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/5/17       hanliangliang     新增              1001
 ********************************************************************/

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 业务回调
 *
 * @author 今日头条号「JAVA前线」
 *
 */
public interface RedisBizCall {
    /**
     * 业务回调方法
     *
     * @return 序列化后数据值
     */
    String call();
}
/**
 * 安全缓存管理器
 *
 * @author 今天头条号「JAVA前线」
 *
 */
//@Service
//public class SafeRedisManager {
//    private static Logger logger = LoggerFactory.getLogger(SafeRedisManager.class);
//    @Resource
//    private RedisClient RedisClient;
//    @Resource
//    private RedisLockManager redisLockManager;
//    public String getDataSafe(String key, int lockExpireSeconds, int dataExpireSeconds, RedisBizCall bizCall, boolean alwaysRetry) {
//        boolean getLockSuccess = false;
//        try {
//            while(true) {
//                String value = redisClient.get(key);
//                if (StringUtils.isNotEmpty(value)) {
//                    return value;
//                }
//                /** 竞争分布式锁 **/
//                if (getLockSuccess = redisLockManager.tryLock(key, lockExpireSeconds)) {
//                    value = redisClient.get(key);
//                    if (StringUtils.isNotEmpty(value)) {
//                        return value;
//                    }
//                    /** 查询数据库 **/
//                    value = bizCall.call();
//                    /** 数据库无数据则返回**/
//                    if (StringUtils.isEmpty(value)) {
//                        return null;
//                    }
//                    /** 数据存入缓存 **/
//                    redisClient.setex(key, dataExpireSeconds, value);
//                    return value;
//                } else {
//                    if (!alwaysRetry) {
//                        logger.warn("竞争分布式锁失败,key={}", key);
//                        return null;
//                    }
//                    Thread.sleep(100L);
//                    logger.warn("尝试重新获取数据,key={}", key);
//                }
//            }
//        } catch (Exception ex) {
//            logger.error("getDistributeSafeError", ex);
//            return null;
//        } finally {
//            if (getLockSuccess) {
//                redisLockManager.unLock(key);
//            }
//        }
//    }
//}
