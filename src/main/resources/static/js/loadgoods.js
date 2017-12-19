function loadgoods(result){
	 //此处是List<Good>集合
	 //打桩:输出商品id
	
	
	 console.log("加载长度:"+result.length);
	 
	 var div="";
	 for(var i=0;i<result.length;i++){
		 var id=result[i].good_id;
		 var name =result[i].good_name;
		 var price  =result[i].good_price;
		 var img = result[i].good_img;
		 var num = result[i].good_num;
		 var type = result[i].good_type;
		 div = div+'<div class="col-md-3 col-md2">'+
					'<div class="col-md1 simpleCart_shelfItem">'+
			'<a href="javascript:single('+id+')" >'+
				'<img class="img-responsive" src='+img+' alt="" />'+
			'</a>'+
			'<h3><a href="single.html">'+name+'</a></h3>'+
			'<div class="price">'+
					'<h5 class="item_price">'+price+'</h5>'+
					'<a href="javascript:addShopCar('+id+')" class="item_add">加入购物车</a>'+
					'<div class="clearfix"> </div>'+
			'</div>'+
		  '</div>'+
	    '</div>';
		 console.log(name);
	 }
	 $("#divs").html(div+""); 
}

/*将指定商品加入购物车*/
function addShopCar(id) {
	if(!getCookie("id")){
		return;
	}else{
        $.ajax({
            url:path+"/api/shopCar/addGoodToShopCar?userId="+getCookie("id")+"&goodId="+id,
            beforeSend: function(request){
                request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
            },
            type:"get",
            dataType:"json",
            success:function(result){
                window.location.href="shopcar.html";
            }
        })
	}


}