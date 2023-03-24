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
