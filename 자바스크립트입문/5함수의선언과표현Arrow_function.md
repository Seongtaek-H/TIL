## 자바스크립트 = Procedual language

Function

- Fundamental building block in the program
- Subprogram can be used     multiple times
- Performs a task or calculates     a value

 

1. Function     declaration

Function name(param1, param2){ body… return;}

One function === one thing

Naming: doSomething, command, verb

Function is object in JS

 

1. Parameters

Primitive parameters: passed by value

Object parameters: passed by reference

 

1. Default     parameters (added in ES6)

functionshowMessage(message,from='unknown'){

console.log(`${message}by ${from}`);

}

showMessage('Hi!');

 

1. Rest     parameters (added in ES6)

functionprintAll(...args){

 for(leti=0;i<args.length;i++){

  console.log(args[i]);

 }

 

 for(constargofargs){

  console.log(arg);

 }

 args.forEach((arg)=>console.log(arg));

}

printAll('dream','coding','ellie');

 

1. Local scope

밖에서는 안이 보이지 않고 안에서만 밖을 볼 수 있다

블록 안에서 선언한 지역변수는 블록 밖에서 출력 불가

중첩된 함수에서 자식함수가 부모함수의 변수에 접근 가능

 

1. Return a value
2. Early return, early exit

```js
// bad
functionupgradeUser(user){
 if(user.point>10){
  // long upgrade logic...
 }
}

// good
functionupgradeUser(user){
 if(user.point<=10){
  return;
 }
 // long upgrade logic...
}
```



