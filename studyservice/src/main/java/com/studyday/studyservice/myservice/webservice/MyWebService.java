package com.studyday.studyservice.myservice.webservice;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 */
@WebService(name = "MyWebService", // 暴露服务名称
        targetNamespace = "http://webservice.edepp.yinhai.com"// 命名空间,一般是接口的包名倒序
)
public interface MyWebService {

    /**
     * 一个参数
     * @param bwlx
     * @return
     */
    @WebMethod
    @WebResult(name = "parameter1Result", targetNamespace = "")
    String test1Parameter(@WebParam(name = "param1")String param1);

    @WebMethod
    @WebResult(name = "parameter2Result" )
    String test2Parameter(String param1, String param2);

    @WebMethod
    @WebResult(name = "parameter3Result")
    String test3Parameter(@WebParam(name = "param1")String param1,
                          @WebParam(name = "param2")String param2,
                          @WebParam(name = "param3")String param3);



    @POST
    @Consumes(MediaType.APPLICATION_JSON)//输入JSON
    @Produces(MediaType.APPLICATION_JSON)//输出JSON
    @Path("/queryjson")
    @ResponseBody
    public String queryjson(@RequestBody String queryParam);




}
