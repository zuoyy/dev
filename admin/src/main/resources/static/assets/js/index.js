$(document).ready(function (){
    stopDrop();
});
function myProfile(id) {
    var addIndex = layer.open({
        title : "My Profile",
        type : 2,
        offset: 'rb',
        area: [ pageWidth, '89%'],
        content : basePath+"/system/my-profile?id="+id,
        success : function(){
            setTimeout(function(){
                layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            },500);
        }
    });
    window.onresize = function(){
        layer.style(addIndex, {
            width: $('#main').width()+'px'
        });
    }
}

function setLanguage(mylang,lang) {
    if(mylang!=lang){
        zy.ajax({
            url : basePath+'/system/setLanguage',
            data : {'language':lang},
            success : function(result) {
                if (result.code==200) {
                    window.location.reload();
                }else{
                    layer.alert(result.msg, {icon: 2});
                }
            }
        });
    }


}


