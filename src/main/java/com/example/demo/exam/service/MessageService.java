package com.example.demo.exam.service;

import com.example.demo.exam.dao.MessageDao;
import com.example.demo.exam.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 业务处理层
* @author 邓建明
* @date 2019/04/25
*/
@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

   /**
    * 查询列表（未删除数据）
    *
    * @return 列表数据
    */
   public List<Message> selectAll() {
        return messageDao.selectAll();
    }

   /**
    * 根据主键id查看详情（未删除数据）
    *
    * @param model 实体类
    * @return 实体类
    */
    public Message getOne(Message model) {
        return messageDao.getOne(model);
    }

   /**
    * 保存一个实体，属性不为null的值
    *
    * @param model 实体类
    */
    public void insertSelective(Message model) {
//        model.setCreateTime(new Date());
//        model.setUpdateTime(new Date());
//        model.setStatus(0);
         messageDao.insertSelective(model);
    }

   /**
    * 根据主键id修改不为null的值
    *
    * @param model 请求参数
    */
    public void updateByPrimaryKeySelective(Message model) {
//        model.setUpdateTime(new Date());
         messageDao.updateByPrimaryKeySelective(model);
    }
}
