### 웹폰트 넣는법과 안티앨리어싱

##### 폰트넣는 법

```css
body {
  font-family : 'gulim', 'gothic'
}
```

> font-famaiy는 inherit되서 body에 넣으면 다 적용
>
> 여러 개 폰트를 넣으면 왼쪽에 있는 폰트부터 적용. 실패하면 뒤에꺼 차례대로
>
> 페이지 방문자들의 pc에 폰트가 설치되어있어야 이용가능

##### 사용자 pc에 설치되어 있지 않은 폰트를 넣으려면

```css
@font-face {
  font-family : '이쁜폰트';
  src : url(nanumsquare.ttf)
}
```

> CSS파일 최상단에 @font-face 라는 명령어를 넣고,
>
> 그 안에 적용할 폰트의 경로 이름 (폴더 내에 폰트 파일 필요)
>
> 그럼 원하는 곳에서 `font-family : '이쁜폰트'` 라는 폰트 사용 가능 (실제로는 nanumsquare.ttf 폰트 적용)
>
> ttf 폰트는 용량이 너무 커서 구할 수 있다면 woff 파일 사용ㄱ

##### IE 옛 버전에서도 호환성 좋게 폰트를 사용하려면

```css
@font-face { 
  font-family: 'NanumSquare'; 
  font-weight: 400; 
  src: url(NanumSquareR.eot); 
  src: url(NanumSquareR.eot?#iefix) format('embedded-opentype'), 
      url(NanumSquareR.woff) format('woff'), 
      url(NanumSquareR.ttf) format('truetype'); 
}
```

> eot, woff, ttf 파일들 구해서 첨부

##### 폰트를 빠르게 사용하기 위한 Google Fonts

> 구글폰트 사용하면 폰트 파일 안구해도됨 [fonts.google.com](http://fonts.google.com/)
>
> HTML에 첨부하고 싶으면 <link>로 시작되는 부분 복붙
>
> CSS에 첨부하고 싶으면 @import 로 시작되는 부분 CSS 맨 위에 복붙

##### 폰트 Anti-aliasing

mac은 안티앨리어싱(픽셀 각진 부분 스무스하게 바꿔주는거) 자동으로 되는데 윈도우는 안됨

```css
transform : rotate(0.04deg); 
```

> 이럴 때 글자를 살짝 회전시키면 윈도우에서도 안티앨리어싱 된 듯한 느낌 줌



### Flexbox

##### flexbox 레이아웃 사용법

```html
<div class="flex-container">
  <div class="box"></div>
  <div class="box"></div>
  <div class="box"></div>
</div>
```

```css
.flex-container {
  display : flex;
}₩
.box {
  width : 100px;
  height : 100px;
  background : grey;
  margin : 5px;
}
```

> 박스들을 감싸는 부모 요소에게 display : flex를 사용
>
> 그럼 박스들이 기본적으로 가로정렬로 배치 (인터넷 익스플로러 11 이상에서만 사용가능)

##### flexbox 세부속성

```css
.flex-container {
  display : flex;
  justify-content : center;  /* 좌우정렬 */ 
  align-items : center;  /* 상하정렬 */
  flex-direction : column; /* 세로정렬 */ // row -> 가로정렬 (기본값)
  flex-wrap : wrap;  /* 폭이 넘치는 요소 wrap 밑으로 보내기 */
}
.box {
  flex-grow : 2;  /* 폭이 상대적으로 몇배인지 결정 */
}
```

> Justify-content : center -> 좌우정렬, flex-start-> 좌측정렬, flex-end -> 우측정렬, space between -> 균등분할
>
> align-items : center -> 상하정렬, flex-start -> 맨위정렬, flex-end -> 맨밑정렬

##### flex-item 삼총사

* flex-basis : 기본크기값. 기본값이니까 이거 이상인 애들은 크기 안변함
* flex-grow : 아이템이 기본값을 넘어 늘어날 것인지? 0이면 안늘어남(기본값). 1부터 늘어남. 1이상을 줘서 크기 비율 정할 수도 있음
* flex-shirink : 화면 줄어들때 크기 줄어들 것인지? 1이면 줄어듦(기본값). 크기 유지하고 싶으면 0 주면 됨
* 세가지 요소를 flex로 한번에 쓸 수 있는데 flex-grow, flex-shrink, flex-basis 순. flex : 1 1 100px;



### 반응형 레이아웃

##### 모던 웹에서 사용하는 단위

```css
.box {
  width : 16px; /* 기본 px 단위 */
  width : 1.5rem; /* html태그 폰트사이즈(기본 16px)의 1.5배 */
  width : 2em; /* 내 폰트사이즈 혹은 상위요소 폰트사이즈의 2배 */
  width : 50vw; /* 브라우저(viewport) 화면 폭의 50% */
  width : 50vh; /* 브라우저(viewport) 화면 높이의 50% */
}
```

> rem 으로 크기 지정하며 기본 fond-size 커져도 모든게 같이 커짐. 요즘엔 ctl + 휠로 화면 키우니까 잘 안씀. typography 쓸때나 가끔씀

##### 반응형 웹을 만들 때 head 태그에 들어가야할 내용

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

##### media query 사용하는 법

```css
@media screen and (max-width : 1200px) { 
  .box { 
    font-size : 40px; 
  } 
} 
@media screen and (max-width : 768px) { 
  .box { 
    font-size : 30px; 
  } 
}
```

> CSS 파일 최하단에 사용
>
> 큰 사이즈 -> 작은 사이즈 순으로 사용

##### 권장 BreakPoint

breakpoint : media query 문법 max-width 안에 넣는 브라우저 폭

breakpoint는 원하는 만큼 저렇게 여러개 넣을 수 있는데 

1200px / 992px / 768px / 576px 사이즈를 권장

4개 저렇게 다 쓰면 스타일 관리하기가 귀찮기 때문에 

1200px 이하는 태블릿, 768px 이하는 모바일 이렇게 디자인하는게 가장 간편 



#### CSS 디버깅 & IE 호환성

##### CSS 디버깅

1. CSS 파일 뒤져보기
2. 구글 크롬 -> 개발자도구 (option+command+i)

##### IE 호환성

익스플로러 브라우저에서 우클릭 -> 검사 -> 에뮬레이션에서 버젼 변경하면 확인 가능

**조건부로 익스플로러 버전용 CSS파일 첨부하기**

```html
[if lt IE 9]
  <link rel="stylesheet" type="text/css" href="css/ie8.css" />
[endif]
```

>익스플로러 X 버전 이하에서만 적용할 수 있는 CSS파일을 첨부 가능



#### Font Awesome

font awesome 사이트로 가서 cdn이든 파일이든 받아서 넣기

cdn은 계정 클릭하면 볼 수 있음

cdn으로 하면 빠르고 간편한데, 사이트 다운되면 못씀. 불안정함

이제 아이콘 쓰려면 font awesome 사이트에서 아이콘 골라서 html 복붙하면 됨



### Transition 애니메이션

1. 시작스타일 만들기
2. 최종스타일 만들기
3. 언제 최종으로 변경되는지 -> psedo 클래스
4. 어떻게 최종으로 변경되는지 -> transition

##### transition 세부 속성

```css
.box {
  transition-delay: 1s; /* 시작 전 딜레이 */
  transition-duration: 0.5s; /* transition 작동 속도 */
  transition-property: opacity; /* 어떤 속성에 transition 입힐건지 */
  transition-timing-function: ease-in; /* 동작 속도 그래프조정 */
}
```

(참고) psedo class 만들때 주의점으로 부모 태그는 선택 불가능함. 형제 태그는 선택할 수 있는 셀렉터 +

크롬 개발자도구에서 점 세개 클릭하고 도구 더보기 -> 애니메이션 탭으로 가면 사이트에서 애니메이션이 어떻게 구현되는지 알 수 있음



### Bootstrap

부트스트랩 설치하려면 부트스트랩 홈페이지 가서 버젼 체크하고

1. Bootstrap.min.css
2. Bootstrap.bundle.min.js 가져와서 html에 넣어야됨

부트스트랩은 뼈대 디자인할 때 유용. 디자인 바꿀 것만 수정해주면 됨 -> 본인의 CSS파일 넣고 커스텀 디자인 넣으면 됨

##### Utility Class 사용가능 -> 홈페이지 utility 메뉴에서 볼 수 있음

```html
<div class="container">이쁜 여백가진 박스</div>
<div class="mt-5">margin-top 쉽게 주기</div>
<div class="pb-5">padding-bottom 쉽게 주기</div>
<div class="fs-3">font-size 쉽게 주기</div>
<div class="text-center">text-align 쉽게 주기</div>
<div class="fw-bold">font-weight 쉽게 주기</div>
```

##### 부트스트랩 쓰면 반응형 레이아웃이 매우 편리

.row 안에 .col 사용하면 균일하게 쪼개기 가능

가로로 쪼개고 싶으면 col-차지할크기(전체 12개 중에 몇개 차지할지)

col 이용해서 가로로 쪼갠걸 반응형으로 하려면 조건문 더하면 됨 col-md-6 (md사이즈 이상에서만 적용) 이런 식으로

md사이즈가 뭔데? 부트스트랩 사이트에서 grid option 검색 ㄱㄱ 768px 임. xl는 1200px

order-숫자 부착해서 div 순서 재배치도 가능



### CSS 덮어쓰기

CSS 원본 파일을 건들면 안되는 경우 새로운 CSS 파일로 덮어쓰기해야됨

1. 그냥 같은 클래스명 하단에 쓰기 : 하단에 쓰는게 무조건 우선임. html 에서도 link 태그를 밑에 있는게 더 우선임

2. 우선순위 높이기(비추) style="" 1000점 #id -> 100점 .class -> 10점 태그 -> 1점 (!important가 오른쪽에 있으면 -> 10,000점) 

3. specificity 높이기 : 더 자세하게 쓰는 경우 20점 ex) div.min-background .custom{} <- 21점

	-> specificity 높이겠다고 셀렉터 이따구로 길게 적으면 나중에 덮어쓰기 힘들어짐



#### 좋은 코드 원칙

1. 나중에 수정.관리 쉬우면 좋은 코드임
2. 확장성이 좋으면 좋은 코드(재활용가능한지, 확장해서 다른 class만들기 쉬운지)

