# Branch

> 독립적인 작업 흐름을 만들기 위한 개념. GIT FLOW의 나뭇가지라고 생각하면 됨.
>
> 작업완료시 `merge` 를 통해 `master` 와 병합한다.



### 1. 브랜치 생성

`$ git branch <브랜치이름>`

```
* 이름 중복시 생성안됨
$ git branch test
fatal: A branch named 'test' already exists.
```



### 2. 브랜치 이동

`$git checkout <브랜치이름>
`

```
$ git checkout test
Switched to branch 'test'
(test) $
```



### 3. 브랜치 생성 및 이동

`$ git checkout -b <브랜치이름>`  : <브랜치이름> 이라는 브랜치를 만들고 그 브랜치로 이동함

```
$ git checkout -b test1
Switched to a new branch 'test1'
```



### 4. 브랜치 조회

`$ git branch`

```
$ git branch
  feature/about
  master
  name
  test
* test1
```

* 현재 test1 브랜치에 있는 것을 확인할 수 있음



### 5. 브랜치 병합

`master branch에서 해야함 `
`$ git merge <브랜치이름>`

```
$ git merge test1
Updating 89cb04f..b2c5ebf
Fast-forward
 index.html | 0
 1 file changed, 0 insertions(+), 0 deletions(-)
 delete mode 100644 index.html
```

* feature 브랜치에서 변경사항이 있는 경우에만 가능함

* 충돌이 있는 경우 충돌을 해결해줘야함.

  

### 6. 브랜치 삭제

`$ git branch -d <브랜치이름>`

```
$ git branch -d feature/about
Deleted branch feature/about (was d8d0cdb).
```

