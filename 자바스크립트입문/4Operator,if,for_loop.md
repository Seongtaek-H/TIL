## 4. operator(연산자), if, for loop

- 1. String      concatenation

- \+ 기호를 이용해서 문자열과 문자열을 합해서 새로운 문자열을 만듬

- 문자+문자 / 문자+숫자 / string literals

-  

- 1. Numeric operators

- 숫자를 연산

- \+ add 

- \- substract 

- / divide 

- \* multiply 

- % remainder 

- ** exponentiation(제곱)

-  

- 1. Increment and decrement operators

- ++ --

- Const preIncrement = ++conter; // 바로 업데이트 되서 할당

- Conter = counter + 1;

- preIncrement = conter;

-  

- Const postIncrement = conter++; // 할당하고 업데이트

- postIncrement = counter;

- Counter = counter + 1;

-  

- 1. Assignment operators : = 할당할 때 필요한 오퍼레이터

- += -= *= /=

-  

- 1. Comparison operators : 비교할 때 쓰는 오퍼레이터

- < <= > >=

-  

- 1. Logical operators : || (or), && (and), ! (not)

- || : 하나만 true면 true 리턴. 처음으로 true가 나오면 거기서 멈춤. 헤비한 오퍼레이션일수록 뒤에 두는 것이 좋음

- && : 전부 true가 나와야 true 리턴

- ! : 값을 반대로 바꿔줌

-  

- 1. Equality

- == 값만 같으면 같음

- === 타입도 같아야 같음

-  

- 1. Conditional operators: if

- If, else if, else

- If(조건){} 조건이 true면 블록 실행행

- Else if(조건식{} 조긴이 true면 블록 실행

- Else{} 둘다 아니면 else 실행

-  

- 1. Ternary operator: ?

-  condition ? Value1 : value2; condition이 true면 value1 실행, false면 value2 실행

-  

- 1. Switch statement

- Statement가 해당하는 case 실행, break걸리면 멈춤

-  switch(browser){

-  case 'IE': 

- 실행문;

- Breake;

-  case 'Chrom':

-  case 'Firefox'

- 실행문;

- Break;

- } 

-  

- 1. While looop: while the condition is truthy, body code      is excuted

-  statement 가 true 일 경우 계속 실행문 실행

-  

- 1. Do while: Do while loop 

-  블록을 먼저 실행하고 statement가 true일 경우 계속 실행문 실행

-  

- 1. For loop: for(begin; condition; step)

-  

- 1. Nested loops

- For(let i = 0; i<10; i++){

- For(let j=0; j<10; j++){

- Console.log(`i: ${i}, j:${j}`);

- }

- }

-  

- 1. Break, continue