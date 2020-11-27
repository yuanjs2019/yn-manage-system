var validator;
var $blogAddForm = $("#blog-add-form");
var $sericsSelect = $blogAddForm.find("select[name='sericsSelect']");
var $serics = $blogAddForm.find("input[name='seriesCode']");

var $classifySelect = $blogAddForm.find("select[name='classifySelect']");
var $classies = $blogAddForm.find("input[name='classies']");
var $tagSelect = $blogAddForm.find("select[name='tagSelect']");
var $tags = $blogAddForm.find("input[name='tags']");

$(function () {
    validateRule();
    initSeries();
    initTags();
    initClassies()

    $("#blog-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $blogAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "blog/add", $blogAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "blog/update", $blogAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#blog-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#blog-add-button").attr("name", "save");
    $("#blog-add-modal-title").html('新增博客');
    validator.resetForm();
    $sericsSelect.multipleSelect('setSelects', []);
    $sericsSelect.multipleSelect("refresh");
    $classifySelect.multipleSelect('setSelects', []);
    $classifySelect.multipleSelect("refresh");
    $tagSelect.multipleSelect('setSelects', []);
    $tagSelect.multipleSelect("refresh");
    $MB.closeAndRestModal("blog-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $blogAddForm.validate({
        rules: {
            title: {
                required: true,
                maxlength: 500
            },
            description: {
                required: true,
                maxlength: 2000
            },
        },
        messages: {
            title: {
                required: icon + "请输入文章标题",
                maxlength: icon + "长度不能超过500个字符"
            },
            description: {
                required: icon + "请输入文章描述",
                maxlength: icon + "长度不能超过2000个字符"
            }
        }
    });
}


function initSeries() {
    $.post(ctx + "series/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].sericsCode + "'>" + data[i].sericsName + "</option>"
        }
        $sericsSelect.html("").append(option);
        var options = {
            selectAllText: '所有系列',
            allSelected: '所有系列',
            width: '100%',
            onClose: function () {
                $serics.val($sericsSelect.val());
                validator.element("input[name='seriesCode']");
            }
        };

        $sericsSelect.multipleSelect(options);
    });
}

function initTags() {
    $.post(ctx + "tag/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].id + "'>" + data[i].tagName + "</option>"
        }
        $tagSelect.html("").append(option);
        var options = {
            selectAllText: '所有标签',
            allSelected: '所有标签',
            width: '100%',
            onClose: function () {
                $tags.val($tagSelect.val());
                validator.element("input[name='tags']");
            }
        };
        $tagSelect.multipleSelect(options);
    });
}

function initClassies() {
    $.post(ctx + "classify/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].id + "'>" + data[i].classifyName + "</option>"
        }
        $classifySelect.html("").append(option);
        var options = {
            selectAllText: '所有分类',
            allSelected: '所有分类',
            width: '100%',
            onClose: function () {
                $classies.val($classifySelect.val());
                validator.element("input[name='classies']");
            }
        };
        $classifySelect.multipleSelect(options);
    });
}