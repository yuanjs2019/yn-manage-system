var validator;
var $documentAddForm = $("#document-add-form");

$(function () {
    validateRule();

    $("#document-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $documentAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "document/add", $documentAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "document/update", $documentAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#document-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#document-add-button").attr("name", "save");
    $("#document-add-modal-title").html('新增文档');
    validator.resetForm();
    $MB.closeAndRestModal("document-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $documentAddForm.validate({
        rules: {
            title: {
                required: true,
                maxlength: 50
            },
            cover: {
                required: true,
                maxlength: 100
            },
        },
        messages: {
            title: {
                required: icon + "请输入文档名称",
                maxlength: icon + "长度不能超过50个字符"
            },
            cover: {
                required: icon + "请输入封面地址",
                maxlength: icon + "长度不能超过100个字符"
            }
        }
    });
}