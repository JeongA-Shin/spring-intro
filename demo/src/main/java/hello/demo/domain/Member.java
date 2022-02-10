package hello.demo.domain;

import javax.persistence.*;


@Entity
public class Member { //JPA가 관리하는 엔티티가 됨
    
    @Id //primary key(pk) //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 데이터베이스에 위ㅣ임. 데이터베이스가 알아서 생성(ex)AUTO_INCREMENT
    private Long id; //회사(내)가 고객에게 임의로 부여하는 값
    
   // @Column(name="username") //현재는 db명이 그냥 name이지만 db table에서 컬럼명이 username이면 (즉 클래스(인스턴스,객체)의 속성,변수명과 다르면) 이렇게 어노테이션을 줘야 매칭됨
    private String name; //고객이 직접 가입시 적는다고 가정
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
