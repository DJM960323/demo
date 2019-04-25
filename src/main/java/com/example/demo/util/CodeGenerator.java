package com.example.demo.util;

import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。 完成开发后请请去处
 *
 * @author WangKeSen
 */
public class CodeGenerator {

    /**
     * 项目基础包名称，根据自己的项目修改
     */
    public static final String BASE_PACKAGE = "com.example.demo.exam";

    /*生成文件地址配置*/

    /**
     * Mapper插件基础接口的完全限定名(第二步提到的核心继承接口Mapper)
     * MyBatis Mapper插件接口
     */
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".Mapper";

    /**
     * 数据库配置
     */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/base?serverTimezone=GMT%2B8&useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";


    /**
     * 项目在硬盘上的基础路径
     */
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    /**
     * 模板位置
     */
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";
    /**
     * java文件路径
     */
    private static final String JAVA_PATH = "/src/main/java";

    /**
     * 作者名称
     */
    private static final String AUTHOR = "邓建明";
    /**
     * 时间
     */
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    /**
     * main函数入口,放入表名运行即可生成代码
     */
    public static void main(String[] args) {
//       genCode("news");
//        genCodeByCustomModelName("t_user","User");
        genCode("message");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames 数据表名称...
     */
    private static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    private static void genCodeByCustomModelName(String tableName, String modelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName);
        getDao(tableName, modelName);

    }


    private static void genModelAndMapper(String tableName, String modelName) {

        String modelPackage = BASE_PACKAGE  + ".entity";
        String mapperPackage = BASE_PACKAGE  + ".mapper";

        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(modelPackage);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage(mapperPackage);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(mapperPackage);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtils.isNotEmpty(modelName)) {
            tableConfiguration.setDomainObjectName(modelName);
        }
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            DefaultShellCallback callback = new DefaultShellCallback(true);
            warnings = new ArrayList<>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) {
            modelName = tableNameConvertUpperCamel(tableName);
        }
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    private static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage",BASE_PACKAGE);

            //取出 service 抽象类
           /* String name = packageConvertPath(BASE_PACKAGE + "." + tableNameConvertMappingPath(tableName) + ".service");

            File file = new File(PROJECT_PATH + JAVA_PATH + name + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");*/

            String namei = packageConvertPath(BASE_PACKAGE +".service");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + namei + modelNameUpperCamel + "Service.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    private static void getDao(String tableName, String modelName) {
        try {
            String name = packageConvertPath(BASE_PACKAGE  + ".dao");
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            File file = new File(PROJECT_PATH + JAVA_PATH + name + modelNameUpperCamel + "Dao.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            getData(tableName, modelNameUpperCamel, file, "dao.ftl");
            System.out.println(modelNameUpperCamel + "Dao.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Dao失败", e);
        }
    }

    private static void genController(String tableName, String modelName) {
        try {
            String name = packageConvertPath(BASE_PACKAGE  + ".api");
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            File file = new File(PROJECT_PATH + JAVA_PATH + name + modelNameUpperCamel + "Api.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            getData(tableName, modelNameUpperCamel, file, "controller.ftl");
            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }
    }

    /**
     * 填充模版数据
     *
     * @param tableName           表名
     * @param modelNameUpperCamel 类名
     * @param name                模版名称
     * @return 填充的数据
     */
    private static Map<String, Object> getData(String tableName, String modelNameUpperCamel, File file, String name) throws Exception {
        freemarker.template.Configuration cfg = getConfiguration();
        Map<String, Object> data = new HashMap<>();
        data.put("date", DATE);
        data.put("author", AUTHOR);
        data.put("baseRequestMapping", tableNameConvertMappingPath(tableName));
        data.put("modelNameUpperCamel", modelNameUpperCamel);
        data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
        data.put("basePackage",BASE_PACKAGE);
        cfg.getTemplate(name).process(data, new FileWriter(file));
        return data;
    }


    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    /**
     * 表名 转换成 url sys_role == /sysRole
     *
     * @param tableName 表名
     * @return url访问路径名
     */
    private static String tableNameConvertMappingPath(String tableName) {
        //兼容使用大写的表名
        tableName = tableName.toLowerCase();
        String[] str = tableName.split("_");
        StringBuilder s = new StringBuilder();
        s.append("/");
        String substring = "";
        String a = "";
        for (int i = 0; i < str.length; i++) {
            if (i > 0) {
                a = str[i];
                substring = str[i].substring(0, 1).toUpperCase() + str[i].substring(1, a.length());
            } else {
                substring += str[i];
            }
            s.append(substring);
        }
        return s.toString();
    }

    private static String tableNameConvertMappingPathS(String tableName) {
        //兼容使用大写的表名
        tableName = tableName.toLowerCase();
        String[] str = tableName.split("_");
        StringBuilder s = new StringBuilder();
        String substring = "";
        String a = "";
        for (int i = 0; i < str.length; i++) {
            if (i > 0) {
                a = str[i];
                substring = str[i].substring(0, 1).toUpperCase() + str[i].substring(1, a.length());
            } else {
                substring += str[i];
            }
            s.append(substring);
        }
        return s.toString();
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}