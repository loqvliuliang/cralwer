//加载所有评论
function load(){
	//alert("ssssss");
	var goodid=getCookie("good_id");
	$.ajax({
		url:path+"/api/writes/getWritesByGoodId",
		type:"get",
		data:{"goodId":goodid},
		dataType:"json",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
        },
		success:function(result){
				console.log(result);
				for(var i=0;i<result.length;i++){
					var desc  = result[i].writesDesc;
					var writesName =result[i].writesName;
					var date = result[i].writesDate;
					console.log("我是描述:"+desc);
                    var sli = "";
                    sli="<li class='writes'>用户("+date+") "+writesName+ "  :  "+ desc +"</li>";
                    $("#userwrite").append(sli);
				}
		},
		error:function(){
			alert("加载评论失败");
		},
		
	});
	
}