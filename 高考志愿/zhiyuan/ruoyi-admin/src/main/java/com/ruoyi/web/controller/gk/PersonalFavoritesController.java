package com.ruoyi.web.controller.gk;

import com.ruoyi.common.core.domain.CodeState;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.PersonalFavorites;
import com.ruoyi.gk.domain.vo.CollectVo;
import com.ruoyi.gk.service.IPersonalFavoritesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;

/**
 * 个人收藏Controller
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
@RestController
@RequestMapping("/gk/favorites")
public class PersonalFavoritesController extends BaseController
{
    @Autowired
    private IPersonalFavoritesService personalFavoritesService;

    /**
     * 获取个人收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:favorites:query')")
    @GetMapping(value = "/{id}")
    public RestBean<CollectVo> getInfo(@PathVariable("id") Long id)
    {
        return RestBean.Success(personalFavoritesService.selectPersonalFavoritesById(id));
    }

    /**
     * 新增个人收藏
     */
    @PreAuthorize("@ss.hasPermi('gk:favorites:add')")
    @Log(title = "个人收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public RestBean<?> add(@RequestBody PersonalFavorites personalFavorites)
    {
        if (personalFavoritesService.insertPersonalFavorites(personalFavorites)==1){
            return RestBean.Success(CodeState.SUCCESS);
        }else {
            return RestBean.Error(CodeState.INSERT_ERROR);
        }

    }

    /**
     * 删除个人收藏
     */
    @PreAuthorize("@ss.hasPermi('gk:favorites:remove')")
    @Log(title = "个人收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public RestBean<?> remove(@PathVariable Long id)
    {
       if(personalFavoritesService.deletePersonalFavoritesById(id)==1){
           return RestBean.Success(CodeState.SUCCESS);
       }else{
           return RestBean.Error(CodeState.DELETE_ERROR);
       }

    }
}
