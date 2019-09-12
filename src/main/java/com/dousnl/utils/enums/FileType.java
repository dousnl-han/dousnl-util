package com.dousnl.utils.enums;


import com.dousnl.utils.execl.ImportUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liukai
 */
public enum FileType {

    XLS_DOC("d0cf11e0a1b11ae100"),
    XLSX_DOCX("504b001400000"),
    XLSX_WPS("504b00000000");

    private String value;

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static boolean checkExcelType(MultipartFile excelFile) throws IOException {
        //验证文件类型是否Excel
        InputStream input = excelFile.getInputStream();
        String fileType = ImportUtils.getFileConent(input);
        if (!fileType.equals(FileType.XLS_DOC.getValue()) && !fileType.equals(FileType.XLSX_DOCX.getValue()) && !fileType.equals(FileType.XLSX_WPS.getValue())) {
            return false;
        } else {
            String fileName = excelFile.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf("."));
            if (!ext.contains("xls")) {
                return false;
            }
        }
        return true;
    }
}
