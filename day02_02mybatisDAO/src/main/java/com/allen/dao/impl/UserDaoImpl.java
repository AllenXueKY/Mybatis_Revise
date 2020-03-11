package com.allen.dao.impl;

import com.allen.dao.UserDao;
import com.allen.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao{

    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        //1、根据factory过去SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.allen.dao.UserDao.findAll");//参数就是能获取配置信息的key
        //3、释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        //1、根据factory过去SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存
        session.insert("com.allen.dao.UserDao.saveUser",user);
        //3、提交事务
        session.commit();
        //4、释放资源
        session.close();
    }

    @Override
    public void updateUser(User user) {
        //1、根据factory过去SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现更新
        session.update("com.allen.dao.UserDao.updateUser",user);
        //3、提交事务
        session.commit();
        //4、释放资源
        session.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        //1、根据factory过去SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现更新
        session.delete("com.allen.dao.UserDao.deleteUser",userId);
        //3、提交事务
        session.commit();
        //4、释放资源
        session.close();
    }

    @Override
    public User findById(Integer userId) {
        //1、根据factory过去SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现查询列表
        User user = session.selectOne("com.allen.dao.UserDao.findById", userId);//参数就是能获取配置信息的key
        //3、释放资源
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String username) {
        //1、根据factory过去SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.allen.dao.UserDao.findByName",username);//参数就是能获取配置信息的key
        //3、释放资源
        session.close();
        return users;
    }

    @Override
    public int findTotal() {
        //1、根据factory过去SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现查询列表
        Integer count = session.selectOne("com.allen.dao.UserDao.findTotal");//参数就是能获取配置信息的key
        //3、释放资源
        session.close();
        return count;
    }
}
