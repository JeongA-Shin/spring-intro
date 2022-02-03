package hello.demo.controller;
import hello.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;
    
    //controller와 service를 연결시켜줌
    @Autowired // 알아서 의존 객체(Bean) 를 찾아서 주입한다.
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }
}
