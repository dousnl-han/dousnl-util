import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 私桩数据
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/16 15:47
 */
@Data
public class PrivatePile implements Serializable {

    private String deviceCode;//私桩编码
    private String provinceCode;//省(一级编码)
    private String provinceName;//省中文名
    private String cityCode;//市(二级编码)
    private String cityName;//市中文名
    private String areaCode;//区(三级编码)
    private String areaName;//区域中文名
    private BigDecimal privatePileLongitude;//私桩经度
    private BigDecimal privatePileLatitude;//私桩纬度

    public PrivatePile(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

}
