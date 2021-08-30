package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/4/26
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/4/26       hanliangliang     新增              1001
 ********************************************************************/
public class BeanCommonUtils {
    private static Logger logger = LoggerFactory.getLogger(BeanCommonUtils.class);

    public BeanCommonUtils() {
    }

    public static final <T> T copyProperties(Object src, Class<T> destClz) {
        if (src == null) {
            return null;
        } else if (src instanceof String) {
            return JSON.parseObject(src.toString(), destClz);
        } else {
            try {
                Object dest = destClz.newInstance();
                BeanUtils.copyProperties(src, dest);
                return (T) dest;
            } catch (IllegalAccessException | InstantiationException var4) {
                logger.error(" bean 克隆出错！");
                return null;
            }
        }
    }

    public static final <T> List<T> copyListWithNull(List<?> list, Class<T> dest) {
        if (list == null) {
            return null;
        } else {
            List<T> result = new ArrayList();
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Object o = var3.next();
                T rt = copyProperties(o, dest);
                result.add(rt);
            }

            return result;
        }
    }

    public static final <T> List<T> copyList(List<?> list, Class<T> dest) {
        if (list == null) {
            return null;
        } else {
            List<T> result = new ArrayList();
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Object o = var3.next();
                if (null != o) {
                    T rt = copyProperties(o, dest);
                    result.add(rt);
                }
            }

            return result;
        }
    }


    public static void main(String[] args) {
        User addResourceBO=new User();
        List<String> classifyIds=Arrays.asList("1","2");
        addResourceBO.setList(classifyIds);
        User editResourceBO = BeanCommonUtils.copyProperties(addResourceBO, User.class);
        System.out.println(editResourceBO);
    }
}
