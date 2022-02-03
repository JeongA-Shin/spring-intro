package hello.demo;

import hello.demo.repository.MemberRepository;
import hello.demo.repository.MemoryMemberRepository;
import hello.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    
    @Bean //스프링 빈을 직접 등록한ㄴ다는 의미
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
