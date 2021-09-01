function fncWithDraPwCfmApi() {
	var password = $("#password").val();
	
	if (password.length == 0) {
		$('.intInner > span').addClass("error");
		$('.intInner > span').find(".alertMessage").show();
		return;
	}
	
	var pw = {password : password};
	
	$.ajax({
		url:'/www/myPage/memberDelete1Action/json',
		type:'post',
		data: JSON.stringify(pw),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			if(response.result) {
				alert('비밀번호가 일치합니다. 회원탈퇴는 신중히 해주시기 바랍니다.');
				location.href = '/www/memberDeleteNext';
			} else {
				alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
				$('#password').focus();
			}
		},
		error:function(request, status, error) { 
			alert("code: " + request.status + "\n" + "massage: " + request.responseText + "\n" + "error: " + error); 
		}
	});
}