# Spring Cloud

## Part 1

> Section 0: Micoroservice 와 Spring Cloud 소개  
> Section 1: Service Discovery  
> Section 2: API Gateway Service  
> [Section 3: E-commerce 애플리케이션](#section-3-e-commerce-application-개발)  
> Section 4: User Microservice - (1)  
> Section 5: Catalogs, Order Microservice  
> Section 6: User Microservice - (2)  
> Section 7: Configuration Service  
> [Section 8: Spring Cloud Bus](#section-8-spring-cloud-bus)  

## Section 3. E-Commerce Application 개발

</br>

### Application 구성 요소

|구성 요소|설명|
|-------|---|
|Git Repository| 마이크로서비스 소스 관리 및 프로파일 관리|
|Config Server| Git 저장소에 등록된 프로파일 정보 및 설정 정보|
|Eureka Server| 마이크로서비스 등록 및 검색|
|API Gateway Server| 마이크로서비스 부하 분산 및 서비스 라우팅|
|Microservices| 회원 MS, 주문 MS, 상품(카테고리) MS|
|Queuing System| 마이크로서비스 간 메시지 발행 및 구독|

</br>

### Application API's

|마이크로서비스|RESTful API|HTTP Method|
|----------|-----------|-----------|
|Catalog Service| /catalog-service/catalogs : 상품 목록 제공 | GET |
|User Service| /user-service/users : 사용자 정보 등록</br> /user-service/users : 전체 사용자 조회</br> /user-service/users/{user-id} : 사용자 정보, 주문 내역 조회</br> /user-service/login : 사용자 로그인 | POST </br> GET </br> GET </br> POST |
|Order Service| /order-service/users/{user_id}/orders : 주문 등록</br> /order-service/users/{user_id}/orders : 주문 확인 | POST </br> GET |

## Section 8. Spring Cloud Bus

### Rabbit MQ 사용하기

Rabbit MQ 설치

```bash
$ brew update

$ brew install rabbitmq
```

__Tip.__ 설치 도중 Python3.9 관련 에러가 발생 한다면, `$ xcode-select --install` 명령어 실행

Rabbit MQ 실행

```bash
# 서비스 등록
$ brew services start rabbitmq

# 서비스 등록하지않고 백그라운드에서 서버만 구동
$ rabbitmq-server
```

Rabbit MQ 환경설정

```bash
$ export:PATH=$PATH:/usr/locl/sbin
```

Rabbit MQ 웹 접속

`http://localhost:15672` 로 접속, 로그인 정보(username: guest, password: guest)

## Part 2

> Section 9: 암호화 처리를 위한 Encryption 과 Decryption  
> Section 10: 마이크로서비스간 통신  
> Section 11: 데이터 동기화를 위한 Kafka 활용 (1)  
> Section 12: 데이터 동기화를 위한 Kafka 활용 (2)  
> Section 13: 장애 처리와 Microservice 분산 추적  
> Section 14: Microservice 모니터링  
> Section 15: 애플리케이션 배포를 위한 컨테이너 가상화  
> Section 16: 애플리케이션 배포 - Docker Container  
> Appendix: Microservice 패턴  

## Section 9. 암호화 처리를 위한 Encryption 과 Decryption

Asummetric Encryption

- Public, Private Key 생성 (JDK keytool 사용)

- `keystore` 폴더 생성

```bash
$ mkdir ${user.home}/Document/lab/tutorial/keystore
```

- Key 생성

```bash
$ keytool -genkeypair -alias apiEncryptionKey -keyalg RSA \
-dname "CN=Mozy Kwon, OU=API Development, O=mozy.co.kr, L=Seoul, C=KR" \
-keypass "test1234" -keystore apiEncryptionKey.jks -storepass "test1234"
```

- Key 파일 확인

```bash
$ keytool -list -keystore apiEncryptionKey.jks
키 저장소 비밀번호 입력: #test1234 입력
키 저장소 유형: PKCS12
키 저장소 제공자: SUN

키 저장소에 1개의 항목이 포함되어 있습니다.

apiencryptionkey, 2023. 11. 3., PrivateKeyEntry,
인증서 지문(SHA-256): 6F:F1:C2:74:3F:39:3A:86:6A:1B:41:B6:7F:0B:79:74:5B:39:47:11:0A:54:24:D4:25:D4:DA:D3:8E:46:92:65
```

- 키 파일에서 인증서 파일 추출

```bash
$ keytool -export -alias apiEncryptionKey -keystore apiEncryptionKey.jks -rfc -file trustServer.cer
키 저장소 비밀번호 입력: #test1234 입력
인증서가 <trustServer.cer> 파일에 저장되었습니다.
```

- 인증서 파일에서 키 파일 추출

```bash
$ keytool -import -alias trustServer -file trustServer.cer -keystore publicKey.jks
키 저장소 비밀번호 입력: #test1234 입력
새 비밀번호 다시 입력:  #test1234 입력
소유자: CN=Mozy Kwon, OU=API Development, O=mozy.co.kr, L=Seoul, C=KR
발행자: CN=Mozy Kwon, OU=API Development, O=mozy.co.kr, L=Seoul, C=KR
일련 번호: 52fad675
적합한 시작 날짜: Fri Nov 03 11:40:54 KST 2023 종료 날짜: Thu Feb 01 11:40:54 KST 2024
인증서 지문:
	 SHA1: 15:34:63:94:A7:96:EF:97:DA:70:BB:1A:95:77:78:24:FB:F5:65:33
	 SHA256: 6F:F1:C2:74:3F:39:3A:86:6A:1B:41:B6:7F:0B:79:74:5B:39:47:11:0A:54:24:D4:25:D4:DA:D3:8E:46:92:65
서명 알고리즘 이름: SHA256withRSA
주체 공용 키 알고리즘: 2048비트 RSA 키
버전: 3

확장:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 9C E8 F0 E8 73 D3 BF F5   69 DD 3E 5F 4C 60 38 1F  ....s...i.>_L`8.
0010: 6A 80 E6 A4                                        j...
]
]

이 인증서를 신뢰합니까? [아니오]:  예
인증서가 키 저장소에 추가되었습니다.
```
