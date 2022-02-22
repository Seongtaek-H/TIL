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



#### 데이터바인딩

자바스크립트로 데이터를 HTML에 꽂아넣는 작업

```html
<h4 id="title">상품제목</h4>
<p id="text">상품내용</p>

<script>
  var 자료 = [ { brand : 'BMW' }, { model : 520 } ];
  $('#title').html(자료[0].brand);
</script>
```

