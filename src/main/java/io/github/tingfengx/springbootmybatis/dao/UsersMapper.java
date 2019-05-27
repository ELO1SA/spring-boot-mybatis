package io.github.tingfengx.springbootmybatis.dao;

import io.github.tingfengx.springbootmybatis.model.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper class
 *
 * @author eloisa
 * @createTime May 2019
 */
@Mapper
public interface UsersMapper {

    /**
     * 选择所有，相当于SELECT * ...
     *
     * @return 数据库内所有用户
     */
    List<Users> selectAll();

    /**
     * 插入users
     *
     * @param users 要插入的新user
     */
    void insert(Users users);

    /**
     * 删除users。
     * 为了测试方便，当前版本直接使用用户名删除用户（一次删除即删除所有同名用户）
     *
     * @param users 要删除的user
     */
    void deleteByName(Users users);

    /**
     * 根据ID 删除users
     *
     * @param users 要删除的user
     */
    void deleteById(Users users);
}
