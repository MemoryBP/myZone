$(function() {
	//提交表单
	$("#userInfo_update").click(function() {
		var user_id = $("#user_id"),
			name = $("#uname"),
			password = $("#upassword"),
			email = $("#uemail"),
			phone = $("#uphone"),
			type = $("#utype"),
			idcard = $("#uidcard"),
        ucreateDate = $("#ucreateDate").val(),
        uupdateDate = $("#uupdateDate").val();
		if (!name.val()) {
			layer.msg('用户名不能为空!');
			return;
		}
		if (!password.val()) {
			layer.msg('密码不能为空!');
			return;
		}
		if (!email.val()) {
			layer.msg('邮箱地址不能为空!');
			return;
		}
		if (!phone.val()) {
			layer.msg('手机号不能为空!');
			return;
		}
		if (!idcard.val()) {
			layer.msg('身份证不能为空!');
			return;
		}
		if (!type.val()) {
			layer.msg('类型不能为空!');
			return;
		}
		if(!user_id.val()){
			layer.msg('id不能为空!');
			return;
		}
		
		var params = {
				'id':user_id.val(),
				'username': name.val(),
				'password': password.val(),
				'email': email.val(),
				'phone': phone.val(),
				'type': type.val(),
				'idCard': idcard.val(),
				'createDate': ucreateDate,
				'updateDate': uupdateDate
			};
		var btn = $(this).button('loading');
		layer.confirm('确定修改当前数据?', {
			shadeClose : true,
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				url: "index/update",
				cache: false,
				type: "POST",
				data: JSON.stringify(params),
				dataType: "JSON",
				contentType: "application/json",
				success: function(result) {
					if (result.code == 0) {
						layer.msg(result.msg);
						btn.button('success');
						getUsers(pageSize,page)
						console.log(pageSize,page)
						setTimeout(function() {
							btn.button('reset');
							$('#userInfoW').modal('hide');
						}, 500);
					} else {
						btn.button('fail');
						setTimeout(function() {
							btn.button('reset');
						}, 1000);
					}
				}
			})
			layer.closeAll()
		},function() {
			btn.button('reset');
		});
		btn.button('reset');
	});
});