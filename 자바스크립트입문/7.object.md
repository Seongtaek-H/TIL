## Object

Object = { key : value};

오브젝트는 key(우리가 접근할 수 있는 변수.property), 그 프로퍼티가 가지고 있는 값으로 이루어짐



1. leterals and properties

- One of the JavaScript's data types
- a collection of related data and/or functionality

```js
// primitive 타입은 변수 하나당 값 하나이기 때문에 각각 파라미터로 전달해야 함수에서 사용 가능
// logical 그룹화, 유지보수가 어려움
// 그래서 object 씀

// 오브젝트를 만드는 방법
const obj1 = {}; // 'object literal' syntax
const obj2 = new Object(); // 'object constructor' syntax

function print(person) {
    console.log(person.name);
    console.log(person.age);
}

// 오브젝트가 없어도 {}이용해서 바로 오브젝트 생성 가능
const seongtaek = { name: 'seongtaek', age: 20 };
print(seongtaek);

// with JavaScript magic (dynamically typed language)
// can add properties later
ellie.hasJob = true;
console.log(ellie.hasJob);

// can delete properties later
delete ellie.hasJob;
console.log(ellie.hasJob);
```

