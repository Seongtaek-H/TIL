## CSS - 속성

### 박스 속성

<img src="./box.png" align="left">

| 속성    | 설명                                                         |
| ------- | ------------------------------------------------------------ |
| margin  | 테두리와 다른 태그 사이의 테두리 바깥쪽 여백                 |
| border  | 테두리                                                       |
| padding | 테두리와 글자 사이의 테두리 안쪽 여백, 배경색은 padding 영역까지만 적용 |
| width   | 글자를 감싸는 영역(content)의 가로 크기                      |
| height  | 글자를 감싸는 영역(content)의 세로 크기                      |

```css
<style>
	div { /* 각 속성 별로 ;으로 끝내야 함 */
    	width:100px; 
        height: 100px;
        background-color:red;
        border:20px solid black; /* 두께: border-width, 형태: border-style, 색상: border-color */
        margin:10px; /* <전체> or <위쪽 오른쪽 아래쪽 왼쪽> or <위아래 왼쪽오른쪽> */
        padding:30px; /* <전체> or <위쪽 오른쪽 아래쪽 왼쪽> or <위아래 왼쪽오른쪽> */
	}
</style>
```



### 가시 속성

> 태그가 화면에 보이는 방식

| 키워드       | 설명                                                         |
| ------------ | ------------------------------------------------------------ |
| none         | 화면에 보이지 않음                                           |
| block        | 블록 박스 형식으로 지정                                      |
| inline       | 인라인 박스 형식으로 지정. margin 과 padding 좌우로만 지정   |
| inline-block | 블록과 인라인의 중간 형태로 지정. margin과 padding 상하좌우로 지정 |

