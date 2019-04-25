package ${basePackage}.service;

import ${basePackage}.dao.${modelNameUpperCamel}Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${basePackage}.entity.${modelNameUpperCamel};

import java.util.List;
import java.util.Date;

/**
* 业务处理层
* @author ${author}
* @date ${date}
*/
@Service
public class ${modelNameUpperCamel}Service {

    @Autowired
    private ${modelNameUpperCamel}Dao ${modelNameLowerCamel}Dao;

   /**
    * 查询列表（未删除数据）
    *
    * @return 列表数据
    */
   public List<${modelNameUpperCamel}> selectAll() {
        return ${modelNameLowerCamel}Dao.selectAll();
    }

   /**
    * 根据主键id查看详情（未删除数据）
    *
    * @param model 实体类
    * @return 实体类
    */
    public ${modelNameUpperCamel} getOne(${modelNameUpperCamel} model) {
        return ${modelNameLowerCamel}Dao.getOne(model);
    }

   /**
    * 保存一个实体，属性不为null的值
    *
    * @param model 实体类
    */
    public void insertSelective(${modelNameUpperCamel} model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setStatus(0);
         ${modelNameLowerCamel}Dao.insertSelective(model);
    }

   /**
    * 根据主键id修改不为null的值
    *
    * @param model 请求参数
    */
    public void updateByPrimaryKeySelective(${modelNameUpperCamel} model) {
        model.setUpdateTime(new Date());
         ${modelNameLowerCamel}Dao.updateByPrimaryKeySelective(model);
    }
}
