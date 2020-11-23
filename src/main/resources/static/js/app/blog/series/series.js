$(function() {
    var $seriesTableForm = $(".series-table-form");
    var settings = {
        url: ctx + "series/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                sericsName: $seriesTableForm.find("input[name='sericsNamee']").val().trim(),
                sericsCode: $seriesTableForm.find("input[name='sericsCodee']").val().trim()
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
                field: 'sericsName',
                title: '系列名称'
            }, {
                field: 'sericsCode',
                title: '系列编码'
            }
        ]
    };

    $MB.initTable('seriesTable', settings);
});

function search() {
    $MB.refreshTable('seriesTable');
}

function refresh() {
    $(".series-table-form")[0].reset();
    search();
}

function deleteSeriess() {
    var selected = $("#seriesTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的系列！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的系列？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'series/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportSeriesExcel(){
	$.post(ctx+"series/excel",$(".series-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportSeriesCsv(){
	$.post(ctx+"series/csv",$(".series-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}