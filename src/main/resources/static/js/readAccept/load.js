//加载收货地址
function load(){
	// alert("ssssss");
	var userId=getCookie("id");
	$.ajax({
		url:path+"/api/accept/getByUserId?userId="+userId,
		type:"get",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
        },
		async:false,
		dataType:"json",
		success:function(result){
				console.log(result);
				for(var i=0;i<result.length;i++){
					var accept_id  = result[i].accept_id;
                    var accept_name  = result[i].accept_name;
                    var user_id  = result[i].user_id;
                    var good_id  = result[i].good_id;
                    var address  = result[i].address;
					var phone = result[i].phone;

                    str='<tr>'+
                        '<td style="text-align:center;">'+accept_name+'</td>	'+
                        '<td style="text-align:center;">'+phone+'</td>	'+
                        '<td style="text-align:center;">'+address+'</td>'+
                        '<td style="text-align:center;">待完善</td>'+
                        '<td style="text-align:center;"><input type="button" onclick="deleteAccept(this,'+accept_id+')" id="delshopcar" value="X"/></td>'+
                        '</tr>';
                    //tr节点加载完毕后,将其添加到父节点的最后
                    $("#trs").append(str);

                 /*   sli="<li class='writes'>收货人:"+accept_name+"    电话:"+ phone +"     地址:"+ address+"</li>";
                    $("#userAccept").append(sli);*/
				}

		},
		error:function(){
			alert("加载收货地址失败");
		},
		
	});
	
}

function deleteAccept(acceptId){
	$.ajax({
        url:path+"/api/accept/deleteById?id="+acceptId,
        type:"delete",
        beforeSend: function(request){
            request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
        },
        async:false,
        dataType:"json",
        success:function(result){
            location.reload();
        },
		error:function () {
			alert("登陆失败，请联系管理员");
        }
	});
}

