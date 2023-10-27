package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data1", "Hello!");
        model.addAttribute("data2", "Hello!!");
        return "hello";
        // 여기서 return 하는 hello는 templates의 hello.html의 hello이다.
        // 이 return 값을 반환하면 뷰리졸버(ViewResolver)가 화면을 찾아서 처리한다.
    }



    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }



    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello!! " + name;
    }   // 참고로 쓸 일이 거의 없다.
    /* 여기 @ResponseBody는 Body에 바로 값을 반환한다는 의미 */




    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }   // JSON으로 반환. 이게 기본이기 때문에 이 방식으로 쓰는 것이 맞음.

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
