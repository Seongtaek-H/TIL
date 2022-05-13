# 20. strict mode

## 20.1 strict mode란?

암묵적 전역 (implicit global) : 모든 스코프에 사용한 변수의 선언이 존재하지 않을 때 자바스크립트 엔진이 암묵적으로 전역 객체에 프로퍼티를 동적 생성하는 것

```js
function foo(){
  x = 10;
}
foo();

console.log(x); // 10
```

암묵적 전역은 개발자의 의도와는 상관없이 발생하기 때문에 오류를 발생시키는 원인이 될 수 있으니, 반드시 변수를 선언한 다음에 사용해야 함

하지만 오타나 문법 지식의 미비로 실수가 발생할 수 있기 때문에, 잠재적 오류를 발생시키기 어려운 개발 환경을 만들고 그 환경에서 개발하는 것이 좋음

이를 지원하기 위해 ES5부터 strcit mode(엄격 모드)가 추가되었고, strict mode는 자바스크립트 언어의 문법을 좀 더 엄격하게 적용시켜 오류를 발생시킬 가능성이 높거나 자바스크립트 엔진의 최적화 작업에 문제를 일으킬 수 있는 코드에 대해 명시적인 에러를 발생시킴

ESLint 같은 린트 도구를 사용해도 strict mode 와 유사한 효과를 얻을 수 있는데 린트 도구는 정적 분석 기능을 통해 소스코드를 실행하기 전에 소스코드를 스캔하여 문법적 오류뿐 아니라 잠재적 오류까지 찾아내고 오류의 원인을 리포팅해줌 (참고 : https://poiemaweb.com/eslint)



## 20.2 strict mode의 적용

strict mode를 적용하려면 전역의 선두 또는 함수의 몸체의 선두에 `'use strict';` 를 추가. 전역 선두에 추가하면 스크립트 전체에 strict mode 적용됨

```js
'use strict';

function foo(){
  x = 10; // ReferenceError : x is not defined
}
foo()
```

함수 몸체의 선두에 추가하면 해당 함수와 중첩 함수에 strict mode 적용

```js
function foo(){
  'use strict';
  
  x = 10; // ReferenceError : x is not defined
}
foo()
```

코드 선두에 'use strict'; 를 위치시키지 않으면 제대로 동작 안한다

```js
function foo(){
  x = 10; // 에러를 발생시키지 않음
  'use strict';
}
foo()
```



## 20.3 전역에 strict mode를 적용하는 것은 피하자

전역에 적용한 strict mode는 스크립트 단위로 적용됨

스크립트 단위로 적용된 strict mode는 다른 스크립트에 영향을 주지 않고 해당 스크립트에 한정되어 적용됨

strict mode 스크립트와 non-stirct mode 스크립트를 혼용하는 것은 오류를 발생시킬 수 있음

특히 외부 서드파티 라이브러리를 사용하는 경우 라이브러리가 non-strict mode인 경우도 있기 때문에 전역에 strict mode를 적용하는 것은 바람직하지 않음

이런 경우 즉시 실행 함수로 스크립트 전체를 감싸서 스코프를 구분하고 즉시 실행 함수 선두에 stirct mode 적용

```js
// 즉시 실행 함수 선두에 strict mode 적용
(function (){
  'use strict';
  
  // Do something
})
```



## 20.4 함수 단위로 stirct mode를 적용하는 것도 피하자

함수 단위로 strict mode를 적용할 수도 있으나,

어떤 함수는 strict mode를 적용하고 어떤 함수는 strict mode를 적용하지 않는 것은 바람직하지 않으며, 모든 함수에 일일이 strict mode를 적용하는 것도 번거롭다.

strict mode가 적용된 함수가 참조할 함수 외부의 컨텍스트에 strict mode를 적용하지 않는다면 문제가 발생할 수 있다.

```js
(function(){
  // non-strict mode
  var let = 10; // 에러가 발생하지 않음
  
  function foo(){
    'use struct';
    let = 20; // SyntaxError = Unexpected strict mode reserved word
  }
  foo();
});
```

**따라서 strict mode는 즉시 실행 함수로 감싼 스크립트 단위로 적용하는 것이 바람직**



## 20.5 strict mode가 발생시키는 에러

### 20.5.1 암묵적 전역

선언하지 않은 변수를 참조하면 ReferenceError 발생

```Js
(function(){
  'use strict';
  x = 1;
  console.log(x); // ReferenceError : x is not defined
}());
```



### 20.5.2. 변수, 함수, 매개변수의 삭제

delete 연산자로 변수, 함수, 배개변수를 삭제하면 SyntaxError 발생

```Js
(function(){
  'use strict';
  
  var x = 1;
  delete x; // SyntaxError : Delete of an unqualified identifier in strict mode.
  
  function foo(a){
    delete a; // SyntaxError : Delete of an unqualified identifier in strict mode.
  }
  delete foo; // SyntaxError : Delete of an unqualified identifier in strict mode.
}());
```



### 20.5.3 매개변수 이름의 중복

중복된 매개변수 이름을 사용하면 SyntaxError 발생

```js
(function(){
  'use strict';

  // SyntaxError : Dupulicate parameter name not allowed in this context
  function foo(x, y){
    return x + x;
  }
  console.log(foo(1, 2));
}());
```



### 20.5.4 with문의 사용

with문을 사용하면 SyntaxError 발생

with문 : 전달된 객체를 스코프 체인에 추가

```js
(function(){
  'use strict'
  
  // SyntaxError : strict mode code may not include a with statement
  with({ x : 1}){
    console.log(x);
  }
}());
```



## 20.6 strict mode 적용에 의한 변화

### 20.6.1 일반 함수의 this

strict mode에서 함수를 일반 함수로서 호출하면 this에 undefined 바인딩. 생성자 함수가 아닌 일반 함수 내부에선 this를 사용할 필요가 없기 때문 (에러는 발생하지 않음)

### 20.6.2 arguments 객체

strict mode에서는 매개변수에 전달된 인수를 재할당하여 변경해도 arguments 객체에 반영되지 않음

```js
(function(a){
  'use strict';
  // 매개변수에 전달된 인수를 재할당하여 변경
  a = 2;
  
  // 변경된 인수가 arguments 객체에 반영되지 않음
  console.log(arguments); // { 0: 1, length: 1}
})());
```



# 21. 빌트인 객체

## 21.1 자바스크립트 객체의 분류

* 표준 빌트인 객체 (standard built-in objects / native objects / global objects)

	> ECMAScript 사양에 정의된 객체. 애플리케이션 전역의 공통 기능 제공. 표준 빌트인 객체는 전역 객체의 프로퍼티로 제공되므로 별도의 선언없이 전역 변수처럼 참조 가능
	
* 호스트 객체 (host objects)

	> 자바스크립트 실행 환경(브라우저나 node.js) 에서 추가로 제공하는 객체. 
	>
	> 브라우저 환경에서는 DOM, BOM, Canvas, XMLHttpRequest, fetch, requestAnimationFram, SVG, Web Storage, Web Component, Web Worker 와 같은 클라이언트 사이드 Web API를 호스트 객체로 제공, Node.js 환경에서는 Node.js 고유의 API를 호스트 객체로 제공

* 사용자 정의 객체 (User-defined objects)

	> 표준 빌트인 객체와 호스트 객체처럼 기본 제공되는 객체가 아닌 사용자가 직접 정의한 객체



## 21.2 표준 빌트인 객체

자바스크립트는 Object, String, Number, Boolean, Symbol, Date, Math, RegExp, Array, Map/Set, WeakMap/WeakSet, Function, Promise, Reflect, Proxy, JSON, Error 등 40여개의 표준 빌트인 객체를 제공 (참고 https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects)

Math, Reflect, JSON을 제외한 표준 빌트인 객체는 모두 인스턴스를 생성할 수 있는 생성자 함수 객체. (생성자 함수로 호출이 가능)

`생성자 함수인 표준 빌트인 객체가 생성한 인스턴스의 프로토타입`은 표준 빌트인 객체의 `prototype 프로퍼티에 바인딩된 객체`임

* String 생성자 함수로 생성한 String 인스턴스의 프로토타입은 String.prototype
* Number 생성자 함수로 생성한 Number 인스턴스의 프로토타입은 Number.prototype ... 40개 모두 동일

표준 빌트인 객체의 prototype 프로퍼티에 바인딩된 객체(ex. String.prototype)는 다양한 기능의 빌트인 프로토타입 메서드를 제공 

이 프로토타입 메서드는 생성자 함수로 생성된 모든 인스턴스가 상속해서 사용 가능

표준 빌트인 객체는 인스턴스 없이도 호출 가능한 빌트인 정적 메서드를 제공함

```js
// Number 생성자 함수에 의한 Number 객체 생성
const numObj = new Number(1.5); // Number {1.5}

// toFixed는 Number.prototype의 프로토타입 메서드
// Number.prototype.toFixed는 소수점 자리를 반올림하여 문자열로 반환
console.log(numObj.toFixed()); // 2

// isInteger는 Number의 정적 메서드
// Number.isInteger는 인수가 정수(integer)인지 검사하여 그 결과를 Boolean으로 반환
console.log(Number.isInteger(0.5)); // false
```



## 21.3 원시값과 래퍼 객체

문자열, 숫자, 불리언 등의 원시값이 있는데도 문자열, 숫자, 불리언 객체를 생성하는 String, Number, Boolean 등의 표준 빌트인 생성자 함수가 존재하는 이유는 무엇일까?

```js
const str = 'hello';

// 원시 타입인 문자열이 프로퍼티와 메서드를 갖고 있는 객체처럼 동작한다.
console.log(str.length); // 5
console.log(str.toUpperCase()); // HELLO
```

원시값은 객체가 아니므로 프로퍼티와 메서드를 가질 수 없는데 원시값인 문자열이 객체처럼 동작

=> 원시값에 대해 객체처럼 마침표 표기법(대괄호 표기법)으로 접근하면 자바스크립트 엔진이 일시적으로 원시값을 연관된 객체로 변환하기 때문

**래퍼 객체 (wrapper object)** : 문자열, 숫자 불리언 값에 대해 객체처럼 접근하면 생성되는 임시 객체

> 문자열에 대해 마침표 표기법으로 접근하면 래퍼 객체인 String 생성자 함수의 인스턴스가 생성되고 문자열은 래퍼 객체의 [[StringData]] 내부 슬롯에 할당
>
> 이 때 문자열 래퍼 객체인 String 생성자 함수의 인스턴스는 String.prototype의 메서드를 상속받아 사용 가능 
>
> 그 후래퍼 객체의 처리가 종료되면 래퍼 객체의 [[StringData]] 내부 슬롯에 할당된 원시값으로 원래의 상태로 되돌리고, 래퍼 객체는 가비지 컬렉션의 대상이 됨

문자열, 숫자, 불리언, 심벌은 래퍼 객체에 의해 객체처럼 사용할 수 있으므로, 표준 빌트인 객체의 프로토타입 메서드 또는 프로퍼티를 참조할 수 있다

따라서 **String, Number, Boolean 생성자 함수를 New 연산자와 함께 호출하여 인스턴스를 생성할 필요가 없다!**

문자열, 숫자, 불리언, 심벌 이외의 원시값인 null, undefined는 래퍼 객체를 생성하지 않으므로 이 값을 객체처럼 사용하면 에러가 발생함



## 21.4 전역 객체

**전역 객체 (global object)** : 코드가 실행되기 이전 단계에서 자바스크립트 엔진에 의해 어떤 객체보다도 먼저 생성되는 측수한 객체, 어떤 객체에도 속하지 않은 최상위 객체

환경에 따라 지칭하는 이름이 다름

* 브라우저 : window, self, this, frames
* Node.js : global
* E11 이후 : globalThis 로 통일

전역 객체는 표준 빌트인 객체와 환경에 따른 호스트 객체, var 키워드로 선언한 전역 변수와 전역 함수를 프로퍼티로 가짐

**전역 객체는 계층적 구조상 어떤 객체에도 속하지 않은 모든 빌트인 객체(표준 빌트인 객체와 호스트 객체)의 최상위 객체**. 프로토타입 상속 관계상 최상위 객체라는 의미가 아님

전역 객체는 어떤 객체의 프로퍼티도 아니며 객체의 구조상 표준 빌트인 객체와 호스트 객체를 프로퍼티로 소유한다는 의미

**`전역 객체의 특징`**

* 개발자가 의도적으로 생성할 수 없다. 전역 객체를 생성할 수 있는 생성자 함수가 제공되지 않는다.
* 전역 객체의 프로퍼티와 메서드를 참조할 때는 window(또는 global)를 생략할 수 있다.
* Object, String, NUmber, Boolean, Function, Array, RegExp, Date, Match, Promise 와 같은 모든 표준 빌트인 객체를 프로퍼티로 가지고 있다.
* 실행 환경(브라우저, Node.js)에 따라 추가적으로 프로퍼티와 메서드를 갖는다. 브라우저에선 Web API를 호스트 객체로 제공하고 Node.js 에서는 고유의 API
* var 키워드로 선언한 전역 변수와 선언하지 않은 변수에 값을 할당한 암묵적 전역, 그리고 전역 함수는 전역 객체의 프로퍼티가 된다.
* let, const는 전역 객체의 프로퍼티가 아니라 개념적인 블록(전역 렉시컬 환경의 선언적 환경 레코드) 내에 존재
* 브라우저의 모든 자바스크립트 코드는 하나의 전역 객체 window를 공유함. 여러 개의 script 태그로 자바스크립트 코드를 분리해도 하나의 전역 객체 공유



### 21.4.1 빌트인 전역 프로퍼티

**빌트인 전역 프로퍼티 (built-in global property)** : 전역 객체의 프로퍼티

##### Infinity

> Infinity 프로퍼티는 무한대를 나타내는 숫자값 Infinity를 갖는다

**NaN**

> NaN 프로퍼티는 숫자가 아님(Not-a-Number)을 타나내는 숫자값 NaN을 갖는다. NaN 프로퍼티는 Number.NaN 프로퍼티와 같다.

**undefined**

> undefined 프로퍼티는 원시 타입 undefined를 값으로 갖는다



### 21.4.2 빌트인 전역 함수

**빌트인 전역 함수 (built-in flobal function)** : 애플리케이션 전역에서 호출할 수 있는 빌트인 함수로 전역 객체의 메서드

##### eval

> 자바스크립트 코드를 나타내는 문자열을 인수로 전달받고, 전달받은 문자열 코드가 표현식이면 문자열 코드를 런타임에 평가하여 값을 생성하고,
>
> 전달받은 인수가 표현식이 아닌 문이라면 eval 함수는 해당 문을 런타임에 실행

```js
// 표현식인 문
eval('1 + 2;'); // -> 3
// 표현식이 아닌 문
eval('var x = 5;'); // -> undefined

// eval 함수에 의해 런타임에 변수 선언문이 실행되어 x 변수가 선언됨
console.log(x); // 5
```

eval 함수를 통해 사용자로부터 입력받은 콘텐츠를 실행하는 것은 보안에 매우 취약하며, eval 함수를 통해 실행되는 코드는 자바스크립트 엔진에 의해 최적화가 수행되지 않으므로 일반적인 코드 실행에 비해 처리 속도가 느리다. **따라서, eval 함수의 사용은 금지해야 한다.** 크롬에서 eval 함수 써보니까 되지도 않는다.



##### isFinite

> 전달받은 인수가 정상적인 유한수인지 검사하여 유한수면 true를 반환하고, 무한수면 false를 반환
>
> 전달받은 인수 타입이 숫자가 아니라면 숫자로 타입을 변환한 후 검사를 수행하고, 인수가 NaN으로 평가되는 값이면 false를 반환함

```js
isFinite(0); // true
isFinite(2e64); // true
isFinite("10"); // true: '10' -> 10
isFinite(null); // true: null -> 0

isFinite(Infinity); // false
isFinite(NaN); // false
isFinite('Hello'); // false
```



##### isNan

> 전달받은 인수가 NaN인지 검사하여 결과를 불리언 타입으로 반환

```js
isNaN(NaN); // true
isNaN(10); // false
```



##### parseFloat

> 전달받은 문자열 인수를 부동 소수점 숫자, 즉 실수로 해석하여 반환

```js
parseFloat("3.14"); // 3.14
parseFloat("10.00"); // 10

// 공백으로 나뉜 문자열은 첫 번째 문자열만 반환
parseFloat("34 45 67"); // 34
parseFloat("40 years"); // 40

// 첫 번째 문자열을 숫자로 변환 할 수 없으면 NaN 반환
parseFloat("years 40"); // NaN

// 앞뒤 공백 무시
parseFloat(" 40 "); // 40
```



##### parseInt

> 전달받은 문자열 인수를 정수로 해석하여 반환. 두 번째 인수로 진법을 나타내는 기수(2~36)을 전달할 수 있고, 기수를 지정하면 첫 번째 인수로 전달된 문자열을 해당 기수의 숫자로 해석하여 반환. 반환값은 언제나 10진수

```js
// '10'을 10진수로 해석하고 그 결과를 10진수 정수로 반환한다.
parseInt("10"); // 10

// '10'을 2진수로 해석하고 그 결과를 10진수 정수로 반환한다.
parseInt("10", 2); // 2

// '10'을 8진수로 해석하고 그 결과를 10진수 정수로 반환한다.
parseInt("10", 8); // 8

// '10'을 16진수로 해석하고 그 결과를 10진수 정수로 반환한다.
parseInt("10", 16); // 16
```

참고로 기수를 지정하여 10진수 숫자를 해당 기수의 문자열로 변환하여 반환하고 싶을 때는 `Number.prototype.toString` 메서드 사용

```js
const x = 15;

// 15를 2진수로 변환해서 문자열로 반환
x.toString(2); // '1111'
// 문자열 '1111'을 2진수로 해석하고 그 결과를 10진수 정수로 반환
parseInt(x.toString(2),2); // 15

// 15를 8진수로 변환해서 문자열로 반환
x.toString(8); // '17'

// 15를 16진수로 변환해서 문자열로 반환
x.toString(16); // 'f'
```

첫 번째 인수로 전달한 문자열이 해당 지수의 숫자로 변경될 수 없다면 NaN 반환

```js
// 'A'는 10진수로 해석할 수 없다.
parseInt("A0"); // NaN

// '2'는 2진수로 해석할 수 없다.
parseInt("20", 2); // NaN
```



##### encodeURI / decodeURI

> encodeURI 함수는 완전한 URI를 문자열로 전달받아 이스케이프 처리를 위해 인코딩. URI는 인터넷에 있는 자원을 나타내는 유일한 주소를 뜻함

한글을 포함한 대부분의 외국어나 아스키 문자 셋에 정의되지 않은 특수 문자의 경우 URL에 포함될 수 없다. 따라서 URL 내에서 의미를 갖고 있는 문자(%, ?. #)나 URL에 올수 없는 문자(한글, 공백 등) 또는 시스템에 의해 해석될 수 있는 문자(<, >)를 이스케이프 처리하여 야기될 수 있는 문제를 예방

```js
const uri = 'https://seongtaek.com/name=성택'
const enc = encodeURI(uri)
console.log(enc) // https://seongtaek.com/name=%EC%84%B1%ED%83%9D

// decodeURI 함수는 인코딩된 완전한 URI를 전달받아 이스케이프 처리 이전으로 디코딩
const dec = decodeURI(enc); // https://seongtaek.com/name=성택
console.log(dec);
```



##### encodeURIComponent / decodeURIComponent

> encodeURIComponent 함수는 URI 구성 요소를 인수로 전달받아 인코딩. 인수로 전달된 문자열을 URI의 구성요소인 쿼리 스트링의 일부로 간주해서 쿼리 스트링 구분자로 사용되는 `=`, `?`, `&`까지 인코딩. encodeURI는 매개변수로 전달된 문자열을 완전한 URI 전체라고 간주하므로 `=`, `?`, `&` 은 인코딩 하지 않음



### 21.4.3 암묵적 전역

**암묵적 전역** : 선언하지 않은 변수를 전역 객체에 프로퍼티로 동적 생성하는 것

```js
var x = 10;

function foo() {
  // 선언하지 않은 식별자에 값을 할당
  y = 20; // window.y = 20
}
foo();

// 선언하지 않은 식별자 y를 전역에서 참조할 수 있다. y는 암묵적 전역
console.log(x + y); // 30
```

변수 선언 없이 단지 전역 객체의 프로퍼티로 추가되었을 뿐이고, y는 변수가 아니다. 따라서 변수 호이스팅이 발생하지 않는다.

```js
// 전역 변수 x는 호이스팅이 발생한다.
console.log(x); // undefined

// 전역 변수가 아니라 단지 전역 객체의 프로퍼티인 y는 호이스팅이 발생하지 않는다.
console.log(y); // ReferneceError : y is not defined

var x = 10;

function foo() {
  // 선언하지 않은 식별자에 값을 할당
  y = 20; // window.y = 20
}
foo();

// 선언하지 않은 식별자 y를 전역에서 참조할 수 있다. y는 암묵적 전역
console.log(x + y); // 30
```



# 22. this

## 22.1. this 키워드

**this** : 자신이 속한 객체 또는 자신이 생성할 인스턴스를 가리키는 자기 참조 변수 (self-referencing variable). 

this를 통해 자신이 속한 객체 또는 자신이 생성할 인스턴스의 프로퍼티나 메서드를 참조할 수 있음

this는 자바스크립트 엔진에 의해 암묵적으로 생성되는데 this가 가리키는 값, **this 바인딩은 함수 호출 방식에 의해 동적으로 결정됨**



## 22.2 함수 호출 방식과 this 바인딩

this 바인딩은 함수 호출 방식, 즉 함수가 어떻게 호출되었는지에 따라 동적으로 결정됨

함수의 상위 스코프를 결정하는 방식인 렉시컬 스코프는 함수 정의가 평가되어 함수 객체가 생성되는 시점에 상위 스코프를 결정. 하지만 this 바인딩은 함수 호출 시점에 결정

**함수 호출 방식**

1. 일반 함수 호출
2. 메서드 호출
3. 생성자 함수 호출
4. Function.prototype.apply / call / bind 메서드에 의한 간접 호출
5. arrow function (ES6)



### 22.2.1 일반 함수 호출

> 일반 함수로 호출하면 함수 내부의 **this는 전역 객체 (global object)**가 바인딩. 이는 전역 함수는 물론이고 중첩 함수도 마찬가지. 
>
> 콜백 함수도 일반 함수로 호출되면 콜백 함수 내부의 this에도 전역 객체가 바인딩됨

```js
function foo() {
  console.log(this); // window
  function bar() {
    console.log(this); // window
  }
  bar();
}
foo();
```

this는 객체의 프로퍼티나 메서드를 참조하기 위한 자기 참조 변수이므로 객체를 생성하지 않는 일반 함수에서 this는 의미가 없음

따라서 strict mode가 적용된 일반 함수 내부의 this는 `undefined`가 바인딩

```js
function foo() {
  'use strict';
  console.log("foo's this: ", this); // undefined
  function bar() {
    console.log("bar's this:", this); // undefined
  }
  bar();
}
foo();
```



### 22.2.2 메서드 호출

> 메서드 내부의 this에는 메서드를 호출한 객체, 즉 메서드를 호출할 때 메서드 이름 앞의 마침표(.) 연산자 앞에 기술한 객체가 바인딩
>
> **메서드를 소유한 객체가 아니라 메서드를 호출한 객체에 바인딩**됨

```js
const person = {
  name: 'Lee',
  getName() {
    // 메서드 내부의 this는 메서드를 호출한 객체에 바인딩됨
    return this.name;
  }
};

// 메서드 getName을 호출한 객체는 person
console.log(person.getName()); // Lee
```

```js
const anotherPerson = {
  name: 'Kim'
};

// getName 메서드를 anotherPerson 객체의 메서드로 할당
anotherPerson.getName = person.getName;

// getName 메서드를 호출한 객체는 anotherPerson
 console.log(anotherPerson.getName()); // Kim

// getName 메서드를 변수에 할당
const getName = person.getName;

// getName 메서드를 일반 함수로 호출
console.log(getName()); // ''
// 일반 함수로 호출된 getName 함수 내부의 this.name 은 브라우저 환경에서 window.name과 같음
// 브라우저 환경에서 window.name은 브라우저 창의 이름을 나타내는 빌트인 프로퍼티이며 기본값은 ''
```



### 22.2.3 생성자 함수 호출

> 생성자 함수 내부의 this에는 **생성자 함수가 (미래에) 생성할 인스턴스**가 바인딩

```js
// 생성자 함수
function Circle(radius){
  // 생성자 함수 내부의 this는 생성자 함수가 생성할 인스턴스를 가리킨다
  this.radius = radius;
  this.getDiameter = function(){
    return 2 * this.radius;
  };
}

// 반지름이 5인 Circle 객체를 생성
const circle1 = new Circle(5);
// 반지름이 10인 Circle 객체를 생성
const circle2 = new Circle(10);

console.log(circle1.getDiameter()); // 10
console.log(circle2.getDiameter()); // 20
```



### 22.2.4 Function.prototype.apply / call / bind 메서드에 의한 간접 호출

> apply 와 call 메서드는 함수를 호출하면서 **첫 번째 인수로 전달한 특정 객체를 호출한 함수의 this에 바인딩**
>
> apply와 call 메서드는 호출할 함수에 인수를 전달하는 방식만 다를 뿐 동일하게 동작함
>
> apply 메서드는 호출할 함수의 인수를 배열로 묶어서 전달. call 메서드는 호출할 함수의 인수를 쉼표로 구분한 리스트 형식으로 전달

```js
function getThisBinding() {
  console.log(arguments);
  return this;
}

// this로 사용할 객체
const thisArg = { a: 1 };

// getThisBinding을 호출하면서 인수로 전달한 객체를 getThisBinding 함수의 this에 바인딩
// apply 메서드는 호출할 함수의 인수를 배열로 묶어서 전달
console.log(getThisBinding.apply(thisArg, [1, 2, 3]));
// Arguments(3) [1, 2, 3, callee: f, Symbol(Symbol.iterator): f]
// {a : 1}

// call 메서드는 호출할 함수의 인수를 쉼표로 구분한 리스트 형식으로 전달
console.log(getThisBinding.call(thisArg, 1, 2, 3));
// Arguments(3) [1, 2, 3, callee: f, Symbol(Symbol.iterator): f]
// {a : 1}
```

apply와 call 메서드의 대표적인 용도는 `arguments` 객체와 같은 **유사 배열 객체에 배열 메서드를 사용하는 경우**

arguments 객체는 배열이 아니기 때문에 Array.prototype.slice 같은 배열의 메서드를 사용할 수 없으나 apply, call 메서드를 사용하면 가능하다

```Js
function convertArgumentsToArray() {
  console.log(arguments); // Arguments(3) [1, 2, 3, callee: ƒ, Symbol(Symbol.iterator): ƒ]

  // arguments 객체를 배열로 변환
  // Array.prototype.slice를 인수 없이 호출하면 배열의 복사본 생성
  const arr = Array.prototype.slice.call(arguments);
  // const arr = Array.prototype.slice.apply(arguments);
  console.log(arr);

  return arr;
}

convertArgumentsToArray(1, 2, 3); // [1, 2, 3]
```



> Function.prototype.bind 메서드는 apply와 call 메서드와 달리 함수를 호출하지 않고 this로 사용할 객체만 전달

```Js
function getThisBinding() {
  return this;
}

// this로 사용할 객체
const thisArg = { a: 1 };

// bind 메서드는 함수에 this로 사용할 객체를 전달
// bind는 함수를 호출하지는 않는다.
console.log(getThisBinding.bind(thisArg)); // getThisBinding

// bind 메서드는 함수를 호출하지는 않으므로 명시적으로 호출해야 적용된 것을 확인할 수 있다.
console.log(getThisBinding.bind(thisArg)()); // { a: 1 }
```

bind 메서드는 메서드의 this와 메서드 내부의 중첩 함수 또는 콜백 함수의 this가 불일치하는 문제를 해결하기 위해 유용하게 사용됨



### 추가. 화살표 함수 (ES6)

> 화살표 함수에서 this는 상위 스코프의 this를 그대로 참조함. 이는 화살표 함수는 함수 자체의 this 바인딩을 갖지 않기 때문
>
> 이를 lexical this라고 하는데 이는 렉시컬 스코프와 같이 화살표 함수의 this가 함수가 정의된 위치에 의해 결정된다는 것을 의미
>
> 화살표 함수와 화살표 함수가 중첩되어 있다면 상위 화살표 함수에도 this 바인딩이 없으므로 스코프 체인 상 가장 가까운 상위 함수 중에서 화살표 함수가 아닌 함수의 this를 참조

```js
var 오브젝트1 = {
  함수 : function(){ console.log(this) }
}

오브젝트1.함수() // {함수: ƒ}


var 오브젝트2 = {
  함수 : () => { console.log(this) }
}

오브젝트2.함수() // Window
```



|                        함수 호출 방식                        |                         this 바인딩                          |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|                        일반 함수 호출                        |                          전역 객체                           |
|                         메서드 호출                          |           메서드를 호출한 객체( . 연산자 앞 객체)            |
|                       생성자 함수 호출                       |            생성자 함수가 (미래에) 생성할 인스턴스            |
| Function.prototype.apply / call /bind 메서드에 의한 간접 호출 | Function.prototype.apply / call / bind 메서드에 첫 번째 인수로 전달한 객체 |
|                      화살표 함수 (ES6)                       |                   상위 스코프(부모)의 this                   |

