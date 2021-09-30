# GIT HUB

git 의 원격 저장소



### 1. 원격저장소 등록

`$ git remote add origin <url>` 

```
$ git remote add origin https://github.com/Seongtaek-H/TIL.git
$ git push -u origin master
```

* git 원격저장소(remote)를 추가(add) 해줘. origin이라는 변수에 url을 등록하는 개념
* u : upstream 기본 원격/브랜치. 이후로는 `git push`만 해도 기본 원격/브랜치에 push
* 만약에 url이 잘못 설정된 경우 => git remote rm origin 으로 지우고 다시 설정



### 2. 원격저장소 확인

`$ git remote -v`

```
$ git remote -v
origin  https://github.com/Seongtaek-H/TIL.git (fetch)
origin  https://github.com/Seongtaek-H/TIL.git (push)
```



### 3. 원격저장소 활용

`push <원격저장소 이름> <브랜치 이름>`  : 원격저장소에 업데이트

```
$ git push origin master
```

* 커밋이 push되는 것임(파일, 폴더 X)

* push 전에 status로 원하는 변경사항들이 모두 커밋되었는지 확인

  

`pull <원격저장소 이름> <브랜치 이름>` : 원격저장소에서 다운

```
$ git pull origin master
```

