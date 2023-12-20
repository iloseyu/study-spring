package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  // 주석을 풀면 오류가 터짐. 왜냐면 빈이 두 개가 인식돼서.
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;   //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade().equals(Grade.VIP)) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
