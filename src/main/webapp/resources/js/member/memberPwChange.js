/*유효성 검사*/
function gfncPatternCheck(str){
    var checkNumber = str.search(/[0-9]/g);
    var checkEnglish = str.search(/[a-z]/ig);
    var checkExp = str.search(/[~!@#$%^&*()_+|<>?:{}]/gi);
 
    if(!/^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+|<>?:{}])(?=.*[0-9]).{8,12}$/.test(str)){            
        return false;
    }else if(checkNumber < 0 || checkEnglish < 0 || checkExp < 0){
        return false;
    }else{
    	return true;
    }
}

function fnChngPwApi() { //  fnChngPwApi
	/*
	사용자 입력정보 VALIDATION 체크
	해당 열  input, select 박스가 하나라도 미기재 된 경우 validation false
	최초 미입력 된 element로 focus 이동됨
	*/
	var frstIdx = "";
	jQuery(".intList span").each(function(){
		var $this = jQuery(this);
		var validYn = true;
		
		$this.find("input[type='text'],input[type='password']").each(function(idx){
			var value = jQuery(this).val();
			var id = jQuery(this).attr("id");
			if(value == "" && id != "emailType"){
				validYn = false;
				if(frstIdx == ""){
					frstIdx = jQuery(this);
				}
			}
		});
		
		if(!validYn){
			$this.addClass("error");
			$this.find(".alertMessage").show();
		}else{
			$this.removeClass("error");
			$this.find(".alertMessage").hide();
		}
	});
	
	if(frstIdx != ""){
		frstIdx.focus();
		return false;
	}
	
	var userPw = $("#curPw").val();// 현재비밀번호
	var newPw = $("#newPw").val(); 	// 새로운 비밀번호
	var newPwRe = $("#newPwRe").val();// 새로운 비밀번호 확인
	
	//alert(userPw + newPw + newPwRe);
	
	if (newPw != newPwRe) {// 비밀번호 확인 일치 검증
		alert('비밀번호 확인이 새로운 비밀번호와 일치하지 않습니다.');
		jQuery("#newPw").focus();
		return;
	} else if(curPw == newPw) {// 이전 비밀번호와 새 비밀번호 일치 검증
		alert('현재 비밀번호와 새로운 비밀번호가 일치합니다.');
		jQuery("##curPw").focus();
		return;
	} else if (!gfncPatternCheck(newPw)) {// 비밀번호1 형식 검증
		alert('비밀번호는 영문/숫자/특수문자 조합 8~12자리까지 입력 가능합니다.');
		jQuery("#newPw").focus();
		return;
	} else if (!gfncPatternCheck(newPwRe)) {// 비밀번호2 형식 검증
		alert('비밀번호는 영문/숫자/특수문자 조합 8~12자리까지 입력 가능합니다.');
		jQuery("#newPwRe").focus();
		return;
	} else {
		var sendPw = {curPw : userPw, newPw : newPw};
		
		$.ajax({
			url:'http://localhost:9091/www/myPage/memberUpdatePwAction/json',
			type:'post',
			data: JSON.stringify(sendPw),
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				if(response.result == 1) {
					alert('비밀번호가 변경되었습니다. 다시 로그인해주세요.');
					location.href='http://localhost:9091/www/login';
				} else {
					alert('비밀번호가 변경되지 않았습니다.');
					location.href='http://localhost:9091/www/memberPwChange';
				}
			},
			error:function(request, status, error) { 
				alert("code: " + request.status + "\n" + "massage: " + request.responseText + "\n" + "error: " + error); 
			}
		});
	}

}