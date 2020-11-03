package com.syyjay.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan("com.syyjay")
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}
