package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // 철저히 DIP를 지키는 중. = 철저히 Interface를 의존하는 중

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 주문 생성 요청이 오면 회원 정보를 먼저 조회 하고
        Member member = memberRepository.findById(memberId);
        // 이 부분이 정말 잘 설계가 된 게, 이 주문과 다르게 이 부분으로 할인 정책의 결과물이 돌아오기 떄문에.
        // = 단일 책임 원칙을 지킴. (SRP)
        // 회원 정보를 할인 정책에 넘기고
        int discountPrice = discountPolicy.discount(member, itemPrice);
        
        // 최종 생성된 주문을 반환함
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
