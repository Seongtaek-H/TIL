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

API_KEY를 .env 파일로 옮기고 `API_KEY=""`(따옴표 안써도됨) config파일에서 `const API_KEY = process.env.API_KEY;`

git ignore에 .env 파일 추가 

\# vercel

.vercel

.env



### Server Side Rendering

nextjs function 중 페이지가 오직 서버사이드렌더링만 할지 선택권을 주는

**getServerSideProps()** : Pre rendering 을 하지 않고 오직 server 에서만 실행되게 함. 즉 데이터가 유효할 때 화면이 보여지게 됨

이름을 절대로 바꾸면 안됨

이 function은 오브젝트를 리턴하게 되고 이 오브젝트 안에는 props 라는 key 가 들어있음. 

props key에는 원하는 데이터를 아무거나 넣을 수 있고, 이 데이터를 server side를 통해 props를 page 로 보낼 수 있음

```js
export async function getServerSideProps() {
  const { results } = await (
    await fetch("http://localhost:3000/api/movies") // server side 이기 때문에 absolute url 을 넣어줘야함
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



### NEXT.js router의 강력함

`<Link>` 태그 말고 another navigating => router.push 훅  **`router.push(url)`**

#### url에 정보를 숨겨 보내기

router.push() 사용할 때 url 을 string 으로 보낼 수도 있지만 객체(프로퍼티명은 정해져있음)로 보낼 수도 있음

`push(*url*: Url, *as*?: Url, *options*?: TransitionOptions): Promise<boolean>`

as는 생략해도 되지만 기입하면 브라우저의 URL을 마스킹 : url에 표시되는 query값을 as로 마스킹

```js
  const onClick = (id, title) => {
    router.push(
      {
        pathname: `/movies/${id}`,
        query: {
          id,
          title,
        },
      },
      `/movies/${id}`
    );
  };
```

넘겨준 query값을 router 로 사용하기 용이함

```js
export default function Detail() {
  const router = useRouter();
  return (
    <div>
      <h4>{router.query.title || "Loading..."}</h4>
    </div>
  );
}
```

`<Link>` 태그에서도 같은 기능을 사용할 수 있다

```js
<Link
	href={{
      pathname: `/movies/${movie.id}`,
      query: {
      	title: movie.original_title,
      },
  }}
  as={`/movies/${movie.id}`}
>
	<a>{movie.original_title}</a>
</Link>
```



### Catch All

catch-all URL은 뭐든 캐치해내는 URL

URL에 title을 넣게 되면  SEO 에 좋음. 이렇게 하면 영화제목을 URL에서 가져올거라 유저가 홈페이지에서 클릭을 통해 상세페이지에 오지 않더라도 영화제목을 볼 수 있음. 

먼저 movies 폴더에 [id].js 파일을 [...id].js 로 이름 변경.

이렇게 되면 /movies/:id로 단 하나의 변수만 받고 있던 것을 배열 형태의 변수를 받아올 수 있음

id라는 변수는 일단 의미에 맞지않으니 [...params].js 로 이름 변경

이제 query.params 로 불러올수있음

router.push에서 URL 마스킹하던 것들을 다 지워준 후 아래처럼 변경. Link태그도 변경해줘야됨

```js
  const onClick = (id, title) => {
    router.push(`/movies/${title}/${id}`);
  };

<Link href={`/movies/${movie.original_title}/${movie.id}`}>
```

그 다음엔 [...params].js에서 ES6 문법으로 해주면 title 변수로 사용할 수 있음

```js
const [title, id] = router.query.params;
```

하지만 해당 페이지는 백엔드에서 pre-render 되기때문에 incognito 모드에서 접속하면 에러 발생

router.query.params 는 서버에 존재하지 않기 때문. 아래처럼 변경하면 에러 없어짐

```js
const [title, id] = router.query.params || [];
```

이건 client-side rendering 만 해준거라 검색엔진에서는 소스코드 어디에서도 title 텍스트를 찾을 수 없음.  

따라서 SEO최적하하게 만들고, 유저에게 절대 로딩 단계를 보여주고 싶지 않으면 getServerSideProps 사용. 그러면 request 정보를 얻을 수 있고, 영화 제목도 얻을 수 있음

```js
export function getServerSideProps(ctx) {
  console.log(ctx)
  return {
    props: {},
  };
}
```

이렇게 출력하면 param가 배열 형태로 서버에 있음을 알 수 있음

이제 context 안에서 params를 가져오고, props에 그 params을 넣어줌

```js
export function getServerSideProps({ params: { params } }) {
  return {
    props: { params },
  };
}
```

이제 Detail 에서는 props로 params을 받아와서 사용할 수 있음

```js
export default function Detail({ params }) {
  const router = useRouter();
  const [title, id] = params || [];
  return (
    <div>
      <Seo title={title} />
      <h4>{title}</h4>
    </div>
  );
}
```

이제 브라우저에서 소스코드를 보면 백엔드에서 가져온 데이터를 확인할 수 있음

이전에는 컴포넌트 내부에서 router를 사용했었는데 컴포넌트 내부에서 router를 사용하면 router는 프론트에서만 실행됨

만약  URL에 들어있는 영화제목을 사용해서 구글 SEO에 최적화하고, 유저가 접속하기 전에 탭 제목을 바꾸고 싶고, 기본적으로 걍 이 페이지를 pre-render 하고 싶으면,

그때에는 server-side 에서 정보를 얻기위한 getServerSideProps 를 실행하면 됨

server-side에서 받아온 정보를 페이지로 넘겨주면, 페이지는 그 정보를 받아서 보여줌. 그럼 server-side에서 pre-render 된 꼴이 됨



### 404 pages

404 페이지를 커스터마이징하고 싶으면 pages 폴더에 404.js 파일을 만들고 함수명은 본인이 원하는 걸로 해서 만들면 끝!

```js
export default function NotFound() {
  return (
    <>
      <h4> This page is not exist. </h4>
    </>
  );
}
```



