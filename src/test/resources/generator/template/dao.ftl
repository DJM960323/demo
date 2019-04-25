package ${basePackage}.dao;

import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ${basePackage}.entity.${modelNameUpperCamel};
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* 数据处理层
* @author ${author}
* @date ${date}
*/
@Component
public class ${modelNameUpperCamel}Dao  {

    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

   /**
    * 查询列表（未删除数据）
    *
    * @return 列表数据
    */
    public List<${modelNameUpperCamel}> selectAll() {
        Example example = new Example(${modelNameUpperCamel}.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",0);
        return ${modelNameLowerCamel}Mapper.selectByExample(example);
    }

   /**
    * 根据主键id查看详情（未删除数据）
    *
    * @param model 实体类
    * @return 实体类
    */
    public ${modelNameUpperCamel} getOne(${modelNameUpperCamel} model) {
        Example example = new Example(${modelNameUpperCamel}.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",0);
        criteria.andEqualTo("id",model.getId());
        List<${modelNameUpperCamel}> dto = ${modelNameLowerCamel}Mapper.selectByExample(example);
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
    public void insertSelective(${modelNameUpperCamel} model) {
        ${modelNameLowerCamel}Mapper.insertSelective(model);
    }

   /**
    * 根据主键id修改不为null的值
    *
    * @param model 请求参数
    */
    public void updateByPrimaryKeySelective(${modelNameUpperCamel} model) {
        ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(model);
    }
}
