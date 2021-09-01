//스크립트 선언 : 선언을 해줘야 코드 사용 가능
var commonJs = {};
this.commonJs = commonJs;

$(document).ready(function () {	
	//파일업로드 스크립트 호출
	commonJs.setFileUpload('.fileUpload');	
});

/***스크립트영역****************/
	//파일 업로드, 삭제
	commonJs.setFileUpload = function (uploadNode) {
	  /** 파일 선택 */
	  $(document).on('change', uploadNode + ' .uploadBtn', function (e) {
	      if (window.FileReader) {
	          if (this.files.length < 1) {
	              $(this)
	                  .closest(uploadNode)
	                  .find('.intDel .btnDel')
	                  .hide();
	              $(this)
	                  .closest(uploadNode)
	                  .find('.intDel')
	                  .eq(0)
	                  .find('.fileName')
	                  .val('');
	              $(this)
	                  .get(0)
	                  .value = "";
	              return;
	          }
	          var filename = this
	              .files[0]
	              .name;
	          $(this)
	              .closest(uploadNode)
	              .find('.intDel .fileName')
	              .val(filename);
	      } else {
	          var filename = $(this)
	              .val()
	              .split('/')
	              .pop()
	              .split('\\')
	              .pop();
	          $(this)
	              .closest(uploadNode)
	              .find('.intDel:last .fileName')
	              .val(filename);
	      }
	      $(this)
	          .closest(uploadNode)
	          .find('.btnDel')
	          .show();
	  });
	
	  /** 파일 삭제 */
	  $(document).on('click', uploadNode + ' .btnDel', function () {
	      var _t = $(this);
	      _t.hide();
	      if (window.FileReader) {
	          _t
	              .closest(uploadNode)
	              .find('.uploadBtn')
	              .get(0)
	              .files[0]
	              .value = '';
	          if (_t.closest(uploadNode).find('.intDel:visible').length > 1) {
	              _t
	                  .closest('.intDel')
	                  .hide();
	          } else {
	              _t
	                  .siblings('.fileName')
	                  .val('');
	              _t
	                  .closest(uploadNode)
	                  .find('.uploadBtn')
	                  .get(0)
	                  .value = "";
	          }
	      } else {
	          _t
	              .siblings('.fileName')
	              .get(0)
	              .value = '';
	          var uploadNodeClone = _t
	              .closest(uploadNode)
	              .find('.uploadBtn')
	              .clone(true);
	          _t
	              .closest(uploadNode)
	              .find('.uploadBtn')
	              .replaceWith(uploadNodeClone);
	      }
	  });
	}
	
	function checkSize(input) {
	      var fileVal = $("#uploadFile").val();
	      fileVal = fileVal.slice(fileVal.indexOf(".") + 1).toLowerCase();
	      
	      if (fileVal != "jpg" && fileVal != "png" && fileVal != "jpeg") {
	         alert("이미지 파일만 가능합니다. 확장자명을 확인해주세요.");
	         input.value = null;
	      } else if (input.files && input.files[0].size > (5 * 1024 * 1024)) {
	         alert("파일 사이즈가 5mb 를 넘습니다.");
	         input.value = null;
	      }
	}
	