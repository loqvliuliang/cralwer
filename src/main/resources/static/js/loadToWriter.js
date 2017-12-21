
function loadToWriter(){
	var userCode=getCookie("code")
	var userId=getCookie("id");
	var goodId=getCookie("good_id");
	//此时需要通过用户id和商品id到数据库中找出用户名和商品名
	if(goodId!=null){
		$.ajax({
			url:path+"/api/good/getGoodById",
			data:{"id":goodId},
            beforeSend: function(request){
                request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
            },
			type:"get",
			dataType:"json",
			success:function(result){
				var goodname  = result.good_name;
				$("#writeuser").text("用户:"+userCode+",您好!");
				$("#writegood").text("欢迎评论:"+goodname);

			},
			error:function(){
				alert("根据商品id查找商品信息失败!");	
			}
		});
	}
	
}