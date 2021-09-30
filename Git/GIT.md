# GIT

> 분산버전관리시스템, 코드의 버전을 관리하는 도구

### 1. GIt 저장소 만들기

```
$ git init
Initialized empty Git repository in C:/Users/User/Desktop/test/.git/
(master) $
```

* .git 폴더가(숨김상태로) 생성되며, 버전이 관리되는 저장소
* git bash 에서는 경로 옆의 ()안에 브랜치가 표기 `(master)`

`주의사항 : CLI에서 현재 디렉토리가 맞는지 확인`

### 2. 버전만들기

 git 에서의 작업공간은 Working directory - Staging area - HEAD(version) 로 구성

1. Working directory에서 작업
2. 작업한 파일을 add 명령어로 Staging area에 모으기
3. commit 명령어로 버전 기록

> add : Working directory → Staging area
>
> ```
> $ git add <파일/폴더/디렉토리>
> $ git add . # 현재 디렉토리 변경 모두
> $ git add a.txt   # 특정 파일
> $ git add folder/ # 특정 폴더 
> $ git add *.png   # 특정 확장자
> ```

> commit : Staging area → HEAD
>
> ```
> $ git commit -m '<커밋메시지>'
> # 커밋 메시지는 변경 사항을 나타낼 수 있도록 명확하게 작성
> ```



`commit 시에는 반드시 작업자와 작업내용이 들어가야 함.`

<작업자 입력방법>

```
$ git config --global user.email "이메일주소"

$ git config --global user.name "이름"
```



### 3. 상태관련 명령어

`status` : Working directory와 staging area 내의 파일 상태 확인

```
$ git status
On branch master
Your branch is up to date with 'origin/master'.
```

`log` : Head의 작업내역 표시. 현재 저장소에 기록된 commit 조회

```
$ git log
commit 00b6288382c59a4d2327ce6d461c689152c1e9fe (HEAD -> master, origin/master)
```













