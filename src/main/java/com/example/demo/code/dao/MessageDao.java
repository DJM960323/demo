package com.example.demo.code.dao;

import com.example.demo.code.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.code.entity.Message;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* 数据处理层
* @author 邓建明
* @date 2019/04/25
*/
@Component
public class MessageDao  {

    @Autowired
    private MessageMapper messageMapper;

   /**
    * 查询列表（未删除数据）
    *
    * @return 列表数据
    */
    public List<Message> selectAll() {
        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",0);
        return messageMapper.selectByExample(example);
    }

   /**
    * 根据主键id查看详情（未删除数据）
    *
    * @param model 实体类
    * @return 实体类
    */
    public Message getOne(Message model) {
        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",0);
        criteria.andEqualTo("id",model.getId());
        List<Message> dto = messageMapper.selectByExample(example);
            if (dto.size() > 0){
            return dto.get(0);
            }
         return null;
    }

   /**
    * 保存一个实体，属性不为null的值
    *
    * @param model 实体类
    */
    public void insertSelective(Message model) {
        messageMapper.insertSelective(model);
    }

   /**
    * 根据主键id修改不为null的值
    *
    * @param model 请求参数
    */
    public void updateByPrimaryKeySelective(Message model) {
        messageMapper.updateByPrimaryKeySelective(model);
    }
}
