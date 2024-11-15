package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.Homework;
import com.core.entity.Message;
import com.core.entity.Student;
import com.core.mapper.MessageMapper;
import com.core.mapper.StudentMapper;
import com.core.service.MessageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 通知消息信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:31
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private StudentMapper studentMapper;


    /**
     * 查询通知消息信息
     *
     * @param id 通知消息信息主键
     * @return 通知消息信息
     */
    @Override
    public Message selectMessageById(Long id) {
        return messageMapper.selectMessageById(id);
    }

    /**
     * 分页查询通知消息信息列表
     *
     * @param message 通知消息信息
     * @return 通知消息信息
     */
    @Override
    public IPage<Message> selectMessagePage(Message message) {
        IPage<Message> iPage = new Page<>(message.getPage(), message.getPageSize());
        LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<>(message);
        lambdaQueryWrapper.orderByDesc(Message::getCreateTime);
        return messageMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询通知消息信息列表
     *
     * @param message 通知消息信息
     * @return 通知消息信息
     */
    @Override
    public List<Message> selectMessageList(Message message) {
        return messageMapper.selectMessageList(message);
    }

    /**
     *  查询是否有未读消息
     *
     * @param message
     * @return
     */
    @Override
    public Boolean hasNoRead(Message message) {
        message.setIsRead("未读");
        List<Message> messages = messageMapper.selectMessageList(message);
        return CollectionUtils.isNotEmpty(messages);
    }

    /**
     * 新增通知消息信息
     *
     * @param message 通知消息信息
     * @return 结果
     */
    @Override
    public void insertMessage(Message message) {
        // 获取所有需要发送通知的学生
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Student::getClazzId, Arrays.asList(message.getClazzIds().split(",")));
        List<Student> students = studentMapper.selectList(lambdaQueryWrapper);
        for (Student studentDb : students) {
            //循环所有学生信息，给学生发布发布通知
            message.setUserId(studentDb.getUserId());
            message.setIsRead("未读");
            message.setSendTime(new Date());
            message.setCreateTime(new Date());
            messageMapper.insertMessage(message);
        }
    }

    /**
     * 修改通知消息信息
     *
     * @param message 通知消息信息
     * @return 结果
     */
    @Override
    public void updateMessage(Message message) {
        messageMapper.updateMessage(message);
    }

    /**
     * 批量删除通知消息信息
     *
     * @param ids 需要删除的通知消息信息主键
     * @return 结果
     */
    @Override
    public void deleteMessageByIds(Long[] ids) {
        messageMapper.deleteMessageByIds(ids);
    }

    /**
     * 删除通知消息信息信息
     *
     * @param id 通知消息信息主键
     * @return 结果
     */
    @Override
    public void deleteMessageById(Long id) {
        messageMapper.deleteMessageById(id);
    }

}
