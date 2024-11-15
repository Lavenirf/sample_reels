package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Message;

/**
 * 通知消息信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:31
 */
public interface MessageService {

    /**
     * 查询通知消息信息
     *
     * @param id 通知消息信息主键
     * @return 通知消息信息
     */
     Message selectMessageById(Long id);

    /**
     * 分页查询通知消息信息列表
     *
     * @param message 通知消息信息
     * @return 通知消息信息集合
     */
    IPage<Message> selectMessagePage(Message message);

    /**
     * 查询通知消息信息列表
     *
     * @param message 通知消息信息
     * @return 通知消息信息集合
     */
    List<Message> selectMessageList(Message message);

    /**
     *  查询是否有未读消息
     *
     * @param message
     * @return
     */
    Boolean hasNoRead(Message message);

    /**
     * 新增通知消息信息
     *
     * @param message 通知消息信息
     * @return 结果
     */
    void insertMessage(Message message);

    /**
     * 修改通知消息信息
     *
     * @param message 通知消息信息
     * @return 结果
     */
    void updateMessage(Message message);

    /**
     * 批量删除通知消息信息
     *
     * @param ids 需要删除的通知消息信息主键集合
     * @return 结果
     */
    void deleteMessageByIds(Long[] ids);

    /**
     * 删除通知消息信息信息
     *
     * @param id 通知消息信息主键
     * @return 结果
     */
    void deleteMessageById(Long id);

}
