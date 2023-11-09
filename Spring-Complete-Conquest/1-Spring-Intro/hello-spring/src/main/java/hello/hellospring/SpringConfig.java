package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpringConfig {

/*    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

/*    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired  // 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository= memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository/*()*/);
    }

/*    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
    // 요거 대신 직접 @Component를 넣어 컴포넌트 스캔하여 Bean 등록

/*    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
    }*/
}
