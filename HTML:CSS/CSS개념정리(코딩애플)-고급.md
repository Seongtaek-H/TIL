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



##### position : sticky (Edge 이상에서 사용가능)

조건부로 fixed

스크롤이 되어서 이미지가 보이는 순간 지정한 위체에서 fixed됨

top : 100px 라고하면 위에서 100px 위치

부모박스 넘어서면 해제됨



#### 스크롤 애니메이션

스크롤 현재 높이를 알 수 있는 js 필요

```	js
document.addEventListener("scroll", function () {
	var 높이 = document.documentElement.scrollTop;
	console.log(높이);
});
```

스크롤 높이가 650일 때 opacity = 1, 1150일때 opacity=0 이 되도록 가변적인 값 y를 설정해서 1차 방정식으로 구해야됨

"스크롤바높이가 650~1150이 될 때 1~0이 되는 가변적인 값" y = a * 높이 + b

1 = a * 650 + b 

0 = a * 1150 + b

a = -1/500 

b = 115/50

```js
document.addEventListener("scroll", function () {
  var 높이 = document.documentElement.scrollTop;
  console.log(높이);
  var y = (-1 / 500) * 높이 + 115 / 50;
  document.querySelector(".card-box").style.opacity = y;
  console.log(y);
});
```

