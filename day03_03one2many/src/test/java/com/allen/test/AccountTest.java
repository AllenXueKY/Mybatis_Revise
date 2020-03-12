package com.allen.test;

import com.allen.dao.AccountDao;
import com.allen.domain.Account;
import com.allen.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试Mybatis的crud操作
 */
public class AccountTest {
    private InputStream in;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    @Before
    public void init() throws IOException{
        //1、读取配置文件生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、获取SqlSession对象
        sqlSession = factory.openSession();
        //4、获取dao的代理对象
        accountDao = sqlSession.getMapper(AccountDao.class);

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
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        //5、执行查询所有方法
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("----------每个account的信息-------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 测试查询帐户同时包含用户名称和地址
     */
    @Test
    public void testFindAllAccountUser() {
        //5、执行查询所有方法
        List<AccountUser> aus = accountDao.findAllAccount();
        for (Account au : aus) {
            System.out.println(au);
        }
    }

}
