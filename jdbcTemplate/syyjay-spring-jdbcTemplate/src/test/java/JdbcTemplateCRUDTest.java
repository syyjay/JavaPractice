
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    public void  testUpdate(){
        jdbcTemplate.update("update tb_user  set money = ? where name = ?","1000","lisi");
    }

    @Test
    public void testDelete(){
        jdbcTemplate.update("delete  from tb_user where name = ?","lisi");
    }
    @Test
    public void testQueryAll(){
        List<User> users = jdbcTemplate.query("select * from tb_user", new BeanPropertyRowMapper<User>(User.class));
        for (User user : users){
            System.out.println(user.getName());
        }
    }

    @Test
    public  void testQueryOne(){
        User user = jdbcTemplate.queryForObject("select * from tb_user where name = ?", new BeanPropertyRowMapper<User>(User.class), "张三");
        System.out.println(user.getName());
    }

    @Test
    public void  testQueryCount(){
        Long l = jdbcTemplate.queryForObject("select count(*) from tb_user",Long.class);
        System.out.println(l);
    }
}
