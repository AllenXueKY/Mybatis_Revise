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

/**
 * 测试Mybatis的crud操作
 */
public class SecondLevelCacheTest {
    private InputStream in;
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException{
        //1、读取配置文件生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    @After
    public void destory() throws IOException {
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        SqlSession sqlSession1 = factory.openSession();
        UserDao dao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = dao1.findById(41);
        System.out.println(user1);
        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2 = factory.openSession();
        UserDao dao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = dao2.findById(41);
        System.out.println(user2);
        sqlSession2.close();
        System.out.println(user1 == user2);
    }

}
