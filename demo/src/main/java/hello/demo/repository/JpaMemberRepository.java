package hello.demo.repository;

import hello.demo.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    
    private final EntityManager em;
    //JPA는 모든 동작을 EntityManager를 통해 한다
    
    //build.gradle의 dependency에 jpa가 있으면 스프링 부트가 자동으로 EntityManager를 생성하므로 우리는 
    //이렇게 선언만 해서 아래와 같이 injection하면 된다
    public JpaMemberRepository(EntityManager em){
        this.em=em;
    }
    
    @Override
    public Member save(Member member) {
        em.persist(member);//persist는 영구 저장한다는 의미
        return member;//반환형 맞추기 위한 것
    }
    
    @Override
    public Optional<Member> findById(Long id) {
        Member member= em.find(Member.class,id); //조회하기
        //em.find(조회할 타입, 식별자)
        return Optional.ofNullable(member);//해당되는 객체가 없을 수도 있으므로 ofNullable
    }
    
    //pk 기반이 아닌 걸로 조회하는 것은 createQuery를 작성해야만 한다
    
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result=em.createQuery("select m from Member m where m.name=:name",Member.class).setParameter("name",name).getResultList();
        return result.stream().findAny();
    }
    
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();
        //table이 아니라 객체를 대상으로 쿼리를 날리는 거임 //Member as m 의 줄임말이 Member m임
        //id나 name등의 필드를 select하는 것이 아니라 해당되는 객체를 통채로 select 하는 것임(row 전체를)
        
    }
}
