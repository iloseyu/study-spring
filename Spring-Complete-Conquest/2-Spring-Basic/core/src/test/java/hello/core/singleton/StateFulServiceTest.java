package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {

    @Test
    @DisplayName("")
    void statefulSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        // ↓ 상황 설명 : A 사용자가 주문을 하고, A의 주문 금액을 조회하려는데 B 사용자가 주문을 했다. ↓

//        // ThreadA : A 사용자 10000원 주문
//        stateFulService1.order("userA", 10000);
//        // ThreadB : B 사용자 20000원 주문
//        stateFulService1.order("userB", 20000);

        // ThreadA : A 사용자 10000원 주문
        int userAPrice = stateFulService1.order("userA", 10000);
        // ThreadB : B 사용자 20000원 주문
        int userBPrice = stateFulService1.order("userB", 20000);

        // ThreadA : 사용자 A 주문 금액 조회
        int price = stateFulService1.getPrice();

        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

//        org.assertj.core.api.Assertions.assertThat(stateFulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }

}