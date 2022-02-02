package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    
    
    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    
    @Test
    void join() {
        //given - 이런 게 주어졌을 때. 즉 이 데이터를 기반으로
        Member member=new Member();
        member.setName("Hello");
        //when - 이렇게 실행하고
        Long saveId=memberService.join(member);
        //then- 이런 결과가 나와야 함(검증)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    
    
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("Spring");
        
        Member member2=new Member();
        member2.setName("Spring");
        
        //when
        memberService.join(member1);
        
        IllegalStateException e=assertThrows(IllegalStateException.class,()->memberService.join(member2));
//        assertThrows(Class<> classType, Executable executable)
//        - assertThrows 메소드는 첫번째 인자로 발생할 예외 클래스의 Class 타입을 받습니다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
        //then
    }
    
    @Test
    void findMembers() {
    }
    
    @Test
    void findOne() {
    }
}