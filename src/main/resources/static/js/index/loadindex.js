    var code;
    var page;
    $(function () {
        //设置点击页数使页数改变事件
    	//将读出的字符型转为整型
	    page = parseInt($("#page").val());
	    //alert("page:"+page);
	    $("#next").click(function(){
	    	page=page+1;
		    $("#page").val(page);
            findGoods($("#page").val(),'');
	    });
	    $("#pre").click(function(){
	    	if(page==1){
	    		return;
	    	}
	    	page=page-1;
	    	//alert(page);
		    $("#page").val(page);
            findGoods($("#page").val(),'');
	    });
	    
    
    $("#find").click(function(){
    	//alert("ss");
    	//通过输入的查询
        var input = $("#input").val();
    	var f=true;

		//发送ajax请求
        findGoods($("#page").val(),input);

    });
    	
      $("#slider").responsiveSlides({
      	auto: true,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });

      code = getCookie("code");
      var id=getCookie("id");
      
      if(code){
    	  $("#user").html("<span style='color:#' class='item_add'>欢迎你:"+code+"</span><a><input type='button' class='item_add' value='退出' id='but'/></a>");  
      }
    	$("#but").click(function(){
    		if(confirm("确定退出："+getCookie("code")+"?")){
           	    delCookie("code");
           	    delCookie("id");
           	    location.reload();	
    		}
    	});
      
      //alert(page);
        findGoods($("#page").val(),'');
      return false;
    });
    
    function findGoods(page,name) {
        $.ajax({
            url:path+"/api/good/getAllGoods?size=8&page="+(page-1)+"&name="+name,
            type:"get",
            //data:{"page":page},//分页查询,默认设置每页显示8个商品

            dataType:"json",
            success:function(result){
            	// alert(result.length);
                if(result.length == 0){
                  return;
                }
				//能查的出来数据，才去加载商品
                loadgoods(result);
            },
            error:function(){
                alert("加载失败");
            }
        });
		
    }
    
    
    
    function single(id){
    	
    	//将点中的商品id绑定到cookie并跳转到商品详情页面
    	//绑定id是为了使用户看到商品详情页面时,是自己选中
    	//的商品详情
    	// alert("详情id:"+id);
    	addCookie("good_id",id,1);
    	window.location.href="single.html";
    }
    
    
    function checkaccepts(){
    	
    	/*
    	先检查用户是否登录
    	*/
    	var flags = true;
    	var userid = getCookie("id");
    	if(userid==null){
    		if(confirm("亲,还登录呢?登录吗?")){
    			window.location.href="mylogin.html";
    		}
    		flags=false;
    	}
    	/*
    		自动检查是否填写了收货地址
    		若填写过,则算修改
    	*/
    	
    	if(flags){
    		tocheck();
    	}
    }
    