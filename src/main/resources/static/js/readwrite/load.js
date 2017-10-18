//加载所有评论
function load(){
	//alert("ssssss");
	var goodid=getCookie("good_id");
	$.ajax({
		url:path+"/loadwrites.do",
		type:"post",
		data:{"goodid":goodid},
		dataType:"json",
		success:function(result){
			if(result.state==0){
				//$("#userwrite")
				
				console.log(result.data);
				
				for(var i=0;i<result.data.length;i++){
					var desc  = result.data[i].write_desc;
					console.log("我是描述:"+desc);
					//userid-查询--usercode
					//goodid-查询--goodname---已写
					var userid = result.data[i].user_id;
					console.log("我是userid:"+userid);
					var goodid= result.data[i].good_id;
					
					$.ajax({
						url:path+"/findUserId.do",
						data:{"id":userid},
						async:false,
						type:"post",
						dataType:"json",
						success:function(result){
							//alert("cahx");
							if(result.state==0){
								//alert("Ddd");
								var usercode = result.data.user_code;
								var sli = "";
								sli="<li class='writes'>用户"+usercode+":"+ desc +"</li>";
								$("#userwrite").append(sli);
							}
							
						},
						error:function(){
							alert("通过用户id查询用户名失败");
						}
						
					});
					
					
					
					
					
				}
				
				
			}else{
				alert("系统繁忙,稍后...");
			}
		},
		error:function(){
			alert("加载评论失败");
		},
		
	});
	
}