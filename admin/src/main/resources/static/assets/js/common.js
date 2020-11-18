var basePath,pageWidth;
layui.use(['layer','form', 'upload'], function () {
    var $ = layui.jquery;
    var layer = layui.layer; //加载layer模块
    var form = layui.form; //加载form模块
    var upload = layui.upload;  //加载upload模块

    basePath=getRootPath();
    pageWidth = $('#main').width()+'px';

    zy = {};
    zy.ajax = (function(params) {
        var load;
        var pp = {
            type : 'post',
            dataType : 'json',
            beforeSend : function() {
                load = layer.load(2);
            },
            complete : function(xhr, status) {
                if (xhr.status == 401) {
                    layer.confirm('session连接超时，需要重新登录', function () {
                        if (window.parent.window != window) {
                            window.top.location = basePath + '/login';
                        }
                    });
                }
                layer.close(load);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                layer.close(load);
                layer.alert("page error", {
                    icon : 2
                });
            }
        };
        $.extend(pp, params);
        $.ajax(pp);
    });
    zy.ajaxJson = (function(params) {
        var load;
        var pp = {
            type : 'post',
            dataType:"json",
            contentType:"application/json",
            beforeSend : function() {
                load = layer.load(2);
            },
            complete : function(xhr, status) {
                if (xhr.status == 401) {
                    layer.confirm('session连接超时，需要重新登录', function () {
                        if (window.parent.window != window) {
                            window.top.location = basePath + '/login';
                        }
                    });
                }
                layer.close(load);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                layer.close(load);
                layer.alert("page error", {
                    icon : 2
                });
            }
        };
        $.extend(pp, params);
        $.ajax(pp);
    });
    zy.ajaxSubmit = (function(form, params) {
        var load;
        var pp = {
            type : 'post',
            dataType : 'json',
            beforeSubmit : function() {
                load = layer.load(2);

            },
            complete : function(xhr, status) {
                if (xhr.status == 401) {
                    layer.confirm('session连接超时，需要重新登录', function () {
                        if (window.parent.window != window) {
                            window.top.location = basePath + '/login';
                        }
                    });
                }
                layer.close(load);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                layer.close(load);
                layer.alert("page error", {
                    icon : 2
                });
            }
        };
        $.extend(pp, params);
        $(form).ajaxSubmit(pp);
    });

    zy.openPage = (function (title,url) {
        window.layerIndex = layer.open({
            type: 2,
            title: title,
            move: false,
            area: ['100%', '100%'],
            content: basePath + url,
            success: function (layero, addIndex) {
                setTimeout(function () {
                    layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500);
            }
        });
        window.onresize = function(){
            layer.style(layerIndex, {
                width: $('#main').width()+'px'
            });
        }
    });

    /* 关闭弹出层 */
    $(".close-page").click(function (e) {
        e.preventDefault();
        parent.layer.close(window.parent.layerIndex);
    });



    /** 上传图片操作 */
    upload.render({
        elem: '.upload-image' //绑定元素
        ,url: $('.upload-image').attr('up-url') //上传接口
        ,field: 'image' //文件域的字段名
        ,acceptMime: 'image/*' //选择文件类型
        ,exts: 'jpg|jpeg|png|gif' //支持的图片格式
        ,multiple: true //开启多文件选择
        ,choose: function (obj) {
            obj.preview(function (index, file, result) {
                var upload = $('.upload-image');
                var name = upload.attr('name');
                var show = upload.parents('.layui-form-item').children('.upload-show');
                show.append("<div class='upload-item'><img src='"+ result +"'/>" +
                    "<input id='"+ index +"' type='hidden' name='"+name+"'/>" +
                    "<i class='upload-item-close layui-icon layui-icon-close'></i></div>");
            });
        }
        ,done: function(res, index, upload){
            var field = $('.upload-image').attr('up-field') || 'id';
            // 解决节点渲染和异步上传不同步问题
            var interval = window.setInterval(function(){
                var hide = $("#"+index);
                if(hide.length > 0){
                    var item = hide.parent('.upload-item');
                    if (res.code === 200) {
                        hide.val(res.data[field]);
                        item.addClass('succeed');
                    }else {
                        hide.remove();
                        item.addClass('error');
                    }
                    clearInterval(interval);
                }
            }, 100);
        }
    });

    // 删除上传图片展示项
    $(document).on("click", ".upload-item-close", function () {
        $(this).parent('.upload-item').remove();
    });


    $.fn.serializeJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(
            function() {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value ];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return serializeObj;
    };
    Date.prototype.format = function(format) {
        var o = {
            "M+" : this.getMonth() + 1, // month
            "d+" : this.getDate(), // day
            "h+" : this.getHours(), // hour
            "m+" : this.getMinutes(), // minute
            "s+" : this.getSeconds(), // second
            "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
            "S" : this.getMilliseconds()
            // millisecond
        }
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
        }
        for ( var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    };
    String.prototype.replaceAll = function(search, replacement) {
        var target = this;
        return target.split(search).join(replacement);
    };

    CommnUtil = {
        /**
         * 判断某对象不为空..返回true 否则 false
         */
        notNull: function (obj) {
            if (obj === null) {
                return false;
            } else if (obj === undefined) {
                return false;
            } else if (obj == "undefined") {
                return false;
            } else if (obj == "") {
                return false;
            } else if (obj == "[]") {
                return false;
            } else if (obj == "{}") {
                return false;
            } else {
                return true;
            }

        },
        notEmpty: function (obj) {
            if (obj === null) {
                return "";
            } else if (obj === undefined) {
                return "";
            } else if (obj == "undefined") {
                return "";
            } else if (obj == "") {
                return "";
            } else if (obj == "[]") {
                return "";
            } else if (obj == "{}") {
                return "";
            } else {
                return obj;
            }

        }
    }


});


function getRootPath() {
    var curPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    var localhostPath = curPath.substring(0, pos);
    var projectName = '';
    return (localhostPath + projectName);
}

function stopDrop() {
    var lastY;//最后一次y坐标点
    $(document.body).on('touchstart', function(event) {
        lastY = event.originalEvent.changedTouches[0].clientY;//点击屏幕时记录最后一次Y度坐标。
    });
    $(document.body).on('touchmove', function(event) {
        var y = event.originalEvent.changedTouches[0].clientY;
        var st = $(this).scrollTop(); //滚动条高度  
        if (y >= lastY && st <= 10) {//如果滚动条高度小于0，可以理解为到顶了，且是下拉情况下，阻止touchmove事件。
            lastY = y;
            event.preventDefault();
        }
        lastY = y;

    });
}