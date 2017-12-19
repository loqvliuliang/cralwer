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
		url:path+"/api/shopCar/getShopCar/ByUserId?userId="+user_id,
        beforeSend: function(request){
            request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
        },
		async:false,
		type:"get",
		dataType:"json",
		success:function(result){
			
			//alert("根据用户名加载他的商品成功!");
			console.log(result);
			var str ="";
			var map = result;
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
					url:path+"/api/good/getGoodById",
                    beforeSend: function(request){
                        request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
                    },
					data:{"id":goodid},
					type:"get",
					async:false,//特别注意,ajax在发送请求时,默认为异步,此时需要发送请求前的num,则需要关闭异步
					dataType:"json",
					success:function(result){
						//alert("lalala");
						var goodname = result.good_name;
						var gooddesc=result.good_desc;
						var goodprice = result.good_price;
						var goodimg=result.good_img;

						console.log(result);
						
						str='<tr>'+
						'<td class="ring-in"><a href="toSingle('+goodid+')" class="at-in"><img src='+goodimg+' class="img-responsive" alt=""></a>'+
						'<div class="sed">'+
						'<h5>'+goodname+'</h5>'+
						'<p>'+gooddesc+'</p>'+				
						'</div>'+
						'<div class="clearfix"> </div></td>'+
						'<td class="check"><input type="text" readonly value='+num+' ></td>	'+	
						'<td>'+goodprice*num+'</td>'+
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


function toSingle(goodid) {
    $.ajax({
        url:path+"/api/good/getGoodById",
        data:{"id":goodid},
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
	
}