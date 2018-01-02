/**
 * Created by 刘亮 on 2017/12/27.
 */
$(function(){
    //校验是否登陆：
    var token = getCookie("id");
    if(!token){
        alert("请登陆后上传商品！");
        location.href="mylogin.html";
        return;
    }


    $("#send").click(function(){
        var name = $("#good_name").val().trim();
        var price  =$("#good_price").val().trim();
        var desc=$("#good_desc").val().trim();
        var ok=true;
        if(name==""){
            alert("商品名称为空！");
            ok=false;
        }
        if(price==""){
            alert("价格为空！");
            ok=false;
        }

        var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
        if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
            alert("请选择图片");
            return;
        }
        var formFile = new FormData();

        formFile.append( "userId",getCookie("id"));
        formFile.append("attachmentType","GOOD");
        formFile.append("file",fileObj);
        formFile.append("good_name",name);
        formFile.append("good_price",price);
        formFile.append("good_desc",desc);
        if(ok){
            $.ajax({
                url:path+"/api/attachment/upload",
                data:formFile,
                enctype: 'multipart/form-data',
                type: "Post",
                cache: false,//上传文件无需缓存
                processData: false,//用于对data参数进行序列化处理 这里必须false
                contentType: false, //必须
                beforeSend: function(request){
                    request.setRequestHeader("Authorization", 'Bearer '+getCookie("token"));
                },
                dataType:"json",
                success:function(result){
                    alert("上传成功");
                    location.href="index.html";
                },
                error:function () {
                    alert("上传失败");
                }



            })
        }



        });
});


