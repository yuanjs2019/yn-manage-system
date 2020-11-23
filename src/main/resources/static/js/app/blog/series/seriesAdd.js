var validator;
var $seriesAddForm = $("#series-add-form");

$(function () {
    validateRule();

    $("#series-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $seriesAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "series/add", $seriesAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "series/update", $seriesAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#series-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#series-add-button").attr("name", "save");
    $("#series-add-modal-title").html('新增系列');
    validator.resetForm();
    $MB.closeAndRestModal("series-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $seriesAddForm.validate({
        rules: {
            sericsName: {
                required: true,
                maxlength: 100
            },
            sericsCode: {
                required: true,
                maxlength: 100
            },
        },
        messages: {
            sericsName: {
                required: icon + "请输入系列名称",
                maxlength: icon + "长度不能超过100个字符"
            },
            sericsCode: {
                required: icon + "请输入系列编码",
                maxlength: icon + "长度不能超过100个字符"
            }
        }
    });
}