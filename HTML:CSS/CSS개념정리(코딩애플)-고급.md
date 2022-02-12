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

