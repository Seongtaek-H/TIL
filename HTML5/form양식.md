## Form양식

> 입력 양식 태그. 내부에 input 태그를 넣어 만듦

```html
<body>
  <form action="전송위치" method="전송방식">
    <input type="text" name="search" />
    <input type="submit" />
  </form>
</body>
```

`전송위치` : 데이터를 전달할 목적지

`전송방식`

- GET방식 : 주소에 데이터를 입력해서 전달.

- POST방식 : 데이터를 별도로 전송.

### 입력 양식 종류

<table border=1>
    <tr>
        <td>태그</td>
        <td>속성</td>
        <td>설명</td>
    </tr>
    <tr>
        <td>form</td>
        <td>보이지 않음</td>
        <td>입력 양식의 시작과 끝 표시</td>
    </tr>
    <tr>
        <td rowspan = "9">input</td>
        <td>text</td>
        <td>글자 입력 양식 생성</td>
    </tr>
    <tr>
        <td>button</td>
        <td>버튼 생성</td>
    </tr>
    <tr>
        <td>checkbox</td>
        <td>체크 박스 생성(다중 선택)</td>
    </tr>
    <tr>
        <td>radio</td>
        <td>라디오 버튼 생성(단일 선택)</td>
    </tr>
    <tr>
        <td>hidden</td>
        <td>해당 내용 표시 안함</td>
    </tr>
    <tr>
        <td>image</td>
        <td>이미지 형태 생성</td>
    </tr>
    <tr>
        <td>password</td>
        <td>비밀번호 입력 양식 생성</td>
    </tr>
     <tr>
        <td>reset</td>
        <td>초기화 버튼 생성</td>
    </tr>
    <tr>
        <td>submit</td>
        <td>제출 버튼 생성</td>
    </tr>
     <tr>
        <td>textarea</td>
        <td>cols/rows</td>
        <td>여러 행의 글자 입력 양식 생성.<br>cols : 너비, rows : 높이</td>
    </tr>
    <tr>
        <td>select<br>optgroup<br>option</td>
        <td></td>
        <td>선택 양식 생성<br>옵션 그룹화<br>옵션 생성</td>
    </tr>
        <td>fieldset<br>legend</td>
        <td></td>
        <td>입력 양식의 그룹 지정<br>입력 양식 그룹의 이름 지정</td>
    </tr>
</table>
