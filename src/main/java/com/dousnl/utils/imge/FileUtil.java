package com.dousnl.utils.imge;

import com.alibaba.fastjson.JSON;
import com.dousnl.utils.JsonMsgBean;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/8/7 15:36
 */
public class FileUtil {

    //电池回收合同文件类型
    private static final List<String> conractExtName = Arrays.asList("PDF", "DOC" ,"DOCX");

    /**
     * idms系统-上传图片自动压缩格式
     */
    public static final List<String> IDMS_IMG_COMPRESS = Arrays.asList("JPG", "PNG");

    /**
     * 是否是支持的文件类型
     *
     * @param fileExtName
     * @return
     */
    public static JsonMsgBean isSupportFile(String fileExtName) {
        if (conractExtName.contains(fileExtName.toUpperCase())) {
            return new JsonMsgBean(true);
        } else {
            return new JsonMsgBean(false, null, "不支持的文件格式，有效格式为" + JSON.toJSONString(conractExtName));
        }
    }
}
