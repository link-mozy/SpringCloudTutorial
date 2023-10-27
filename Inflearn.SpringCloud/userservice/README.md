# Spring Cloud

## Nflix Eureka Client

### 실행 방법

maven 포트 설정 실행 방법

```bash
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=9003'
```

### Spring Security

- Authentication + Authorization

- 작업 순서 :
  - Step 1: 애플리케이션에 __spring security jar__ 을 Dependency에 추가
  - Step 2: __WebSecurityConfigurerAdapter__ 를 상속받는 __Security Configuration__ 클래스 생성
  - Step 3: Security Configuration 클래스에 __@EnableWebSecurity__ 추가
  - Step 4: Authentication → __configure(AuthenticationManagerBuilder auth)__ 메서드를 재정의
  - Step 5: Password encode를 위한 __BCryptPasswordEncoder__ 빈 정의
  - Step 6: Authorization → __configure(HttpSecurity http)__ 메서드를 재정의

### 로그인 기능

일반적인 로그인 처리 순서

1. Query
```roomsql
SELECT PASSWORD FROM USERS
WHERE USERNAME = ?
```

2. 값이 존재하는지 확인.
  - 만약 존재하지 않는다면, `Not Exist` Message 출력
  - 만약 존재한다면, Query 에서 구한 PASSWORD 와 입력 받은 PASSWORD 를 비교
