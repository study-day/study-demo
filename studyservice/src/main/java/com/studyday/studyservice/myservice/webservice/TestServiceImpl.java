package com.studyday.studyservice.myservice.webservice;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * webservice测试接口实现
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年12月19日 下午9:37:20
 */
@WebService(serviceName = "TestService", // 与接口中指定的name一致
targetNamespace = "http://webservice.yinhai.com"
        //, // 与接口中的命名空间一致,一般是接口的包名倒
//endpointInterface = "com.studyday.study.webservice.TestService"// 接口地址
)
@Component
public class TestServiceImpl {//implements TestService {

    //@Override
    public String sendMessage(String username) {
       // System.out.println(requ);
        return "hello "+username;
    }

}
