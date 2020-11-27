function updateBlog() {
    var selected = $("#blogTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的博客！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个博客！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "blog/getBlog", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#blog-add');
            $form.modal();
            var blog = r.msg;
            $("#blog-add-modal-title").html('修改博客');
            $form.find("input[name='id']").val(blog.id);
            $form.find("input[name='title']").val(blog.title);
            $form.find("input[name='description']").val(blog.description);
            $form.find("input[name='author']").val(blog.author);
            $form.find("input[name='description']").val(blog.description);
            $form.find("input[name='mainPicture']").val(blog.mainPicture);
            $("input:radio[value='" + blog.type + "']").prop("checked", true);
            var seriesArr = [];
             seriesArr.push(blog.seriesCode);
            $form.find("select[name='sericsSelect']").multipleSelect('setSelects', seriesArr);
            $form.find("input[name='seriesCode']").val($form.find("select[name='sericsSelect']").val());


            var tagArr = [];
            for (var i = 0; i < blog.tags.length; i++) {
                tagArr.push(blog.tags[i].id);
            }
            $form.find("select[name='tagSelect']").multipleSelect('setSelects', tagArr);
            $form.find("input[name='tags']").val($form.find("select[name='tagSelect']").val());

            var clssArr = [];
            for (var i = 0; i < blog.classifies.length; i++) {
                clssArr.push(blog.classifies[i].id);
            }
            $form.find("select[name='classifySelect']").multipleSelect('setSelects', clssArr);
            $form.find("input[name='classies']").val($form.find("select[name='classifySelect']").val());

            $("#blog-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}