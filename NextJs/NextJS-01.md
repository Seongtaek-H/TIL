### NextJS 프로젝트 설치

터미널에서 **npx create-next-app@latest** (타입스크립트 버젼으로 하려면 npx create-next-app@latest--typescript)

프로젝트명 정한 후 VSCode로 생성된 프로젝트 폴더 열기

**실행 : npm run dev ** 



### Library vs Framework

라이브러리 : 개발자로서 내가 사용하는 것. 내가 원하는대로 코드를 작성할 수 있고, 라이브러리는 사용하고 싶을 때 원한다면 사용할 수 있음

프레임워크 : 내 코드를 불러오는 것. 코드를 적절한 위치에서 잘 적기만 한다면 프레임워크가 코드를 불러와서 모든걸 동작하게 함. 특정한 규칙을 따라서 특정한 것을 해야함. 정해져있는 구조를 변경할 수는 없음



예를 들어 새로운 next.js 프로젝트를 만들었을 때는 index.js 가 없는데,

cra로 리액트 앱을 만들면 자동으로 index.js가 있고, ReactDom.render() 부분이 자동으로 만들어져 있음. 

반면에 next.js framework 에선 이 과정을 커스텀할 수 있는 곳이 없음

cra 을 하게 되면 항상 App component 로 시작하는데 App component 는 비어져 있고, 

Components 폴더를 만든다든지 Routes 폴더를 만들던지 해서 어떤 폴더 구조로 만들지 언제 react를 부를지 모두 정할 수 있음

react 가 next.js 보다 더 많은 자유도가 있어서 react는 next js 에 비해 라이브러리적인 요소가 많다고 할 수 있고, next js 는 프레임워크



### Pages

next Js에서 페이지를 추가할 때는 component를 export 하고 있는 파일을 pages 폴더에 넣어놓으면 됨

그러면 Next Js가 파일 이름을 알아서 url로 가져가씀. 예를 들어 about.js 를 생성하면 /about 이 그대로 url 이 됨. 함수명은 아무거나 써도 상관없음

중요한 것은 export default function 를 반드시 해줘야함. 아니면 에러남

예외 사항은 index.js 는 첫화면으로 설정되어 있고 /index 이런걸로 해도 404 오류뜸. 그냥 / 로만 표현됨

jsx 를 리턴한다고 해도 react를 import 할 필요는 없고, 파일명이 jsx가 될 필요도 없음

다만 useState 나 useEffect 같은 리액트 메소드를 쓰고 싶으면 react 를 import 해야함



### Static Pre Rendering

next.js 는 앱에 있는 페이지들이 미리 렌더링 되는데, 이것들은 static(정적)으로 생성됨

create react app 은 client-side render 를 하는 앱을 만듬

클라이언트 사이드 렌더링 : 

client-side의 자바스크립트가 모든 UI를 만드는 것을 의미.

브라우저가 유저가 보는 UI를 만드는 모든 것을 수행. 모든 UI가 react.js로 렌더링되며 html 안에 있지 않음. 

브라우저가 html을 가져올때 빈 div 하나만 가져오고 전부 자바스크립트로 렌더링됨

만약 자바스크립트가 비활성화된다면 유저는 <noscript> 태그만 보게됨

cra로 만들어진 앱을 아주 느린 속도로 설정한다면 처음에는 브라우저가 가져오는 것은 흰 화면. 

왜냐면 브라우저가 자바스크립트 코드를 요청하고 있기 때문. 브라우저는 자바스크립트, react 등 모든 것을 fetch한 후에야 UI가 보임

##### Next Js 동작 방식 

실제 html 요소들을 가져오게 되고, 페이지가 미리 렌더링됨.(초기 상태로 Pre-rendering. HTML DOM 으로 렌더링됨).

따라서 연결속도가 느리거나 자바스크립트가 비활성화되어 있어도 볼 수 있음.(단, 초기상태만)

페이지가 로딩될 때 많은 스크립트를 같이 요청하는데, 페이지가 다 로딩되면  react.js 코드들을 넘겨받아서 기능이 동작함

next.js는 react.js를 백엔드에서 동작시켜서 페이지를 미리 만드는데 component 들을 render 시키고, 렌더링이 끝났을 때 html 이 됨

next.js는 그 Html을 페이지의 소스코드에 넣어줌. 그럼 유저는 자바스크립트와 react.js 가 로딩되지 않았더라도 컨텐츠를 볼 수 있음

유저가 웹사이트에 가면 초기 상태의 component로 된 미리 생성된 Html 페이지를 보게 되고 그리고 상호작용이 일어나면 react.js는 그걸 받아서 아주 잘 동작함 (이 방식은 SEO에도 좋음)



hydration : react.js를 프론트엔드 안에서 실행하는 것. 처음 Pre-rendering 된 빈 html 요소들만 받은 상태에서 react 코드(자바스크립트 코드)들이 이전에 보내진 html 요소 위에서 한번 더 렌더링을 하면서 각자 자기 자리를 찾아가며 매칭되는 것(파싱).



### Routing

a 태그를 쓰면 eslint 오류가 발생하는데, NextJS에서 앱 네이서 페이지를 네비게이트할 때 사용해야만 하는 Link 태그가 있기 때문

a 태그를 쓰면 브라우저가 다른페이지로 보내기 위해 전체 페이지를 새로고침함. React Router DOM에서 React Router Link를 사용하는 이유와 같음. 

`<Link>` : NextJS 어플리케이션의 클라이언트 사이드 네비게이션을 제공함. 

Next Js 에서 앱 내의 페이지를 네비게이트할 때 사용해야만 하는 컴포넌트. next/link를 임포트해야함

```js
import Link from "next/link";
import { useRouter } from "next/router";

export default function NavBar() {
  const router = useRouter();
  return (
    <nav>
      <Link href="/">
        <a style={{ color: router.pathname === "/" ? "red" : "blue" }}>Home</a>
      </Link>
      <Link href="/about">
        <a style={{ color: router.pathname === "/about" ? "red" : "blue" }}>
          About
        </a>
      </Link>
    </nav>
	);
}
```

* Link 태그에 href 값을 주어야 작동함
* Style, className 등 다른 값은 a 태그에 주어야 작동함. Link에는 href 만 줌

useRouter : 라우터와 연결할 수 있도록 해주는 훅. next/router 에서 import 하고 next에서 제공되는 훅

위의 예시에서 router 를 콘솔로그해보면 location 에 대한 정보들이 있음을 알 수 있는데, pathname, asPath, back, basePath 등 다양한 속성 있음



### CSS Modules

css 파일은 파일명.module.css 파일 형식을 가져야 함

그 후 css 파일을 자바스크립트 오브젝트로써 import

```js
import styles from "./Navbar.module.css"
```

Jsx에서 클래스명을 줄 때에는 className={임포트명.클래스명} 이렇게 줘야 작동. 자바스크립트 오브젝트에서의 프로퍼티 형식

```js
<nav className={styles.nav}>
```



이렇게 하는 이유는 Next Js에서 충돌을 막기 위해 클래스명을 무작위로 지정하기 때문

클래스명을 무작위로 지정함으로써 다른 컴포넌트에서 같은 클래스명을 써도 충돌이 나지 않게 함

 

두 개 이상의 클래스명을 지정할 때에는 백틱과 제이쿼리를 이용한 방법과 배열을 join 하는 방법이 있음

```jsx
<Link href="/">
	<a className={`${styles.link} ${router.pathname === "/" ? styles.active : ""}`}>Home</a>
</Link>
<Link href="/about">
	<a className={[styles.link, router.pathname === "/about" ? styles.active : "",].join(" ")}>About</a>
</Link>
```



### Styles JSX

style 태그에 jsx 프롭을 넣고 {``} 안에 CSS 요소를 입력해서 사용할 수 있음

```jsx
<style jsx>{`
  nav {
    background-color: tomato;
  }
  a {
    text-decoration: none;
  }
  .active {
    color: ${props.color};
  }
`}</style>
```

* 사용한 스타일 요소는 오직 컴포넌트 내부로 범위가 한정
* 클래스명도 컴포넌트 내부로만 범위가 한정됨
* props 사용 가능 



### Custom App

style jsx 태그에서 전역 요소를 지정하고 싶으면 global 프롭을 추가하면 됨. 

그러나 같은 페이지 내에 있는 컴포넌트들에게만 적용됨. 예를 들어 index.js 에서 global 이라고 지정해줘도 about 페이지에서는 적용안됨

```js
<style jsx global>{``}
```



App Component는 일종의 어떤 컴포넌트의 청사진. NextJS가 모든 페이지를 렌더링할 수 있게 하는

이건 NextJS 프레임워크에 포함되어 있는 건데 커스텀하려면  **_app.js** 파일을 pages 폴더 내부에 생성. 

Next Js에서 어떤 파일을 랜러링하기 전에 가장 먼저 _app.js 확인함

NextJs 는 가장 먼저 _app.js 를 불러오고 그 안의 함수를 두개의 Props와 함께 부르는데, 

한 프롭은 Component, 다른 하나는 pageProps.

App 에는 네비게이션 바 같이 모든 페이지에 쓰이는 요소를 넣을 수도 있고 전역 스타일링을 할 수도 있음

글로벌 스타일링을 지정할 때에는 모듈 css 파일이 아닌 일반 css 파일을 import 할 수도 있고, style jsx global 을 통해 할 수도 있음

```js
import NavBar from "../components/NavBar";
import "../styles/globals.css"; 
// 본래 모듈이 아니면 css 파일 임포트 안되는데 커스텀 App 컴포넌트가 있는 곳에서는 모든 global styles를 임포트 할 수 있음

export default function MyApp({ Component, pageProps }) { // 컴포넌트와 프롭스를 변수로 가져와서 리턴
  return (
    <>
      <NavBar />
      <Component {...pageProps} />
  		<style jsx global>{`
  			a {
	  			color : white;
  			}
  		`}</style>
    </>
  );
}
```



### Patterns

##### Layout 패턴 : custiom app component를 사용할 때 쓰는거

먼저 Layout.js 라는 component를 만들고, export default function

```js
import NavBar from "./NavBar";

// children 은 react.js 가 제공하는 Prop 인데 하나의 컴포넌트를 또 다른 컴포넌트 안에 넣을 때 쓸 수 있음
export default function Layout({ children }) {
  return (
    <>
      <NavBar />
      <div>{children}</div>
    </>
  );
}
```

```js
import Layout from "../components/Layout";
import "../styles/globals.css";

export default function MyApp({ Component, pageProps }) {
  return (
    <Layout>
      <Component {...pageProps} /> // Layout 에서 children prop 
    </Layout>
  );
}
```

너무 큰 _app.js 파일보다 필요한 양식을 layout 컴포넌트로 빼서 이를 사용하는 형식



##### head component

next/head 컴포넌트 import. 페이지마다 헤드를 기입하면 너무 번잡하니 {title} prop을 가지는 컴포넌트 작성 후 해당 컴포넌트를 import 하자

```js
import Head from "next/head";

export default function Seo({ title }) {
  return (
    <Head>
      <title>{title} | Next Movies</title>
    </Head>
  );
}
```

```js
import Seo from "../components/Seo";

export default function About() {
  return (
    <div>
      <Seo title="About" />
      <h1>About</h1>
    </div>
  );
```

```js
import Seo from "../components/Seo";

export default function Home() {
  return (
    <div>
      <Seo title="Home" />
      <h1 className="active">Hello</h1>
    </div>
  );
```

