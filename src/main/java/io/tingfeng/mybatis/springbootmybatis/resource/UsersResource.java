package io.tingfeng.mybatis.springbootmybatis.resource;

import io.tingfeng.mybatis.springbootmybatis.dao.UsersMapper;
import io.tingfeng.mybatis.springbootmybatis.model.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersResource {

    private UsersMapper usersMapper;

    public UsersResource(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }
    
    @RequestMapping(value="/")
    public String defaultHandler(){
        return "thymeleaf";
    }

    @GetMapping("/home")
    public String home(){
        return "This is home page, although (almost) empty\n";
    }

    @GetMapping("/all")
    public List<Users> getAll() {
        return usersMapper.findAll();
    }

    @GetMapping("/update")
    private List<Users> update() {

        Users users = new Users();
        users.setName("xiaogang");
        users.setSalary(6666L);

        usersMapper.insert(users);

        return usersMapper.findAll();
    }
}
