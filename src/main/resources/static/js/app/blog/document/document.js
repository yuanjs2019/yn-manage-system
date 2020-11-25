$(function() {
    var $documentTableForm = $(".document-table-form");
    var settings = {
        url: ctx + "document/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                title: $documentTableForm.find("input[name='title']").val().trim(),
                cover: $documentTableForm.find("input[name='cover']").val().trim()
            };
        },
        columns: [{
                checkbox: true
            },
            {
                field: 'id',
                title: '主键ID',
                width: 150
            },
            {
                field: 'title',
                title: '文档名称'
            },
            {
                field: 'cover',
                title: '封面',
                formatter: function (value, row, index) {
                    return '<img width="45px" height="63x" src="'+value+'">';
                }
            },
            {
                title: '操作',
                formatter: function (value, row, index) {
                    return "<a href='#' onclick='xx(\"" + row.id  + "\")'>详情</a>";
                }
            }
        ]
    };

    $MB.initTable('documentTable', settings);
});

function xx(id) {
  alert("获取到此id为:"+id);
  var xx ='<span name="classify" onclick="loadMain(this);" class="navigation__active"><i class=""></i>&nbsp;&nbsp;分类管理</span>';
    loadMain(xx,id);
}

function search() {
    $MB.refreshTable('documentTable');
}

function refresh() {
    $(".document-table-form")[0].reset();
    search();
}

function deleteDocuments() {
    var selected = $("#documentTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的文档！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的文档？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'document/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportDocumentExcel(){
	$.post(ctx+"document/excel",$(".document-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportDocumentCsv(){
	$.post(ctx+"document/csv",$(".document-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}