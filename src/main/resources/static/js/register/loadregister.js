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
				url:path+"/login/insertOrUpdate?userName="+code+"&password="+psw1+"&mail="+email+"&authCode="+auth,
				type:"get",
				dataType:"json",
				async:false,
                crossDomain: true,
                success:function(result){
                    window.location.href="mylogin.html";
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
            $("#message").html("");
        	$.ajax({
				url:path+"/login/authMail?mail="+email,
				type:"get",
				dataType:"json",
				success:function(){
                    alert("验证码发送成功!");
				},
                error:function (result) {
					console.log(result);
                    $("#message").html("验证码发送失败!");
                }

			})
		}
		return false;
    });

});