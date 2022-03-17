#### Array

여러가지 자료를 한곳에 저장하고 싶을 때 사용하는 자료형

```js
var 어레이 = ['BMW', 520, 'white'];
console.log(어레이[1]); // 520
```



#### Object

역시 여러가지 자료를 한곳에 저장하고 싶을 때 사용하는 자료형

```js
var 오브젝트 = { brand : 'BMW', model : 520 };
console.log(오브젝트.brand); // BMW
console.log(오브젝트['model']); // 520
```

Key & Value 로 저장

수정할 때는 오브젝트.brand = '그랜져' 이런 식으로 등호 이용해서 수정할 수 있음

array는 순서개념이 있고, object는 순서 개념이 없음. 대신에 key값으로 구분

array 안에 object, array 넣기도 가능함



#### 데이터바인딩

자바스크립트로 데이터를 HTML에 꽂아넣는 작업

```html
<div class="container mt-3">
  <div class="card p-3">
    <span class="car-title">상품명</span>
    <span class="car-price">가격</span>
  </div>
</div> 

<script>
  var car2 = { name : '소나타', price : 50000 } 
  document.querySelector('.car-title').innerHTML = car2.name;
  document.querySelector('.car-price').innerHTML = car2.price;
</script>
```



#### SSR & CSR

서버에서 유저한테 html을 보낼때는 두가지 방법이 있음

1. 서버에서 html을 미리 완성해서 고객한테 보내줌 => server-side-rendering 
2. 서버가 비어있는 html과 데이터를 보내주고 자바스크립트를 이용해서 유저 브라우저 안에서 html 완성 => client-side-rendering

서버사이드렌더링은 서버가 html 다 만들어야해서 서버가 좀 귀찮고, 클라이언트사이드렌더링은 서버가 편함

각각 장단점이 있으니 하고 싶은대로 하면 됨. 



#### 문자 중간에 변수 넣기

자바스크립트에서는 덧셈기호 쓰면 문자이어붙일 수 있음

```js
var a = '안녕';
console.log('문자' + a + '문자');  // '문자안녕문자'출력됨
```

백틱 기호 활용 가능 : ES6

```Js
var a = '안녕';
console.log(`문자 ${a} 문자`);  // '문자안녕문자'출력됨
```



#### input & change 이벤트

input 이벤트 : input 값 바뀌면 발동

change 이벤트 : input 값 바뀌고 focus 다른데로 찍으면 발동

```js
document.queryselector('input').addEventListener('change', function(){
  인풋 값이 변경되었을 때 실행할 코드 
});

document.queryselector('input').addEventListener.on('input', function(){
  인풋 값이 변경되었을 때 실행할 코드  
});
```

.value : input 값 가져오는 메서드



#### 자바스크립트로 html 만들기

append() : html을 하단에 추가하는 메서드

```js
var 템플릿 = '<p>안녕하세요</p>'
document.queryselector('div').append(템플릿);
```

백틱(``) : 백틱 안에서 문자 입력하면 엔터키 가능. ES6

```js
$('#option1').on('change', function(){
      if ( $('#option1').val() == '셔츠' ) {
        $('#option2').html(''); // 원래 있던 값 초기화
         var 템플릿 = '<option>95</option><option>100</option><option>105</option>';
         $('#option2').append(템플릿);
      } else {
        $('#option2').html('');
         var 템플릿 = '<option>28</option><option>30</option><option>32</option>';
         $('#option2').append(템플릿);
      }
});
```



#### forEach 반복문

일반적인 for 반복문

```js
var 사이즈 = [26,28,30,32,34,36];

$('#option1').on('change', function(){
  if ($('#option1').val() == '바지'){

     for (var i = 0; i < 6; i++) {
       var 템플릿 = `<option>${사이즈[i]}</option>`;
       $('#option2').append(템플릿);
     }
 }
});
```

forEach 반복문으로 변경 : 자료에 저장된 데이터 개수만큼 function 내부의 코드를 반복

```js
var 사이즈 = [26,28,30,32,34,36];

$('#option1').on('change', function(){
  if ($('#option1').val() == '바지'){

     사이즈.forEach(function(i){
        var 템플릿 = `<option>${i}</option>`; // 사이즈 개수만큼 반복할 코드, i는 자료에 저장된 하나하나의 데이터
        $('#option2').append(템플릿);
     });
 }
});
```



#### Arrow Function : ES6

형태 : () => {}

```js
var 사이즈 = [26,28,30,32,34,36];

사이즈.forEach((i) => {
    console.log(i)
});
```

1. 파라미터가 하나면 소괄호를 생략가능 

2. 함수의 중괄호 내에 한줄밖에 없으면 {} 중괄호도 생략가능 

3. function 문법 내에선 this라는 키워드의 값이 새롭게 변하지만 arrow function의 경우 그냥 함수 바깥에 있던 this를 그대로 사용



### array 정렬

##### sort() : array 가나다순 정렬

```js
var 어레이 = [7,3,5,2,40];
어레이.sort();
// [2,3,40,5,7]
```

```Js
// 가나다순 정렬하려면 문자열을 등호로 비교
$('#abc').click(function(){
    products.sort(function(a,b){
      if (a.title < b.title == true ) {
        return -1
      } else {
        return 1
      }
    })
});
```

##### sort()로 숫자 정렬할 때는 

```js
var 어레이 = [7,3,5,2,40];
어레이.sort(function(a,b){
  return a - b
});
// [2,3,5,7,40]
```

a, b 는 array 안의 데이터들

양수(+)를 return 하면 a를 오른쪽 b를 왼쪽으로

음수(-)를 return 하면 a를 왼쪽 b를 오른쪽으로 보냄



##### filter() : 원본 수정하지 않고 조건에 맞는 새배열을 만들어냄

```js
var 어레이 = [7,3,5,2,40];
var 뉴어레이 = 어레이.filter(function(a){
  return a < 4 // 조건식
})
// 뉴어레이 = [2,3]
```

return 조건식이 참인 경우에만 리턴



##### map() : array 자료들에 똑같은 작업을 시킬 때

````js
var 어레이 = [7,3,5,2,40];
var 뉴어레이 = 어레이.map(function(a){
  return a * 2
})
// 뉴어레이 = [14,6,10,4,80]
````



### Ajax

##### Ajax : 서버에다가 GET / POST 요청해서 받아오는데 새로고침 없이 받아오기

서버 : 유저가 요청을 하면 데이터를 가져다주는 역할 수행

요청(request) : Get / Post

Get : URL을 입력해서 요청 (읽기)

Post : 숨겨서 정보를 전달하거나 요청 (쓰기)

```js
$('어쩌구버튼').click(function(){
  $.ajax({ 
    url : 'https://codingapple1.github.io/hello.txt',
    type : 'GET'
  }).done(function(데이터){
    $('h4태그').html(데이터)
  });
});
```

##### 여러가지 프로퍼티 설정 가능

```js
$.ajax({ 
    url : 'https://codingapple1.github.io/hello.txt',
    type : 'GET',
    data : '어쩌구',
    dataType : '저쩌구'
});
```

##### .done 함수말고 .fail, .always 함수도 사용 가능

```js
$.ajax({ 
    url : 'https://codingapple1.github.io/hello.txt',
    type : 'GET'
}).fail(function(jqXHR, textStatus, errorThrown){
  // 요청이 실패했을 때 실행할 코드 
  // textStatus, errorThrown을 출력해보면 Ajax 요청이 왜 실패했는지 이유 출력
}).always(function(){
  // 실패하든 성공하든 항상 실행할 코드
})
```



#### JSON 자료형

서버에서 데이터를 보내줄때 object, array 대신 활용하는 자료형

데이터 중간에 깨지는 것을 막아줌

```js
{"brand" : "Hyundai", "model" : "Kona", "price" : 30000, "img" : "https://codingapple1.github.io/kona.jpg"}
```

따라서 JSON을 바꿔줘야 활용가능해짐

```js
var 오브젝트 = JSON.parse(제이슨데이터)
```

반대로 오브젝트를 제이슨으로 바꿀때는

```js
var 제이슨 = JSON.stringify(오브젝트데이터)
```



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



#### Hammer.js

터치 기능 구현하는데 도움을 주는 라이브러리

설치 : 직접 설치 및 cdn 방식

```js
var 이미지1 = document.querySelectorAll('.slide-box img')[0];
    
var 매니저 = new Hammer.Manager(이미지1);
매니저.add(new Hammer.Pan({ threshold: 0 }));
    
매니저.on('pan', function(e){
  //이미지1이 pan 되었을 때 실행할 코드 
})
```

터치를 구현할 요소를 찾아서 new Hammaer.manager() 함수에 담고, 

내가 사용할 이벤트를 add() 함수로 등록하고 threshold 값 셋 (이벤트 발동 전까지의 역치값)

마지막으로 핵심적인 이벤트리스너를 매니저에 달아줌

e.deltaX : X축 드래그한 거리

드래그한 거리만큼 이미지를 왼쪽으로 이동

```js
매니저.on('pan', function(e){
  $('.slide-container').css('transform', 'translateX(' + e.deltaX + ')' );
})
```

왼쪽으로만 슬라이딩 하고 싶으면 e.deltaX가 음수일때만

```js
매니저.on('pan', function(e){
  if (e.deltaX < -1) {
    $('.slide-container').css('transform', 'translateX(' + e.deltaX + ')' );
  }
})
```

마우스를 놓으면 이미지2로 변하는 기능

e.isFinal : 터치 끝났는지 알려줌

```js
매니저.on('pan', function(e){
    if ( e.deltaX < -1 ){
      $('.slide-container').css('transform', 'translateX('+ e.deltaX +'px)');
      //터치가 끝날 때 이미지2로 변하게 해주셈 
      if (e.isFinal) {
        $('.slide-container').css('transform', 'translateX(-100vw)');
      }
    }
});
```

transition 부여하면 드래그하는 속도도 느려짐

이미지2를 보여주기전에 잠깐만 transition 부여해주는 코드로 짜고, 끝나면 제거

```js
매니저.on('pan', function(e){
    if ( e.deltaX < -1 ){
      $('.slide-container').css('transform', 'translateX('+ e.deltaX +'px)');
      //터치가 끝날 때 이미지2로 변하게 해주셈 
      if (e.isFinal) {
        $('.slide-container').addClass('transforming');
        $('.slide-container').css('transform', 'translateX(-100vw)');
        //0.5초 후에 코드를 실행하고 싶을 때 쓰는 문법 
        setTimeout(function(){
          $('.slide-container').removeClass('transforming');
        }, 500);
      }
    }
});
```

