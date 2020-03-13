package com.allen.dao;

import com.allen.domain.Account;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有帐户
     * @return
     */
    List<Account> findAll();

    /**
     * 根据用户id查询账户信息
     * @param uid
     * @return
     */
    List<Account> findAccountByUid(Integer uid);
}
