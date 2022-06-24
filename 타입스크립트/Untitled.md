# 타입스크립트 기본 타입 정리

### 변수 만들때 타입 정하기

`변수명 : 타입`

```js
let 이름 :string = 'kim';
let 나이 :number = 20;
let 결혼했니 :boolean = false;
```



### array 또는 object 자료 안에도 타입 지정가능

array 자료안에 들어갈 타입은 타입명[]

```js
let 회원들 :string[] = ['kim', 'park']
var 좋아하는거 :{ song :string, singer :string } = { song : '사랑하기때문에', singer : '유재하' }
```



object 자료안에 들어갈 타입은 내가 만들 object와 똑같은 모습으로 지정

```js
let 내정보 : { age : number } = { age : 20 }
let project :{
  member : string[],
  days : number,
  started : boolean,
} = {
  member : ['kim', 'park'],
  days : 30,
  started : true,
}
```



### 사실 변수 생성시 타입스크립트가 타입을 자동으로 부여함

모든 변수에 타입지정할 필요 없음

```js
let 이름 = 'kim'; // string 으로 되어있음
let 나이 = 20; // number로 되어있음
```

심지어 변수만들고 나중에 할당해도 타입이 변함

```js
let 이름;
이름 = 'kim'; 
```

그래서 간단한 변수를 만들때는 타입 생략해도 되는데 타입지정하는게 보기좋으면 지정해도됨.



# 타입을 미리 정하기 애매할 때 (union type, any, unknown)

### union 타입

`|` 연산자를 사용해서 만드는 유니온 타입. 변수에 이 타입이 들어올 수도 있고 또는 저타입이 들어올수도있음 하고 선언하는 거임. OR 같은 느낌

```js
let 이름: string | number = 'kim';
let 나이: (string | number) = 100;

var 어레이: (number | string)[] = [1,'2',3]
var 오브젝트: {data : (number | string) } = { data : '123' }
```

변수에 정의된 Union 타입은 할당과 동시에 OR 역할이 사라짐

array, object에 정의된 Union 타입은 OR 연산자가 유지 



### any 타입 

아무 자료나 집어넣을 수 있는 타입. 아무 때나 나 막쓰면 안됨. 타입관련 버그 생기면 추적 어려워짐. 비상시 쓰는 변수 타입체크 해제기능 용도로 쓰셈

```js
let 이름: any = 'kim';
이름 = 123;
이름 = undefined;
이름 = [];
```



### unknown 타입

any와 똑같이 모든 타입을 집어넣을 수 있음.

```js
let 이름: unknown = 'kim';
이름 = 123;
이름 = undefined;
이름 = [];
```

근데 any랑 다르게 변수에 할당이 되도 타입이 그대로 unknown 타입으로 남아있음.

그래서 아직 뭘 넣을지 모르는데 약간의 안정성을 도모하고 싶으면 unknown 타입 쓰면 됨

```js
let 이름: unknown;

let 변수1: string = 이름; // 에러
let 변수2: boolean = 이름; // 에러
let 변수3: number = 이름; // 에러

이름[0]; // 에러
이름 - 1; // 에러 number만 - 가능
이름.data; // 에러 object만 이렇게 쓸 수 있음

let 나이: string|number;
나이 + 1; // union 타입은 number 가 아님. 변수의 타입을 확실히 해야 연산 수행함

let 나이: unknown = 1;
나이 + 1; // 타입이 그대로 unknown 이라서 에러임
```



# 함수에 타입 지정하는 법 & void 타입

### 파라미터와 반환값 타입 지정

1. 함수로 들어오는 파라미터 타입지정은 파라미터 옆

2. 함수가 실행된 후 남는 값 (return 우측에 있는 값) 타입지정하고 싶으면 함수명() 우측

```js
function 내함수(x :number) :number { 
  return x * 2 
} 
```



### void 타입

void 타입는 return 할 자료가 없는 함수의 타입으로 사용 가능

```js
function 내함수(x :number) :void { 
  return x * 2 //여기서 에러남 
} 
```



### 파라미터가 옵션일 경우

함수에 파라미터 자리는 있지만 가끔 파라미터 없이도 쓰이는 함수일 경우 파라미터가 옵션이라고 정의내려줘야됨

파라미터 우측에 그냥 물음표 (=**x : number | undefined** 이거랑 똑같은 의미)

```Js
function 내함수(x? :number) { 

}
내함수(); // 가능
내함수(2); // 가능
```



### 함수에서 Union type

타입스크립트에서 파라미터가 union type 인 경우 연산 불가능 (타입 확정이 안되기 때문)

```js
function 자릿수세기(x :number | string){ 
  return x + 1 // 에러남
}

function 내함수(x? :number) :number { 
  return x * 2  // 이것도 에러남 x? :number(=x : number | undefined)로 타입정의가 되기때문
}  
```



# 타입 확정하기 Narrowing & Assertion

### Type Narrowing

if문 등으로 타입을 하나로 정해주는 것

이렇게 코드를 짜야 정상적으로 사용이 가능

```Js
function 내함수(x :number | string){
  if (typeof x === 'number') {
    return x + 1
  } 
  else if (typeof x === 'string') {
    return x + 1
  }
  else {
    return 0
  }
}
```







