package hello.demo.repository;

import hello.demo.domain.Member;

import java.util.*;

//interface인 MemberRepository를 구현하는 클래스임
public class MemoryMemberRepository implements MemberRepository {
    
    private static Map<Long,Member> store=new HashMap<>();//키는 아이디, 값은 멤버 객체
    private static long sequence=0L; //키 값을 생성해주는 애
    
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //해당 member에 대해 ID값 생성(즉 sequence값이 id값이 되는 것)
        store.put(member.getId(),member); //그리고 sequence로 set한 아이디를 get으로 가져오고(키), 멤버 객체(value)를 store에 put함수로 저장
        return member;
    }
    
    @Override
    public Optional<Member> findById(Long id) {
        //store(회원 정보가 저장되어 있는 map)에서 꺼내면 됨
        return Optional.ofNullable(store.get(id));
        //결과가 없으면 null이 반환될 수 있으므로 optional로 감쌈
    }
    
    @Override
    public Optional<Member> findByName(String name) {
        //List를 정렬할 때는 Collection.sort()를 사용해야하고, 배열을 정렬할 때는 Arrays.sort()를 사용해야 한다. 이렇게 데이터 소스마다 다른 방식으로 다루어야하는 문제점을 해결해주는 것이 Stream 이다.
        return store.values().stream()
                .filter(member->member.getName().equals(name)) //filter는 요소들을 조건에 따라 걸러내는 작업을 해줍니다.
                .findAny();//findAny()는 Multi thread에서 Stream을 처리할 때 가장 먼저 찾은 요소를 리턴합니다.순서에 주의를 기울이지 않고 요소를 찾을 때 사용하십시오.
    }
    
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store의 각 value(키가 아닌)들이 멤버들임. 걔를 리스트로 해줌
        //ArrayList<Integer> integers4 = new ArrayList<>(integers1); // 다른 Collection값으로 초기화
    }
}
