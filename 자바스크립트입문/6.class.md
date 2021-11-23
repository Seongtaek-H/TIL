Class person {

​	name; // 속성(field)

​	age;

​	speak(); // 행동(method)

}

 

데이타클래스 = 메소드 없이 데이터(필드)만 들어있는 경우

캡슐화 = 클래스 내부에서 외부에서 볼 수 있는 변수를 나누어서

상속, 다양화 등등 

 

Class : 정의만 한거라 메모리에 안올라감

- Template
- Declare once
- No data in



Object

- Instance of a class
- Created many times
- Data in

 

1. Class declarations

```js
class Person {
  // constructor
    constructor(name, age) {
    // fields
    this.name = name;
    this.age = age;
    }

  // methods
    speak() {
    console.log(`${this.name}: hello!`);
    }
}
```



2. getter & setter

클래스를 사용하는 사용자가 잘못사용하더라도 방어적인 자세로 사용할 수 있게 해주는 것

```js
class User {
  constructor(firstName, lastName, age) {
    this.firstName = firstName;
    this.lastName = lastName;
    // get age 라는 getter를 정의하면 this.age가 getter 호출 
    // set age 라는 setter를 정의하면 = age 가 메모리에 값을 할당하는 것이 아니라 setter 호출
    this.age = age; 
      
  }

  get age() {
    return this._age;
  }

  set age(value) { 
    // setter 안에 this.age 라고 하면 = value가 계속 set age 호출 -> Maximun call stack 오류  
    // if (value < 0) {
    //   throw Error('age can not be negative');
    // }
    this._age = value < 0 ? 0 : value;
  }
```



3. Public & Private

```js
// 3. Fields (public, private)
// Too soon! 최근에 나와서 지원안되는 브라우저 많음
class Experiment {
  publicField = 2;
  #privateField = 0; // 해쉬태그 이용해서 private 설정. 클래스 외부에서는 r/w 불가
}
const experiment = new Experiment();
console.log(experiment.publicField);
console.log(experiment.privateField);
```



4. static 

오브젝트/데이터에 상관없이 클래스가 가지고 있는 고유값, 동일하게 사용되는 메소드를 클래스 자체에 연결

```js
// 4. Static properties and methods
// Too soon!
class Article {
  static publisher = 'Dream Coding';
  constructor(articleNumber) {
    this.articleNumber = articleNumber;
  }

  static printPublisher() {
    console.log(Article.publisher);
  }
}

const article1 = new Article(1);
const article2 = new Article(2);
console.log(Article.publisher); // static 필드라 클래스 자체에 연결되어있음
Article.printPublisher();
```



5. 상속 & 다향성

기존의 클래스를 extends 클래스 를 이용해서 재사용 가능

공통된 클래스를 고치면 상속받은 클래스 한번에 수정 가능하여 유지보수 용이함

다형성 : 상속받은 클래스를 고치면 개별적으로 부모로부터 가져온 필드,메소드,생성자 자유롭게 수정 가능함(오버라이딩)

``` js
// 5. Inheritance
// a way for one class to extend another class.
class Shape {
  constructor(width, height, color) {
    this.width = width;
    this.height = height;
    this.color = color;
  }

  draw() {
    console.log(`drawing ${this.color} color!`);
  }

  getArea() {
    return this.width * this.height;
  }
}

class Rectangle extends Shape {}
class Triangle extends Shape {
  draw() {
    super.draw(); // super 연산자 이용하여 부모에 정의된 메소드 호출
    console.log('🔺');
  }
  getArea() {
    return (this.width * this.height) / 2;
  }

  toString() {
    return `Triangle: color: ${this.color}`;
  }
}

const rectangle = new Rectangle(20, 20, 'blue');
rectangle.draw();
console.log(rectangle.getArea());
const triangle = new Triangle(20, 20, 'red');
triangle.draw();
console.log(triangle.getArea());
```



6. instanceOf : 클래스  체킹

왼쪽에 있는 오브젝트가 오른쪽에 있는 클래스의 인스턴스인지 아닌지. true or false 리턴

```js
// 6. Class checking: instanceOf
console.log(rectangle instanceof Rectangle); // true
console.log(triangle instanceof Rectangle); // false
console.log(triangle instanceof Triangle); // true
console.log(triangle instanceof Shape); // true
console.log(triangle instanceof Object); // true
console.log(triangle.toString());
```

 