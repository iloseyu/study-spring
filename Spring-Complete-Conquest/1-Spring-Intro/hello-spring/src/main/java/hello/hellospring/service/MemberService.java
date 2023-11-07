package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository();


    private final MemberRepository memberRepository;

    //memberRepository를 외부에서 받도록 바꿔줌. (MemberServiceTest BeforeEach 볼 것)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /*
    * 회원가입
    * @param member
    * @return
    * */
    public long join(Member member) {
        // 같은 이름의 중복 회원이 있으면 안 됨
        // result.get으로 꺼낼 수 있으나 권장하지 않음
        /*
        * 이 분을 아래와 같이 작성하는 것이 보기에 더 좋음
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        // 그리고 이렇게 줄글로 나오는 경우엔 메서드를 하나로 뽑는 것이 좋음.
        /*
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
         */
        
        // 중복 회원 검증 메서드 호출
        validateDuplicateMember(member);
        // 검증 후 저장
        memberRepository.save(member);
        return member.getId();
    }

    /*
    * 중복 회원 검증
    * */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }



    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    
    
    
    /*
    * 아이디로 회원 조회
    * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
