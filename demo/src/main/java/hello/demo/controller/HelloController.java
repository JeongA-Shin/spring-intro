package hello.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //어노테이션
public class HelloController {
    //url에서 /hello (어노테이션의 파라미터) 로 들어오면 hello 메서드가 실행된다.
    @GetMapping("hello") //@GetMapping 어노테이션은 HTTP GET 요청을 처리하는 메서드를 맵핑(@RequestMapping) 하는 어노테이션이다
    public String hello(Model model){
        model.addAttribute("data","hello!");//model에 key와 value를 넘겨줌
        return "hello"; //templates폴더에서 이 controller가 가고자 하는 html이름. 즉 templates/hello.html
    }
    
    
    @GetMapping("hello-mvc")
//    @RequestParam
//    단일 HTTP 요청 파라미터의 값을 메소드 파라미터에 넣어주는 어노테이션
//    가져올 요청 파라미터의 이름을 @RequestParam 어노테애션의 기본 값으로 지정해주면 됨
//    요청 파라미터의 값은 메소드 파라미터의 타입에 따라 적절히 변환 됨
//    해당 파라미터가 반드시 존재해야하며, 없으면 HTTP 400 - Bad Request 발생
//    파라미터를 필수가 아니라 선택적으로 제공하게 하려면 required 엘리먼트를 false로 설정할 것
    public String helloMvc(@RequestParam("name") String name,Model model){
        model.addAttribute("name",name);//model에 key와 value를 넘겨줌
        return "hello-template";//templates폴더에서 이 controller가 가고자 하는 html이름
    }
}
