package com.ruoyi.web.controller.gk;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.CodeState;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/baidu")
public class BaiDuApiController {
    //查询区的学校
    @GetMapping("/selectSchools")
    public RestBean<?> findSchools(@RequestParam("address") String address,@RequestParam("key") String key) throws UnsupportedEncodingException {
        String url = "https://restapi.amap.com/v3/place/text";
        String parma ="types=141201&city="+ address+"&key="+key;
        String s = HttpUtils.sendGet(url, parma);
        JSONObject parsed = JSON.parseObject(s);
        return RestBean.Success(parsed);
    }
    //查询区的范围
    @GetMapping("/selectCounty")
    public RestBean<?> findCounty(@RequestParam("address") String address, @RequestParam("ak") String ak) throws UnsupportedEncodingException {
        System.out.println(address+"address");
        String url = "https://api.map.baidu.com/geocoding/v3";
        String parma ="address="+ address+"&output=json&ak="+ak;
        String s = HttpUtils.sendGet(url, parma);
        JSONObject parsed = JSON.parseObject(s);
        System.out.println(parsed+"ooo");
        if (parsed==null){
            return RestBean.Error(CodeState.APIError);
        }
        return RestBean.Success(parsed);
    }
    //查询省以及一下两级的名称
    @GetMapping("/selectDistrictName")
    public RestBean<?> findDistrictName(@RequestParam("address") String address, @RequestParam("ak") String ak) throws UnsupportedEncodingException {
        System.out.println(address+"address");
        String url = "https://api.map.baidu.com/api_region_search/v1";
        String parma ="keyword="+ address+"&sub_admin=1&ak="+ak;
        String s = HttpUtils.sendGet(url, parma);
        JSONObject parsed = JSON.parseObject(s);
        if (parsed==null){
            return RestBean.Error(CodeState.APIError);
        }
        return RestBean.Success(parsed);
    }
    //查询区代码
    @GetMapping("/selectAdCode")
    public RestBean<?> selectAdCode(@RequestParam("address") String address, @RequestParam("key") String key) throws UnsupportedEncodingException {
        System.out.println(address+"address");
        String url = "https://restapi.amap.com/v3/config/district";
        String parma ="keywords="+ address+"&key="+key;
        String s = HttpUtils.sendGet(url, parma);
        JSONObject parsed = JSON.parseObject(s);
        if (parsed == null || !"1".equals(parsed.getString("status"))) {
            return RestBean.Error(CodeState.APIError);
        }
        JSONArray districts = parsed.getJSONArray("districts");
        if (districts == null || districts.isEmpty()) {
            return RestBean.Error(CodeState.APIError);
        }
        JSONObject district = districts.getJSONObject(0);
        String districtName = district.getString("name");
        String adcode = district.getString("adcode");

        JSONObject result = new JSONObject();
        result.put("districtName", districtName);
        result.put("adcode", adcode);

        return RestBean.Success(result);
    }
}
