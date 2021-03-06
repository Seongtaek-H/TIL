## CSS - 선택자

> CSS에서 특정 HTML 태그를 선택할 때 선택자를 사용

```css
hi{color : red;}
선택자{스타일 속성 : 스타일 값;}
```



### 기본 선택자

| 종류          | 형태    | 설명                                     |
| ------------- | ------- | ---------------------------------------- |
| 전체 선택자   | *       | HTML 페이지 내부의 태그를 모두 선택      |
| 태그 선택자   | 태그    | HTML 페이지 내부의 특정 태그를 모두 선택 |
| 아이디 선택자 | #아이디 | 특정 id 속성이 있는 태그 선택            |
| 클래스 선택자 | .클래스 | 특정 클래스가 있는 태그 선택             |

* 한꺼번에 선택자를 여러 개 입력하여 스타일 속성 적용할 때는 쉼표 사용



### 속성 선택자

| 형태            | 설명                                           |
| --------------- | ---------------------------------------------- |
| 선택자[설명]    | 특정한 속성이 있는 태그 선택                   |
| 선택자[속성=값] | 특정한 속성 내부 값이 특정 값과 같은 태그 선택 |

* input 태그는type 속성에 따라 형태가 다르므로, input 태그를 선택할 때는 속성 선택자를 많이 사용



### 후손 선택자 / 자손 선택자

`후손 선택자` : 아래에 위치한 모든 태그

| 형태            | 설명                          |
| --------------- | ----------------------------- |
| 선택자A 선택자B | 선택자A의 후손인 선택자B 선택 |

```css
<style>
	#header h1 {color : red;} //#header 태그 아래 위치한 h1 태그 선택
	#section h1 {color : orange;} //#section 태그 아래 위치한 h1 태그 선택
</style>
```

`자손 선택자` : 한 단계 아래에 위치한 태그

| 형태            | 설명                          |
| --------------- | ----------------------------- |
| 선택자A>선택자B | 선택자A의 자손인 선택자B 선택 |

* 바로 밑에 있는 태그만 선택 

```html
<head>
	<style>
		#header > h1 {color : red;} //#header 태그 아래 위치한 h1 태그 선택
		#section > h1 {color : red;} //#section 태그 아래 위치한 h1 태그 선택
	</style>
</head>
<body>
    <div id="header">
        <h1 class="title">Lorem ipsum</h1>
        <div id="nav">
            <h1>Navigation</h1> // 해당 h1은 header의 자손이 아니고 후손이기 때문에 자손 선택자로 선택 불가
        </div>
    </div>
    <div id="section">
        <h1 class="title">Lorem ipsum</h1>
    </div>
</body>
```



### 반응/상태 선택자

`반응 선택자` : 사용자 반응으로 생성되는 특정한 상태

| 형태    | 설명                                  |
| ------- | :------------------------------------ |
| :active | 사용자가 마우스로 클릭한 태그 선택    |
| :hover  | 사용자가 마우스 커서를 올린 태그 선택 |

`상태 선택자` : 입력 양식의 상태를 선택할 때 사용

| 형태      | 설명                                               |
| --------- | -------------------------------------------------- |
| :checked  | 체크 상태의 input 태그 선택                        |
| :focus    | 입력 양식에 포커스를 맞춘 input 태그 선택          |
| :enabled  | 사용 가능한(값을 입력할 수 있는) input 태그 선택   |
| :disabled | 사용 불가능한(값을 입력할 수 없는) input 태그 선택 |

