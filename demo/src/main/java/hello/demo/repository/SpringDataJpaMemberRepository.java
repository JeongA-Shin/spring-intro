package hello.demo.repository;

import hello.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository

import javax.management.openmbean.OpenDataException;
import java.util.Optional;

//interface는 interfacce를 받는다고 해서 implements가 아니라 extends임
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
    
    @Override
    Optional<Member> findByName(String name);
}
