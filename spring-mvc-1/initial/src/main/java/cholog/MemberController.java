package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    // 얘는 Thymeleaf 없이는 사용할 수 없음--그리고 templates 패키지에 반환하는 문자열 이름을 가진 HTML 문서가 있어야 함
    // 따라서 localhost:8080/a 로 가도 hi.html가 뜨지 않음
    @GetMapping("/a")
    public String pageA() {
        return "hi";
    }

    // 대신 경로를 String 매개변수로 리턴하는 건 됨--근데 조건은 Thymeleaf 쓰고 있지 않아야 함!
    // localhost:8080/a2 로 이동하면 hi.html가 뜸 (Thymeleaf 의존성 없을 때만)
    @GetMapping("/a2")
    public String pageA2() {
        return "hi.html";
    }

    // 응답 값으로 페이지에 띄울 text/html Content-Type을 반환하는 메서드
    // @ResponseBody annotation으로 인해 text/html Content-Type인 것으로 인지됨
    // HTML syntax 사용 가능
    @GetMapping("/b")
    @ResponseBody
    public String pageB() {
        return "<p>Opens a page with this text as its <strong>only content</strong></p>";
    }

    // Thymeleaf 엔진은 파일 경로도 인식 가능
    // localhost:8080/c 로 이동하면 static.html 페이지가 뜸
    @GetMapping("/c")
    public String pageC() {
        return "./static.html";
    }

    // 템플릿 엔진을 사용해서 뷰 페이지를 클라이언트에 반환하는 메서드
    @GetMapping("/hello")
    public String world(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    // 클라이언트에 JSON 데이터를 반환하는 메서드
    @GetMapping("/json")
    @ResponseBody
    public Person json() {
        return new Person("brown", 20);
    }
}
