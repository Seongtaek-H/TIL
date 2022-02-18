### NextJS 프로젝트 설치

터미널에서 **npx create-next-app@latest** (타입스크립트 버젼으로 하려면 npx create-next-app@latest--typescript)

프로젝트명 정한 후 VSC로 생선된 프로젝트 폴더 열기

**실행 : npm run dev ** 



### Library vs Framework

라이브러리 : 개발자로서 내가 사용하는 것. 내가 원하는대로 코드를 작성할 수 있고, 사용하고 싶을 때 사용할 수 있음

프레임워크 : 내 코드를 불러오는 것. 코드를 적절한 위치에서 잘 적기만 한다 프레임워크가 코드를 불러와서 모든걸 동작하게 함

리액트, Next Js는 라이브러리적인 요소도 프레임워크인 요소도 가지고 있음



### Pages

Next Js에서 페이지를 추가할 때는 component를 export 하고 있는 파일을 pages 폴더에 넣어놓으면 됨

그러면 Next Js가 파일 이름을 알아서 url로 가져가씀. 함수명은 아무거나 써도 상관없음

useState 나 useEffect 같은 리액트 메소드를 쓰고 싶으면 react 를 import 해야함



### Static Pre Rendering

리액트는 클라이언트 사이드 렌더링 . 

클라이언트 사이드 렌더링 : 브라우저가 유저가 보는 UI를 만드는 모든 것을 수행. 모든 UI가 리액트에서 렌더링되며 html 안에 있지 않음. 브라우저가 html을 가져올때 빈 div 하나만 가져오고 전부 자바스크립트로 렌더링됨

##### Next Js 동작 방식 

1. 실제 html을 가져오게 되고, 페이지가 미리 렌더링됨.(초기 상태로 Pre-rendering). 

2. hydration : 페이지가 로딩되었을 때 react js가 많은 스크립트들을 넘겨받아서 모든 기능을 동작하게 함



### Routing

`<Link>` : Next Js 에서 앱 내의 페이지를 네비게이트할 때 사용해야만 하는 컴포넌트. next/link를 임포트해야함

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
* Style, className 등 다른 값은 a 태그에 주어야 작동함



### CSS Modules

css 파일은 파일명.module.css 파일 형식을 가져야 함

그 후 css 파일을 자바스크립트 오브젝트로써 import

Jsx에서 클래스명을 줄 때에는 className={임포트명.클래스명} 이렇게 줘야 작동

이렇게 하는 이유는 Next Js에서 충돌을 막기 위해 클래스명을 무작위로 지정하기 때문

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

Style 태그에 jsx 프롭을 넣고 {``} 안에 CSS 요소를 입력해서 사용할 수 있음

```jsx
<style jsx>{`
  nav {
    background-color: tomato;
  }
  a {
    text-decoration: none;
  }
  .active {
    color: yellow;
  }
`}</style>
```

* 사용한 스타일 요소는 오직 컴포넌트 내부로 범위가 한정



### Custom App

style jsx 태그에서 전역 요소를 지정하고 싶으면 global 프롭을 추가하면 됨. 그러나 같은 페이지 내에 있는 컴포넌트들에게만 적용됨

모든 페이지에서 통용하는 요소를 만들고 싶으면 우선 _app.js 파일을 pages 폴더 내부에 생성. Next Js에서 가장 먼저 확인하는 파일명임

```js
import NavBar from "../components/NavBar";
import "../styles/globals.css"; 
// 본래 모듈이 아니면 css 파일 임포트 안되는데 커스텀 App 컴포넌트가 있는 곳에서는 모든 global styles를 임포트 할 수 있음

export default function MyApp({ Component, pageProps }) { // 컴포넌트와 프롭스를 변수로 가져와서 리턴
  return (
    <>
      <NavBar />
      <Component {...pageProps} />
    </>
  );
}
```



### Patterns

##### Layout pattern

App 컴포넌트가 커지는것보단 필요한 양식을 layout 컴포넌트로 빼서 이를 사용하는 형식

```js
import NavBar from "./NavBar";

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
      <Component {...pageProps} />
    </Layout>
  );
}
```

##### Head

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

