
/*加载页面时,就运行此函数*/
$(function(){
	//ajax显示欢迎用户
	$.ajax({
			"url":"loaduser.do",
			"type":"post",
			"dataType":"text",
			"success":function(data){
				$("#user").text("欢迎你:"+data);
			}
	});
});


/**
 * 点击图片发送ajax请求
 */
function tosingle(img){
	
		$.ajax({
			"url":"tosingle.do",
			"type":"post",
			"data":"id=2",
			"dataType":"text",
			"success":function(data){
				alert(data);
				//$("#user").text("欢迎你:"+data);
			}
		
		});
	
	
	//img.setAttribute('src','tosingle.do');
	//img.setAttribute('id','2');
}

