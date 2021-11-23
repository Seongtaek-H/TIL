API : Application Programming Interface 

 

Async vs defer

1. Head 안에 script 포함

Pasing html <blocked> parsing html

 <fetching js><executing js>

단점 : js파일이 크면 사용자가 웹사이트를 보는데 시간이 많이 걸림

 

1. Body안에 script 포함

Parsing html <fetching js><executing js>

페이지가 js를 받기 전에도 페이지 컨텐츠를 볼 수 있다.

단점 : 사용자가 기본적인 html을 보긴 하지만, 웹사이트가 자바스크립트에 의존적이라면 사용자가 정상적으로 페이지를 보기전까지 기다려야함

 

1. Head + async

Pasing html <blocked> parsing html

 <fetching js><executing js>

장점 : fetching 시간 절약

단점 : 자바스크립트가 fetching 되기전에 페이지가 실행되기 때문에 위험할 수 있음.

Html 을 parsing 하기 위해 자바스크립트를 실행하기 멈출 수 있기 때문에 여전히 페이지를 보기까지 시간이 걸릴 수 있음

여러 js 파일을 실행할때 fetching 이 완료된 순서대로 실행하기 때문에 순서에 의존하는 자바스크립트 작성시 문제 발생

 

1. Head + defer

Parsing html <executing js>

 <fetching js>

Fetching 명령만 내려놓고 parsing 이 끝나고 executing js

여러개를 실행하더라도 다운받아놓고 parsing 완료 후 에 순서대로 실행하기 때문에 효율적이고 안전함

 

Use strict 

자바스크립트 이용시 제일 윗부분에 use strict 정의하는 것이 좋음

Why? JavaScript is very flexible

Flexible == dangerous

Use strict added ECMAScript 5

Strict mode로 개발해야 조금 더 상식적인 선에서 개발 가능

자바스크립트 엔진이 조금 더 효율적이고 빠르게 자바스크립트 분석 -> 실행시 약간의 성능개선 기대 가능