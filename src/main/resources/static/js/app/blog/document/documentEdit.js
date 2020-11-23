function updateDocument() {
    var selected = $("#documentTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的文档！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个文档！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "document/getDocument", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#document-add');
            $form.modal();
            var document = r.msg;
            $("#document-add-modal-title").html('修改文档');
            $form.find("input[name='id']").val(document.id);
            $form.find("input[name='title']").val(document.title);
            $form.find("input[name='cover']").val(document.cover);
            $("#document-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}