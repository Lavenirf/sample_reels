package org.example.labsystem;

import jakarta.annotation.Resource;
import org.example.labsystem.mapper.*;
import org.example.labsystem.pojo.entity.*;
import org.example.labsystem.pojo.vo.UserVo;
import org.example.labsystem.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LabSystemApplicationTests {
    @Resource
    private RightMapper mapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private LabMapper labMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private JwtUtils jwtUtils;
    @Test
    void contextLoads() {
        List<Right> rightList = mapper.getRightList();
        rightList.forEach(System.out::println);
    }
    @Test
    void getRoleList(){
        List<Role> roleList = roleMapper.getRoleList();
        roleList.forEach(System.out::println);
    }
    @Test
    void UserMapper(){
/*        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        List<User> userList = userMapper.getUserList(user);
        userList.forEach(System.out::println);*/
        UserVo userVo = new UserVo();
        User login = userMapper.login("admin","123");
        System.out.println(login.toString());
    }
    @Test
    void labMapper() {
        Lab lab = new Lab();
        lab.setTitle("qaa");
        lab.setLabType(1);
        lab.setCapacity(5);
        lab.setCollegeType(3);
        labMapper.insertLab(lab);
    }
    @Test
    void getBook(){
        Book book = new Book();
        book.setBookUser(1);
        List<Book> list = bookMapper.getList(book);
        list.forEach(System.out::println);
    }
    @Test
    void tokenTest() throws InterruptedException {
        String token = jwtUtils.createToken("admin");
        System.out.println(token);
        Thread.sleep(5000);
        String data = jwtUtils.getData(token);
        System.out.println(data);
    }
}
