package com.allen.test;

import com.allen.dao.UserDao;
import com.allen.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AnnotationCRUDTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public  void init() throws IOException {
        //1、获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、根据SqlSessionFactory生产一个SqlSession
        sqlSession = factory.openSession(true);
        //4、使用SqlSession获取Dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);

    }

    @After
    public void destory(){
        //6、释放资源
        if(sqlSession != null){
            sqlSession.close();
        }
        if (in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询所有用户
     */
    @Test
    public void TestFindAll(){
        //5、执行Dao的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 删除用户
     */
    @Test
    public void TestDeleteUser(){
        //5、执行Dao的方法
        userDao.deleteUser(51);
    }

    /**
     * 根据id查询用户
     */
    @Test
    public void TestFindOne(){
        //5、执行Dao的方法
        User user = userDao.findById(57);
        System.out.println(user);
    }

    /**
     * 根据用户名模糊查询用户
     */
    @Test
    public void TestFindByName(){
        //5、执行Dao的方法
//        List<User> users = userDao.findUserByName("%王%");
        List<User> users = userDao.findUserByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询总用户数量
     */
    @Test
    public void TestFindTotalUser(){
        int total = userDao.findTotalUser();
        System.out.println(total);
    }
}
