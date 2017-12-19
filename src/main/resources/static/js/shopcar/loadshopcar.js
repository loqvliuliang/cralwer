//需要在加载此页面时,读取userid和goodid---均绑定在cookie中
	$(function(){
		console.log("上一个页面:"+document.referrer);
		var userid=getCookie("id");
		var goodid=getCookie("good_id");
		//此两个判断防止用户在商品详情页面
		//停留时间过长,而cookie消失
		if(userid==null){
			//返回登录页面
			alert("请先登录");
			window.location.href="mylogin.html";
			return;
		}
		
		loadshopcar(userid);
	});	
	
	
	/***
	*此函数实现删除购物车商品
	*/
	function del(btn,goodId){
		if(goodId==""){
			alert("请先选择商品");
			return;
		}
		//alert("ss");
		if(confirm("是否移出购物车")){
			//alert("goodid:"+goodid);
			$.ajax({
				url:path+"/api/shopCar/delete/ByGoodIdAndUserId?goodId="+goodId+"&userId="+getCookie("id"),
				type:"delete",
				dataType:"json",
                beforeSend: function(request){
                    request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
                },
				success:function(result){
					//alert("ss");
					location.reload();

				},
				error:function(){
					alert("系统异常,删除商品失败!");
				}
			});
			
			
		}
		
		
	}