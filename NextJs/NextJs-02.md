### Redirect & Rewrite

api key 숨기기

브라우저 개발자툴 -> network -> request한 url 확인가능 -> popular : 주소, api key 보임 Bad

request에 마스크를 씌우는 것과 같은 redirect 와 rewrite



next.config.js 파일

#### redirect : 브라우저에게 다른 url 로 가라고 지시하는 것. url 이 바뀌는 것을 유저가 인식 가능

```js
const nextConfig = {
  reactStrictMode: true,
  async redirects() {
    return [
      {
        source: "/contact",   // 들어오는 request 경로 패턴
        destination: "/form", // 라우팅되는 url 경로, 프로젝트 밖의 외부 url 가능
        permanent: false, 		
        // true인 경우 클라이언트와 search 엔진에 redirect를 영구적으로 cache하도록 지시하는 308 status code를 사용하고, 
        // false인 경우 일시적이고 cache되지 않은 307 status code를 사용	
      }, // 여러개 사용하고 싶으면 {} 추가하고 속성 입력
      {
        source: "/old-blog/:path*",   		// 뒤의 글 아이디와 상관없이 리디렉션 가능 (뒤에 *를 붙여서 모든걸 catch)
        destination: "/new-blog/:path*", // 앞쪽은 바뀌고 뒤쪽은 catch 됨
        permanent: false,
      }
    ];
  },
};
```



#### rewrite : 들어오는 request 경로를 다른 destination 경로로 매핑 (url 변경되지 않음)

url 주소를 확인못하는 특성으로 api key를 가릴 수 있음

```js
  async rewrites() {
    return [
      {
        source: "/api/movies", // fetch 주소를 해당 url 로 변경
        destination: `https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}&language=en-US&page=1`,
      },
    ];
  }
```

##### 깃허브 연동까지 막을 수 있음

API_KEY를 .env 파일로 옮기고 `API_KEY=""` config파일에서 `const API_KEY = process.env.API_KEY;`

git ignore에 .env 파일 추가 

\# vercel

.vercel

.env



### Server Side Rendering

nextjs function 중 페이지가 오직 서버사이드렌더링만 할지 선택권을 주는

**getServerSideProps()** : Pre rendering 을 하지 않고 오직 server 에서만 실행되게 함. 즉 데이터가 유효할 때 화면이 보여지게 됨

이 function은 오브젝트를 리턴하게 되고 이 오브젝트 안에는 props 라는 key 가 들어있음. 

props key에는 원하는 데이터를 아무거나 넣을 수 있고, 이 데이터를 server side를 통해 props를 page 로 보낼 수 있음

```js
export async function getServerSideProps() {
  const { results } = await (
    await fetch("http://localhost:3000/api/movies") // server side 이기 때문에 absolue url 을 넣어줘야함
  ).json();
  return {
    props: { results }, // 리턴값을 props로서 page에게 주게됨
  };
}
```

```js
export default function Home({ results }) {
	...
}
```



NextJs는 _app.js 파일에서 Component로 Home 을 렌더링 하고, getServerSideProps를 호출하고 서버사이드렌더링을 하는 것을 확인,

 그 props를 pageProps 자리에 넣을 거임

```js
export default function App({ Component, pageProps }) {
  return (
    <>
      <NavBar />
      <Component {...pageProps} /> // <Home {...results}/>
    </>
  );
}
```



### Dynamic Routes

how to work Dynamic URL

/movies 주소를 만들고 싶으면 pages 폴더에 movies.js 를 만들면 되지만

/movies/:id 를 만들고 싶으면 pages 폴더 안에 movies 폴더를 만들고 그 안에 파일을 만들어줘야함

movies 폴더에 index.js 를 만들면 /movies url 에 index 파일이 연결되고 그 안에 all.js 를 만들어주면 movies/all url 이 연결됨

파일에는 함수를 써줘야하는 것은 똑같다

```js
export default function All(){
  return "movie index";
}
```

변수를 url 로 받고 싶으면 [변수명].js 를 만들어주면 됨

라우터를 출력해보면 라우터안에 query 가 있고 query 가 url 로 쓰인 변수값이 됨을 확인할 수 있다