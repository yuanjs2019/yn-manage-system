var validator;
var $classifyAddForm = $("#classify-add-form");

$(function () {
    validateRule();

    $("#classify-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $classifyAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "classify/add", $classifyAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "classify/update", $classifyAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#classify-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#classify-add-button").attr("name", "save");
    $("#classify-add-modal-title").html('新增分类');
    validator.resetForm();
    $MB.closeAndRestModal("classify-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $classifyAddForm.validate({
        rules: {
            classifyName: {
                required: true,
                maxlength: 100
            },
            classifyCode: {
                required: true,
                maxlength: 100
            },
        },
        messages: {
            classifyName: {
                required: icon + "请输入分类名称",
                maxlength: icon + "长度不能超过100个字符"
            },
            classifyCode: {
                required: icon + "请输入分类编码",
                maxlength: icon + "长度不能超过100个字符"
            }
        }
    });
}