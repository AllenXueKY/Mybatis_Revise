package com.allen.test;

import com.allen.dao.AccountDao;
import com.allen.dao.UserDao;
import com.allen.domain.Account;
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

public class AccountTest {

    private InputStream in;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    @Before
    public  void init() throws IOException {
        //1、获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、根据SqlSessionFactory生产一个SqlSession
        sqlSession = factory.openSession(true);
        //4、使用SqlSession获取Dao的代理对象
        accountDao = sqlSession.getMapper(AccountDao.class);

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
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("-----------每个账户的信息---------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }


}
