package com.core.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;

import com.core.entity.User;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.core.entity.Message;
import com.core.service.MessageService;

/**
 * 通知消息信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:31
 */
@Api(tags = "通知消息信息控制层")
@RestController
@RequestMapping("/api/message")
public class MessageController extends BaseController {

    @Resource
    private MessageService messageService;

    /**
     * 获取通知消息信息详细信息
     */
    @ApiOperation(value = "获取通知消息信息详细信息", notes = "获取通知消息信息详细信息", response = Message.class)
    @GetMapping(value = "/{id}")
    public Message getInfo(@PathVariable("id") Long id) {
        return messageService.selectMessageById(id);
    }

    /**
     * 分页查询通知消息信息列表
     */
    @ApiOperation(value = "分页查询通知消息信息列表", notes = "分页查询通知消息信息列表", response = Message.class)
    @GetMapping("/page")
    public IPage<Message> page(Message message) {
        User userByToken = getUserByToken();
        message.setUserId(userByToken.getId());
        IPage<Message> page = messageService.selectMessagePage(message);
        return page;
    }

    /**
     * 查询通知消息信息列表
     */
    @ApiOperation(value = "查询通知消息信息列表", notes = "查询通知消息信息列表", response = Message.class)
    @GetMapping("/list")
    public List<Message> list(Message message) {
        User userByToken = getUserByToken();
        message.setUserId(userByToken.getId());
        List<Message> list = messageService.selectMessageList(message);
        return list;
    }

    /**
     * 查询是否有未读消息
     */
    @ApiOperation(value = "查询是否有未读消息", notes = "查询是否有未读消息", response = Message.class)
    @GetMapping("/has/no/read")
    public Boolean hasNoRead(Message message) {
        User userByToken = getUserByToken();
        message.setUserId(userByToken.getId());
        return messageService.hasNoRead(message);
    }

    /**
     * 新增通知消息信息
     */
    @ApiOperation(value = "新增通知消息信息", notes = "新增通知消息信息")
    @PostMapping
    public void add(@RequestBody Message message) {
         messageService.insertMessage(message);
    }

    /**
     * 修改通知消息信息
     */
    @ApiOperation(value = "修改通知消息信息", notes = "修改通知消息信息")
    @PutMapping
    public void edit(@RequestBody Message message) {
        messageService.updateMessage(message);
    }

    /**
     * 删除通知消息信息
     */
    @ApiOperation(value = "删除通知消息信息", notes = "删除通知消息信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        messageService.deleteMessageByIds(ids);
    }

}
