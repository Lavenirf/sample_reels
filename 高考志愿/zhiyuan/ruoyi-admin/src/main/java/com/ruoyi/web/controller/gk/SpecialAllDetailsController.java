package com.ruoyi.web.controller.gk;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.SchoolSpecial;
import com.ruoyi.gk.domain.vo.SpecialDetailsVo;
import com.ruoyi.gk.service.ISpecialAllDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruoyi.common.core.controller.BaseController;

import java.util.List;

/**
 * 专业详情Controller
 * 
 * @author ruoyi
 * @date 2024-06-29
 */
@RestController
@RequestMapping("/gk/specialDetails")
public class SpecialAllDetailsController extends BaseController
{
    @Autowired
    private ISpecialAllDetailsService specialAllDetailsService;

    /**
     * 获取专业详情详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}")
    public RestBean<SpecialDetailsVo> getInfo(@PathVariable("id") Long id)
    {
        return RestBean.Success(specialAllDetailsService.selectSpecialAllDetailsById(id));
    }


    /**
     *  搜索专业
     */
    @Anonymous
    @GetMapping("/search")
    public RestBean<List<SchoolSpecial>> getSpecial(@RequestParam("name") String name)
    {
        return RestBean.Success(specialAllDetailsService.selectSpecialByName(name));
    }


}
