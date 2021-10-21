package com.cxhit.mybatisplus;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * MP 代码生成器
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-09-01 0001 18:51
 */
public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入").append(tip).append("：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 执行此处
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 项目根目录
        // 这里注意，追加的是本项目的模块目录。如果是单项目，此行注释掉，更换成下一行
        String projectPath = System.getProperty("user.dir") + "/study-mybatis-plus";
//        String projectPath = System.getProperty("user.dir");

        // 1. 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                // TODO 代码生成的目录
                .setOutputDir(projectPath + "/src/main/java")
                .setAuthor("拾年之璐")       // TODO 修改项目作者
                .setOpen(false)             // 是否打开输出目录
                .setIdType(IdType.AUTO)     // 主键策略
                .setFileOverride(false)     // TODO 是否覆盖已有文件
                .setBaseResultMap(true)     // 是否开启 BaseResultMap（XML文件）
                .setBaseColumnList(true);   // 是否开启 baseColumnList（XML文件）
//                .setServiceName("");

        //把全局配置添加到代码生成器主类
        mpg.setGlobalConfig(globalConfig);

        // 2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig
                // 数据库链接URL
                .setUrl("jdbc:mysql://localhost:3306/db_study_mp?useUnicode=true&serverTimezone=UTC&useSSL=false&characterEncoding=utf8")
                // .setSchemaName("public")     //数据库 schema name
                .setDbType(DbType.MYSQL)        // 数据库类型
                // 数据库驱动，MySQL5.X：com.mysql.jdbc.Driver
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("chen")            // TODO 数据库用户名
                .setPassword("123456");         // TODO 数据库密码

        // 把数据源配置添加到代码生成器主类
        mpg.setDataSource(dsConfig);

        // 3. 包配置
        PackageConfig pkConfig = new PackageConfig();
        // 添加这个后 会以一个实体为一个模块 比如user实体会生成user模块 每个模块下都会生成三层
        pkConfig.setModuleName(scanner("模块名（系统端建议system，客户端建议client）"));
        // TODO 父包名。如果为空，将下面子包名必须写写完整的包路径。如果不为空，只需写子包名。
        pkConfig.setParent("com.cxhit.mybatisplus")
                // TODO 子包名
                .setEntity("entity")                // 实体类 Entity包名
                .setXml("mapper")                   // 持久层 Mapper.xml 包名
                .setMapper("mapper")                // 持久层 Mapper.java 包名
                .setService("service")              // 服务层 Service.java 包名
                .setServiceImpl("service.impl")     // 服务层 ServiceImpl.java 包名
                .setController("controller");       // 控制层 Controller.java 包名

        // 把包配置添加到代码生成器主类
        mpg.setPackageInfo(pkConfig);

        // 4. 自定义配置【这是MyBatisPlus 3.x 新增的】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名称，如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pkConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 5. 策略配置(数据库表配置)
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig
                .setNaming(NamingStrategy.underline_to_camel)           // 数据库表映射到实体的命名策略:下划线转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel)     // 数据库表字段映射到实体的命名策略:下划线转驼峰
                .setEntityLombokModel(false)                             // 实体是否为lombok模型（默认 false）
                // .setSuperEntityClass("com.zxdmy.wx.mp.wechat.common.BaseEntity")                 // 设置实体类的父类（如果启用，实体类会增加BaseEntity的继承）
                // .setSuperControllerClass("com.zxdmy.wx.mp.wechat.common.BaseController")         // 设置控制类的父类（如果启用，控制类会增加BaseController的继承）
                // 提示输入生成的表名
                .setInclude(scanner("表全名，多个英文名称，使用英文逗号间隔").split(","))
                // .setExclude("***")                                   // 需要排除的表名，允许正则表达式
                // .setSuperEntityColumns("id");                        // 实体类主键名称设置（如果启用，生成的实体类没有此字段）
                // .setTablePrefix(pc.getModuleName() + "_")            // 表前缀符（如果启用，生成的所有文件名没有此前缀）
                .setControllerMappingHyphenStyle(true);                 // 驼峰转连字符

        // 把数据库配置添加到代码生成器主类
        mpg.setStrategy(strategyConfig);

        // 在代码生成器主类上配置模板引擎,选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 开始执行
        mpg.execute();
    }
}
