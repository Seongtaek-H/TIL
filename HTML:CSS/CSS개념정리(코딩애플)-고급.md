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
