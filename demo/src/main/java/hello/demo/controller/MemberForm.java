package hello.demo.controller;

public class MemberForm {
    private String name; 
// <input type="text" id="name" name="name" placeholder="이름을 입력하세요">에서 name태그가 name인데 얘랑 연결됨
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
