function checkname(){
	var username_obj=document.getElementById("username").value;
	var name_msg=document.getElementById("name_msg");
	var ragex=/^\w{3,20}$/;
	if(ragex.test(username_obj)){
		name_msg.className="";
		
		return true;
	}else{
		name_msg.className="error_msg";
		return false;
	}
}

function checkpsw(){
	var psw= document.getElementById("psw").value;
	var psw_msg=document.getElementById("psw_msg");
	var ragex=/^\w{6,10}$/;
	if(ragex.test(psw)){
		psw_msg.className="";
		
		return true;
	}
	else{
		psw_msg.className="error_msg";
		return false;
	}
}


$(function(){
	alert("a");
});

/*检查输入帐号以及密码是否正确*/
function login(){
	var usercode = $("#usercode").val().trim();
	var password = $("#password").val().trim();
    var ok=true;	
	$("#msg").text("");
	if(usercode==""){
		$("#msg").text("用户名不正确！");
		ok=false;
	}else if(password==""){
		$("#msg").text("密码不正确！");
		ok=false;
	}
	
	
	if(ok){
		$ajax({
			"url":"login.do",
			"type":"post",
			"dataType":"text",
			"success":function(data){
				alert(data);
				if("true".equals(data)){
					alert("1");
					//正确信息--直接发送到toindex.do
					$("#msg").text(data);
					
				}else if("false".equals(data)){
					//系统错误
					alert("2");
					$("#msg").text(data);
				}else{
					//应用错误
					alert("3");
					$("#msg").text(data);
				}
			},
			error:function(){
				alert("登录失败！");
			}
		});
	}
	
	
	
}










