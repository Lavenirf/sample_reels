package com.core.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CommonService {

    String uploadFile(MultipartFile[] file);

}
