# Spring Boot组件集成专栏之：集成MyBatis-Plus代码生成器（3.5.1版本）

## 1. 关于MP代码生成器

`mybatis-plus-generator`是 `MyBatis-Plus`（简称MP）的代码生成器，通过 `AutoGenerator` （新版是`FastAutoGenerator`）可以快速生成 `Entity`、`Mapper`、`Mapper XML`、`Service`、`Controller` 等各个模块的代码，极大的提升了开发效率。

在上一篇文章《[集成Druid数据库连接池和MyBatis-Plus（含代码生成器）](https://blog.csdn.net/cxh_1231/article/details/120904636)》中，我们有介绍关于集成`MyBatis-Plus代码生成器`的操作。但是此文中，我们使用的是`mybatis-plus-generator`版本是`3.4.1`。如果想使用此版本的代码生成器，可[点击此处](https://blog.csdn.net/cxh_1231/article/details/120904636)查看此文。

而在今年9月末，`苞米豆`更新了`mybatis-plus-generator`代码生成器。不就后，MP的官网更新了关于`3.5.1版本`的教程。

>   官网教程：[https://mp.baomidou.com/guide/generator-new.html](https://mp.baomidou.com/guide/generator-new.html)

基于此版本，我们实现了`两个通用的的代码生成器示例`，并配有详细的`代码注释`，方便结合官网教程理解其使用方法。

>   两个示例分别是：
>
>   +   单模块生成代码的示例
>   +   多模块生成代码的示例

## 2、集成MP代码生成器

>   下文讲述的是集成`3.5.1版本`的代码生成器。

### 2.1 引入依赖

在 `pom.xml` 文件中引入以下依赖。

```xml
<!-- freemarker，作为代码生成器mapper文件的模板引擎使用（当然也可以使用velocity，二选一即可） -->
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>

<!-- 必须引入的两个依赖 -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus</artifactId>
    <version>3.4.3.2</version>
</dependency>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.5.1</version>
</dependency>

<!-- Druid依赖 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.6</version>
</dependency>

<!-- 数据库连接依赖 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

以上依赖的详细说明：

+   `freemarker`：代码生成器的模板引擎。不加生成xml出错。
+   `mybatis-plus`：MP的核心依赖。
+   `mybatis-plus-generator`：MP代码生成器的核心依赖。
+   `druid`：数据库连接池。不加无法连接数据库。
+   `mysql-connector`：连接数据库的依赖。不加无法连接数据库。

### 2.2 完善并修改生成器

在`测试文件夹`下，新建一个名为 `FastCodeGenerator` 的 `java class` 文件，如下图所示。

![image-20211026205528038](README.assets/image-20211026205528038.png)

然后在项目 `FastCodeGenerator` 类中，写入如下代码。

>   <font color=red>注意：以下代码长达340行！但每句均有详细的注释，请仔细阅读！</font>

```java
package com.cxhit.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.cxhit.mybatisplus.generator.base.BaseController;
import com.cxhit.mybatisplus.generator.base.BaseEntity;


import java.util.*;

/**
 * <p>
 * 代码生成器（快速版本）
 * </p>
 *
 * @author 拾年之璐
 * @since 2021-10-22 0022 16:51
 */
public class FastCodeGenerator {

    // 基础信息配置
    // 数据库连接字符
    private static final String URL = "jdbc:mysql://localhost:3306/db_study_mp?useUnicode=true&serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
    // 数据库用户名
    private static final String USERNAME = "root";
    // 数据库密码
    private static final String PASSWORD = "123456";
    // 项目根路径。生成结果如：D:\MyProject\spring-boot
    private static final String projectRootPath = System.getProperty("user.dir");
    // 项目根路径（测试用，非通用）（此句是本项目测试用的。实际项目中，包括多模块项目，请注释掉此句，使用上句）
//    private static final String projectRootPath = System.getProperty("user.dir") + "/study-mybatis-plus-fast-generator";
    // 父包名。用于生成的java文件的import。
//    private static final String parentPackageName = "com.cxhit.mybatisplus.generator";
    private static final String parentPackageName = "com.yourdomain.projectname";

    /**
     * 执行此处
     */
    public static void main(String[] args) {
        // 简单示例，适用于单模块项目
        simpleGenerator();
        // 完整示例，适用于多模块项目
//        completeGenerator();
    }

    /**
     * 【单模块】简单的实现方案
     */
    protected static void simpleGenerator() {

        // 包路径
        String packagePath = projectRootPath + "/src/main/java";
        // XML文件的路径
        String mapperXmlPath = projectRootPath + "/src/main/resources/mapper";

        // 开始执行代码生成
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 1. 全局配置
                .globalConfig(builder -> builder
                        // 作者名称
                        .author("拾年之璐")
                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。
                        // .fileOverride()
                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                        .disableOpenDir()
                        // 指定输出目录。如果指定，Windows生成至D盘根目录下，Linux or MAC 生成至 /tmp 目录下。
                        .outputDir(packagePath)
                        // 开启swagger2.注释掉则默认关闭。
                        // .enableSwagger()
                        // 指定时间策略。
                        .dateType(DateType.TIME_PACK)
                        // 注释时间策略。
                        .commentDate("yyyy-MM-dd")
                )

                // 2. 包配置
                .packageConfig((scanner, builder) -> builder
                        // 设置父表名
                        .parent(parentPackageName)
                        .moduleName(scanner.apply("请输入模块名："))
                        // mapper.xml 文件的路径。单模块下，其他文件路径默认即可。
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlPath))
                )

                // 3. 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？生成所有表，请输入[all]：")))
                        // 阶段1：Entity实体类策略配置
                        .entityBuilder()
                        // 开启生成实体时生成字段注解。
                        // 会在实体类的属性前，添加[@TableField("nickname")]
                        .enableTableFieldAnnotation()
                        // 逻辑删除字段名(数据库)。
                        .logicDeleteColumnName("is_delete")
                        // 逻辑删除属性名(实体)。
                        // 会在实体类的该字段属性前加注解[@TableLogic]
                        .logicDeletePropertyName("isDelete")
                        // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                        // 阶段2：Mapper策略配置
                        .mapperBuilder()
                        // 开启 @Mapper 注解。
                        // 会在mapper接口上添加注解[@Mapper]
                        .enableMapperAnnotation()
                        // 启用 BaseResultMap 生成。
                        // 会在mapper.xml文件生成[通用查询映射结果]配置。
                        .enableBaseResultMap()
                        // 启用 BaseColumnList。
                        // 会在mapper.xml文件生成[通用查询结果列 ]配置
                        .enableBaseColumnList()
                        // 阶段4：Controller策略配置
                        .controllerBuilder()
                        // 会在控制类中加[@RestController]注解。
                        .enableRestStyle()
                        // 开启驼峰转连字符
                        .enableHyphenStyle()
                        .build()
                )

                // 4. 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                //.templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())

                // 5. 执行
                .execute();
    }

    /**
     * 【多模块使用】完整的实现方案
     */
    protected static void completeGenerator() {
        //【1】基础信息配置
        // 指定模块名，用于生成的java文件的import。
        String moduleName = scanner("请输入模块名：");
        // 六个文件的路径。多模块项目下，一般来说每个文件的路径都是不同的（根据项目实际，可能在不同的模块下）。
        String entityPath = projectRootPath + "/project-entity/src/main/java/com/yourdomain/projectname/entity/" + moduleName;
        String mapperPath = projectRootPath + "/project-dao/src/main/java/com/yourdomain/projectname/mapper/" + moduleName;
        String mapperXmlPath = projectRootPath + "/project-dao/src/main/resources/mapper/" + moduleName;
        String servicePath = projectRootPath + "/project-service/src/main/java/com/yourdomain/projectname/service/" + moduleName;
        String serviceImplPath = projectRootPath + "/project-service/src/main/java/com/yourdomain/projectname/service/" + moduleName + "/impl";
        String controllerPath = projectRootPath + "/project-controller/src/main/java/com/yourdomain/projectname/controller/" + moduleName;
        // 关于以上写法的解释：
        // --- 假设我们的项目有四个模块：project-entity、project-dao、project-service、project-controller
        // --- project-entity 的包路径：com.yourdomain.projectname.eneity，
        //   ---则生成system模块下的sys_user表，生成的实体文件将放在：com.yourdomain.projectname.entity.system包下，SysUser.java。
        // --- project-dao 的包路径：com.yourdomain.projectname.mapper，
        //   ---则生成system模块下的sys_user表，生成的mapper接口文件将放在：com.yourdomain.projectname.mapper.system包下，类名为：SysUserMapper.java。
        // --- 其他文件以此类推，即每个模块放MVC结构中对应的类型文件。
        // --- 注意：这里最后的文件路径修改了，下文配置中的【2 包配置】中的包路径也要同步修改！否则生成的java文件，首句import会报错。原因是路径错误。

        // 【2】开始执行代码生成
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 1. 全局配置
                .globalConfig(builder -> builder
                        // 作者名称
                        .author("拾年之璐")
                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。请谨慎开启此选项！
                        // .fileOverride()
                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                        .disableOpenDir()
                        // 指定输出目录。多模块下，每个类型的文件输出目录不一致，在包配置阶段配置。
                        // .outputDir(packagePath)
                        // 开启swagger2。注释掉则默认关闭。
                        // .enableSwagger()
                        // 开启 kotlin 模式。注释掉则关闭此模式
                        // .enableKotlin()
                        // 指定时间策略。
                        .dateType(DateType.TIME_PACK)
                        // 注释时间策略。
                        .commentDate("yyyy-MM-dd")
                )

                // 2. 包配置
                .packageConfig((scanner, builder) -> builder
                        // 阶段1：各个文件的包名设置，用来拼接每个java文件的第一句：package com.XXX.XXX.XXX.xxx;
                        // 父包名配置
                        .parent(parentPackageName)
                        // 输入模块名。此模块名会在下面的几个包名前加。多模块项目，请根据实际选择是否添加。
                        // .moduleName(moduleName)
                        .entity("entity." + moduleName)
                        .mapper("mapper." + moduleName)
                        .service("service." + moduleName)
                        .serviceImpl("service." + moduleName + ".impl")
                        .controller("controller." + moduleName)
                        .other("other")
                        // 阶段2：所有文件的生成路径配置
                        .pathInfo(
                                new HashMap<OutputFile, String>() {{
                                    // 实体类的保存路径
                                    put(OutputFile.entity, entityPath);
                                    // mapper接口的保存路径
                                    put(OutputFile.mapper, mapperPath);
                                    // mapper.xml文件的保存路径
                                    put(OutputFile.mapperXml, mapperXmlPath);
                                    // service层接口的保存路径
                                    put(OutputFile.service, servicePath);
                                    // service层接口实现类的保存路径
                                    put(OutputFile.serviceImpl, serviceImplPath);
                                    // 控制类的保存路径
                                    put(OutputFile.controller, controllerPath);
                                }}
                        )
                )

                // 3. 策略配置【请仔细阅读每一行，根据项目实际项目需求，修改、增删！！！】
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？生成所有表，请输入[all]：")))
                        // 阶段1：Entity实体类策略配置
                        .entityBuilder()
                        // 设置父类。会在生成的实体类名后：extends BaseEntity
                        // .superClass(BaseEntity.class)
                        // 禁用生成 serialVersionUID。（不推荐禁用）
                        // .disableSerialVersionUID()
                        // 开启生成字段常量。
                        // 会在实体类末尾生成一系列 [public static final String NICKNAME = "nickname";] 的语句。（一般在写wapper时，会用到）
                        // .enableColumnConstant()
                        // 开启链式模型。
                        // 会在实体类前添加 [@Accessors(chain = true)] 注解。用法如 [User user=new User().setAge(31).setName("snzl");]（这是Lombok的注解，需要添加Lombok依赖）
                        // .enableChainModel()
                        // 开启 lombok 模型。
                        // 会在实体类前添加 [@Getter] 和 [@Setter] 注解。（这是Lombok的注解，需要添加Lombok依赖）
                        // .enableLombok()
                        // 开启 Boolean 类型字段移除 is 前缀。
                        // .enableRemoveIsPrefix()
                        // 开启生成实体时生成字段注解。
                        // 会在实体类的属性前，添加[@TableField("nickname")]
                        .enableTableFieldAnnotation()
                        // 逻辑删除字段名(数据库)。
                        .logicDeleteColumnName("is_delete")
                        // 逻辑删除属性名(实体)。
                        // 会在实体类的该字段属性前加注解[@TableLogic]
                        .logicDeletePropertyName("isDelete")
                        // 数据库表映射到实体的命名策略(默认下划线转驼峰)。一般不用设置
                        // .naming(NamingStrategy.underline_to_camel)
                        // 数据库表字段映射到实体的命名策略(默认为 null，未指定按照 naming 执行)。一般不用设置
                        // .columnNaming(NamingStrategy.underline_to_camel)
                        // 添加父类公共字段。
                        // 这些字段不会出现在新增的实体类中。
                        .addSuperEntityColumns("id", "delete_time")
                        // 添加忽略字段。
                        // 这些字段不会出现在新增的实体类中。
                        // .addIgnoreColumns("password")
                        // 添加表字段填充
                        // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                        // 全局主键类型。如果MySQL主键设置为自增，则不需要设置此项。
                        // .idType(IdType.AUTO)
                        // 格式化文件名称。
                        // 如果不设置，如表[sys_user]的实体类名是[SysUser]。设置成下面这样，将是[SysUserEntity]
                        // .formatFileName("%sEntity")

                        // 阶段2：Mapper策略配置
                        .mapperBuilder()
                        // 设置父类
                        // 会在mapper接口方法集成[extends BaseMapper<XXXEntity>]
                        // .superClass(BaseMapper.class)
                        // 开启 @Mapper 注解。
                        // 会在mapper接口上添加注解[@Mapper]
                        .enableMapperAnnotation()
                        // 启用 BaseResultMap 生成。
                        // 会在mapper.xml文件生成[通用查询映射结果]配置。
                        .enableBaseResultMap()
                        // 启用 BaseColumnList。
                        // 会在mapper.xml文件生成[通用查询结果列 ]配置
                        .enableBaseColumnList()
                        // 设置缓存实现类
                        // .cache(MyMapperCache.class)
                        // 格式化 mapper 文件名称。
                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper]。写成下面这种形式后，将变成[SysUserDao]。
                        // .formatMapperFileName("%sDao")
                        // 格式化 xml 实现类文件名称。
                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper.xml]，写成下面这种形式后，将变成[SysUserXml.xml]。
                        // .formatXmlFileName("%sXml")

                        // 阶段3：Service策略配置
                        // .serviceBuilder()
                        // 设置 service 接口父类
                        // .superServiceClass(BaseService.class)
                        // 设置 service 实现类父类
                        // .superServiceImplClass(BaseServiceImpl.class)
                        // 格式化 service 接口文件名称
                        // 如果不设置，如表[sys_user]，默认的是[ISysUserService]。写成下面这种形式后，将变成[SysUserService]。
                        // .formatServiceFileName("%sService")
                        // 格式化 service 实现类文件名称
                        // 如果不设置，如表[sys_user]，默认的是[SysUserServiceImpl]。
                        // .formatServiceImplFileName("%sServiceImpl")

                        // 阶段4：Controller策略配置
                        .controllerBuilder()
                        // 设置父类。
                        // 会集成此父类。
                        // .superClass(BaseController.class)
                        // 开启生成 @RestController 控制器
                        // 会在控制类中加[@RestController]注解。
                        .enableRestStyle()
                        // 开启驼峰转连字符
                        .enableHyphenStyle()

                        // 最后：构建
                        .build()
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
}
```

### 2.3 通用信息配置

在代码最开始，配置一下数据库、父包名等通用信息。如下图所示。

![image-20211026210013690](README.assets/image-20211026210013690.png)

### 2.3 单模块使用示例

1、在`main`函数中，启用`simpleGenerator()` 方法，如下图所示。

![image-20211026210433259](README.assets/image-20211026210433259.png)

2、根据`注释`，结合自己项目实际，修改 `simpleGenerator()` 方法，如下图所示。

![image-20211026210540376](README.assets/image-20211026210540376.png)

3、执行`main`函数，在`控制台`输入相关信息，然后`回车`键执行，如下图所示。

![image-20211026210626226](README.assets/image-20211026210626226.png)

4、即可看到生成的文件，如下图所示。

![](README.assets/image-20211026203247908.png)

以上就是单模块的使用示例。

### 2.5 多模块使用示例

1、在`main`函数中，启用`completeGenerator()` 方法，如下图所示。

![image-20211026210741540](README.assets/image-20211026210741540.png)

2、根据注释，以及项目实际情况，修改各个文件保存的模块、路径名，如下图所示。

![image-20211026210845699](README.assets/image-20211026210845699.png)

3、执行`main`函数，在`控制台`输入相关信息，然后`回车`键执行。

4、即可看到生成的代码，如下图所示。

![](README.assets/image-20211026201532072.png)

>   注：上图示例仅在模拟环境下，测试生成路径、包名的准确性，并非真实项目截图。
>
>   经过测试，路径、包名均无误。

以上就是MP集成最新版代码生成器的完整源码+示例。