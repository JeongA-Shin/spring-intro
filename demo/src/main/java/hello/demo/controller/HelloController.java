package hello.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //어노테이션
public class HelloController {
    /////////웹 개발 기초 - 정적 콘텐츠
    //url에서 /hello (어노테이션의 파라미터) 로 들어오면 hello 메서드가 실행된다.
    @GetMapping("hello") //@GetMapping 어노테이션은 HTTP GET 요청을 처리하는 메서드를 맵핑(@RequestMapping) 하는 어노테이션이다
    public String hello(Model model){
        model.addAttribute("data","hello!");//model에 key와 value를 넘겨줌
        return "hello"; //templates폴더에서 이 controller가 가고자 하는 html이름. 즉 templates/hello.html
    }
    
    /////////웹 개발 기초 - 동적 콘텐츠
    @GetMapping("hello-mvc")
//    @RequestParam
//    단일 HTTP 요청 파라미터의 값을 메소드 파라미터에 넣어주는 어노테이션
//    가져올 요청 파라미터의 이름을 @RequestParam 어노테애션의 기본 값으로 지정해주면 됨
//    요청 파라미터의 값은 메소드 파라미터의 타입에 따라 적절히 변환 됨
//    해당 파라미터가 반드시 존재해야하며, 없으면 HTTP 400 - Bad Request 발생
//    파라미터를 필수가 아니라 선택적으로 제공하게 하려면 required 엘리먼트를 false로 설정할 것
    public String helloMvc(@RequestParam("name") String name,Model model){
        //http://localhost:8080/hello-mvc?name=jeongjeong 이런 식으로 url에 파라미터가 넘겨져야한다는 의미임
        model.addAttribute("name",name);//model에 key와 value를 넘겨줌
        return "hello-template";//templates폴더에서 이 controller가 가고자 하는 html이름
    }
    
    
    /////////웹 개발 기초 - API
    //얘는 view가 필요 없음
    @GetMapping("hello-string") //@GetMapping 어노테이션은 HTTP GET 요청을 처리하는 메서드를 맵핑(@RequestMapping) 하는 어노테이션이다
    @ResponseBody //이게 완전 중요- http 통신의 헤더/바디 부분 중 바디 부분에 return값을 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello"+name; //그냥 이 문자 그대로를 전달함
    }
    
    /////////웹 개발 기초 - API2
    @GetMapping("hello-api")
    @ResponseBody
    //아래에 Hello라는 객체가 있음. 이 함수의 반환형은 Hello라는 객체임
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello=new Hello();//객체 만듦
        hello.setName(name); //파라미터인 name에 해당되는 값을 setter함수에 넣어서 객체에 적용함
        return hello; //별도의 뷰를 거치지 않고 그냥 이 객체 그대로를 (json형식)전달함
    }
    
    
    static class Hello{
        private String name;
        
        public String getName(){
            return name;
        }
        
        public void setName(String name){
            this.name=name;
        }
    }
    
}
