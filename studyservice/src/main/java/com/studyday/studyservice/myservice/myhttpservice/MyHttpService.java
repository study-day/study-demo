package com.studyday.studyservice.myservice.myhttpservice;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>MyHttpService</p>
 * <p>创建时间：2020/12/19</p>
 *
 * @version 1.0
 */
public class MyHttpService {

    /**
     * 将请求的参数返回给原服务。并在控制台打印
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/printParameter")
    public Map testPrintParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("header start----------------------------------------------");
        StringBuffer sb = new StringBuffer();
        Map header = new HashMap<String, Object>();
        Map body = new HashMap<String, Object>();
        JSONObject result = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + "  :  " + headerValue + " ");
            header.put(headerName, headerValue);
        }
        System.out.println("header end----------------------------------------------");
        System.out.println("paramter start-------------------------------------------");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramnamme = parameterNames.nextElement();
            System.out.println(paramnamme + " : " + request.getParameter(paramnamme));
            body.put(paramnamme, request.getParameter(paramnamme));
        }
        System.out.println("paramter end-------------------------------------------");
        BufferedReader in = null;
        StringBuffer json = new StringBuffer();
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String inputLine = null;
            //一直读到空，并设置流编码是UTF8
            while ((inputLine = in.readLine()) != null) {
                json.append(new String(inputLine.getBytes(), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();

            }
        }
        if (json.length() != 0) {
            try {

                if(request.getContentType().toUpperCase().contains("JSON")){
                    JSONObject jn = JSONObject.parseObject(json.toString());
                    result.put("requestBodyJson", ((JSONObject) jn).toJSONString());
                    System.out.println("requestBodyJson" + ((JSONObject) jn).toJSONString());
                }else if(request.getContentType().toUpperCase().contains("XML")){
                    result.put("requestBodyXML", json);
                    System.out.println("requestBodyXML" + json);
                }else{
                    result.put("requestBodyContent", json);
                    System.out.println("requestBodyContent" + json);
                }
            } catch (Exception e) {
                result.put("requestBodyContent", json);
                System.out.println("requestBodyContent" + json);
            }

        }

        result.put("requestContentType", request.getContentType());
        result.put("msg", "测试成功");
        result.put("header", header);
        result.put("body", body);
        System.out.println("打印返回结果 开始");
        System.out.println(result.toString());
        System.out.println("打印返回结果 结束");
        return result;
    }
}
