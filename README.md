# ex2
#### 최근 수정일 ) 2022.04.09 (토)  
#### 작성 목적 ) 따라하면서 헷갈린 부분, 책과 상이한 부분에 대해서 기록한다.
##도서명 : 스프링 부트 쇼핑몰 프로젝트 with JPA
> ###개발환경 
>> MacOS Monterey 12.3.1  
>> IntelliJ IDEA 2021.3.3 Community Edition  
>> MySQL Ver 8.0.28 for macos12.2 on arm64  
>> Spring boot 2.6.6  
>> Gradle 7.4.1 (책에선 Maven으로 하지만 Maven을 개인적으로 안 좋아해서 바꿈)  


-----

### Profile 세팅
   > 개발환경, 운영환경에서 properties 구분짓기
   - application-{세팅값}.properties 작성
   ```properties
   # 예시
   server.port = 80
   ```
   1. Edit Configurations
   2. application 설정
   3. modify options 클릭
   4. Add VM Options 클릭
   5. -Dspring.profiles.active={세팅값}
   
### IntelliJ LiveReload 세팅하기
   > 코드 실행 중 변경사항이 자동으로 반영됨
   - gradle 의존성 추가
   ```groovy
   implementation "org.springframework.boot:spring-boot-devtools"
   ```
   - properties 수정
   ```properties
   # 개발도구 라이브 리로드 설정
   spring.devtools.livereload.enabled=true
   ```
   - Preference 설정
     - Build, Execution, Deployment -> Compiler -> Build project automatically
     - (IntelliJ 2021.2 이상) Advanced Settings -> (Compiler) Allow auto-make... 체크