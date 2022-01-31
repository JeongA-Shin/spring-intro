package hello.demo.domain;

public class Member {
    
    private Long id; //회사(내)가 고객에게 임의로 부여하는 값
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
