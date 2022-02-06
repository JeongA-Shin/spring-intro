package hello.demo.controller;
import hello.demo.domain.Member;
import hello.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    
    //controller와 service를 연결시켜줌
    @Autowired // 알아서 의존 객체(Bean) 를 찾아서 주입한다.
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }
    
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; //template의 이 파일을 열어줌
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());
        
        memberService.join(member);
        
        return "redirect:/";
    }
    
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        
        return "members/memberList";
        
    }


}
