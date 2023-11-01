# Spring Cloud

## Config Service

application yaml 파일 우선순위

![우선순위](./res/images/Prioritisation.png)

1. application.yml 파일

2. application-{name}.yml 파일
   - eg. application-user-service.yml

3. application-{name}-{profile}.yml 파일
   - eg. application-user-service-dev.yml

## Troubleshooting

__[SESSION 7. Configuration Service]__ Spring Cloud Config - 프로젝트 생성

(trouble) 서비스 실행 후 `http://127.0.0.1:8888/ecommerce/default` 페이지 404 에러 발생

(shooting) spring-cloud-config-server 에서 기본적으로 git 의 mater branch 에서 정보를 읽어온다. 따라서 자신이 커밋한 branch 명을 명시해줘야한다.

- application.yml 파일에 `spring.cloud.config.server.git.default-label: main` 추가

---