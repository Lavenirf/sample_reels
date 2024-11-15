package com.core.service.impl;

import com.core.service.CommonService;
import com.core.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public String uploadFile(MultipartFile[] file) {
        return FileUtils.upload(file);
    }

}
