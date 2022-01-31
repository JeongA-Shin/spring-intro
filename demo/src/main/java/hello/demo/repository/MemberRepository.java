package hello.demo.repository;
//(Member)Repository는 멤버(클래스의) 객체를 저장하는 곳

import hello.demo.domain.Member; //얘를 임포트해와야함
import java.util.Optional;
import java.util.List;


//레포지토리가 구현해야하는 기능들을 interface로 해줌
public interface MemberRepository {
    Member save(Member member);
    //Optional<T> 클래스는 Integer나 Double 클래스처럼 'T'타입의 객체를 포장해 주는 래퍼 클래스(Wrapper class)입니다.
    //이러한 Optional 객체를 사용하면 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피할 수 있습니다.
    //즉, 복잡한 조건문 없이도 널(null) 값으로 인해 발생하는 예외를 처리할 수 있게 됩니다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
