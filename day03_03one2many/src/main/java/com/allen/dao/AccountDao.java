package com.allen.dao;

import com.allen.domain.Account;
import com.allen.domain.AccountUser;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有帐户
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有帐户，并且带有用户名称和地址信息
     * @return
     */
    List<AccountUser> findAllAccount();
}
