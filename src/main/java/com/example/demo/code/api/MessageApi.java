package com.example.demo.code.api;

import com.example.demo.code.entity.Message;
import com.example.demo.code.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* API控制层

* @author 邓建明
* @date 2019/04/25
*/
@RestController
@CrossOrigin
@RequestMapping("/message")
@Api(value = "Message接口", description = "邓建明")
public class MessageApi {

    @Autowired
    private MessageService services;

//    @ApiOperation(value = "查询列表")
//    @PostMapping("/getList")
//    public PageInfo<Message> getList(@RequestBody(required = false) Page page) {
//        PageHelper.startPage(page.getPageNum(), page.getPageSize());
//        List<Message> list =  services.selectAll();
//        return new PageInfo<>(list);
//    }

    @ApiOperation(value = "根据id查看详情")
    @PostMapping(value = "/get")
    public Message getOne(@RequestBody Message model) {
        return services.getOne(model);
    }

    @ApiOperation(value = "保存一个实体，属性不为null的值")
    @PostMapping(value = "/add")
    public void addOne(@RequestBody Message model) {
        services.insertSelective(model);
    }

    @ApiOperation(value = "根据主键id修改不为null的值")
    @PostMapping(value = "/edit")
    public void editOne(@RequestBody Message model) {
        services.updateByPrimaryKeySelective(model);
    }
}
