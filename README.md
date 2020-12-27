# spring-boot-demo

## 项目介绍

基于 spring boot 2.X 开发，主要用于造轮子。

### 目录结构
```
spring-boot-demo
|-- common 公共模块
|-- ucenter 用户系统
|-- kapi 接口管理系统
|-- s-registry 注册中心
```

## 涉及技术纲目

- lombok
- [MyBatis-Plus](http://mp.baomidou.com)
    > 是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

## 涉及技术集成介绍
    
### MyBatis-Plus

1. 添加引用

        <!-- mybatis plus -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.1</version>
        </dependency>
        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        
1. 配置数据库

        spring:
          datasource:
            driver-class-name: com.mysql.jdbc.Driver
            url: jdbc:mysql://localhost:3306/spring-boot-demo
            username: spring
            password: spring
            
1. 修改启动类

    添加 `@MapperScan` 注解，扫描 `Mapper` 文件夹。

        @SpringBootApplication
        @MapperScan("com.example.mapper") // 启用 MyBatis-Plus
        public class SpringBootDemoApplication {
        
            public static void main(String[] args) {
                SpringApplication.run(SpringBootDemoApplication.class, args);
            }
        }
