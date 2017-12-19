    var code;
    var page;
    $(function () {
        //设置点击页数使页数改变事件
    	//将读出的字符型转为整型
	    page = parseInt($("#page").val());
	    //alert("page:"+page);
	    $("#next").click(function(){
	    	page=page+1;
	    	//alert(page);
		    $("#page").val(page);
	    });
	    $("#pre").click(function(){
	    	if(page==1){
	    		return;
	    	}
	    	page=page-1;
	    	//alert(page);
		    $("#page").val(page);
	    });
	    
    
    $("#find").click(function(){
    	//alert("ss");
    	//通过输入的查询
        var input = $("#input").val();
    	var f=true;
    	if(input==null){
    		alert("请输入查询信息！");
    		f=false;
    	}
    	
    	//输入不为空
    	if(f){
    		//发送ajax请求
            $.ajax({
            	url:path+"/findByInput.do",
          	    type:"post",
          	    data:{"input":input},
          	    dataType:"json",
        		success:function(result){
        			if(result.state==7){//查询输入为空
        				alert("输入查询信息为空");
        			}
        			//alert("查询成功");
        			//让页面加载商品
        			loadgoods(result);
        		},
        		error:function(){
        			alert("查询失败!");
        		}
        	});
    	}
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
      $.ajax({
    	  url:path+"/api/good/getAllGoods",
    	  type:"get",
    	  //data:{"page":page},//分页查询,默认设置每页显示8个商品
    	  
    	  dataType:"json",
    	  success:function(result){
    		 loadgoods(result);
    	  },
    	  error:function(){
    		  alert("加载失败");
    	  }
      });
      return false;
    });
    
    
    
    
    
    function single(id){
    	
    	//将点中的商品id绑定到cookie并跳转到商品详情页面
    	//绑定id是为了使用户看到商品详情页面时,是自己选中
    	//的商品详情
    	//alert("详情id:"+id);
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
    