package hello.core.member;

public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 지금 이 코드가 좋지 않은 이유 : 현재 interface인 MemberRepository를 의존하지만
    // 실제 할당하는 부분은 구현체인 MemoryMemberRepository를 의존하고 있음.
    // 이 말인즉슨 DIP(의존 역전 원칙)을 위반하고 있는 것
    
    private final MemberRepository memberRepository;
    // 이 부분은 추상화에만 의존

    
    public MemberServiceImpl(MemberRepository memberRepository) {
        // 생성자를 통해 memberRepository에 어떤 게 들어갈 지 선택
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
