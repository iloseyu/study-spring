package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
        // 기존에 내가 직접 생성했는데, 
/*        AppConfig appConfig = new AppConfig();
        // 이제 AppConfig에서 부름
        MemberService memberService = appConfig.memberService();
        // memberService에는 memberServiceImpl이 들어있음*/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // AppConfig의 설정 내용을 가지고 옴
        // ApplicationContext를 스프링 컨테이너라고 함.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // 기본적으로 메서드 이름을 name으로 등록되고, 두 번째는 반환 타입을 정해줌

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
