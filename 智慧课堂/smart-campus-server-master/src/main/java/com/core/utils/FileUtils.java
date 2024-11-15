package com.core.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtils {

    @Value("${file.upload.path}")
    private String uploadPathValue;

    @Value("${file.view.path}")
    private String viewPathValue;

    private static String uploadPath;

    private static String viewPath;

    @PostConstruct
    public void init() {
        uploadPath = this.uploadPathValue;
        viewPath = this.viewPathValue;
    }

    public static String upload(MultipartFile[] file) {
        try {
            File uploadFile = new File(uploadPath + File.separator);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            String fileOriginalName = file[0].getOriginalFilename();
            if (StringUtils.isNotBlank(fileOriginalName) && fileOriginalName.indexOf(".") > 0) {
                String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
                File saveFile = new File(uploadFile.getAbsolutePath() + File.separator + fileName + extName);
                file[0].transferTo(saveFile);
                return viewPath + fileName + extName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }


}
