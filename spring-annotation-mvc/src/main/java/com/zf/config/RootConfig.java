package com.zf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author 郑凡
 * @create 2020-09-29 16:32
 *
 * spring 不扫描 controller
 */

@Configuration
@ComponentScan(value = "com.zf",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfig {
}
