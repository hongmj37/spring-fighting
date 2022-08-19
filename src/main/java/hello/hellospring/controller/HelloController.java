package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data"," hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody  //이 애너테이션이 붙으면 template를 찾아가지 않고 그대로 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  //문자면 문자 그대로 반환
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); //command + shift + Enter 하면
        hello.setName(name);
        return hello; //객체이면 json 구조로 나타남 {"name" : "spring!!"}
    } //viewResolver 대신 HttpMessageConverter가 대신 동작
       //문자 처리 : StringHttpMessageConverter
       //객체 처리 : MappingJackson2HttpMessageConverter

    static class Hello {  //static으로 만들면 HelloController 클래스 안에서 클래스를 또 만들 수 있음
        private String name;
        //name이 private이기 때문에 getter/setter 해줌
        public String getName() {  //command + N 단축키로 getter setter 쉽게 만들 수 있음
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
