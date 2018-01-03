# crawler
(作者：刘亮)
效果地址（www.hand-liang.top:2222）

SpringBoot上面集成各种技术

  1.为什么要做这个项目?
  
     	 最近使用SpringBoot进行开发。个人觉得SpringBoot简洁方便。想通过某种方式加强巩固SpringBoot技术。于是有了本项目。
	 
      
  2.本项目都使用到了哪些技术?
  
  	截止2018-01-03日本项目集成了SpringSecurity(用户登陆注册),Oauth2(密码模式，客户端模式获取token),mybatisPlus,liquibase,Nginx(映射静态资源),PaysAPI(第三方支付),fileUpload(文件上传),fileDownload(文件下载)，springBoot统一异常处理的配置以及读取，typeHandler等后台技术。在使用过程中会有各种技术的融合。前台使用的是的springboot+thymeleaf,js,jquery,ajax等技术。后续会集成activiti，做出一个完整的商城网页。
      
      
      
  3.梳理项目逻辑
    
    登陆集成springSecurity,添加SecurityConfiguration类。使用自己表里面的用户名（或邮箱）密码登陆
https://github.com/loqvliuliang/cralwer/blob/master/
	
