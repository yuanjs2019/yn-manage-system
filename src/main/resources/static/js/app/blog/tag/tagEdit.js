function updateTag() {
    var selected = $("#tagTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的标签！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个标签！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "tag/getTag", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#tag-add');
            $form.modal();
            var tag = r.msg;
            $("#tag-add-modal-title").html('修改标签');
            $form.find("input[name='id']").val(tag.id);
            $form.find("input[name='tagName']").val(tag.tagName);
            $form.find("input[name='tagCode']").val(tag.tagCode);
            $("#tag-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}