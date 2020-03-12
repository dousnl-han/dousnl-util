package com.dousnl.utils.imge;

import com.dousnl.utils.JsonMsgBean;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * IDMS-上传图片自动压缩
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/25 11:00
 */
@Service
public class IdmsUploadFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdmsUploadFileService.class);

    public static final Integer IMG_FILE_SIZE = 5 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        long timeStart = System.currentTimeMillis();
        String imgLocalUrl = "E:\\tp\\zz.jpg";
        byte[] imgBytes = getByteByPic(imgLocalUrl);
        byte[] resultImg = compressPicForScale(imgBytes,800,"x");
        //new ByteArrayInputStream(resultImg);
        byte2image(resultImg,"E:\\tp\\yasuo.jpg");
        long timeEnd = System.currentTimeMillis();
        System.out.println("耗时："+ (timeEnd - timeStart));
    }
    //byte数组到图片
    public static void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    public JsonMsgBean uploadFile(MultipartFile file) throws IOException {
        String annexName = file.getOriginalFilename();
        String suffix = null;
        if (annexName.indexOf(".") > -1) {
            suffix = annexName.substring(annexName.lastIndexOf(".") + 1);
            //图片文件
            if (FileUtil.IDMS_IMG_COMPRESS.contains(suffix)) {
                if (file.getSize() > IMG_FILE_SIZE) {
                    //图片压缩
                    byte[] xes = compressPicForScale(getByteByPic(file), 800, "x");
                    return new JsonMsgBean(false, null, xes);
                }
            } else {
                //非图片文件，只效验大小，判断附件不能大于5M
                if (file.getSize() > IMG_FILE_SIZE) {
                    return new JsonMsgBean(false, null, "附件不能大于5M");
                }
            }
        } else {
            return new JsonMsgBean(false, null, "文件格式有误");
        }
        return new JsonMsgBean(true, null, file);
    }

    public static byte[] getByteByPic(MultipartFile file) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
        BufferedImage bm = ImageIO.read(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().length() - 3);
        ImageIO.write(bm, type, bos);
        bos.flush();
        byte[] data = bos.toByteArray();
        return data;
    }

    public static byte[] getByteByPic(String imageUrl) throws IOException {
        File imageFile = new File(imageUrl);
        InputStream inStream = new FileInputStream(imageFile);
        BufferedInputStream bis = new BufferedInputStream(inStream);
        BufferedImage bm = ImageIO.read(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String type = imageUrl.substring(imageUrl.length() - 3);
        ImageIO.write(bm, type, bos);
        bos.flush();
        byte[] data = bos.toByteArray();
        return data;
    }

    /**
     * 根据指定大小压缩图片
     *
     * @param imageBytes  源图片字节数组
     * @param desFileSize 指定图片大小，单位kb
     * @param imageId     影像编号
     * @return 压缩质量后的图片字节数组
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize, String imageId) {
        System.out.println("imageBytes.length:"+imageBytes.length);
        System.out.println("desFileSize:"+(desFileSize * 1024));
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try {
            while (imageBytes.length > desFileSize * 1024) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);

                Thumbnails.of(inputStream).scale(accuracy).outputQuality(accuracy).toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            LOGGER.info("【图log片压缩】imageId={} | 图片原大小={}kb | 压缩后大小={}kb", imageId, srcSize / 1024, imageBytes.length / 1024);
        } catch (Exception e) {
            LOGGER.error("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.85;
        } else if (size < 2047) {
            accuracy = 0.6;
        } else if (size < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }
}
