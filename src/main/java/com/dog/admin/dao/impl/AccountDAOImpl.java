package com.dog.admin.dao.impl;

import com.dog.admin.dao.AccountDAO;
import com.dog.admin.domain.AccountDO;
import com.dog.admin.domain.AccountQuery;
import com.dog.admin.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号管理DAO组件
 * @author yangxi
 *
 */
@Repository
public class AccountDAOImpl implements AccountDAO {

	/**
	 * 账号管理mapper组件
	 */
	@Autowired
	private AccountMapper accountMapper;
	
	/**
	 * 分页查询账号
	 * @param query 账号查询条件
	 * @return 账号
	 */
	@Override
	public List<AccountDO> listByPage(AccountQuery query) {
		return accountMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询账号
	 * @param id 账号id 
	 * @return 账号
	 */
	@Override
	public AccountDO getById(Long id) {
		return accountMapper.getById(id);
	}


	/**
	 * 根据username查询账号
	 *
	 * @param username 账号名
	 * @return 账号
	 */
	@Override
	public AccountDO getByUsername(String username) {
		return accountMapper.getByUsername(username);
	}

	/**
	 * 新增账号
	 * @param account 账号
	 */
	@Override
	public Long save(AccountDO account) {
		accountMapper.save(account);  
		return account.getId();
	}
	
	/**
	 * 更新账号
	 * @param account 账号
	 */
	@Override
	public void update(AccountDO account) {
		accountMapper.update(account); 
	}
	
	/**
	 * 更新密码
	 * @param account 账号
	 */
	@Override
	public void updatePassword(AccountDO account) {
		accountMapper.updatePassword(account); 
	}
	
	/**
	 * 删除账号
	 * @param id 账号ID
	 */
	@Override
	public void remove(Long id) {
		accountMapper.remove(id); 
	}
	
}
