package com.scyking.ucenter;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.scyking.ucenter.controller.BaseController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scyking
 * @description 测试 不依赖springboot启动
 **/
public class UCenterTests {

    @Test
    public void codeGenerator() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("scyking");
        globalConfig.setOpen(false);

        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://localhost:3306/u_center?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("Root_12root");

        PackageConfig packageInfo = new PackageConfig();
        packageInfo.setParent("com.scyking.ucenter");
        packageInfo.setEntity("model");

        //自定义输出配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //to do nothing
            }
        };
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        FileOutConfig fileOutConfig = new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return System.getProperty("user.dir") + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        };
        fileOutConfigs.add(fileOutConfig);
        cfg.setFileOutConfigList(fileOutConfigs);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(BaseController.class);
        strategy.setInclude("tb_user"); // 表名，多个英文逗号分割
        strategy.setControllerMappingHyphenStyle(true);

        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(globalConfig);
        generator.setDataSource(dataSource);
        generator.setPackageInfo(packageInfo);
        generator.setCfg(cfg);
        generator.setTemplate(templateConfig);
        generator.setStrategy(strategy);
        // set freemarker engine
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        generator.execute();
    }
}
