## Form양식 연습

* form 양식 연습

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Data Collection</title>
<style type="text/css">
	table {
		border : solid 1px black;
		border-collapse : collapse;
	}
	

	td {
		border : solid 1px black;
		border-collapse : collapse;
	}

</style>
</head>
<body>
<!-- 3(column) X 10(row) 테이블 생성-->
<form action="" method="">
	<table>
		<tr><td colspan="3">데이터수집</td></tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="user_name" value="kim"></td>
			<td></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="user_id" value="admin"></td>
			<td><input type="button" value="id검사"></td>
		</tr>
		<tr>
			<td rowspan="2">패스워드</td>
			<td><input type="password" name="pwd1" value="1234"></td>
			<td></td>
		</tr>
		<tr>
			<td><input type="password" name="pwd2" value="1234"></td>
			<td>
				<button>패스워드확인</button>
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
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
			<td>이메일</td>
			<td>
				<input type="text" size="10" name="email1">@
				<input type="text" size="12" name="email2">
			</td>
			<td>
				<select name="email">
					<option>--이메일 선택--</option>
					<option selected="selected">nate.com</option>
					<option>naver.com</option>
					<option>google.com</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>사용언어</td>
			<td>
				<input type="checkbox" name="langs" checked="checked">자바
				<input type="checkbox" name="langs" >파이썬
				<input type="checkbox" name="langs" >자바스크립트
				<input type="checkbox" name="langs" >C#
			</td>
			<td><button>다중선택확인</button></td>
		</tr>
		<tr>
			<td>사용툴</td>
			<td>
				<input type="radio" name="tools">이클립스
				<input type="radio" name="tools" checked="checked">메모장
				<input type="radio" name="tools">VSC
			</td>
			<td><button>단일선택확인</button></td>
		</tr>
		<tr>
			<td>비고</td>
			<td>
				<textarea rows="5" cols="30" name="etc">Welcome</textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="submit" value="전송">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
```

