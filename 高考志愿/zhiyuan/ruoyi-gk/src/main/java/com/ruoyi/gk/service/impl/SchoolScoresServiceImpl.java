package com.ruoyi.gk.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gk.domain.*;
import com.ruoyi.gk.domain.vo.ProfessionallyRecommendedVo;
import com.ruoyi.gk.domain.vo.RecommendVo;
import com.ruoyi.gk.mapper.RecommendedMajorsMapper;
import com.ruoyi.gk.mapper.SchoolMajorScoresMapper;
import com.ruoyi.gk.service.ICollegesService;
import com.ruoyi.gk.service.ISchoolMajorScoresService;
import com.ruoyi.gk.service.ISchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.SchoolScoresMapper;
import com.ruoyi.gk.service.ISchoolScoresService;

/**
 * 学校录取分数线Service业务层处理
 *
 * @author cyz
 * @date 2024-07-16
 */
@Service
public class SchoolScoresServiceImpl implements ISchoolScoresService
{

    @Autowired
    private ISchoolScoresService schoolScoresService;

    @Autowired
    private SchoolScoresMapper schoolScoresMapper;

    @Autowired
    private RecommendedMajorsMapper recommendedMajorsMapper;

    @Autowired
    private ICollegesService collegesService;

    @Autowired
    private ISchoolsService schoolsService;

    @Autowired
    private SchoolMajorScoresMapper schoolMajorScoresMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISchoolMajorScoresService schoolMajorScoresService;


    private Executor asyncExecutor;

    @Async("asyncExecutor")
    @Override
    public ProfessionallyRecommendedVo selectSpecializedListAsync(RecommendVo recommendVo) {
       // recommendedMajorsMapper.delectCollegesByuserId((long) recommendVo.getUserId());
        List<specialized> specializedList = new ArrayList<>();
        Colleges colleges1 = new Colleges();
        colleges1.setUserId(SecurityUtils.getLoginUser().getUserId());
        List<Colleges> colleges = new ArrayList<>();


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
               // collegesService.insertColleges(college);
                colleges.add(college);
            }
            number = 0;
        }









        // 收集所有查询条件
        List<Long> schoolIds = colleges.stream()
                .map(Colleges::getShoolId)
                .collect(Collectors.toList());

        // 一次性批量查询
        List<SchoolMajorScores> allSchoolMajorScores = schoolMajorScoresMapper
                .selectSchoolMajorScoresListBySchoolIds(schoolIds, recommendVo.getLocalProvinceName(), recommendVo.getLevel2name());

        // 按SchoolId分组
        Map<Long, List<SchoolMajorScores>> schoolIdToScoresMap = allSchoolMajorScores.stream()
                .collect(Collectors.groupingBy(SchoolMajorScores::getSchoolId));

        // 处理每个college的结果
        for (Colleges college : colleges) {
            List<SchoolMajorScores> schoolMajorScores1 = schoolIdToScoresMap.get(college.getShoolId());
            if (schoolMajorScores1 != null && !schoolMajorScores1.isEmpty()) {
                specialized specializeds = new specialized();
                specializeds.setSchoolMajorScores(schoolMajorScores1);
                specializeds.setName(college.getName());
                specializedList.add(specializeds);
            }
        }

        Map<Long, List<SchoolMajorScores>> groupedScores = new HashMap<>();
        for (specialized specialized : specializedList) {
            List<SchoolMajorScores> scores1 = specialized.getSchoolMajorScores();
            if (scores1 != null && !scores1.isEmpty()) {
                // 使用Stream API对schoolMajorScores按specialId分组
                scores1.stream()
                        .collect(Collectors.groupingBy(SchoolMajorScores::getSpecialId))
                        .forEach((specialId, scoreGroup) -> {
                            // 合并分组结果到总的groupedScores映射中
                            groupedScores.computeIfAbsent(specialId, k -> new ArrayList<>()).addAll(scoreGroup);
                        });
            }
        }

        List<SchoolGroup> schoolGroups = new ArrayList<>();
        List<RecommendedMajors> recommendedMajors1 = new ArrayList<>();
        Map<Long, List<SchoolMajorScores>> schoolIdGroup = specializedList.stream()
                .flatMap(specialized -> specialized.getSchoolMajorScores().stream())
                .collect(Collectors.groupingBy(SchoolMajorScores::getSchoolId));

        for (Map.Entry<Long, List<SchoolMajorScores>> entry : schoolIdGroup.entrySet()) {
            Long schoolId = entry.getKey();
            String schoolName = entry.getValue().get(0).getName();
            long mcid =  entry.getValue().get(0).getMcid();
            Map<Long, List<SpecialDetail>> specialDetailsMap = entry.getValue().stream()
                    .collect(Collectors.groupingBy(
                            SchoolMajorScores::getSpecialId,
                            Collectors.mapping(sms -> {
                                // 创建SpecialDetail对象并填充信息
                                double probability1 = 0;

                                return new SpecialDetail(sms.getSpecialId(), sms,probability1); // 可以根据需要添加更多字段
                            }, Collectors.toList())));

            SchoolGroup schoolGroup = new SchoolGroup();
            schoolGroup.setSchoolId(schoolId);
            schoolGroup.setSchoolName(schoolName);
            schoolGroup.setMcid(mcid);
            // 转换Map<Long, List<SpecialDetail>>为List<Map.Entry<Long, List<SpecialDetail>>>以便于后续处理或存储
            List<Map.Entry<Long, List<SpecialDetail>>> specialIdEntries = new ArrayList<>(specialDetailsMap.entrySet());
            schoolGroup.setSpecialIdEntries(specialIdEntries); // 假设SchoolGroup有一个setSpecialDetails方法来设置这个列表
            schoolGroups.add(schoolGroup);
        }

        double num = 0;

        for (SchoolGroup schoolGroup :schoolGroups) {
            for (Map.Entry<Long, List<SpecialDetail>> entry : schoolGroup.getSpecialIdEntries()) {
                Long specialId = entry.getKey(); // 获取特殊ID
                List<SpecialDetail> details = entry.getValue(); // 获取该特殊ID下的所有SpecialDetail列表
                Long zhuanye =null;
                String zhuanyemingcheng = "";
                Long fenduan = 0L;
                int year = 0;
                long mcid = 0;
                for (SpecialDetail detail : details) {
                    // 打印或处理每个SpecialDetail的信息
                    zhuanyemingcheng = detail.getSchoolMajorScores().getSpname().toString();
                    fenduan = detail.getSchoolMajorScores().getSpType();
                    zhuanye=detail.getSchoolMajorScores().getSchoolId();
                    year = Math.toIntExact(detail.getSchoolMajorScores().getYear());
                    mcid = detail.getSchoolMajorScores().getMcid();
                    if(detail.getSchoolMajorScores().getMinSection()>recommendVo.getPosition()){
                        num++;
                    }

                }
                double gl = num/(double)details.size();
                Random random = new Random();
                if (gl== 1){
                    gl  = gl - random.nextDouble()*0.1;
                }else if (gl>0.1) {
                    gl = gl - random.nextDouble()*0.1;
                }
                num = 0;
                RecommendedMajors recommendedMajors = new RecommendedMajors();

                recommendedMajors.setSchoolName(schoolGroup.getSchoolName());
                recommendedMajors.setSpecialId(specialId);
                recommendedMajors.setGlRatio(gl);
                recommendedMajors.setSublevel(fenduan);
                recommendedMajors.setSchoolId(zhuanye);
                recommendedMajors.setSpecialName(zhuanyemingcheng);
                recommendedMajors.setYear(year);
                recommendedMajors.setMcid(mcid);
                recommendedMajors.setUserId((long) recommendVo.getUserId());

                zhuanyemingcheng = "";
                zhuanye=null;
                //if(recommendedMajors.getGlRatio()>0.1)
                recommendedMajors1.add(recommendedMajors);


               // recommendedMajorsMapper.insertRecommendedMajors(recommendedMajors);
            }

        }

        recommendedMajors1.sort(null);
        ProfessionallyRecommendedVo professionallyRecommendedVo = new ProfessionallyRecommendedVo();
        for (RecommendedMajors recommendedMajors :recommendedMajors1){
            if(recommendedMajors.getYear()>2022){
                if (recommendedMajors.getGlRatio()>0.7){
                    professionallyRecommendedVo.getBao().add(recommendedMajors);
                }
                if (recommendedMajors.getGlRatio()<0.4 && recommendedMajors.getGlRatio()>0.05){
                    professionallyRecommendedVo.getChong().add(recommendedMajors);
                }
                if (recommendedMajors.getGlRatio()<0.7 && recommendedMajors.getGlRatio()>=0.4){
                    professionallyRecommendedVo.getWen().add(recommendedMajors);
                }
            }

        }
        professionallyRecommendedVo.setRecommendedMajors(recommendedMajors1);
        //System.out.println(recommendedMajors1);
        return professionallyRecommendedVo;
    }


    public double probability(RecommendVo recommendVo, List<SchoolMajorScores> schoolMajorScores) {
        double num = 0;
        for (SchoolMajorScores score : schoolMajorScores) {
            if(recommendVo.getPosition()<score.getMinSection())
                num++;
        }
        return num/schoolMajorScores.size();
    }

    @Override
    public List<SchoolScores> selectPosition(RecommendVo recommendVo) {
        return schoolScoresMapper.selectPosition(recommendVo);
    }

    /**
     * 查询学校录取分数线
     *
     * @param id 学校录取分数线主键
     * @return 学校录取分数线
     */
    @Override
    public SchoolScores selectSchoolScoresById(Long id)
    {
        return schoolScoresMapper.selectSchoolScoresById(id);
    }

    /**
     * 查询学校录取分数线列表
     *
     * @param schoolScores 学校录取分数线
     * @return 学校录取分数线
     */
    @Override
    public List<SchoolScores> selectSchoolScoresList(SchoolScores schoolScores)
    {
        return schoolScoresMapper.selectSchoolScoresList(schoolScores);
    }

    /**
     * 新增学校录取分数线
     *
     * @param schoolScores 学校录取分数线
     * @return 结果
     */
    @Override
    public int insertSchoolScores(SchoolScores schoolScores)
    {
        return schoolScoresMapper.insertSchoolScores(schoolScores);
    }

    /**
     * 修改学校录取分数线
     *
     * @param schoolScores 学校录取分数线
     * @return 结果
     */
    @Override
    public int updateSchoolScores(SchoolScores schoolScores)
    {
        return schoolScoresMapper.updateSchoolScores(schoolScores);
    }

    /**
     * 批量删除学校录取分数线
     *
     *
     * @param ids 需要删除的学校录取分数线主键
     * @return 结果
     */
    @Override
    public int deleteSchoolScoresByIds(Long[] ids)
    {
        return schoolScoresMapper.deleteSchoolScoresByIds(ids);
    }

    /**
     * 删除学校录取分数线信息
     *
     * @param id 学校录取分数线主键
     * @return 结果
     */
    @Override
    public int deleteSchoolScoresById(Long id)
    {
        return schoolScoresMapper.deleteSchoolScoresById(id);
    }
}
