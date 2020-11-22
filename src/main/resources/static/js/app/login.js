function reloadCode() {
    $("#validateCodeImg").attr("src", ctx + "gifCode?data=" + new Date() + "");
}

function login() {
    var $loginButton = $("#loginButton");
    var username = $(".sign-in input[name='username']").val().trim();
    var password = $(".sign-in input[name='password']").val().trim();
    var code = $(".sign-in input[name='code']").val().trim();
    var rememberMe = $(".sign-in input[name='rememberme']").is(':checked');
    if (username === "") {
        alert("请输入用户名！");
        return;
    }
    if (password === "") {
        alert("请输入密码！");
        return;
    }
    if (code === "") {
        alert("请输入验证码！");
        return;
    }
    $loginButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");

    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "code": code,
            "rememberMe": rememberMe
        },
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                location.href = ctx + 'index';
            } else {
                reloadCode();
                alert(r.msg);
                $loginButton.html("登录");
            }
        }
    });
}

function regist() {
    var username = $(".sign-up input[name='username']").val().trim();
    var password = $(".sign-up input[name='password']").val().trim();
    var cpassword = $(".sign-up input[name='cpassword']").val().trim();
    if (username === "") {
        alert("用户名不能为空！");
        return;
    } else if (username.length > 10) {
        alert("用户名长度不能超过10个字符！");
        return;
    } else if (username.length < 3) {
        alert("用户名长度不能少于3个字符！");
        return;
    }
    if (password === "") {
        alert("密码不能为空！");
        return;
    }
    if (cpassword === "") {
        alert("请再次输入密码！");
        return;
    }
    if (cpassword !== password) {
        alert("两次密码输入不一致！");
        return;
    }
    $.ajax({
        type: "post",
        url: ctx + "user/regist",
        data: {
            "username": username,
            "password": password
        },
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $MB.n_success("注册成功，请登录");
                $(".sign-up input[name='username']").val("");
                $(".sign-up input[name='password']").val("");
                $(".sign-up input[name='cpassword']").val("");
                $('.form-toggle').trigger('click');
            } else {
                alert(r.msg);
            }
        }
    });
}

document.onkeyup = function (e) {
    if (window.event)
        e = window.event;
    var code = e.charCode || e.keyCode;
    if (code === 13) {
        login();
    }
};