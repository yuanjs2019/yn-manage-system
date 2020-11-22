$(function() {
    var $tagTableForm = $(".tag-table-form");
    var settings = {
        url: ctx + "tag/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                tagName: $tagTableForm.find("input[name='tagNamee']").val().trim(),
                tagCode: $tagTableForm.find("input[name='tagCodee']").val().trim()
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
                field: 'tagName',
                title: '标签名称'
            }, {
                field: 'tagCode',
                title: '标签编码'
            }
        ]
    };

    $MB.initTable('tagTable', settings);
});

function search() {
    $MB.refreshTable('tagTable');
}

function refresh() {
    $(".tag-table-form")[0].reset();
    search();
}

function deleteTags() {
    var selected = $("#tagTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的标签！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的标签？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'tag/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportTagExcel(){
	$.post(ctx+"tag/excel",$(".tag-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportTagCsv(){
	$.post(ctx+"tag/csv",$(".tag-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}