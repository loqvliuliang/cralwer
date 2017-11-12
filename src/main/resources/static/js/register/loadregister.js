//加载完页面就运行此函数
$(function(){
		var ok = true;
		$("#Register").click(function(){
		var code = $("#usercode").val().trim();
		var email= $("#email").val().trim();
		var psw1 = $("#psw1").val().trim();
		var auth = $("#auth").val().trim();
		if(code==""){
			ok=false;
			$("#message").html("用户名为空!");
		}
		if(auth==""){
            $("#message").html("请输入邮箱验证码!");
		}
		if(psw1.length<6){
			ok=false;
			$("#message").html("密码不得少于6位!");
		}
		if(ok){//如果浏览器检查过关,则发送ajax请求
			$.ajax({
				url:path+"/authMail/insertOrUpdate",
				type:"post",
				data:{"userName":code,"password":psw1,"mail":email,"authCode":auth},
				dataType:"json",
				success:function(result){
                    alert("注册成功!");
					window.location.href="mylogin.html";
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

    $("#authMail").click(function(){
        var email= $("#email").val().trim();
        if(email==""){
        	ok=false;
            $("#message").html("请输入邮箱!");
		}
		if(ok){
        	$.ajax({
				url:path+"/login/authMail",
				type:"post",
				data:{"mail":email},
				dataType:"json",
				success:function(result){
                    $("#message").html("已发送验证码!");
                    alert("发送成功!");
				},
                error:function (result) {
					console.log(result);
                    alert(result.responseText);
                    $("#message").html("验证码发送失败!");
                }

			})
		}
    });

});