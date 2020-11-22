package cn.yuan;

import cn.yuan.common.config.YnProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.yuan.*.dao")
@EnableConfigurationProperties({YnProperties.class})
@EnableCaching
@EnableAsync
public class YnApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(YnApplication.class).run(args);
    }
}
