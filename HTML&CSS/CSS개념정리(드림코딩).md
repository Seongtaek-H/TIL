## CSS 개념 정리



### Box model

`box-sizing : content-box -> 기본값`

border, padding 박스크기(weight, height) 에 영향을 끼치지 않고 외부적으로 커짐

`Box-sizing : border-box `

border, padding 포함하여 박스 사이즈가 결정됨



### Position : static, relative, absolute

`position : static -> 기본값`

top, left 등의 값을 넣어도 변화가 없음

`position : relative`

원래 있던 자리를 유지하면서 그 자리에서 상대적으로 지정한만큼 이동

`position : absolute`

원래 있던 자리에서 빠져나오면서 근접한 부모 중에 static이 아닌 부모 기준에서 이동, 주변 아이템들의 재배치 발생



### Position : sticky, fixed (스크롤을 움직여도 그 자리에 지정되어있음)

`position : sticky`

들어있는 부모 안에서 포지션이 지정

`position : fixed`

브라우저에서 보여주는 뷰포트에서 포지션 변경이 일어남



### Centering (정렬)

flex 가 아닌 경우 가운데 정렬

`margin : auto`

수평적으로만 중간 정렬

`text-align : center`

텍스트가 아닌 요소도 중간 정렬할 수 있지만 블럭 레벨이 아닌 경우에만 가능 (div는 안됨, 버튼같은 인라인 블록 등만 가능)

수직적인 정렬을 할때는 line-height를 부모 박스 높이와 같이 하면 가능할 수 있음. 한줄일때만 가능 (해키한 방법임)

`Transform : translate(-50%, -50%)`

지정한 요소의 크기만큼 이동하므로 body단에서 중간 정렬을 하기 위헤서는 position : absolute; top:50%; left:50%; 로 설정한 뒤에

본인의 크기만큼 다시 마이너스로 이동 transform : transition(-50%, -50%) 해주면 됨



### Background

background-image : url('주소값')

background-repeat : 어떻게 반복할지 결정 https://developer.mozilla.org/ko/docs/Web/CSS/background-repeat

background-position : 배경 위치 설정 https://developer.mozilla.org/en-US/docs/Web/CSS/background-position

background-size 

* cover -> 이미지가 찌그러지지 않는 한도 내에서 제일 크게 설정. 이미지 가로세로비가 요소와 다르면 이미지를 잘라내어 빈공간이 생기지 않게함
* contain -> 이미지가 잘리거나 찌그러지지 않는 한도 내에서 제일 크게 설정
* auto -> 배경 이미지의 원본 크기를 유지
* <length> -> 원본 크기의 너비/높이를 주어진 값으로 늘리거나 줄임

* <percentage> -> background-positioning area의 퍼센테이지만큼 늘리거나 줄임

background : center/cover no-peat url('주소값'); 과 같이 기입할 수 있음



### Transformation

애니메이션 효과

`transform : translate(x, y);`

x축, y축으로 이동할 수 있고 tlanslateX, translateY로 따로 지정할 수도 있음

`transform : scale();`

크기를 바꿀 수 있음

`transform : rotate(xdeg);`

회전시킬 수 있음

Transform : translate(100px, 100px) scale(2) rotate(50deg); 이렇게 한 번에 쓸 수도 있다



### Animation

.box1:hover {} 이런 식으로 선택자에 호버값을 줘서 마우스를 올려놨을 때 어떻게 동작할 지 지정할 수 있다

`transition-property : 무엇을 바꿀건지`

`transition-duration : 얼마동안 바꿀건지`

`transition-timing-function : 효과` https://developer.mozilla.org/en-US/docs/Web/CSS/animation-timing-function

* linear : 균등한 속도로
* ease : 갈수록 속도가 증가하고 끝에서 다시 느려짐
* ease-in : 천천히 시작하여 완료될때까지 속도가 증가
* ease-out : 빠르게 시작하여 완료될때까지 속도가 감소
* ease-in-out : 천천히 시작하여 빨라졌다가 다시 느려짐

transition : background-color 300ms linear; 이렇게 한 번에 쓸 수 있음