# 简介

快速生成web开发后台代码模版，操作简单，导入依赖后一行代码即可生成所有模版，配合MyBatisX使用效果更佳

生成的内容包括`maven依赖`、`yaml配置`、`基本模板代码`，同时也支持自定义生成器

v2.0 基于 MyBatisFlex 代码生成器进行二次开发，保持 v1.0 功能的同时，支持根据表生成对应的业务类



# 快速开始

第一步：下载lib文件夹中的jar包

第二步：新建一个**maven工程**

第三步：将lib中的jar包导入项目中

第四步：在项目任意地方，编写一个类，用于生成模版代码，参考如下

```java
import tech.beanmak1r.generate.project.Project;
import tech.beanmak1r.generate.project.Projects;
import tech.beanmak1r.generate.starter.datebase.mybatis.MyBatisGenerateConfig;

/**
 * 效果演示
 */
public class Main {
    public static void main(String[] args) {
        // MyBatis 生成器配置对象
        MyBatisGenerateConfig myBatisGenerateConfig = new MyBatisGenerateConfig();
        // 生成实体类时，忽略表的前缀
        myBatisGenerateConfig.setTablePrefix("tb_");
        // 需要生成的表
        myBatisGenerateConfig.setGenerateTable("tb_user", "tb_account");
        // 获取 Project 对象 参数1数据库名称 参数2数据库用户密码
        Project project = Projects.all("db_flex", "dk18111448638", myBatisGenerateConfig);
        // 设置 作者
        project.setAuthor("beanmak1r");
        // 执行生成
        project.generate();
    }
}
```

整体项目结构如下

![截屏2023-05-04 10.16.13](image/新建项目结构图.png)

完成后，运行main方法即可生成代码



# 生成的模板

即可以生成哪些相关代码



## 模板可选项

默认支持生成`Spring boot parent`、`Spring boot Web`、`Spring boot Validation`、`Mybatis-Plus`、`Mybatis-Flex`、`Lombok`、`RedisTemplate`、`SpringDoc`、`redisson`、的模版代码。可以通过`Projects`工具类快速构建需要的模板

> knife4j 已被移除，由 SpringFox 替代



装配简单模板(`Spring boot + SpringWeb + MybatisFlex + lombok`)

```java
/**
 * 简单配置 ssm + lombok
 *
 * @param dbName     数据库名称
 * @param dbPassword 数据库密码
 * @return Project对象
 */
public static Project simple(String dbName, String dbPassword);


/**
 * 简单配置 ssm + lombok，使用 MyBatis 代码生成器
 *
 * @param dbName     数据库名称
 * @param dbPassword 数据库密码
 * @param config     MyBatis 代码生成器配置对象
 * @return Project对象
 */
public static Project simple(String dbName, String dbPassword, MyBatisGenerateConfig config);
```



装配所有模板（除 `redisson`、`mybatisplus`）

```java
/**
 * 全部装配
 *
 * @param dbName     数据库名称
 * @param dbPassword 数据库密码
 * @return Project对象
 */
public static Project all(String dbName, String dbPassword);

/**
 * 全部装配，使用 MyBatis 代码生成器
 *
 * @param dbName     数据库名称
 * @param dbPassword 数据库密码
 * @param config     MyBatis 代码生成器配置对象
 * @return Project对象
 */
public static Project all(String dbName, String dbPassword, MyBatisGenerateConfig config);
```



按需装配模版
按照约定大于配置的原则，所有配置均有默认值，可以自行选配

```java
// 自己按需定制模板
Project customerProject = Projects.customer();
// basePackage，默认为 groupId
customerProject.setBasePackage("com.xxx.xxx");
// 更改版本控制，由 jdk8-SpringBoot2.3.6.RELEASE 修改为 jdk17-SpringBoot2.6.13
customerProject.setVersionControls(new Jdk17VersionControl());

// Starter 工厂，单例实现
StarterFactory factory = StarterFactorySingleImpl.getStarterFactory();

// 构建一个 MyBatisFlexStarter，用于生成 MyBatisFlex 相关代码
MyBatisFlexStarter myBatisFlexStarter = factory.getInstance(MyBatisFlexStarter.class);
// 用于 MyBatisFlex 代码生成器的配置信息
MyBatisGenerateConfig myBatisGenerateConfig = new MyBatisGenerateConfig();
// 生成实体类时，忽略表的前缀
myBatisGenerateConfig.setTablePrefix("tb_");
// 需要生成的表
myBatisGenerateConfig.setGenerateTable("tb_user", "tb_account");
myBatisFlexStarter
        // MyBatisFlex 依赖版本
        .version("1.5.5")
        // 开启 MyBatisFlex 代码生成
        .enableMyBatisFlexGenerate(myBatisGenerateConfig);
customerProject.addStarter(myBatisFlexStarter);

// Spring Boot Parent依赖
SpringBootParentStarter parentStarter = factory.getInstance(SpringBootParentStarter.class);
parentStarter
        // 启动类名称，默认为 artifactId + 'Application'
        .mainBootName("MainApplication")
        // yaml 中的 spring.application.name 配置项，,默认为 artifactId
        .yamlAppName("test-app")
        // 是否导入spring-boot-test-starter，默认导入
        .test(true)
        // 是否导入spring-boot-devtools-starter，默认不导入（MyBatisFlex 与 devtools 整合会有一些小 bug）
        .devtools(false);
customerProject.addStarter(parentStarter);

// web 场景依赖 + validation 场景依赖
SpringBootWebStarter webStarter = factory.getInstance(SpringBootWebStarter.class);
webStarter
        // 是否配置数据校验，默认开启
        .valid(true)
        // 是否导入断言工具类，默认开启
        .asserts(true)
        // 端口号，默认8080
        .serverPort(8080)
        // 是否配置跨域，默认开启
        .cors(true);
customerProject.addStarter(webStarter);


// mybatisPlus 场景依赖
MyBatisPlusStarter myBatisPlusStarter = factory.getInstance(MyBatisPlusStarter.class);
myBatisPlusStarter
        // 是否开启逻辑删除（isDeleted属性，0未删除，1删除），默认开启
        .logicDelete(true)
        // mysql版本
        .version("8.0.32");
// MyBatisPlusStarter 与 MyBatisFlexStarter 只能存在一个
//customerProject.addStarter(myBatisPlusStarter);

// MySql 场景依赖
MySqlStarter mySqlStarter = factory.getInstance(MySqlStarter.class)
        // 数据库用户名，默认root
        .username("root")
        // 数据库密码，默认'123456'
        .password("dk18111448638")
        // 数据库名称，默认为 artifactId
        .dbName("db_flex")
        // 数据库端口号，默认为 3306
        .port(3306);
customerProject.addStarter(mySqlStarter);

// Lombok 依赖
LombokStarter lombokStarter = factory.getInstance(LombokStarter.class);
customerProject.addStarter(lombokStarter);

// redisTemplate，暂时支持单机
RedisStarter redisStarter = factory.getInstance(RedisStarter.class)
        // redis 服务器ip，默认本地地址
        .host("localhost")
        // redis端口号，默认 6379
        .port(6379)
        // 密码，默认无
        .password("123456")
        // 哪个数据库，取值0～15，默认0
        .database(1);
customerProject.addStarter(redisStarter);

// redisson 场景依赖，暂支持单机配置
RedissonStarter redissonStarter = factory.getInstance(RedissonStarter.class);
redissonStarter
        // 哪个数据库，取值0～15，默认0
        .database(0)
        // redisson依赖版本
        .version("3.13.6")
        // redis端口号，默认 6379
        .port(6379)
        // redis 服务器ip，默认本地地址
        .host("127.0.0.1")
        // redis 密码
        .password("123456");
customerProject.addStarter(redissonStarter);


// SpringDocStarter 场景依赖
SpringDocStarter springDocStarter = factory.getInstance(SpringDocStarter.class);
springDocStarter
        // SpringDoc 版本
        .version("1.7.0")
        // open API 访问路径，默认 /api-docs
        .openAPIPath("/api-docs")
        // ui 接口访问路径，默认 /swagger-ui.html
        .swaggerUIPath("/swagger-ui.html");
customerProject.addStarter(springDocStarter);

// 开始执行生成代码
customerProject.generate();
```



## 自定义模板

提供了自定义模板功能，自定义模板只需要继承`StarterAdapter`，按需重写`addCode()`、`addDependency()`、`addYaml()`方法即可

```java
public class CustomerStarter extends StarterAdapter {
    
    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem dependencyItem = new DependencyItem();
      	// 必须配置
        dependencyItem.setGroupId("org.springframework.boot");
        dependencyItem.setArtifactId("spring-boot-starter-web");
        dependencyItem.setTabName(DependencyConst.DEPENDENCY.getTabName());
        dependencyItem.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
      
      	// 选配
        dependencyItem.setScope("runtime");
        dependencyItem.setOptional("true");
        dependencyItem.setVersion("2.3.6.RELEASE");
      
      	// 添加到dependency中
        dependency.addDependencyItem(web);

    }
    
    @Override
    protected void addYaml(BaseYaml yaml) {
      	// 添加yaml配置项
        yaml.addYamlConfig("spring.mvc.format.date", "yyyy-MM-dd");
        yaml.addYamlConfig("spring.mvc.format.date-time", "yyyy-MM-dd HH:mm:ss");
        yaml.addYamlConfig("spring.jackson.date-format", "yyyy-MM-dd");
        yaml.addYamlConfig("spring.jackson.time-zone", "Asia/Shanghai");
    }
    
    
    @Override
    protected void addCode(BaseCode code) {
        // 添加代码模板
      	CodeItem cors = new CodeItem();
      	// basePackage下的路径
      	// 例如：完整包名'com.xxx.xxx.config.web'，其中'com.xxx.xxx'为basePackage，只需要添加'config/web'即可
        cors.setPath("config/web");
      	// 类的名字
        cors.setFileName("CORSConfig.java");
      	// 代码模板
        cors.setCodeTemplate(
                "import org.springframework.context.annotation.Configuration;\n" +
                        "import org.springframework.core.Ordered;\n" +
                        "import org.springframework.core.annotation.Order;\n" +
                        "import org.springframework.web.servlet.config.annotation.CorsRegistry;\n" +
                        "import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;\n" +
                        "\n" +
                        "@Configuration\n" +
                        "@Order(Ordered.HIGHEST_PRECEDENCE)\n" +
                        "public class CORSConfig implements WebMvcConfigurer {\n" +
                        "    \n" +
                        "    @Override\n" +
                        "    public void addCorsMappings(CorsRegistry registry) {\n" +
                        "        //允许所有的访问请求（访问路径）\n" +
                        "        registry.addMapping(\"/**\")\n" +
                        "                //允许所有的请求方法访问该跨域资源服务器\n" +
                        "                .allowedMethods(\"*\")\n" +
                        "                //允许所有的请求域名访问我们的跨域资源\n" +
                        "                .allowedOrigins(\"*\")\n" +
                        "                //允许所有的请求header访问\n" +
                        "                .allowedHeaders(\"*\")\n" +
                        "                .allowCredentials(true).maxAge(3600);\n" +
                        "    }\n" +
                        "}"
        );
      	// 添加到代码模板中
        code.addCodeItem(cors);
    }
}
```



# 处理器

处理器用于执行生成模板的逻辑，处理器统一实现了`handler.tech.beanmak1r.generate.GenerateHandler`，默认提供`代码生成处理器`、`yaml生成处理器`、`pom生成处理器`，可以自行删减

下面示例删除了`pom生成处理器`，生成模板时不会添加pom依赖

```java
Project project = Projects.customer();
project.deleteGenerateHandlerByType(PomHandler.class);
```

也可以自定义处理器，并添加到处理器执行列表中

```java
// 自定义一个处理器
GenerateHandler customerHandler = new GenerateHandlerAdapter() {
    /**
     * 代码生成前、starter项加载前执行
     * @throws Exception
     */
    @Override
    public void beforeGenerate() throws Exception {}

    /**
     * 代码执行后执行
     * @throws Exception
     */
    @Override
    public void afterGenerate() throws Exception {}
    /**
     * 代码的生成方法
     * @param starter 当前处理到的starter会被传到这个方法中
     * @throws Exception
     */
    @Override
    public void generateHandler(AbstractStarter starter) throws Exception {}
};
// 将处理器添加到执行列表中
project.addGenerateHandler(customerHandler);
```

