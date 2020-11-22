$(function() {
    var $classifyTableForm = $(".classify-table-form");
    var settings = {
        url: ctx + "classify/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                classifyName: $classifyTableForm.find("input[name='classifyNamee']").val().trim(),
                classifyCode: $classifyTableForm.find("input[name='classifyCodee']").val().trim()
            };
        },
        columns: [{
                checkbox: true
            },
            {
                field: 'id',
                title: '主键ID',
                width: 150
            }, {
                field: 'classifyName',
                title: '分类名称'
            }, {
                field: 'classifyCode',
                title: '分类编码'
            }
        ]
    };

    $MB.initTable('classifyTable', settings);
});

function search() {
    $MB.refreshTable('classifyTable');
}

function refresh() {
    $(".classify-table-form")[0].reset();
    search();
}

function deleteClassifys() {
    var selected = $("#classifyTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的分类！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的分类？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'classify/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportClassifyExcel(){
	$.post(ctx+"classify/excel",$(".classify-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportClassifyCsv(){
	$.post(ctx+"classify/csv",$(".classify-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}