$(function(){
	//用户切换到登录页面的时候,自动清空cookie
	delCookie("id");
	delCookie("code");

	/*检查输入帐号以及密码是否正确*/
	$("#Login").click(function(){
		
		var usercode = $("#usercode").val().trim();
		var password = $("#password").val().trim();
	
	    var ok=true;	
		$("#msg").text("");
		if(usercode==""){
			$("#msg").text("用户名或密码为空！");
			ok=false;
		}else if(password==""){
			$("#msg").text("用户名或密码为空！");
			ok=false;
		}
		
		
		if(ok){
			
			$.ajax({
				url:path+"/login.do",
				type:"post",
				data:{"usercode":usercode,"password":password},
				dataType:"json",
				success:function(result){//返回user对象
				
					if(result.state==0){
						var user = result.data;
						addCookie("id",user.user_id,2);
						addCookie("code",user.user_code,2);
						//将user_id存入cookie
						window.location.href="index.html";
					}else{
						$("#msg").text(result.msg);
					}
				    
				},
				error:function(){
					alert("登录失败！");
				}
			});
		}
	});
});