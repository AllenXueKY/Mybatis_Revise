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

public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before
    public  void init() throws IOException {
        //1、获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、根据字节输入流构建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3、根据SqlSessionFactory生产一个SqlSession
        sqlSession = factory.openSession(true);
        //4、使用SqlSession获取Dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);

    }

    @After
    public void destory(){
        if (in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据id查询用户
     */
    @Test
    public void TestFindOne(){
        //5、执行Dao的方法
        User user = userDao.findById(57);
        System.out.println(user);
        sqlSession.close();

        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findById(57);
        System.out.println(user1);
        sqlSession1.close();
        System.out.println(user==user1);
    }
}
