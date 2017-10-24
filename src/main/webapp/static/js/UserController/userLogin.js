$(
	//登陆
	function() {
		function validateStatus() {
			
			$("#name").click(function() {
				$("#p1").text("");
			});
			$("#password").click(function() {
				$("#p2").text("");
			});
		};

		//	输入框验证
		/*validateStatus();*/
		//提交表单
		$("#login").click(function() {
			var name = $("#name"),
				password = $("#password");
			if (!name.val()) {
				layer.msg("用户名不能为空!")
				return;
			}
			if (!password.val()) {
				layer.msg("密码不能为空!")
				return;
			}
			var btn = $(this).button('loading');
			$.ajax({
				url: "index/login",
				cache: false,
				type: "POST",
				data: {
					'username': name.val(),
					'password': md5(password.val())
				},
				dataType: "JSON",
				success: function(result) {
					console.log(result);
					if (result.code == 0) {
						btn.button('success');
						setTimeout(function() {
							window.location.href = "index.html";
						}, 500);
					} else {
						layer.msg("账号或密码错误!");
						btn.button('fail');
						setTimeout(function() {
							btn.button('reset');
						}, 1000);
						
					}
				}
			});
		});
	});