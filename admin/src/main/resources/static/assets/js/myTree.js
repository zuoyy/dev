

// 树形选择器
(function($){
    var self;
    var SelectTree=function(param){
        self=this;
        this.defaults={
            tree: $(".select-tree"),
            rootTree: null,
            onSelected: function () {}
        }
        this.options=$.extend({},this.defaults,param);
    }

    SelectTree.prototype={
        // 初始化
        init: function(){
            // 获取树形列表数据
            var tree = self.options.tree;
            // 构造悬浮选择器
            self.selector();
            // 重构选择框
            self.resetSelect(tree);
            // 点击时显示悬浮选择器
            tree.click(function(){
                var node = $(this);
                $.get(node.data('url'),function(result){
                    //if(result.data.length > 0){
                        // 显示定位悬浮选择器
                        self.position(node);
                        // zTree传递列表数据
                        self.zTreeReady(result.data, node);
                    //}
                });
            });
        },

        // 操作zTree组件
        zTreeReady: function(listData, node){
            var setting = {
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: function(event, treeId, treeNode){
                        node.val(treeNode.name);
                        node.siblings("[type='hidden']").val(treeNode.id);
                        $(".selectContent").hide();
                        self.options.onSelected(treeNode);
                    }
                }
            };

            // 封装zTree数据
            var zNodes = [];
            if(self.options.rootTree != null){
                zNodes.push({id: 0, name: self.options.rootTree, open: true});
            }
            listData.forEach(function (val) {
                var nav = {
                    id: val.id,
                    pId: val.pid,
                    name: val.title
                };
                if(nav.pId == '0'){
                    nav.isParent = true;
                    nav.open = true;
                }
                zNodes.push(nav);
            });

            $(document).ready(function(){
                $.fn.zTree.init($(".selectContent>.ztree"), setting, zNodes);
            });
        },

        // 构造悬浮选择器
        selector: function () {
            $("body").append("\n" +
                "<div class='selectContent'>" +
                "    <ul class='ztree'></ul>" +
                "</div>");
        },

        // 重构选择框
        resetSelect: function(tree){
            tree.each(function (key, item) {
                var name = $(item).attr("name");
                var value = $(item).data("value");
                $(item).removeAttr("name");
                $(item).attr("readonly",true);
                var input = $("<input name='"+name+"' type='hidden'>");
                if(value != undefined) input.val(value);
                $(item).after(input);
                $(item).after("<i class='layui-edge'></i>");
            });
        },

        // 显示定位悬浮选择器
        position: function (tree) {
            var source = self.options.tree;
            var offset = tree.offset();
            $(".selectContent").css({
                top: offset.top + tree.outerHeight() + 'px',
                left: offset.left + 'px',
                width: source.innerWidth()
            }).show();

            $("body").bind("click", function (e) {
                var target = $(e.target).parents(".selectContent");
                if(!target.length > 0){
                    $(".selectContent").hide();
                }
            });
        },
    }

    $.fn.selectTree=function(param){
        var selectTree=new SelectTree(param);
        return selectTree.init();
    }
})(jQuery);