function withdrawalApi() {
		var notichk = document.getElementById('notice').checked;
		
		if(!notichk) {
			alert('유의사항 숙지에 동의해주시기 바랍니다.');
		} else {
			var chk = {notichk : notichk};
			
			$.ajax({
				url:'/www/myPage/memberDelete2Action/json',
				type:'post',
				data: JSON.stringify(chk),
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				success: function(response) {
					if(response.result) {
						alert('회원탈퇴가 완료되었습니다.');
						location.href='/www/';
					} else {
						alert('회원탈퇴에 실패하였습니다.');
						location.href='/www/memberDelete';
					}
				},
				error:function(request, status, error) { 
					alert("code: " + request.status + "\n" + "massage: " + request.responseText + "\n" + "error: " + error); 
				}
			});
		}//end.else
}