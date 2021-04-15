# springboot-webservice

#HelloControllerTest 에러

에러코드)<로그>
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaAuditingHandler': Cannot resolve reference to bean 'jpaMappingContext' while setting constructor argument;
 
nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaMappingContext': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: JPA metamodel must not be empty!

1.원인
JPA는 Entity들의 생성 및 수정 LocalDate Time을 자동으로 관리해주는 Auditing기능이 있는데, 해당 기느을 사용하기 위해
@EnableJPAAuditing을 추가했었는데, @SpringBootApplication클래스에 등록해 놓은것이 원인이다.
모든 테스트는 기본이 되는 XXXAplication 클래스가 항상 로드되는데, @EnableJpaAuditing이 해당 클래스에 등록되어 있어서
모든 테스트들이 항상 JPA관련 Bean들을 필요로 하고 있는 상태였다.
통합 테스트는 전체 컨텍스트를 로드하고 JPA를 포함한 모든 Bean들을 주입받기 때문에 에러가 발생하지않지만
@WebMVCTest같은 슬라이스 테스트는 JPA 관련 Bean들을 로드하지 않기 때문에 에러가 발생했다.

2.해결
1)@Configuration 분리

2)@MockBean 추가

@MockBean(JpaMetamodelMappingContext.class)
을 추가하면 된다.

추가로 시큐리티 설정을 하기 위해
secure = false를 MockTest에 넣었다.

