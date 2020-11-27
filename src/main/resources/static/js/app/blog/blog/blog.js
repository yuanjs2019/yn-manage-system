$(function () {
    var $blogTableForm = $(".blog-table-form");
    var settings = {
        url: ctx + "blog/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                title: $blogTableForm.find("input[name='title']").val().trim(),
                author: $blogTableForm.find("input[name='author']").val().trim(),
                type: $blogTableForm.find("select[name='type']").val(),
                year: $blogTableForm.find("input[name='year']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        },
            {
                field: 'id',
                title: '主键ID',
                width: 150
            }, {
                field: 'title',
                title: '文章标题'
            }, {
                field: 'mainPicture',
                title: '文章头图片',
                formatter: function (value, row, index) {
                    return '<img width="45px" height="19px" src="' + value + '">';
                }
            }, {
                field: 'author',
                title: '作者'
            }, {
                field: 'seriesName',
                title: '系列名称'
            }, {
                field: 'tags',
                title: '标签',
                formatter: function (value, row, index) {
                    var tagx = "";
                    $.each(value, function () {
                        tagx += this.tagName + ","
                    })
                    if (new RegExp(',').test(tagx)) {
                        return tagx.substring(0, tagx.length - 1);
                    }
                    return tagx;
                }
            }, {
                field: 'classifies',
                title: '分类',
                formatter: function (value, row, index) {
                    var clasx = "";
                    $.each(value, function () {
                        clasx += this.classifyName + ","
                    })
                    if (new RegExp(',').test(clasx)) {
                        return clasx.substring(0, clasx.length - 1);
                    }
                    return clasx;
                }
            }, {
                field: 'type',
                title: '文章类型',
                formatter: function (value, row, index) {
                    if (value === 1) return '<span class="badge badge-danger">转载</span>';
                    if (value === 2) return '<span class="badge badge-success">原创</span>';
                }
            }, {
                field: 'year',
                title: '年份'
            }, {
                field: 'month',
                title: '月份'
            }, {
                field: 'createTime',
                title: '创建时间'
            }, {
                field: 'updateTime',
                title: '更新时间'
            }, {
                title: '操作',
                formatter: function (value, row, index) {
                    return "<a href='#' onclick='interConent(\"" + row.id + "\")'>内容修改</a>";
                }
            }
        ]
    };

    $MB.initTable('blogTable', settings);
});

function search() {
    $MB.refreshTable('blogTable');
}

function refresh() {
    $(".blog-table-form")[0].reset();
    search();
}

function deleteBlogs() {
    var selected = $("#blogTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的博客！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的博客？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'blog/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportBlogExcel() {
    $.post(ctx + "blog/excel", $(".blog-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportBlogCsv() {
    $.post(ctx + "blog/csv", $(".blog-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function interConent(id) {
    //获取内容数据，进行回显
    $.post(ctx + "blog/getContent", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#blog-Content');
            var blog = r.msg;
            if (blog == null) {
                $form.find("input[name='id']").val('');
                $form.find("textarea[name='textContent']").text('');
                $form.find("textarea[name='text']").text('');
            } else {
                $form.find("input[name='id']").val(blog.id);
                if(blog.textContent==null){
                    $form.find("textarea[name='textContent']").text('');
                    $form.find("textarea[name='text']").text('');
                }else{
                    $form.find("textarea[name='textContent']").text(blog.textContent);
                    $form.find("textarea[name='content']").html(blog.content);
                }
            }
            EditorOpen();
            $form.modal();
        } else {
            $MB.n_danger(r.msg);
        }
    });

}

function EditorOpen() {
    var contentEditor;
    $(function () {
        contentEditor = editormd("content-editormd", {
            width: "100%",
            height: 500,
            syncScrolling: "single",
            path: ctx + "editor/lib/",
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/file",
            saveHTMLToTextarea: true,
            previewTheme: "dark"
        });
    });
}