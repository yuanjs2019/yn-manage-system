function updateSeries() {
    var selected = $("#seriesTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的系列！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个系列！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "series/getseries", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#series-add');
            $form.modal();
            var series = r.msg;
            $("#series-add-modal-title").html('修改系列');
            $form.find("input[name='id']").val(series.id);
            $form.find("input[name='sericsName']").val(series.sericsName);
            $form.find("input[name='sericsCode']").val(series.sericsCode);
            $("#series-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}