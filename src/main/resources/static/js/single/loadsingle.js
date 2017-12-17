
	function toread(){
		//alert("sss");
		window.location.href="readwrites.html";
	}
	
	//写评论
	function write(){
		var userid = getCookie("id");
		if(userid==null){
			if(confirm("要评论先登录,是否去登录?")){
				window.location.href="mylogin.html";
				return;
			}			
			return;
		}
		
		//如果用户已经登录,就直接跳转到评论页面
		window.location.href="write.html";
	}


	function tocheck(){
		//alert("检查用户是否填写了收货地址");
		//alert("userid:"+getCookie("id"));
		var userid = getCookie("id");
		var flag = true;
		if(userid==null){
			flag=false;
			alert("请先登录会员帐号");
			window.location.href="mylogin.html";
		}
		if(flag){
			$.ajax({
				//通过用户id,检查用户是否填写了收货地址
				url:path+"/checkaccept.do",
				data:{"userId":userid},
				type:"post",
				dataType:"json",
				success:function(result){
					//alert("通过用户id检查收货地址完毕!");
					var user_accept=result.data;
					console.log(result.data);
					if(user_accept==null){
						alert("请先填写收货地址!");
						window.location.href="accept.html";
					}else{
						//alert("收货地址已经填写,直接加入购物车--跳转到购物车界面");
						//加入购物车在此处实现
						//alert("Ddd");
						var userid=getCookie("id");
						var goodid=getCookie("good_id");
						//alert("ssss");
						console.log(userid);
						console.log(goodid);
						var ok = true;
						if(goodid==null){
							ok=false;
							alert("请先选择商品");
							window.location.href="index.html";
						}
						console.log("可以发送ajax请求了");
						
						//1:根据goodid添加此商品,
						//2:在数据库里面查出userid对应的goodid
						if(ok){
							$.ajax({
								url:path+"/addgoodcar.do",
								type:"post",
								data:{"goodid":goodid,"userid":userid},
								dataType:"json",
								success:function(result){
									if(result.state==0){//正常返回
										alert("成功加入购物车");
										//加入购物车成功后,就跳转到购物车界面
										window.location.href="shopcar.html";
									}else{
										alert("加入购物车失败");
									}
								},
								error:function(){
									alert("加载购物车失败");
								}	
							});
						}
						
					}
				},
				error:function(){
					alert("通过用户id检查收货地址失败！");
				}
			});
		}
	}
	
	
	$(function(){
		
		//
		console.log(getCookie("good_id"));
		var id = getCookie("good_id");
		/*******2.14********/
		//发送ajax请求--通过id查询商品信息
		//然后将查询到的商品信息拼到商品详情页面上
		/*
		    明天继续---
		   $.ajax({
			   
		   });
		
		*/
		$.ajax({
			url:path+"/api/good/getGoodById",
			data:{"id":id},
			type:"get",
			dataType:"json",
		    success:function(result){
		    	/**
		    		遇到了问题----将对应的图片显示
		    	*/
		    	var sli;
		    	console.log(result);
	    		sli="";
		    	sli+='<li data-thumb='+result.good_img+' style="list-style-type:none">';
		        sli+='<div class="thumb-image">' ;
		        sli+='<img src='+result.good_img+' data-imagezoom="true" class="img-responsive"/>';
		        sli+='</div>';
		    	sli+='</li>';
		    	//将sli转换为jquery对象
		    	$("#goodimg").html(sli);
		    	$("#divs").html(
		    			
						'<div class="single-para simpleCart_shelfItem">'+
						'<h1>'+result.good_name+'</h1>'+
						'<p>'+result.good_desc+'</p>'+
						'<div class="star-on">'+
							'<ul>'+
								'<li><a href="#"><i class="glyphicon glyphicon-star"> </i></a></li>'+
								'<li><a href="#"><i class="glyphicon glyphicon-star"> </i></a></li>'+
								'<li><a href="#"><i class="glyphicon glyphicon-star"> </i></a></li>'+
								'<li><a href="#"><i class="glyphicon glyphicon-star"> </i></a></li>'+
								'<li><a href="#"><i class="glyphicon glyphicon-star"> </i></a></li>'+
							'</ul>'+
							'<div class="review">'+
								'<a href="javascript:toread()"> '+result.good_com+'条评论 </a>/'+
								'<a href="javascript:write()">  写评论</a>'+
							'</div>'+
						'<div class="clearfix"> </div>'+
						'</div>'+
							'<label  class="add-to item_price">'+result.good_price+'</label>'+
						
						'<div class="available">'+
							'<h6>选择:</h6>'+
							'<ul>'+
							'<li>尺码:<select>'+
								'<option>L</option>'+
								'<option>M</option>'+
								'<option>S</option>'+
							'</select></li>'+
							'<li>支付:'+
									'<select>'+
									'<option>RMB</option>'+
									'<option>$</option>'+
								'</select></li>'+
						'</ul>'+
					'</div>'+
							'<a href="javascript:tocheck()" class="cart item_add">加入购物车</a>'+
					'</div>'
				);
		    	
		    	
		    	
		    	
		    	
		    },
		    error:function(){
		    	alert("详情页面加载失败,请稍后再试");
		    }
		});
	});