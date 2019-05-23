package io.tingfeng.mybatis.springbootmybatis.resource;

import io.tingfeng.mybatis.springbootmybatis.dao.UsersMapper;
import io.tingfeng.mybatis.springbootmybatis.model.Users;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersResource {

    private UsersMapper usersMapper;

    public UsersResource(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * 主页
     *
     * @return 返回主页问候语
     */
    @GetMapping("/home")
    public String home() {
        return "This is home page, although (almost) empty\n";
    }

    /**
     * 返回当前数据库内所有人员信息
     *
     * @return 人员信息
     */
    @GetMapping("/all")
    public List<Users> getAll() {
        return usersMapper.selectAll();
    }

    /**
     * 插入新用户(用户名为name，薪水为salary)
     *
     * @param name         用户名
     * @param stringSalary 薪水(LONG)的字符串表达
     * @return 插入后当前数据库内所有人员信息
     */
    @PostMapping("/insert/name={name}&salary={salary}")
    private List<Users> insertUser(@PathVariable("name") String name, @PathVariable("salary") String stringSalary) {
        Users users = new Users();
        users.setName(name);
        users.setSalary(Long.parseLong(stringSalary));
        usersMapper.insert(users);
        return usersMapper.selectAll();
    }

    /**
     * 从数据库中删除名为name的用户
     *
     * @param name 用户名
     * @return 删除后当前数据库内所有人员信息
     */
    @DeleteMapping(value = "/delete/name={name}")
    private List<Users> deleteByName(@PathVariable("name") String name) {
        Users users = new Users();
        users.setName(name);
        usersMapper.delete(users);
        return getAll();
    }
}
