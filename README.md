# crawler
效果地址（http://www.iamcrawler.cn ）

SpringBoot上面集成各种技术

  1.为什么要做这个项目?
  
     	 最近使用SpringBoot进行开发。个人觉得SpringBoot简洁方便。想通过某种方式加强巩固SpringBoot技术。于是有了本项目。
	 
      
  2.本项目都使用到了哪些技术?
  
  	截止目前项目集成了
	SpringSecurity(用户登陆注册),
	Oauth2(密码模式，客户端模式获取token),
	mybatisPlus,liquibase,
	Nginx(映射静态资源),
	PaysAPI(第三方支付),
	fileUpload(文件上传),fileDownload(文件下载),
	javaMail,
	maven私服,
	rabbitMq,
	aop添加注解,
	springBoot统一异常处理的配置以及读取,
	typeHandler等后台技术。
	在使用过程中会有各种技术的融合。前台使用的是的springboot+thymeleaf,js,jquery,ajax等技术。后续会集成activiti，做出一个完整的商城网页。
      
      
      
  3.梳理项目逻辑
    
    
    
    登陆集成springSecurity,添加SecurityConfiguration类。使用自己表里面的用户名（或邮箱）密码登陆
   
    
  
    ···
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
                // 通过用户名或邮箱获取用户信息
                User account = userMapper.selectList(
                        new EntityWrapper<User>()
                                .eq("user_name",name)
                                .or()
                                .eq("mail",name))
                                .get(0) ;
                List<String> roles = new ArrayList<>();
                if(account!=null){
                    List<UserRole> userRoles =userRoleMapper.selectList(new EntityWrapper<UserRole>().eq("user_id",account.getId()));
                    userRoles.stream().map(UserRole::getRoleId).forEach(roleId->{
                        roles.add(roleMapper.selectById(roleId).getRole());
                    });
                    // 创建spring security安全用户
                    org.springframework.security.core.userdetails.User user = new 			org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
                            AuthorityUtils.createAuthorityList(StringUtils.strip(roles.toString(),"[]")));
                    return user;
                } else {
                    throw new UsernameNotFoundException("用户[" + name + "]不存在");
                }
            }
        };

    }
 4.maven私服
  私服引用的jar主要封装了一些常用的注解。自定义注解，然后生成jar包，这里不做解释，主要说明一下怎样引入自己私服上面的jar包
   将jar包上传到私服nexus（不会的自行百度）
   在pom.xml中引入如下代码
   
   
   
   <!--配置maven私服-->
	<repositories>
		<repository>
			<id>nexus</id>
			<name>nexus</name>
			<url>http://www.iamcrawler.cn:8081/nexus/content/groups/public/</url>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>
	<!--配置maven私服-->
	<pluginRepositories>
		<pluginRepository>
			<id>nexus</id>
			<name>nexus</name>
			<url>http://www.iamcrawler.cn:8081/nexus/content/groups/public/</url>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</pluginRepository>
	</pluginRepositories>
	


然后添加依赖：
	
	
	
	
	...
	<dependency>
		<groupId>cn.iamcrawler</groupId>
		<artifactId>crawler</artifactId>
		<version>1.0</version>
	</dependency>
