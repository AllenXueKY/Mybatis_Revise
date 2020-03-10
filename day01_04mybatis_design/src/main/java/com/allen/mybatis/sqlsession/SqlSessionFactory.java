package com.allen.mybatis.sqlsession;

public interface SqlSessionFactory {
    /**
     * 用户打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
