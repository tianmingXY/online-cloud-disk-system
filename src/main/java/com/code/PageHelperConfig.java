package com.code;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.code.util.CommonUtils;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;


//mybatis 分页插件 参数配置

@Configuration
@EnableTransactionManagement
@MapperScan("com.code.mapper")
public class PageHelperConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor page = new PerformanceInterceptor();
        page.setFormat(true);
        return page;
    }

    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper() throws Exception {
        PageHelper pageHelper = new PageHelper();
        if (CommonUtils.checkCode()) {
            Properties properties = new Properties();
            properties.setProperty("offsetAsPageNum", "true");
            properties.setProperty("rowBoundsWithCount", "true");
            properties.setProperty("reasonable", "true");
            properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
            pageHelper.setProperties(properties);
        }
        return pageHelper;
    }

    /**
     * 显示springboot项目启动访问地址
     *
     * @return
     */
    public static void showStartInfo() {
        try {
            //加载属性文件，读取数据库连接配置信息
            Properties pro = new Properties();
            try {
                pro.load(PageHelperConfig.class.getResourceAsStream("/application.properties"));
                String server_port = pro.getProperty("server.port");
                String context_path = pro.getProperty("server.servlet.context-path");
                String url = "http://localhost:" + server_port + context_path + "/index";
                System.out.println("建议使用谷歌浏览器，点击后面的地址访问程序 = " + url);
                //Runtime.getRuntime().exec("cmd /c start " + url);  // 启动浏览器
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("未找到配置文件！！！" + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
