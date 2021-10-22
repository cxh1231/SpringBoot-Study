package com.cxhit.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 代码生成器（快速版本）
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-22 0022 16:51
 */
public class FastCodeGenerator {

    // 数据库连接字符
    private static final String URL = "jdbc:mysql://localhost:3306/db_study_mp?useUnicode=true&serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
    // 数据库用户名
    private static final String USERNAME = "chen";
    // 数据库密码
    private static final String PASSWORD = "123456";
    // 项目根路径（注意：本项目中追加的是此组件模块的文件名。实际的单模块项目中，请使用下面的）
    private static final String projectPath = System.getProperty("user.dir") + "/study-mybatis-plus-fast-generator";
    // 项目根路径（单模块下，使用此句）
    // private static final String projectPath = System.getProperty("user.dir");

    /**
     * 执行此处
     */
    public static void main(String[] args) {
        simpleGenerator();
    }

    /**
     * 简单的实现方案
     */
    protected static void simpleGenerator() {

        // 包路径
        String packagePath = projectPath + "/src/main/java";
        // XML文件的路径
        String mapperXmlPath = projectPath + "/src/main/resources";

        // 开始执行代码生成
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> builder
                        // 作者名称
                        .author("拾年之璐")
                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。
                        .fileOverride()
                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                        .disableOpenDir()
                        // 指定输出目录
                        .outputDir(packagePath)
                        // 开启swagger2.注释掉则默认关闭。
                        // .enableSwagger()
                        // 指定时间策略。
                        .dateType(DateType.TIME_PACK)
                        // 注释时间策略。
                        .commentDate("yyyy-MM-dd")
                )

                // 包配置
                .packageConfig((scanner, builder) -> builder
                        .parent("com.cxhit.mybatisplus.generator")
                        .moduleName(scanner.apply("请输入模块名："))
                        // mapper.xml 文件的路径
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlPath + "/mapper"))
                )

                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        // .enableLombok()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build()
                )

                //模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                //.templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())

                // 执行
                .execute();
    }

    /**
     * 完整的实现方案
     */
    protected static void completeGenerator() {

        // 包路径
        String packagePath = projectPath + "/src/main/java";
        // XML文件的路径
        String mapperXmlPath = projectPath + "/src/main/resources";

        // 开始执行代码生成
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> builder
                        // 作者名称
                        .author("拾年之璐")
                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。
                        .fileOverride()
                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                        .disableOpenDir()
                        // 指定输出目录
                        .outputDir(packagePath)
                        // 开启swagger2.注释掉则默认关闭。
                        .enableSwagger()
                        // 指定时间策略。
                        .dateType(DateType.TIME_PACK)
                        // 注释时间策略。
                        .commentDate("yyyy-MM-dd")
                )

                // 包配置
                .packageConfig((scanner, builder) -> builder
                        .parent("com.cxhit.mybatisplus.generator")
                        .moduleName(scanner.apply("请输入模块名："))
                        // mapper.xml 文件的路径
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlPath + "/mapper"))
                )

                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        .enableLombok()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build()
                )

                //模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                //.templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())

                // 执行
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
