package hello.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //어노테이션
public class HelloController {
    //url에서 /hello (어노테이션의 파라미터) 로 들어오면 hello 메서드가 실행된다.
    @GetMapping("hello") //@GetMapping 어노테이션은 HTTP GET 요청을 처리하는 메서드를 맵핑(@RequestMapping) 하는 어노테이션이다
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello"; //templates폴더에서 이 controller가 가고자 하는 html이름. 즉 templates/hello.html
    }
}
