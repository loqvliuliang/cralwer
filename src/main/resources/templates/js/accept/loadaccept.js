$(function(){
		//点击提交按钮后
		$("#send").click(function(){
			var userId=getCookie("id");
			alert("userid:"+userId);
			//alert("Ss");
			//读取页面的值
			var name = $("#accept_name").val().trim();
			var add  =$("#accept_add").val().trim();
			var phone=$("#accept_phone").val().trim();
			var bz=$("#bz").val().trim();
			var ok=true;
			if(name==""){
				alert("收货人不能为空！");
				ok=false;
			}
			if(add==""){
				alert("请填写收货地址！");
				ok=false;
			}
			if(phone==""){
				alert("请填写联系方式！");
				ok=false;
			}
			
			if(ok){
				//当浏览器验证通过,则像服务器发送ajax请求
				$.ajax({
					url:path+"/accept.do",
					type:"post",
					data:{"name":name,"add":add,"phone":phone,"bz":bz,"userId":userId},
					dataType:"json",
					success:function(result){
						alert("收货地址添加成功!");
						window.location.href="index.html";
					},
					error:function(){
						alert("提交收货信息失败");
					}
				});	
			}
		});
	});