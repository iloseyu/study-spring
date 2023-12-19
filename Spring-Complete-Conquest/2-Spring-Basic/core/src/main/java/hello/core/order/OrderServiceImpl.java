package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component/*("service")*/
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

/*    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

/*    @Autowired private MemberRepository memberRepository;
    @Autowired  private DiscountPolicy discountPolicy;
    // 필드 주입. 이렇게 쓰면 코드도 간결하고 좋아 보이지만
    // 필드에 바로 주입하면 외부에서 변경이 불가능해서 테스트하기 어렵다는 단점이 있음
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    // 어쨌든 결국엔 set에 Autowried를 해야함
    // 보통 잘 안 쓰지만 테스트하는 경우에 사용할 수도 있음*/



/*    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 현재 클래스처럼 생성자가 딱 하나일 때는 @Autowired 생략 가능*/

/*    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 한 번에 여러 필드를 주입받을 수 있음
    // 일반적으로 잘 사용하지 않음*/
    
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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
