package interview.spring.websecurity.controller;

import interview.spring.websecurity.model.User;
import interview.spring.websecurity.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HelloController {
    @Autowired
    UserRepo userRepo;
    @GetMapping("/")
    public @ResponseBody  User hello() throws IOException {

        File file = new File("test.test");
        file.createNewFile();
        String path = file.getAbsolutePath();
        System.out.println("path:"+path);

        User user = new User("username1","password1");
        User user1 = userRepo.save(user);
        return user1;
    }

    @GetMapping("/cloudConfigTest")
    public @ResponseBody List<String> getConfigureServer(){
        return Stream.of("David","Tom","Tim","Smith").collect(Collectors.toList());
    }
}
