# ex2
스프링 부트 쇼핑몰 프로젝트 with JPA
1. 개발환경
   - IntelliJ IDEA 2021.3.3 Community Edition
   - MySQL Ver 8.0.28 for macos12.2 on arm64
   - Spring boot 2.6.6
   - Gradle 7.4.1
-----

## 2022.04.08 (금)
### Profile 세팅
1. 개발환경, 운영환경에서 properties 구분짓기
   - application-{세팅값}.properties 작성
   - Edit Configurations -> application 설정 -> modify options 클릭 -> Add VM Options 클릭 -> -Dspring.profiles.active={세팅값}
