package com.ruoyi.web.controller.gk;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gk.domain.*;
import com.ruoyi.gk.domain.vo.PopularSchoolVo;
import com.ruoyi.gk.domain.vo.ProfessionallyRecommendedVo;
import com.ruoyi.gk.domain.vo.RecommendVo;
import com.ruoyi.gk.domain.vo.SchoolFractionVo;
import com.ruoyi.gk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学校Controller
 *
 * @author cyz
 * @date 2024-07-16
 */
@RestController
@RequestMapping("/gk/schools")
public class SchoolsController extends BaseController {
    @Autowired
    private ICollegesService collegesService;

    @Autowired
    private ISchoolsService schoolsService;

    @Autowired
    private ISchoolScoresService schoolScoresService;

    @Autowired
    private ISchoolMajorScoresService schoolMajorScoresService;

    @Autowired
    private RedisCache redisCache;



    /**
     * 查询热门学校
     */
    @Anonymous
    @GetMapping("/popular")
    public RestBean<List<PopularSchoolVo>> getPopularSchool(@RequestParam(value = "local",defaultValue = "") String local){
        return RestBean.Success(schoolsService.getPopularSchool(local));
    }

    @Anonymous
    @GetMapping("/pushdata")
    public void Push_data_into_memory() {
        List<SchoolMajorScores> schoolMajorScores = schoolMajorScoresService.selectSchoolMajorScoresList(null);
        for(SchoolMajorScores score : schoolMajorScores){
//"school_id:"+score.getSchoolId()+"local_province_name:"+score.getLocalProvinceName()+"level2_name:"+score.getLevel2Name()+
            String key = "school_major_score:" + score.getMcid(); // 使用 mcid 作为 Redis 的键
            redisCache.setCacheObject(key, score);
        }
       // schoolMajorScoreRedis.cacheSchoolMajorScore();
    }

    @Anonymous
    @GetMapping("/getdata")
    public Collection<String> Get_data_into_memory() {
        String key = "school_id:170local_province_name:吉林level2_name:工学*" ;

        return redisCache.keys(key);
    }
    /**
     * 批量查询学校1739944
     */
    @Anonymous
    @GetMapping("/ids")
    public RestBean<List<PopularSchoolVo>> getSchoolByIds(@RequestParam(value = "ids") List<Integer> ids){
        return RestBean.Success(schoolsService.selectSchoolsByIds(ids));
    }
    @PostMapping("/specialized")
    public RestBean<ProfessionallyRecommendedVo> Recommendedspecialized(@RequestBody RecommendVo recommendVo) {

        try {
            ProfessionallyRecommendedVo future = schoolScoresService.selectSpecializedListAsync(recommendVo);
            return RestBean.Success(future);
        } catch (Exception e) {
            return RestBean.Error(-1,"获取专业列表失败：" + e.getMessage());
        }
    }
    @PostMapping("/recommend")
    public AjaxResult RecommendedInstitutions(@RequestBody RecommendVo recommendVo) {
        collegesService.deleteCollegesByUserId((long) recommendVo.getUserId());
        List<SchoolScores> schoolScores = schoolScoresService.selectPosition(recommendVo);
        List<Schools> schools = schoolsService.selectSchoolsList(null);
        List<RecommendedInstitutions> recommendedInstitutions = new ArrayList<>();
        double number = 0;
        for (Schools school : schools) {
            List<SchoolScores> relatedScores = new ArrayList<>();
            for (SchoolScores schoolScore : schoolScores) {

                if (schoolScore.getSchoolId().equals(school.getSchoolId())) {
                    relatedScores.add(schoolScore);
                    if (schoolScore.getMinSection() >= recommendVo.getPosition()) {
                        number += 1.0;
                    }

                }
            }

            // 创建RecommendedInstitutions实例并设置属性
            RecommendedInstitutions institution = new RecommendedInstitutions();
            institution.setAcceptanceRate(number / relatedScores.toArray().length);

            institution.setName(school.getName());
            institution.setId(school.getId());
            institution.setNumber(relatedScores.toArray().length);

            //institution.setScores(relatedScores);
            if (!relatedScores.isEmpty() && number != 0) {
                recommendedInstitutions.add(institution);
               /* SchoolMajorScores majorScore = new SchoolMajorScores();
                majorScore.setSchoolId(school.getSchoolId());
                List<SchoolMajorScores> schoolMajorScores = schoolMajorScoresService.selectSchoolMajorScoresList(majorScore);
                System.out.println(schoolMajorScores);*/
                Colleges college = new Colleges();


                college.setAcceptanceRate(BigDecimal.valueOf(0));
                college.setRecommendation(BigDecimal.valueOf(institution.getAcceptanceRate()));
                //college.setScores(relatedScores.toString());
                college.setName(school.getName());
                college.setShoolId(school.getSchoolId());
                college.setUserId((long) recommendVo.getUserId());
                college.setNumber((long) number);
               // collegesService.saveCollegeToRedis(college);
                collegesService.insertColleges(college);
            }
            number = 0;
        }


        return success(recommendedInstitutions);
    }


    /**
     * 查询学校列表
     */
    @Anonymous
    @GetMapping("/list")
    public RestBean<TableDataInfo> list(@RequestParam(value = "localName",defaultValue = "山东") String localName, @RequestParam(value = "typeName", defaultValue = "") String typeName,
                                        @RequestParam(value = "levelName", defaultValue = "") String levelName,@RequestParam(value = "name",defaultValue = "") String name,
                                        @RequestParam(value = "year",defaultValue = "2023") Integer year,@RequestParam(value = "provinceName",defaultValue = "") String provinceName) {
        startPage();
        List<SchoolFractionVo> list = schoolsService.selectSchoolsListByLocal(localName, typeName, levelName,name,year,provinceName);
        return RestBean.Success(getDataTable(list));
    }
    /**
     * 查询学校列表
     */
    @Anonymous
    @GetMapping("/search")
    public RestBean<List<PopularSchoolVo>> search(@RequestParam(value = "name") String name) {
        return RestBean.Success(schoolsService.selectSchoolsListByName(name));
    }
    /**
     * 导出学校列表
     */

    @Log(title = "学校", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Schools schools) {
        List<Schools> list = schoolsService.selectSchoolsList(schools);
        ExcelUtil<Schools> util = new ExcelUtil<>(Schools.class);
        util.exportExcel(response, list, "学校数据");
    }
    @Anonymous
    @PostMapping("/batch")
    public RestBean<Map<String,String>> getImages(@RequestBody List<String> imageIds) {
        Map<String,String> imageBase64 = new HashMap<>();
        for (String id : imageIds) {
            try {
                Resource resource = new ClassPathResource("/static/schoolLogo/" + id + ".jpg");
                InputStream inputStream = resource.getInputStream();
                byte[] imageBytes = inputStream.readAllBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                imageBase64.put(id,"data:image/jpeg;base64," + base64Image);
            } catch (IOException e) {
                return RestBean.Error(404,"");
            }
        }
        return RestBean.Success(imageBase64);
    }


    /**
     * 获取学校详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(schoolsService.selectSchoolsById(id));
    }

    /**
     * 新增学校
     */

    @Log(title = "学校", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Schools schools) {
        return toAjax(schoolsService.insertSchools(schools));
    }

    /**
     * 修改学校
     */

    @Log(title = "学校", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Schools schools) {
        return toAjax(schoolsService.updateSchools(schools));
    }

    /**
     * 删除学校
     */

    @Log(title = "学校", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(schoolsService.deleteSchoolsByIds(ids));
    }
}
