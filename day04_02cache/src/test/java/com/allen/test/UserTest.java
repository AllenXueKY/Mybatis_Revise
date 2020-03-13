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

/**
 * 测试Mybatis的crud操作
 */
public class UserTest {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException{
        //1、读取配置文件生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3、获取SqlSession对象
        sqlSession = factory.openSession();
        //4、获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);

    }

    @After
    public void destory() throws IOException {
        //提交事务
        sqlSession.commit();
        //6、释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        User user1 = userDao.findById(41);
        System.out.println(user1);

//        sqlSession.close();
//        //再次获取sqlsession对象
//        sqlSession = factory.openSession();

        sqlSession.clearCache();//此方法也可以清空缓存
        userDao = sqlSession.getMapper(UserDao.class);
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    /**
     * 测试缓存的同步
     */
    @Test
    public void testClearCache(){
        //1、根据id查询用户
        User user1 = userDao.findById(41);
        System.out.println(user1);
        //2、更新用户信息
        user1.setUsername("update user clear cache");
        user1.setAddress("西安市周至县");
        userDao.updateUser(user1);
        //3、再次查询id为41的用户
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

}
