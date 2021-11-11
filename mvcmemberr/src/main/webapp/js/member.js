//JavaScript
//내가해보던거
/*function useId(id) {
	opener.document.getElementById("id").innerText = id;

}*/
//아이디중복체크
$('#checkIdClose').click(function(){
	//alert($('#id').val()); 그냥 잘 나오는지 확인하려고  -->> hidden id 값으로 가져 온다 id를	
	//opener.document.getElementById("id").value =$('#checkId').val();//열려있는 창들중에서 (부모쪽)
	/*자바스크립트에는 ${requestScope.id } 이런 el태그 없다 jsp에서 쓰는것일뿐*/
/*방법1
	opener.writeForm.id.value =$('#checkId').val();
	window.close();
	opener.writeForm.pwd.focus();
	*/
//방법2
	$('#id',opener.document).val($('#checkId').val());
	$('#check',opener.document).val($('#checkId').val());//중복체크버튼 눌렀는지 확인용
	window.close();
	$('#pwd', opener.document).focus();
});



/*----------------------------------------------------------------------------*/
//우편번호
$('#zipcodeBtn').click(function(){
	window.open("/mvcmemberr/member/checkPost.do", "checkPost", "width=800 height=500 top=200 left=700");
	
});
$('.addressA').click(function(){
	//alert($(this).text()); //주소
	//alert($(this).parent().prev().text());//parent():부모태그명검색 /prev():부모앞 //우편번호
	
	$('#zipcode', opener.document).val($(this).parent().prev().text());
	$('#addr1', opener.document).val($(this).text());
	window.close();
	$('#addr2', opener.document).focus();
});


/*----------------------------------------------------------------------------*/

function checkWrite() {
	document.getElementById("nameDiv").innerText = "";
	document.getElementById("idDiv").innerText = "";
	document.getElementById("pwdDiv").innerText = "";
	document.getElementById("repwdDiv").innerText = "";

	if (document.writeForm.name.value == "")
		document.getElementById("nameDiv").innerText = "이름을 입력하세요";
	else if (document.writeForm.id.value == "")
		document.getElementById("idDiv").innerText = "아이디를 입력하세요";
	else if (document.writeForm.pwd.value == "")
		document.getElementById("pwdDiv").innerText = "비밀번호를 입력하세요";

	else if (document.writeForm.pwd.value != document.writeForm.repwd.value)
		document.getElementById("repwdDiv").innerText = "비밀번호가 맞지 않습니다";
	else
		document.writeForm.submit();
}
//아이디 중복체크
function checkId() {

	var id = document.getElementById("id").value;

	if (id == "")
		document.getElementById("idDiv").innerText = "먼저 아이디를 입력하세요";
	else
		//window.open("http://localhost:8080/mvcmember/CheckIdService?id="+id, "아이디 중복 체크", "width=350 height=100 top=200 left=700");
		window.open("/mvcmemberr/member/checkId.do?id=" + id, "아이디 중복 체크", "width=500 height=300 top=200 left=700"); //상대주소
}
//어차피 버튼누르면하는거라서 위치가 온로드를 벗어나도 상관없다?!
//제이쿼리로 바꿔보기
$('#checkIdBtn').click(function() {
	//var id = document.getElementById("id").value;
	var id = $('#id').val();
	if (id == "")
	alert("먼저 아이디를 입력하세요");
		//window.open("http://localhost:8080/mvcmember/CheckIdService?id="+id, "아이디 중복 체크", "width=350 height=100 top=200 left=700");
	else
		window.open("/mvcmemberr/member/checkId.do?id=" + id, "아이디 중복 체크", "width=500 height=300 top=200 left=700"); //상대주소

});
//jQuery
//회원가입
$(function() { //onload
	$('#writeBtn').click(function() {
		//초기화 과정
		$('#nameDiv').empty();
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		$('#repwdDiv').empty();

		//if($('#name').val() == '') $('#nameDiv').html('이름 입력'); //id속성

		//name속성 이용
		if ($('input[name=name]').val() == '') {
			$('#nameDiv').html('이름 입력');
			$('#name').focus();
		}
		else if ($('input[name=id]').val() == '') $('#idDiv').html('아이디 입력');
		else if ($('input[name=pwd]').val() == '') $('#pwdDiv').html('비밀번호 입력');
		else if ($('input[name=pwd]').val() != $('input[name=repwd]').val()) $('#repwdDiv').html('비밀번호 틀림');
		else if ($('#id').val() != $('#check').val())
			 $('#idDiv').html('중복체크 하세요');
		else $('form[name=writeForm]').submit();
	});

	//로그인
	$('#loginBtn').click(function() {
		$('#idDiv').empty();
		$('#pwdDiv').empty();

		if ($('input[name=id]').val() == '')
			$('#idDiv').html('아이디 입력');
		else if ($('input[name=pwd]').val() == '')
			$('#pwdDiv').html('비밀번호 입력');
		else $('form[name=loginForm]').submit();
	});
}); //$(function())