package com.jin.eledger.appconfig;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Http消息转换器配置
 */
@Component
public class HttpMessageConverterConfig {
    @Bean
    public HttpMessageConverters configureMessageConverters() {

        //创建消息转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //创建配置对象
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        return new HttpMessageConverters(converter);
    }

}
