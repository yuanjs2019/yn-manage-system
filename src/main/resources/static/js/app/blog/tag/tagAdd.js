var validator;
var $tagAddForm = $("#tag-add-form");

$(function () {
    validateRule();

    $("#tag-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $tagAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "tag/add", $tagAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "tag/update", $tagAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#tag-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#tag-add-button").attr("name", "save");
    $("#tag-add-modal-title").html('新增标签');
    validator.resetForm();
    $MB.closeAndRestModal("tag-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $tagAddForm.validate({
        rules: {
            tagName: {
                required: true,
                maxlength: 20
            },
            tagCode: {
                required: true,
                maxlength: 10
            },
        },
        messages: {
            tagName: {
                required: icon + "请输入标签名称",
                maxlength: icon + "长度不能超过20个字符"
            },
            tagCode: {
                required: icon + "请输入标签编码",
                maxlength: icon + "长度不能超过10个字符"
            }
        }
    });
}