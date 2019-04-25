package ${basePackage}.api;

import ${basePackage}.Page;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
* API控制层

* @author ${author}
* @date ${date}
*/
@RestController
@CrossOrigin
@RequestMapping("${baseRequestMapping}")
@Api(value = "${modelNameUpperCamel}接口", description = "${author}")
public class ${modelNameUpperCamel}Api {

    @Autowired
    private ${modelNameUpperCamel}Service services;

    @ApiOperation(value = "查询列表")
    @PostMapping("/getList")
    public PageInfo<${modelNameUpperCamel}> getList(@RequestBody(required = false) Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<${modelNameUpperCamel}> list =  services.selectAll();
        return new PageInfo<>(list);
    }

    @ApiOperation(value = "根据id查看详情")
    @PostMapping(value = "/get")
    public ${modelNameUpperCamel} getOne(@RequestBody ${modelNameUpperCamel} model) {
        return services.getOne(model);
    }

    @ApiOperation(value = "保存一个实体，属性不为null的值")
    @PostMapping(value = "/add")
    public void addOne(@RequestBody ${modelNameUpperCamel} model) {
        services.insertSelective(model);
    }

    @ApiOperation(value = "根据主键id修改不为null的值")
    @PostMapping(value = "/edit")
    public void editOne(@RequestBody ${modelNameUpperCamel} model) {
        services.updateByPrimaryKeySelective(model);
    }
}
