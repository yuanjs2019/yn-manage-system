$(function () {
    $("#blog-Content .btn-close").click(function () {
        closeModal2();
    });
    //保存
    $("#content-save-button").click(function () {
        var $form = $('#blog-Content');
        //id
        var id = $form.find("input[name='id']").val();
        //内容
        var textContent = $form.find("textarea[name='textContent']").text();
        //内容
        var content  = $form.find("textarea[name='text']").text();
        $.post(ctx + "blog/saveContent",
            {"id": id, "content": content, "textContent": textContent},
            function (r) {
            if (r.code === 0) {
                closeModal2();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
});

function closeModal2() {
    validator.resetForm();
    var $form = $('#blog-Content');
    $form.find("input[name='id']").val('');
    $form.find("textarea[name='textContent']").text('');
    $form.find("textarea[name='text']").text('');
    $MB.closeAndRestModal("blog-Content");
}