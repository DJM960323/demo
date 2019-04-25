package com.example.demo.exam;

import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * Created by Administrator on 2019/2/16.
 * @author
 */
public interface Mapper<T>  extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        MySqlMapper<T>,
        tk.mybatis.mapper.common.Mapper<T>, Marker{
}
