package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    /* Controller 애노테이션을 보고 해당 클래스 내의 내용으로 분류 */

    //private final MemberService memberService = new MemberService();
    // 이렇게 등록해도 되지만 이제 스프링 컨테이너에 등록하면 하나만 등록하여 사용할 수 있다
    @Autowired
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        // @Autowired 가 스프링 컨테이너의 멤버 서비스를 연결해줌
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
