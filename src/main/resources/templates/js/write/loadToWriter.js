var userCode=getCookie("code")
var userId=getCookie("id");
var goodId=getCookie("good_id");
function loadToWriter(){
	
	//此时需要通过用户id和商品id到数据库中找出用户名和商品名
	if(goodId!=null){
		$.ajax({
			url:path+"/loadGoodById.do",
			data:{"id":goodId},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.state==0){
					var goodname  = result.data.good_name;
					$("#writeuser").text("用户:"+userCode+",您好!");
					$("#writegood").text("欢迎评论:"+goodname);
				}
			},
			error:function(){
				alert("根据商品id查找商品信息失败!");	
			}
		});
	}
}




function send(){
	//alert("Sss");
	//alert($("#bz").text());
	var msg =$("#bz").val();
	//alert(msg);
	if(userId!=null && goodId!=null){
		//alert("ajax");
		$.ajax({
			url:path+"/writemsg.do",
			data:{"userid":userId,"goodid":goodId,"msg":msg},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.state==0){
					//alert("chengg");
					alert("提交评论成功！");
					window.location.href="single.html";
				}else{
					alert(result.msg);
				}
				
			},
			error:function(){
				alert("提交评论失败");
			}
			
		});
		
		
		
	}else{
		alert("评论时间太长,请先登录");
		window.location.href="mylogin.html";
	}
	
	
	
}