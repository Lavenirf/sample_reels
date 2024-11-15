package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.Message;

/**
 * 通知消息信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:31
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询通知消息信息
     *
     * @param id 通知消息信息主键
     * @return 通知消息信息
     */
    Message selectMessageById(Long id);

    /**
     * 查询通知消息信息列表
     *
     * @param message 通知消息信息
     * @return 通知消息信息集合
     */
    List<Message> selectMessageList(Message message);

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
     * 删除通知消息信息
     *
     * @param id 通知消息信息主键
     * @return 结果
     */
    void deleteMessageById(Long id);

    /**
     * 批量删除通知消息信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteMessageByIds(Long[] ids);
}
