package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;
import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)    // 자동 주입 대상이 없으면 메서드 호출 안 됨
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1); // 결과: 호출 안 됨
        }

        @Autowired  // @Nullable 자동 주입 대상이 없으면 Null이 입력됨
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2); // 결과: noBean2 = null
        }

        @Autowired  // 자동 주입 대상이 없으면 Optional.empty가 입력됨
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3); // 결과: noBean3 = Optional.empty
        }


    }

}
