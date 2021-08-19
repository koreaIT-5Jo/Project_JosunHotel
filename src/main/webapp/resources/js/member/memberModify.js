$(document).ready(function () {	
	// 코드 작성 영역	
	//input에 한글만 입력되게 설정
	var name = document.querySelector('#korNm');
	var onlyKorean = function() {
	  var pattern = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
	  this.value = this.value.replace(pattern, '');
	};
	name.addEventListener('keyup', onlyKorean);
	
	//이메일 도메인 선택 이벤트
	jQuery("#emailType").on("change", function(){
		$("#eDomain").val($(this).val());
		if($(this).val() == ""){
			$("#eDomain").prop("readonly", false);
		}else{
			$("#eDomain").prop("readonly", true);
		}
	});
	
});

function singupModify() {
	//정규식
	var nameJ = /^[가-힣]{2,20}$/;
	var telMidJ = /^[0-9]$/;
	var telEndJ = /^[0-9]$/;
	var email_rule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
	var email_id = $("#email1").val();
	var email_domain = $("#email2").val();
	var mail ="";
	mail = email1+"@"+email2;
	$("#mail").val(mail);
	
	//유효성 검사
	if(!(nameJ.test($("#korNm").val()))) {
		alert("이름은 한글 이름으로 시작하는 2~20자리여야 합니다.");
		return false;
	} else if(!(telMidJ.test($("#telMidNo").val()))) {
		alert("핸드폰 중간 번호는 -를 제외한 숫자로만 입력해야 합니다.11");
		return false;
	} else if(!(telEndJ.test($("#telIndNo").val()))) {
		alert("핸드폰 중간 번호는 -를 제외한 숫자로만 입력해야 합니다.22");
		return false;
	} else if(!(email_rule.test(mail))) {//DB연결하면 이메일이 있을고임. 아직 없어서 계속 뜸 
		alert("이메일을 형식에 맞게 입력해주세요.");
		return false;
	}
	
	alert("변경되었습니다!");
	return true;
}

function mailS() {
	$("#mailSelect option:selected").val();
}