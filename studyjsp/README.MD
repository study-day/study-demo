#  访问地址
http://127.0.0.1:8080/studyjsp/index.jsp
# 注意事项
1.注意结构
```
studysjp
  |-WEB-INF
    |-lib
    |-classes
  |index.jsp
```
2.maven 是如何把jar包放在lib下的？

在Web deployment Assembly中配置

cookie：放在用户浏览器。

session：是放在web服务器上的，所以要求session里的属性是可序列化的，否则可能引发不可序列化异常。session机制通过用于保存客户端的状态信息，通常只应把与用户会话状态相关的信息放入sessioin范围。



写三个servlet 
1.1 使用注解  OK
1.2 使用web.xml  OK
1.3 写和项目一起启动。
1.4 filter，listener


总结
1.<!--metadata-complete="true" 如果想使用注解方式定义servlet 则不能写这一句，这一句的意思是完全使用配置文件  -->
2.<!-- 同一个servet 只能使用注解或者xml配置方式实现。对于不通的servet，在项目中两种方式可以同时存在。-->