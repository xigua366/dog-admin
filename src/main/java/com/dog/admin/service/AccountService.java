package com.dog.admin.service;


import com.dog.admin.domain.AccountDTO;
import com.dog.admin.domain.AccountQuery;

import java.util.List;

/**
 * 账号管理service组件接口
 * @author yangxi
 *
 */
public interface AccountService {

	/**
	 * 分页查询账号
	 * @param query 查询条件
	 * @return 账号
	 * @throws Exception
	 */
	List<AccountDTO> listByPage(AccountQuery query) throws Exception;
	
	/**
	 * 根据id查询账号
	 * @param id 账号id
	 * @return 账号
	 * @throws Exception
	 */
	AccountDTO getById(Long id) throws Exception;

	/**
	 * 根据username查询账号
	 * @param username 账号名
	 * @return 账号
	 * @throws Exception
	 */
	AccountDTO getByUsername(String username) throws Exception;
 	
	/**
	 * 新增账号
	 * @param account 账号
	 * @return 处理结果
	 * @throws Exception
	 */
	void save(AccountDTO account) throws Exception;
	
	/**
	 * 更新账号
	 * @param account 账号
	 * @return 处理结果
	 * @throws Exception
	 */
	void update(AccountDTO account) throws Exception;
	
	/**
	 * 更新密码
	 * @param account 账号
	 * @throws Exception
	 */
	void updatePassword(AccountDTO account) throws Exception;
	
	/**
	 * 删除账号
	 * @param id 账号id
	 * @return 处理结果
	 * @throws Exception
	 */
	void remove(Long id) throws Exception;
	
}
