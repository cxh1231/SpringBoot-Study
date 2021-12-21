# Spring Boot 组件集成实战

> 本项目主要集成各种常用的Spring组件。
> 
> 对于每个组件，均实现了基本的配置方法和使用示例。注意，本项目中的所有组件的使用方法，均为作者参考官方开发文档和网络开源资料后，根据理解撰写开发。其中部分组件的配置方法是为了满足其他项目需求而配置，但基本方法不变。
> 
> 本项目仅供初学者参考学习使用，如果本项目对您有帮助，欢迎点亮右上角的 star ~

## 1、使用方法

本项目是基于Spring Boot的多模块项目。每个模块（组件）均有一个可独立运行的`XxxApplication`，右击此方法，选择 `Run XxxApplication` 即可运行。

但是，有的模块没有Web演示示例，其详细使用方法均集成在**测试代码**中，具体可见相应模块的**Test包**下的各个**测试类**。每个测试方法均有详细的注释说明。

详细使用方法见后文。

## 2、开发环境

+ IDEA 2021.2
+ JDK 1.8
+ Maven 3.6
+ Spring Boot 2.5.5

> 注：开发环境非必须的运行环境，通常高于此版本均可顺利运行，此项目并未集成特殊的组件。

## 3、组件实现计划与进展

> ✅：已集成并实现Demo的组件；🎉：计划中，即将实现Demo的组件。

| 🚩 | 模块名                      | 集成组件简介                                                       |
|-----|--------------------------- |-----------------------------------------------------------------|
| ✅ | [Kaptcha](./study-captcha) | 谷歌验证码组件。主要实现生成文本、数学算式等验证码图片。                 |
| 🎉 | [Login OAuth](./study-login-oauth) | 各个OAuth登录平台基础。基于JustAuth实现。登录页面可以借鉴。     |
| ✅ | [MyBatis Plus](./study-mybatis-plus) | 基于MyBatis的增强工具。主要集成MP并实现旧版代码生成器。 |
| ✅ | [MyBatis Plus Fast Generator](./study-mybatis-plus-fast-generator) | 基于MyBatis Plus的新版代码生成器，版本>=3.5.1。 |
| 🎉 | Notice Email | 邮件发送服务组件。主要实现邮件发送功能。 |
| ✅ | [Notice SMS](./study-notice-sms) | 短信发送服务组件。已实现阿里云短信服务。更多短信服务持续集成中。 |
| 🎉 | OSS Aliyun | 阿里云对象存储服务。主要实现阿里云OSS的文件上传与下载 |
| ✅ | [OSS Qiniu](./study-oss-qiniu) | 七牛云对象存储服务。主要实现七牛云OSS的文件上传与下载。 |
| 🎉 | OSS Tencent | 腾讯云对象存储服务。主要实现腾讯云OSS的文件上传与下载。|
| 🎉 | Pay Alipay | 支付宝支付组件。主要实现支付宝的当面付、电脑支付、网站支付等常用支付服务。 |
| 🎉 | Pay Wechat | 微信支付组件。主要实现微信的JSAPI支付、Native支付等常用支付服务。 |
| ✅ | [Redis](./study-redis) | key-value型数据库。主要实现使用Redis进行数据缓存。 |
| ✅ | [Swagger3](./study-swagger3) | 简化API开发的工具。主要实现基于SpringDoc的Swagger3组件。 |
| ✅ | [WebSocket](./study-websocket) | 单个TCP连接上进行全双工通信的协议。 |


> 持续更新中......

## 4、运行

**1、clone项目至本地**

+ 使用如下命令克隆GitHub仓库只本机：

```
git clone https://github.com/cxh1231/SpringBoot-Study.git
```

+ 或使用国内仓库：

```
git clone https://gitee.com/cxh1231/SpringBoot-Study.git
```

**2、使用IDEA导入项目**

+ 在IDEA主页面，访问 File >> Open，选中项目父目录SpringBoot-Study文件夹，点击OK。

**3、更新Maven依赖**

+ **侧栏**打开**Maven**窗口，点击**刷新**按钮（Reload All Maven Projects）更新依赖。

**4、运行子项目**

+ 对于有Web演示的实例，此模块（组件）有一个可独立运行的`XxxApplication`，右击此方法，选择 `Run XxxApplication` 即可运行。

+ 对于没有Web演示示例的模块，其详细使用方法均集成在**测试代码**中，具体可见相应模块的**Test包**下的各个**测试类**。每个测试方法均有详细的注释说明。