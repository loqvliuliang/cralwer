//加载完页面就运行此函数
$(function(){
    var ok = true;
    $("#Register").click(function(){
        var code = $("#usercode").val().trim();
        var email= $("#email").val().trim();
        var psw1 = $("#psw1").val().trim();
        var auth = $("#auth").val().trim();
        if(code==""){
            ok=false;
            $("#message").html("用户名为空!");
        }
        if(auth==""){
            $("#message").html("请输入邮箱验证码!");
        }
        if(psw1.length<6){
            ok=false;
            $("#message").html("密码不得少于6位!");
        }
        var data = {
            "userName":code,
            "password":psw1,
            "mail":email,
            "authCode":auth
        }
        if(ok){
            $.ajax({
                url:path+"/login/insertOrUpdate",
                type:"post",
                data:JSON.stringify(data),
                dataType:"json",
                contentType:"application/json",
                success:function () {
                    alert("注册成功!");
                },
                error:function (result) {
                    console.log(result);
                }
                
            })


        }
    });

    $("#authMail").click(function(){
        var email= $("#email").val().trim();
        if(email==""){
            ok=false;
            $("#message").html("请输入邮箱!");
        }
        if(ok){
            $.ajax({
                url:path+"/login/authMail?mail="+email,
                type:"get",
                dataType:"json",
                success:function(){
                    alert("发送成功!");
                },
                error:function (result) {
                    console.log(result);
                    $("#message").html("验证码发送失败!");
                }

            })
        }
    });

    $("#testDownLoad").click(function () {
        $.ajax({
            url:path+"/login/download?path=D:/budgetFile/2ec774f5-7aba-486c-bd48-cf2ae74c9d9f/budgetJournal/a9680bcf-f4a6-4893-a6ca-9084e86c00bf-阿里云.txt",
            type:"get",
            success:function (){
                
            },
            error:function(){

            }
        })
    });

});