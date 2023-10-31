package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService(memberRepository);

    // 지금 가장 큰 문제가 있다면,
    // MemberService 클래스에서 사용하는 memberRepository와 현 테스트에 있는 memberRepository가 다른 인스턴스라는 것.
    MemberService memberService;
    // MemberService 클래스 14Line 참고
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach // = 각 테스트를 실행하기 전에!
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트 때마다 이름이 다르기 어려우니 돌 때마다 메모리를 clear를 해줘야 함
    @AfterEach  // = 각 테스트를 실행하고 난 후
    public void afterEach() {
        memberRepository.clearStore();
    }


    // 테스트 이름은 과감하게 한글로 바꿔도 된다!
    @Test
    void 회원가입() {
        //given : 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        //when  : 실행했을 때
        Long saveId = memberService.join(member);

        //then : 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        // memberService에서 이름이 똑같은 member2를 join한 경우, 예외가 터져서 성공
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 만약 에러 메시지를 맞춰보고 싶다면 아래처럼!
        IllegalStateException e= assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //여기서 try ~ catch로 잡을 수도 있음
        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
            // MemberService의 validateDuplicateMember 메서드와 메세지가 같아야 오류가 안 남
        }
         */


        //then
    }

    @Test
    void 전체회원조회() {
    }

    @Test
    void 회원조회() {
    }
}