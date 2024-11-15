package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.ClazzRoomSeat;
import com.core.mapper.ClazzRoomSeatMapper;
import com.core.service.ClazzRoomSeatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教室座位信息业务层处理
 *
 * @author zjp
 * @date 2023-07-25 20:16:59
 */
@Service
public class ClazzRoomSeatServiceImpl implements ClazzRoomSeatService {

    @Resource
    private ClazzRoomSeatMapper clazzRoomSeatMapper;

    /**
     * 查询教室座位信息
     *
     * @param id 教室座位信息主键
     * @return 教室座位信息
     */
    @Override
    public ClazzRoomSeat selectClazzRoomSeatById(Long id) {
        return clazzRoomSeatMapper.selectClazzRoomSeatById(id);
    }

    /**
     * 分页查询教室座位信息列表
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 教室座位信息
     */
    @Override
    public IPage<ClazzRoomSeat> selectClazzRoomSeatPage(ClazzRoomSeat clazzRoomSeat) {
        IPage<ClazzRoomSeat> iPage = new Page<>(clazzRoomSeat.getPage(), clazzRoomSeat.getPageSize());
        LambdaQueryWrapper<ClazzRoomSeat> lambdaQueryWrapper = new LambdaQueryWrapper<>(clazzRoomSeat);
        lambdaQueryWrapper.orderByDesc(ClazzRoomSeat::getCreateTime);
        return clazzRoomSeatMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询教室座位信息列表
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 教室座位信息
     */
    @Override
    public List<ClazzRoomSeat> selectClazzRoomSeatList(ClazzRoomSeat clazzRoomSeat) {
        return clazzRoomSeatMapper.selectClazzRoomSeatList(clazzRoomSeat);
    }

    /**
     *  查询教室座位信息Map
     *
     * @param clazzRoomSeat
     * @return
     */
    @Override
    public Map<String, ClazzRoomSeat> selectClazzRoomSeatMap(ClazzRoomSeat clazzRoomSeat) {
        Map<String, ClazzRoomSeat> resultMap = new HashMap<>();
        List<ClazzRoomSeat> clazzRoomSeats = clazzRoomSeatMapper.selectClazzRoomSeatList(clazzRoomSeat);
        for (ClazzRoomSeat clazzRoomSeatDb : clazzRoomSeats) {
            resultMap.put(clazzRoomSeatDb.getRowNum() + "-" + clazzRoomSeatDb.getColNum(), clazzRoomSeatDb);
        }
        return resultMap;
    }

    /**
     * 新增教室座位信息
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 结果
     */
    @Override
    public void insertClazzRoomSeat(ClazzRoomSeat clazzRoomSeat) {
        clazzRoomSeat.setCreateTime(new Date());
        clazzRoomSeatMapper.insertClazzRoomSeat(clazzRoomSeat);
    }

    /**
     * 修改教室座位信息
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 结果
     */
    @Override
    public void updateClazzRoomSeat(ClazzRoomSeat clazzRoomSeat) {
        clazzRoomSeatMapper.updateClazzRoomSeat(clazzRoomSeat);
    }

    /**
     * 批量删除教室座位信息
     *
     * @param ids 需要删除的教室座位信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzRoomSeatByIds(Long[] ids) {
        clazzRoomSeatMapper.deleteClazzRoomSeatByIds(ids);
    }

    /**
     * 删除教室座位信息信息
     *
     * @param id 教室座位信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzRoomSeatById(Long id) {
        clazzRoomSeatMapper.deleteClazzRoomSeatById(id);
    }

}
