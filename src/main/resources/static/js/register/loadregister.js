//加载完页面就运行此函数
$(function(){
		var ok = true;
		$("#Register").click(function(){
		var code = $("#usercode").val().trim();
		var email= $("#email").val().trim();
		var psw1 = $("#psw1").val().trim();
		var psw2 = $("#psw2").val().trim();
		if(code==""){
			ok=false;
			$("#message").html("用户名为空!");
		}
		if(psw1!=psw2){
			ok=false;
			$("#message").html("密码输入不一致!");
		}
		if(psw1.length<6){
			ok=false;
			$("#message").html("密码不得少于6位!");
		}
		if(ok){//如果浏览器检查过关,则发送ajax请求
			$.ajax({
				url:path+"/register.do",
				type:"post",
				data:{"code":code,"psw":psw1,"email":email},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						//注册成功
						window.location.href="mylogin.html";
							
					}
					if(result.state==2){
						//帐号被占用
						$("#message").html("此用户名已被注册");								
					}
					if(result.state==1){
						//未知异常
						alert("未知异常");
					}
				},
				error:function(){
					alert("注册失败!");
					}
				});				
		    }
		});		
});