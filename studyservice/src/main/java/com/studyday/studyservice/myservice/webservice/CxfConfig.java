package com.studyday.studyservice.myservice.webservice;

//import org.apache.cxf.Bus;
//import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * cxf配置
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年12月19日 下午9:38:24
 */
@Configuration
public class CxfConfig {
    
//    @Autowired
//    private Bus bus;
//
//    @Autowired
//    private AuthInterceptor authInterceptor;
//
//    @Autowired
//    private TestService testService;
//    @Autowired
//    private MyWebService rsb;
//    @Autowired
//    private MyJSONCxf myJSONCxf;
//    @Bean
//    public Endpoint endpoint(){
//        EndpointImpl endpoint = new EndpointImpl(bus, testService);
//        endpoint.publish("/TestService");
//        return endpoint;
//    }
//
//    @Bean
//    public Endpoint endpoint1(){
//        EndpointImpl endpoint2 = new EndpointImpl(bus, rsb);
//        endpoint2.publish("/MyWebservice");
//        //endpoint2.getInInterceptors().add(authInterceptor);
//        return endpoint2;
//    }
//
//    @Bean
//    public Endpoint endpoint3(){
//        EndpointImpl endpoint3 = new EndpointImpl(bus, myJSONCxf);
//        endpoint3.publish("/MyJSONCxf");
//        //endpoint2.getInInterceptors().add(authInterceptor);
//        return endpoint3;
//    }

/*    @Bean
    public Endpoint endpointWithAuthInterceptor(){
        EndpointImpl endpoint3 = new EndpointImpl(bus, rsb);
        endpoint3.publish("/MyWebservice");
        endpoint3.getInInterceptors().add(authInterceptor);
        return endpoint3;
    }*/
}
