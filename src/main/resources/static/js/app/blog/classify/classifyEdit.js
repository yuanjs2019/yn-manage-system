function updateClassify() {
    var selected = $("#classifyTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的分类！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个分类！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "classify/getClassify", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#classify-add');
            $form.modal();
            var classify = r.msg;
            $("#classify-add-modal-title").html('修改分类');
            $form.find("input[name='id']").val(classify.id);
            $form.find("input[name='classifyName']").val(classify.classifyName);
            $form.find("input[name='classifyCode']").val(classify.classifyCode);
            $("#classify-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}