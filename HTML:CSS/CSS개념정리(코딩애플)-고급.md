### pseudo-elements

: pseudo-class - 특정 요소가 다른 상태일 때 스타일 줄 수 있게 도와줌

:: pseudo-element - 내부의 일부분만 스타일 줄 때 

```css
.text::first-letter { /* 첫글자 */ 
  color : red;
}

.text::first-line { /* 첫 줄 */
  color : red;
}

.text::after { /* 내부의 맨 마지막 부분에 뭔가 추가할 때 */ 
  content : '뻥이지롱';
  color : red;
}

.text::before { /* 내부의 맨 앞 부분에 뭔가 추가할 때 */
  content : '뻥이지롱';
  color : red;
}
```

##### **pseudo-element로 clear : both 박스 편하게 만들기** 

```css
.box::after {
  content : '';
  display : block;
  clear : both;
}
```



### Shadow dom

shadow dom : HTML 개발시 코드가 너무 복잡해지지 않기 위해 숨겨놓은 HTML 요소

크롬 -> 개발자 도구 -> setting ->Show user agent shadow DOM 체크하면 볼 수 있음

pseudo element를 통해서 shadow DOM 요소들도 스타일을 줄 수 있음.

파일 업로드 버튼, 스크롤바, 드래그시 하이라이트되는 파란색 색상, placeholder 글씨색깔 등

```css
input[type:file]::-webkit-input-placeholder {
  color : red; 
}
```

-webkit- : 크롬(사파리, edge) 에서만 적용되는 스타일. / -ms- : IE / -moz- : 파이어폭스

-> 실제 파일업로드 버튼은 label 태그를 스타일링하고 버튼은 display : none 하는 방식으로 스타일링 함

shadow dom 없는거 같은데 어떡함 -> 브라우저 기본 CSS -> 개발자 도구에서 셀렉터 훔쳐오면 됨

User agent stylesheet : 브라우저 기본 css -> appearance : none 하면 없어짐 



### SASS

설치 : vs code extension 설치 **Live sass compile** v 5.0 이상

하단에 watch Sass 버튼 생기는데 누르면 자동으로 sass 파일을 css 파일로 컴파일 (하단 바 안보이면 View - Appearance - Status bar)

sass 문법은 .sass 파일과 .scss 파일에서 사용할 수 있는데, .sass 파일은 sass 문법으로 코드 짤 때 중괄호 생략 가능

#### SASS 문법 1 : 값을 저장해놓고 쓰는 '변수'

```scss
(test.scss)
$메인색상 : #2a4c6e;
$서브색상 : #333333;

.text {
  color: $메인색상
}
.box {
  background: $서브색상
} 
```

사칙연산도 바로바로 가능

```scss
(test.scss)

$기본사이즈 : 16px;

.box {
  font-size : $기본사이즈 + 2px;
  width : (100px * 2);
  height : (300px / 3)
}
```

(참고1) SASS없이 그냥 CSS 파일에도 var() 이용해서 변수문법 사용가능

(참고2) 그냥 CSS 파일에서도 calc() 함수 이용하면 사칙연산 사용가능



#### Sass 문법 2. 셀렉터 대신 쓰는 Nesting

UI들을 뭉텅이로 관리할 수 있게 하는 문법

기존 css

```css
.navbar ul { 
  width : 100%; 
}
.navbar li { 
  color : black; 
}
```

sass

````scss
.navbar {
  ul {
    width : 100%;
  }
  li {
    color : black;
  }
}
````



#### Sass 문법 3. 클래스 전체를 복사해주는 @extend 

중복 스타일이 많으면 클래스로 묶어두고 @extend로 필요할 때 복사

```scss
.btn {
  font-size : 16px;
  padding : 10px;
  background : grey;
}

.btn-green {
  @extend .btn;
  background : green;
}
```

.btn 대신 %btn 쓸 수도 있는데,

% 기호는 .대신 쓸 수 있는 임시클래스

CSS파일에서 클래스로 컴파일하지 않고싶을 때 쓰는 기호



#### Sass 문법 4. 코드를 한단어로 축약하는 @mixin

@mixin 으로 축약하고 @include 로 갖다씀

```scss
@mixin 버튼기본디자인() {
  font-size : 16px;
  padding : 10px;
}

.btn-green {
  @include 버튼기본디자인();
  background : green;
}
```

파라미터 사용가능

```scss
@mixin 버튼기본디자인($파라미터) {
  font-size : 16px;
  padding : 10px;
  background : $파라미터;
}

.btn-green {
  @include 버튼기본디자인(#229f72);
}
```

글자 중간 $변수나 $파라미터 넣을때는 #{ $변수명 }



#### Sass 문법 5. @use와 언더바 파일

다른 파일에 있는 내용 가져오고 싶을 때는 @use '파일경로';

```scss
@use 'reset.scss';
```

```scss
@use '_reset.scss';
```

_파일명일 경우 css 파일로 따로 컴파일하지 않는다는  (그냥 참조용 파일이라는 뜻)



##### video 넣는 법 : `<video src="경로">`

```html
 <video src="경로" controls>  controls 넣어야 재생버튼 생김
```

##### video 넣는 법2 : `<video><source></video>`

```html
<video controls>
	<source src="bridge.mp4" type="video/mp4" />
</video>
```

장점 : 호환성을 챙길 수 있음

source를 여러개 넣어서 위에꺼 안되면 밑에꺼 해보세요~ 식으로 코딩할 수 있음

넣을 수 있는 속성

```html
<video autoplay muted loop poster="썸네일경로" preload="metadata">
  <source src="비디오파일경로">
</video>
```

muted: 음소거상태

autoplay: 자동재생 (muted와 함께 넣어야 동작함)

loop : 무한 반복

poster: 썸네일이미지

preload: 영상을 먼저 다운을 받을지 말지(auto 미리다운, metadata 조금 미리다운, none 미리다운X)



##### audio 넣는 법 : `<audio src="파일경로">`

controls 넣어줘야 재생할 수 있음

muted : 음소거 상태로 로드

autoplay 속성은 안먹힘

`<source>` 사용 가능



### transform & @keyframes

##### transform 관련 css 속성

```css
.box {
  transform : rotate(10deg); 
  transform : translate(10px, 20px);
  transform : scale(2);
  transform : skew(30deg);
  
  /*transform 두개 이상을 한꺼번에 쓰려면*/
  transform : rotate(10deg) translateX(30px);
}
```

rotate는 회전, translate는 좌표이동, scale은 확대축소, skew는 비틀기

애니메이션 줄 경우 그냥 margin 이런걸로 움직이는 것보다 부드러움

transition 은 a -> b 이런 것만 가능하고

복잡한 애니메이션은 **@keyframes**

```css
@keyframes 와리가리 {
  0% {
    transform: translateX(0px);
  }
  50% {
    transform: translateX(-100px);
  }
  100% {
    transform: translateX(100px);
  }
}
```

진행도에 따라 어떤 스타일 넣을지 기입하면 됨. 0% 50% 100% 뿐 아니라 마음대로 쪼개서 쓸 수 있음

@keyframes 넣고 싶으면 animation-name : 이름,  animation-duration : 지속기간

```css
.ani-text:hover {
  animation-name: 와리가리;
  animation-duration: 1s;
}
```

애니메이션 세부조정

```css
.box:hover {
  animation-name : 움찔움찔;
  animation-duration : 1s;
  animation-timing-function : linear; /*베지어 주기*/
  animation-delay : 1s; /*시작 전 딜레이*/
  animation-iteration-count : 3; /*몇회 반복할것인가*/
  animation-play-state : paused;  /*애니메이션을 멈추고 싶은 경우 자바스크립트로 이거 조정*/
  animation-fill-mode: forwards;  /*애니메이션 끝난 후에 원상복구 하지말고 정지*/
}
```



##### transform 쓰는 이유? 성능이 좋음

transform 변경보다 margin 변경이 더 느림

웹브라우저는 html, css를 그래픽으로 바꿔주는 간단한 프로그램

브라우저가 그림 그리는 순서

1. Render Tree 만들기
2. Layout 잡기 : width, height, margin, padding 등
3. Paint 하기 : 픽셀에 색칠함 background-color 같은거
4. Composite 처리 : transform, opacity 있으면 처리

그럼 width 바꾸면? 2번, 3번, 4번 다시 다해야됨. transform 을 바꾸면 4번만 다시하면 됨

##### 빠른이유2 : transform 이런건 다른 쓰레드에서 처리해줌

웹브라우저는 쓰레드 1개만 씀. 자바스크립트 실행, html, css 처리 전부 한 곳에서 하는데

애니메이션 이런 애들은 다른 쓰레드에서 처리함



##### 성능 잡는 방법1 : will-change

```css
.box {
  will-change: transform;
} 
```

바뀔 내용을 미리 렌더링 해주는 속성

너무 남발하면 브라우저 자체가 더 느려질 수도 있음

##### 성능 잡는 방법2 : 3d 애니메이션 사기치기

```css
.box {
  transform: translate3d(0, 0, 0);
}
```

transform : translate3d 쓰면 3D 이동도 가능한데 GPU를 사용해서 연산

그래서 translate3d(0, 0, 0) 이런 식으로 아무데도 움직이지 않는 3D 이동명령을 주고

뒤에 필요한 transform을 더 적용하면 GPU를 이용해서 .box가 가진 transform 속성들을 연산



### grid 레이아웃 (IE 지원X, Edge 이상)

```html
<div class="grid-container">
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
</div>
```

```css
.grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 100px 100px 100px;
  grid-gap: 10px;
}
```

부모 <div>에 display : grid를 주면 자식 <div>들은 전부 격자처럼 진열

grid-template-columns : 격자의 열 너비와 갯수

grid-template-rows : 격자의 행 높이와 갯수 설정

fr : 몇배만큼 차지할지를 나타내는 값

grid-gap : 격자 간격



그리드 레이아웃 만들려면

1. 내부 박스 크기 조절 (grid-column, grid-row 주기)
2. 부모 조작



##### Grid로 레이아웃 만드는 법 1. 자식 div 높이와 폭을 조정하기

```html
<div class="grid-container">
    <div class="grid-nav">헤더</div>
    <div class="grid-sidebar">사이드바</div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
</div>
```

```css
.grid-nav {
  grid-column : 1 / 4; /* 세로선 1~4까지 차지 */
  grid-row : 2 / 4;		 /* 가로선 2~4까지 차지 */
}
```



##### Grid로 레이아웃 만드는 법 2. 자식에게 이름쓰고 부모가 배치하기

```html
<div class="grid-container">
    <div class="grid-nav">헤더</div>
    <div class="grid-sidebar">사이드바</div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
</div>
```

```css
.grid-nav {
  grid-area: 헤더;
}

.grid-sidebar {
  grid-area: 사이드;
}

.grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 100px 100px 100px;
  grid-gap: 10px;
  grid-template-areas: 
    "헤더 헤더 헤더 헤더"
    "사이드 사이드 . ."
    "사이드 사이드 . ."
}
```

