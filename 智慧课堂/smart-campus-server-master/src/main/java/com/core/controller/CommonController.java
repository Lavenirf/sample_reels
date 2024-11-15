package com.core.controller;

import com.core.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Api(tags = "公共模块")
@RestController
@RequestMapping("/api/common")
public class CommonController extends BaseController {

    @Resource
    private CommonService commonService;

    /**
     * 上传文件
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("/file")
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile[] file) {
        return commonService.uploadFile(file);
    }

}
