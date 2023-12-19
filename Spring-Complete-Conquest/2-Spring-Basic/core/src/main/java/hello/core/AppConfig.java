package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Bean memberService -> new MemoryMemberRepository()
// @Bean orderService -> new MemoryMemberRepository()


// soutm 예상
// CALL AppConfig.memberService
// CALL AppConfig.memberRepository
// CALL AppConfig.memberRepository
// CALL AppConfig.orderService
// CALL AppConfig.memberRepository

// 결과
// CALL AppConfig.memberService
// CALL AppConfig.memberRepository
// CALL AppConfig.orderService

// 왜 이런 결과가?
// 이유는 @Configuration에 있음.

@Configuration
public class AppConfig {
    // AppConfig는 애플리케이션 실제 동작에 필요한 구현 객체를 생성
    // 생성한 객체의 참조를 생성자를 통해서 주입

    @Bean
    public MemberService memberService() {
        System.out.println("CALL AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        // 만약에 이게 나중에 DB로 바뀌어도, 이 부분만 바꿔주면 됨
        System.out.println("CALL AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("CALL AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
        // 일단 DiscountPolicy는 Fix로 설정
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("CALL AppConfig.discountPolicy");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
