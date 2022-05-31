### 셀렉터의 우선순위

style="" -> 1000점

#id -> 100점

.class -> 10점

p -> 1점



### float : 요소를 공중에 띄워 왼쪽 / 오른쪽 정렬하는 속성

```css
.left__box{
  width : 100px;
  height : 100px;
	float : left;
}
.right__box{
  width : 100px;
  height : 100px;
	float : left;
}
```

float를 쓰고 나면 요소를 붕띄워서 그 다음 요소들이 자리를 못찾음

-> float를 쓰고 나면 항상 clear 속성이 필요함

```css
.footer{
  clear : both;
  float : none; // 이것도 추가해주는게 나중에 생길 버그예방차원에서 좋음
}
```



### Inline-block

display : inline-block; 으로도 가로 정렬을 할 수 있는데, 태그 사이의 공백도 제거해줘야되서 귀찮음..

공백제거 팁1 : 주석처리 기호 사용

공백제거 팁2 : 부모 폰트사이즈 0으로 만들기

둘 다 별로임 그냥 float 쓰셈



### 셀렉터 사용법

##### html 태그에 클래스 두개 이상 붙일 수 있음

```html
<div class="container text-center">
  클래스 두개 붙여서 필요에 따라 속성 부여 가능쓰
</div>
```

##### 공백 넣어서 자손 선택 가능

```css
.navbar li{
  display : inline-block;
}
```

>  tag 셀렉터 말고 class, id 가능

##### > 꺾쇠 넣어서 바로 밑에 있는 자손만 선택 가능

```css
.navbar>li{
  display : inline-block;
}
```

##### 셀렉터 연속 사용 가능 (더욱 상세히 선택하고 싶다면)

```css
.navbar li>span{
  color : red;
}
```



### 배경 속성

```css
.main-background {
  background-image : url(../img/shoes.jpg);
  background-size : cover;
  background-repeat : no-repeat;
  background-position : center;
  background-attachment : fixed;
}
```



### margin collapse

```html
<div class="배경">
  <p>
    하이욤
  </p>
</div>
```

> p 태그에 margin-top 을 주게 되면 오잉? div와 p가 동시에 margin-top이 생김
>
> 박스의 테두리가 만나면 margin이 합쳐지는데 일종의 버그임
>
> 그래서 안겹치게 하려면 부모 박스에 padding : 1px 이렇게 주면 해결됨



### position 속성

```css
.box {
  position : static; /* 기본값, 기준이 없음 (좌표적용 불가) */ 
  position : relative; /* 기준이 내 원래 위치 */
  position : absolute; /* 기준이 내 부모 중 position : relative 가지고 있는 부모 */
  position : fixed; /* 기준이 브라우저 창 (viewport) */
}
```

> position 부여하면 1. 좌표이동가능 2. 공중에 뜸



#### Z-index

공중에 떠있는 요소들이 많으면 누가 맨 앞에 올 거?

z-index : 정수; 높을수록 앞으로 옴



#### Max-width 

반응형 웹페이지 만들 때 width 를 % 로 부여하는 경우 많음

문제는 PC사이즈에서 너무 큼 -> max-width 와 함께 쓰면 됨



#### 박스의 폭을 border까지 설정해주고 싶을 때 쓰는 속성 

```css
.box {
  box-sizing : border-box; /*박스의 폭은 border까지 포함입니다*/
  box-sizing : content-box; /*박스의 폭은 padding 안쪽입니다*/
}
```



#### CSS normalize

브라우저간 통일된 스타일을 주기 위해 특정 스타일을 맨 위에

CSS Normalize 링크 : https://github.com/necolas/normalize.css/blob/master/normalize.css 



### form 형식

```html
 <form action="경로" method="방식"> 작성한 내용이 어떤 서버 경로로 어떤 방식으로 전달될지
```

##### input의 type 

```html
<input type="text">
<input type="email">
<input type="password">
<input type="radio">
<input type="file">
<input type="checkbox">
<input type="submit">
<select>
  <option>옵션1</option>
</select>
<textarea></textarea>
```

##### input에 넣는 속성들

```html
<input placeholder="어쩌구" value="어쩌구" name="age">
```

placeholder는 배경 글자,

value는 미리 입력된 값,

name은 서버 기능개발에 필요한 인풋의 이름을 설정 가능

##### 전송버튼 

```html
<button type="submit">전송</button>
<input type="submit">
```

둘 중 하나 쓰면됨

##### HTML 속성으로 CSS셀렉터 사용 가능

```css
input[type=email] {
  color : grey
}
```

> Input[속성명=속성값]



#### label 태그

label 태그의 for 속성과 input의 id를 똑같이 맞추면 input대신 label 눌러도 input 선택 가능

```html
<input type="checkbox" id="subscribe">
<label for="subscribe">누르기</label>
```



### table 레이아웃, vertical-align 속성

> 가로행 만들 땐 <tr>
>
> 세로행 만들 땐 <td>
>
> 제목용 세로 열 만들 땐 <th>

> 제목행은 <thead> 
>
> 일반행은 <tbody>
>
> 굳이 쓸 필요는 없음

table은 기본적으로 틈이 존재. 없애려면 border-collapse : collapse;

##### 테이블 셀 내에서 상하정렬할 땐 vertical-align

```css
td, th {
  vertical-align : middle;
}
```

> vertical-align 속성 -> 테이블 내에서의 상하정렬 가능
>
> top, bottom, middle 사용가능

##### inline 요소 간 상하정렬할 때도 vertical-align

> display : inline 혹은 inline-block 요소들을 나란히 배치하면 상하정렬이 이상한 경우가 있음
>
> 특히 큰 이미지와 글, 또는 큰 글씨옆에 있는 작은 글씨, 이런걸 나란히 배치했을 때 서로 높이가 맞지 않는 경우가 많은데,
>
> vertical-align 속성으로 높이 맞춰줌
>
> top, middle, bottom 말고도 super, sub, px 단위로 사용가능
>
> (table 안에선 top, middle, bottom만 사용가능)



### Pseudo-class

스타일을 줄 수 있는 Pseudo-class 셀렉터

```css
.btn:hover {
  background : chocolate; /*마우스를 올려놓을 때*/
}
.btn:focus {
  background : red; /*클릭 후 계속 포커스 상태일 때*/
}
.btn:active {
  background : brown; /*클릭 중일 때*/
}
```

> hover, focus, active 스타일 넣을 때 순서는 hover, focus, active 순으로 선언해야 잘 동작

##### input 등에도 자주 사용

```css
input:focus {
  border : 2px solid red;
}
```

인풋에 커서가 찍혀있을 때 인풋에 스타일을 주고 싶으면 당연히 :focus를 붙이면 됩니다.

##### a 태그에도 자주 사용

```css
a:link { 
  color : red; /*방문 전 링크*/ 
} 
a:visited { 
  color : black; /*방문 후 링크*/ 
} 
```

:link를 붙이면 방문 전 링크 스타일

:visited를 붙이면 방문 후 링크 스타일

모든 링크의 밑줄제거는 a태그에 text-decoration : none 



### 코드양이 줄어드는 class 작명법 (OOCSS, BEM)

##### OOCSS : css를 분리하여 작성. 태그에 여러가지 클래스를 주는 방식으로 css 속성 주기

```css
.main-btn {
  font-size : 20px;
  padding : 15px;
  border : none;
  cursor : pointer;
}

.bg-red {
  background : red;
}
.bg-blue {
  background : blue;
}
```

```css
<button class="main-btn bg-red">빨간버튼</button>
<button class="main-btn bg-blue">파란버튼</button>
```

##### BEM : Block__Element--Modifer 순서대로 클래스명 작성

리액트나 뷰 등에서는 잘안쓰여서 요즘에는 잘 안쓰임