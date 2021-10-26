## JQuery

> 모든 웹 브라우저에서 동작하는 클라이언트용 자바스크립트 라이브러리

`사용방법`

* 라이브러리 다운

  ``` HTML
  <script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
  ```

* 네트워크로 CDN 호스트 설정

  ```html
  <script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
  ```

`형태`

```javascript
$(선택자).메서드(매개변수,매개변수)
```



`제이쿼리 선택자`

| 선택자 종류       | 선택자 표현 방법                      | 설명                                          |
| ----------------- | ------------------------------------- | --------------------------------------------- |
| All selector      | $("*")                                | 모든 DOM 선택                                 |
| ID selector       | $("#id")                              | 해당되는 id를 가지는 DOM 선택                 |
| Element selector  | $("elementName")                      | 해당되는 이름을 가지는 DOM 선택               |
| class selector    | $(".className")                       | CSS 중 해당되는 클래스 이름을 가지는 DOM 선택 |
| Multiple selector | $("selector1,selector2,...,slectorN") | 해당되는 선택자를 가지는 모든 DOM 선택        |



```javascript
$(document).ready(function(){	//document에 DOM 로드되는 이벤트 처리함수
	
	$('#idcheck_btn').click(function(){		// #idcheck_btn 클릭시
		var id = $('#user_id').val();		// #user_id의 값을 var id에 저장 후
		alert(id);							//var id 알림
	});
	
	$('#pwd_btn').click(function(){								
		var pw1 = $('#pwd').val();	// #pwd 값 불러오기										
		var pw2 = $('#pwd2').val();	// #pwd2 값 불러오기
		
		if(pw1!=pw2){
			alert("비밀번호 불일치");
		}else{
			alert("비밀번호 일치");
		}
	});
	
	$('#email').change(function(){
		// 선택한 이메일 값 읽어오기
		var email_val = this.value;		// #email 값 email_val에 담기
		$('#email2').val(email_val);	//id = email2의 값 var email_val값으로 세팅
	});
	
	$('#direct').click(function(){		// #direct 클릭
		if(this.checked){				// check 되있으면	
            alert('선택');
			$('#email').removeAttr("disabled");		// #email 항목 disabled 속성 제거
		} else {									// check 안되어있으면
            alert('선택해제');
			$('#email').attr("disabled", true);		// #email 항목 disabled 속성 추가
		}
	});
	
	$('#box_btn').click(function(){		/// #box_btn 클릭
		var langs = $('.lang');			// .lang 값 var langs에 저장
		var count = 0;
        //input 태그에서 class가 lang인 값이 checked 되어 있는 갯수를 var size 에 저장
		var size = $('input[class="lang"]:checked').length;	
		for(var i=0; i<langs.length;i++){
			if(langs[i].checked){
				//alert(langs[i].value);
				count++;					
			}
		}
		alert("선택갯수는 "+size);
	});
	
	$('#name_btn').click(function(){
		//var c_name = $('#c_name').text();	//#c_name의 텍스트 값을 var c_name에 저장
		//alert(c_name);
		//$('#c_name').text('사용자이름');	//#c_name의 텍스트 값을 '사용자이름'으로 변경
        // #choice_box 에 html 태그 바로 삽입하여 checkbox 생성
		$('#choice_box').html('<input type="checkbox" id ="add_select">직접선택');	
	});
	
	var timer;
	$('#start').click(function(){
		var clock = function(){	
			var now = new Date().toLocaleTimeString();
			//$('#loc').text(now);
			$('#loc').html("<h3>"+now+"</h3>");
		}
		timer = setInterval(clock,1000);//1초마다 clock 실행
	});

	$('#stop').click(function(){
		clearInterval(timer); // timer 종료
	});
	
});
```

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Data Collection</title>
<link rel="stylesheet" href="../css/mystyle.css">
<script type="text/javascript" src="../js/validate.js"></script>
</head>
<body>
<!-- 3(column) X 10(row) 테이블 생성-->
<form action="./data_process.jsp" method="post">
	<table>
		<tr><th colspan="3">데이터수집</th></tr>
		<tr>
			<td class="title">이름</td>
			<td class="data"><input type="text" name="user_name" value="kim"></td>
			<td></td>
		</tr>
		<tr>
			<td class="title">아이디</td>
			<td class="data"><input type="text" name="user_id" value="admin" id="user_id"></td>
			<td><input type="button" value="id검사" onclick="getId()"></td>
		</tr>
		<tr>
			<td rowspan="2" class="title">패스워드</td>
			<td class="data"><input type="password" name="pwd1" value="1234" id="pwd1"></td>
			<td></td>
		</tr>
		<tr>
			<td class="data"><input type="password" name="pwd2" value="1234" id="pwd2"></td>
			<td>
				<button onclick="return compare_password()">패스워드확인</button>
			</td>
		</tr>
		<tr>
			<td class="title">전화번호</td>
			<td class="data">
				<!-- <input type="text" size="3" name="phone_no1"> -->
				<select name="phone_no1">
					<option>--선택--</option>
					<option selected="selected">010</option>
					<option>051</option>
					<option>02</option>
				</select>
				-
				<input type="text" size="3" name="phone_no2">-
				<input type="text" size="3" name="phone_no3">
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="title">이메일</td>
			<td class="data">
				<input type="text" size="10" name="email1">@
				<input type="text" size="12" name="email2">
			</td>
			<td>
				<select name="email">
					<option>--이메일 선택--</option>
					<option selected="selected" value="nate.com">nate.com</option>
					<option value="naver.com">naver.com</option>
					<option value="google.com">google.com</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="title">사용언어</td>
			<td class="data">
				<input type="checkbox" name="langs" checked="checked" value="java" class="langs">자바
				<input type="checkbox" name="langs" value="python" class="langs">파이썬
				<input type="checkbox" name="langs" value="javascript" class="langs">자바스크립트
				<input type="checkbox" name="langs" value="c#" class="langs">C#
			</td>
			<td><button type="button" onclick="check_box()">다중선택확인</button></td>
		</tr>
		<tr>
			<td class="title">사용툴</td>
			<td class="data">
				<input type="radio" name="tools" value="이클립스" class="tools">이클립스
				<input type="radio" name="tools" checked="checked" value="메모장" class="tools">메모장
				<input type="radio" name="tools" value="VSC" class="tools">VSC
			</td>
			<td><button type="button" onclick="check_radio()">단일선택확인</button></td>
		</tr>
		<tr>
			<td class="title">비고</td>
			<td class="data">
				<textarea rows="5" cols="30" name="etc">Welcome</textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="3" id="action_btn">
				<input type="submit" value="전송">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
```

