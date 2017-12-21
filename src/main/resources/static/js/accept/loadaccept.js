$(function(){
		//点击提交按钮后
		$("#send").click(function(){
			var userId=getCookie("id");
			// alert("userid:"+userId);
			//alert("Ss");
			//读取页面的值
			var name = $("#accept_name").val().trim();
			var add  =$("#accept_add").val().trim();
			var phone=$("#accept_phone").val().trim();
			var remark=$("#bz").val().trim();
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

			var accept = {"accept_name":name,"address":add,"phone":phone,"remark":remark,"user_id":userId};
			if(ok){
				//当浏览器验证通过,则像服务器发送ajax请求
				$.ajax({
					url:path+"/api/accept/insertOrUpdate",
					type:"post",
					data:JSON.stringify(accept),
					dataType:"json",
                    contentType: "application/json",
                    beforeSend: function(request){
                        request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
                    },
					success:function(result){
						alert("收货地址添加成功!");
						window.location.href="readaccept.html";
					},
					error:function(){
						alert("提交收货信息失败");
					}
				});	
			}
		});
	});