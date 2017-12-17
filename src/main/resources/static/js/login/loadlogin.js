$(function(){
    //用户切换到登录页面的时候,自动清空cookie
    delCookie("id");
    delCookie("code");

	/*检查输入帐号以及密码是否正确*/
    $("#Login").click(function(){

        var usercode = $("#usercode").val().trim();
        var password = $("#password").val().trim();

        var ok=true;
        $("#msg").text("");
        if(usercode==""){
            $("#msg").text("用户名或密码为空！");
            ok=false;
        }else if(password==""){
            $("#msg").text("用户名或密码为空！");
            ok=false;
        }
        var data = {
            "username":usercode,
            "password":password,
            "grant_type":"password",
            "scope":"select",
            "client_id":"client_2",
            "client_secret":"123456"
        }


        if(ok){
            $.ajax({
                contentType : 'application/json',
                url:path+"/oauth/token?username="+usercode+"&password="+password+"&grant_type=password&scope=select&client_id=client_2&client_secret=123456",
                type:"post",
                dataType:"json",
                success:function(result){//返回user对象
                    console.log(result);
                    addCookie("token",result.access_token,2);
                    console.log(getCookie("token"));
                    //通过用户名获取用户信息，并封装，放在cookie里面
                    $.ajax({
                        contentType : 'application/json',
                        beforeSend: function(request){
                            request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
                        },
                        url:path+"/user/getUser/ByUserNameOrMail?code="+usercode,
                        type:"get",
                        dataType:"json",
                        success:function(userInfo){
                            // console.log(response);
                            // console.log(status);
                            // console.log(xhr);
                            console.log(userInfo);
                            addCookie("user",userInfo);
                            addCookie("code",userInfo.username);
                            window.location.href="index.html";
                        },
                        timeout:5000
                    });

                },
                error:function(){
                    alert("登录失败123！");
                }
            });
        }
    });
});