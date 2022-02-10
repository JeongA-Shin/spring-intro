package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    
    private final MemberRepository memberRepository;
    
//    @Autowired //멤버 서비스는 레포지토리와 autoWired 되어야 함
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    
    private void validateDuplicateMember(Member member){
        //같은 이름이 있는 중복회원이 있으면 안 됨
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
        
    }
    
    /**
     * 회원 가입
     * */
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
