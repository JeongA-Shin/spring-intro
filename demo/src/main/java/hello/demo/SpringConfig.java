package hello.demo;

import hello.demo.repository.MemberRepository;
import hello.demo.repository.JdbcTemplateMemberRepository;
import hello.demo.repository.MemoryMemberRepository;
import hello.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    
    private final DataSource dataSource; //datasource는 h2같은 db. resources/application.properties 참고
    
    public SpringConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }
    
    @Bean //스프링 빈을 직접 등록한ㄴ다는 의미
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
