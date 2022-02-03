package hello.demo.repository;

import hello.demo.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.HashCodeAndEqualsSafeSet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@Repository
public class MemoryMemberRepositoryTest {
    
    MemoryMemberRepository repository=new MemoryMemberRepository();
    
    @AfterEach //클래스의 각 메서드가 끝나고 나서의 작업을 명시하는 어노테이션
    public void afterEach(){
        repository.clearStore();
    }
    
    @Test //@Test는 해당 메소드가 단위 테스트임을 명시하는 어노테이션임
    public void save(){ //save함수를 테스트함
        Member member=new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result="+(result == member));
        //Assertions.assertEquals(member,result); //같은지 확인할 수 있게 해줌
        assertThat(member).isEqualTo(result);
    }
    
    @Test //findByName을 테스트함
    public void findByName(){
        Member member1=new Member();
        member1.setName("Spring1");
        repository.save(member1);
    
        Member member2=new Member();
        member2.setName("Spring2");
        repository.save(member2);
        
        Member result=repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
    
}
