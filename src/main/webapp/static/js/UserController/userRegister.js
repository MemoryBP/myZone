$(function() {
	//提交表单
	$("#reg").click(function() {
		var name = $("#username"),
			password1 = $("#password1"),
			password2 = $("#password2"),
			email = $("#email"),
			phone = $("#phone"),
			idcard = $("#idcard");
		if (!name.val()) {
			layer.msg('用户名不能为空!');
			return;
		}
		if (!password1.val()) {
			layer.msg("密码不能为空!")
			return;
		}
		if (!password2.val()) {
			layer.msg("密码不能为空!")
			return;
		}
		if (password1.val() != password2.val()) {
			layer.msg("两次密码输入不正确!")
			return;
		}
		if (!email.val()) {
			layer.msg("邮箱地址不能为空!")
			return;
		}
		if (!phone.val()) {
			layer.msg("手机号不能为空!")
			return;
		}
		if (!idcard.val()) {
			layer.msg("身份证不能为空!")
			return;
		}

		var params = {
			'username': name.val(),
			'password': md5(password1.val()),
			'email': email.val(),
			'phone': phone.val(),
			'idCard': idcard.val()
		};
		var btn = $(this).button('loading');
		$.ajax({
			url: "index/register",
			cache: false,
			type: "POST",
			data: JSON.stringify(params),
			dataType: "JSON",
			contentType: "application/json",
			success: function(result) {
				if (result.code == 0) {
                    btn.button('success');
                    layer.msg(result.msg);
                    setTimeout(function () {
                        window.location.href = "login.html";
                    }, 500);
                }else if(result.code == 2) {
                    btn.button('fail');
                    layer.msg(result.data)
				} else {
					btn.button('fail');
					layer.msg("服务器异常!")
					/*setTimeout(function() {
						btn.button('reset');
					}, 1000);*/
				}
			}
		});

	});
});