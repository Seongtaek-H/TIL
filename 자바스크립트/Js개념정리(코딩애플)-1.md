### 자바스크립트의 목적

웹 환경에서 JavaScript의 목적 : HTML 조작과 변경



##### Alert 박스 만들기

미리 UI 디자인하고 UI를 평소엔 안보이게 숨겨놓고, 버튼을 누르거나할 경우 UI 뜨게하기

(숨길 땐 보통 display : block 혹은 visibility : hidden)

```js
document.getElementById('어쩌구').style.display = 'block'
```

이렇게 자바스크립트 코드짜고 온클릭 속성으로 넣어서 다시 뜨게 하기도 하고



### function(함수) 사용법

1. 긴 코드 하나로 묶기 (특정 기능을 다음에도 쓰기 위해 모듈화)

```js
function 안내창열기(){ // 축약
  document.getElementById('alert').style.display = 'block';
}
안내창열기(); 	// 다시사용
```

2. 파라미터 - 구멍뚫기

```js
function 안내창열기(구멍){
  document.getElementById('alert').style.display = 구멍;
}

안내창열기('none');
안내창열기('block');
```

* 함수에 구멍뚫어놓으면 앞으로 함수쓸 때 () 안에 아무거나 입력 가능해짐

```js
function 더하기(구멍, 구멍2){ 
  구멍 + 구멍2; 
} 

더하기(3, 4); 
```

* 파라미터는 여러개 사용 가능



### 이벤트 리스너

<img src="./이벤트리스너.png" aligh="left"/>

```js
셀렉터로찾은요소.addEventListener('click', function(){ 
  실행할코드
});

document.getElementById('close').addEventListener('click', function(){
    document.getElementById('alert').style.display = 'none'
});
```

* 이벤트 종류는 클릭뿐만 아니라 매우 많음 https://developer.mozilla.org/en-US/docs/Web/Events



### JQuery

##### 설치

1. CDN 이용해서 설치
2. 파일 직접 다운받아서 설치

##### 기본문법

```js
document.getElementById('title').innerHTML = '바보'; // 바닐라JS
$('#title').html('바보');		// 제이쿼리
```

##### JQuery 셀렉터

```js
$('#title'); 
$('.box') 
```

* ${} 안에 찾는 셀렉터 입력하면 됨

##### jQuery 함수/메소드

```js
$('#title').text('바보'); 
$('#title').html('<p>바보</p>'); 
$('#title').css('color', 'red'); 
```

* 자바스크립트 처럼 메소드를 뒤에 붙여주면 되는데 자바스크립트랑 메소드명이 다름
* 자바스크립트로 찾은 요소는 자바스크립트 함수/메소드 붙여야되고, 제이쿼리로 찾는 요소는 제이쿼리 함수/메소드 붙여야됨



### JQuery 쓰는 이유

1. ##### 코드가 짧아짐

2. ##### 여러개의 요소를 한번에 싸잡아서 바꿀 수 있음

```html
<p class="greeting">안녕하세요</p>
<p class="greeting">안녕하세요</p>
```

* 위 두개 요소를 한 번에 바꾸려면?

```js
document.getElementsByClassName('greeting')[0].innerHTML = '안녕';
document.getElementsByClassName('greeting')[1].innerHTML = '안녕';

$('.greeting').html('안녕')
```

(참고) jQuery로 찾은 요소 중 맨 위의 것만 바꾸고 싶은 경우 **$('.greeting').eq(0)** 

```html
<button class="btn">버튼btn</button>
<button class="btn">버튼btn</button>
<button class="btn">버튼btn</button>

<script>
  $('.btn').on('click', function(){ 버튼누르면 실행할 코드~~ } );
</script>
```

* 버튼 여러개 있어도 한번에 이벤트리스너 넣기 가능



3. ##### 애니메이션 넣기 쉬움

```js
$('.greeting').hide() 		// 사라지게 하기 <=> show()
$('.greeting').fadeOut()	// 페이드 아웃	 <=> fadeIn()
$('.greeting').slideUp()	// 접어올리기	 <=> slideDown()
```



#### JQuery 이벤트리스너

```js
$('어쩌구').click(function(){
  //어쩌구를 클릭시 실행할 코드
});

$('어쩌구').on('click', function(){
  //어쩌구를 클릭시 실행할 코드
});
```



#### JQuery toggle 함수

```js
$('버튼').click(function(){
  $('서브메뉴').slideToggle(); // lideUp과 slideDown을 번갈아가며 적용
});
```

* toggle 기능 : 한 버튼으로 on / off 가 되는 기능
* toggle 이라는 이름이 붙은 함수들로 '토글' 기능을 쉽게 개발할 수 있음

```js
$('서브메뉴').slideToggle(); 
$('서브메뉴').fadeToggle(); 
$('서브메뉴').toggle(); 
```



### If / else 조건문

```js
if (조건식1) {
  조건식1 참이면 실행할 코드
} else if (조건식2) { // 첫번째 조건식이 참이 아니면 확인
  조건식2 참이면 실행할 코드
} else {
  조건식 1,2 둘다 false면 실행할 코드
}
```

(참고) 0, "", null, undefined, NaN 얘네도 조건식에서는 false로 판단함

* 조건식에서 여러 조건을 확인하고 싶으면 &&, || 사용
* if문은 위에서부터 읽어내려가다가 조건이 참인 하나만 실행하고, 그 뒤의 if/else는 해석안함



##### 폼을 전송할 때, input이 공백인 경우

```js
$('form').on('submit',function(e){ 
  if ($('#email').val() == ''){
    e.preventDefault();			// 폼 전송 막는 메소드
    $('#email-alert').show();
  } 
});
```



form과 관련된 이벤트

input : $('#email') 요소 내부에 입력된 값이 바뀔 때마다 내부 코드 실행 (값이 변경될 때 실행)

```js
$('#email').on('input', function(e){ 
  실행할 코드
});
```

change : $('#email') 요소 내부에 입력된 값이 바뀔 때마다 내부 코드 실행. focus를 잃을 때(커서를 다른 곳으로 옮길때)만 실행

```js
$('#email').on('change', function(e){ 
  실행할 코드
});
```

