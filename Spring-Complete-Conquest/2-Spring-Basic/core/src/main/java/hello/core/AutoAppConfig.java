package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// classes = Configuration.class 설정 이유 : 기본의 AppConfig가 스캔되지 않도록 하기 위해
// 보통 설정 정보를 제외하지는 않지만, 기존의 예제 코드를 남겨놓기 위해!
// 만약 basePackages를 설정하지 않으면 @ComponentScan이 있는 설정 정보 클래스의 패키지가 시작 위치가 됨
@Configuration
@ComponentScan(
        basePackages = "hello.core", /* hello.core.member 이면 이 패키지만 인식 */
        basePackageClasses = AutoAppConfig.class,   /* 지정하지 않으면 기본값으로. 기본값 = 첫번째 줄의 package의 hello.core */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 같은 이름의 빈이 있는데 왜 성공할까?
    // 이 경우, 수동으로 등록한 빈이 우선권을 가짐
}

/**
 * @Component : 컴포넌트 스캔에서 사용
 * @Controller : 스프링 MVC 컨트롤러에서 사용 & 스프링 MVC 컨트롤러로 인식
 * @Service : 스프링 비즈니스 로직에서 사용 & 특별한 처리를 하지 않는 대신 핵심 지브니스 로직이 여기에 있겠구나 하고 비즈니스 계층을 인식하는 데에 도움이 됨
 * @Repository : 스프링 데이터 접근 계층에서 사용 & 데이터 계층의 예외를 스프링 예외로 변환
 * @Configuration : 스프링 설정 정보에서 사용 & 스프링 빈이 싱글톤을 유지하도록 추가 처리
 * 
 * 참고로 useDefaultFilters 옵션을 끄면 기본 스캔 대상에서 제외
 */