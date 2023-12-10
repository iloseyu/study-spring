package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
        // 기존에 내가 직접 생성했는데, 
        AppConfig appConfig = new AppConfig();
        // 이제 AppConfig에서 부름
        MemberService memberService = appConfig.memberService();
        // memberService에는 memberServiceImpl이 들어있음

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
