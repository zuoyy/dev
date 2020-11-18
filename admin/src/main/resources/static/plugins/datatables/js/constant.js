/*常量*/
var CONSTANT = {
		DATA_TABLES : {
			DEFAULT_OPTION : { //DataTables初始化选项
				autoWidth: false,	//禁用自动调整列宽
                stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
                order: [],			//取消默认排序查询,否则复选框一列会出现小箭头
                processing: false,	//隐藏加载提示,自行处理
                serverSide: true,	//启用服务器端分页
                searching: false,	//禁用原生搜索
                bLengthChange: false
                
			},
			COLUMN: {
                CHECKBOX: {	//复选框单元格
                	title:'<input type="checkbox" name="cb-check-all">',
                    className: "td-checkbox",
                    orderable: false,
                    width: "30px",
                    data: null,
                    render: function (data, type, row, meta) {
                        return '<input type="checkbox" class="iCheck">';
                    }
                }
            },
            RENDER: {	//常用render可以抽取出来
                ELLIPSIS: function (data, type, row, meta) {
                	data = data||"";
                	return '<span title="' + data + '">' + data + '</span>';
                },
                DATE_TIME: function (data, type, row, meta) {
                    return data ? new Date(data).format("yyyy-MM-dd hh:mm") : '';
                },
                DATE: function (data, type, row, meta) {
                    return data ? new Date(data).format("yyyy-MM-dd") : '';
                }
            }
		}
};

var manage = {
    currentItem: null,
    init: function () {
        $(".table-form-tip").tooltip();
    },
    getIds: function () {
        var ids = [];
        $("tbody :checkbox:checked", $table).each(function (i) {
            var item = _table.row($(this).closest('tr')).data();
            ids.push(item.id);
        });
        return ids;
    },
    getQueryCondition: function (data) {
        var param = $("#searchForm").serializeJson();
        // 排序参数
        if (data.order && data.order.length && data.order[0]) {
            param.column = data.columns[data.order[0].column].data;
            param.sort = data.order[0].dir;
        }
        // 分页参数
        param.startIndex = data.start;
        param.pageSize = data.length;
        param.draw = data.draw;
        return param;
    },
    tableToolBar: function (tableButtons,row) {
        var btn_html = '';
        Object.keys(tableButtons).forEach(function (index) {
            var i = tableButtons[index];
            btn_html += '<button style="margin-left: 4px;" id="' + i.btnId + '" class="' + i.btnClass + '" onclick="' + i.btnOnclick + '(' + JSON.stringify(row).replace(/"/g, '&quot;') + ')">' + i.title + '</button>'

        });
        return btn_html;
    }

};
