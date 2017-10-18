/*
 * 这个js是根据用户id加载购物车的商品
 * */
function loadshopcar(user_id){

	//检查用户id是否为空
	if(user_id==null){
		alert("登录一下啦");
		window.location.href="mylogin.html";
		return null;
	}
	
	//若不为空则可以发送ajax请求
	$.ajax({
		url:path+"/loadgoodsByUserId.do",
		data:{"userid":user_id},
		async:false,
		type:"post",
		dataType:"json",
		success:function(result){
			
			//alert("根据用户名加载他的商品成功!");
			console.log(result);
			var str ="";
			var map = result.data;
			console.log(map);
			
			/************************
			 * 新收获---在js中遍历map集合
			 ************************
			 */
			for(var key in map){
				
				str="";
				var goodid=key;
				var num=map[key];
				console.log("goodid:"+goodid);
								
				//此时还要根据goodid查询出商品的详细信息
				$.ajax({
					url:path+"/loadGoodById.do",
					data:{"id":goodid},
					type:"post",
					async:false,//特别注意,ajax在发送请求时,默认为异步,此时需要发送请求前的num,则需要关闭异步
					dataType:"json",
					success:function(result){
						//alert("lalala");
						var goodname = result.data.good_name;
						var gooddesc=result.data.good_desc;
						var goodprice = result.data.good_price;
						var goodimg=result.data.good_img;
						
						str='<tr>'+
						'<td class="ring-in"><a href="single.html" class="at-in"><img src='+goodimg+' class="img-responsive" alt=""></a>'+
						'<div class="sed">'+
						'<h5>'+goodname+'</h5>'+
						'<p>'+gooddesc+'</p>'+				
						'</div>'+
						'<div class="clearfix"> </div></td>'+
						'<td class="check"><input type="text" readonly value='+num+' ></td>	'+	
						'<td>'+goodprice+'</td>'+
						'<td>待完善</td>'+
						'<td><input type="button" onclick="del(this,'+goodid+')" id="delshopcar" value="X"/></td>'+
					    '</tr>'
						//tr节点加载完毕后,将其添加到父节点的最后
						$("#trs").append(str);
					},
					error:function(){
						alert("添加超时,需要重新登录啦");
						window.location.href="mylogin.html";
					}
				});
			}
			
		
		},
		error:function(){
			alert("根据用户名加载商品失败");
		}
		
	});
	
	
	
	
	
}