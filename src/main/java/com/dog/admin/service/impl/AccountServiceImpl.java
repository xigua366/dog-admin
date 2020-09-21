package com.dog.admin.service.impl;

import com.dog.admin.dao.AccountDAO;
import com.dog.admin.dao.AccountPriorityRelationshipDAO;
import com.dog.admin.dao.AccountRoleRelationshipDAO;
import com.dog.admin.domain.*;
import com.dog.admin.service.AccountService;
import org.dogframework.boot.commons.utils.DateProvider;
import org.dogframework.boot.commons.utils.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账号管理service组件
 * @author zhonghuashishan
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

	/**
	 * 账号管理DAO组件
	 */
	@Autowired
	private AccountDAO accountDAO;
	/**
	 * 账号和角色关系管理DAO组件
	 */
	@Autowired
	private AccountRoleRelationshipDAO roleRelationDAO;
	/**
	 * 账号和权限关系管理DAO组件
	 */
	@Autowired
	private AccountPriorityRelationshipDAO priorityRelationDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 权限缓存管理组件
	 */
	@Autowired
	private PriorityCacheManager priorityCacheManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 分页查询账号
	 * @param query 查询条件
	 * @return 账号
	 */
	@Override
	public List<AccountDTO> listByPage(AccountQuery query) throws Exception {
		List<AccountDO> accounts = accountDAO.listByPage(query);
		List<AccountDTO> resultAccounts = ObjectCloneUtils.convertList(
				accounts, AccountDTO.class);
		return resultAccounts;
	}
	
	/**
	 * 根据id查询账号
	 * @param id 账号id
	 * @return 账号
	 */
	@Override
	public AccountDTO getById(Long id) throws Exception {
		AccountDO account = accountDAO.getById(id);
		AccountDTO resultAccount = account.clone(AccountDTO.class);
		
		List<AccountRoleRelationshipDO> roleRelations =
				roleRelationDAO.listByAccountId(id);
		List<AccountRoleRelationshipDTO> resultRoleRelations =
				ObjectCloneUtils.convertList(roleRelations, AccountRoleRelationshipDTO.class);
		resultAccount.setRoleRelations(resultRoleRelations); 
		
		List<AccountPriorityRelationshipDO> priorityRelations = 
				priorityRelationDAO.listByAccountId(id);
		List<AccountPriorityRelationshipDTO> resultPriorityRelations =
				ObjectCloneUtils.convertList(priorityRelations, AccountPriorityRelationshipDTO.class);
		resultAccount.setPriorityRelations(resultPriorityRelations); 
		
		return resultAccount;
	}

	/**
	 * 根据username查询账号
	 *
	 * @param username 账号名
	 * @return 账号
	 * @throws Exception
	 */
	@Override
	public AccountDTO getByUsername(String username) throws Exception {
		AccountDO account = accountDAO.getByUsername(username);
		if(account == null) {
			return null;
		}
		AccountDTO resultAccount = account.clone(AccountDTO.class);
		return resultAccount;
	}

	/**
	 * 新增账号
	 * @param account 账号
	 * @return 处理结果
	 */
	@Override
	public void save(AccountDTO account) throws Exception {
		account.setGmtCreate(dateProvider.getCurrentTime());
		account.setGmtModified(dateProvider.getCurrentTime());

		// 密码加密
		String password = passwordEncoder.encode(account.getPassword());
		account.setPassword(password);

		Long accountId = accountDAO.save(account.clone(AccountDO.class));
		
		account.setId(accountId);
	
		saveRelations(account); 
	}
	
	/**
	 * 更新账号
	 * @param account 账号
	 * @return 处理结果
	 */
	@Override
	public void update(AccountDTO account) throws Exception {
		account.setGmtModified(dateProvider.getCurrentTime());
		accountDAO.update(account.clone(AccountDO.class));  
		
		roleRelationDAO.removeByAccountId(account.getId()); 
		priorityRelationDAO.removeByAccountId(account.getId()); 
		
		saveRelations(account); 
		
		priorityCacheManager.remove(account.getId()); 
	}
	
	/**
	 * 更新密码
	 * @param account 账号
	 */
	@Override
	public void updatePassword(AccountDTO account) throws Exception {
		account.setGmtModified(dateProvider.getCurrentTime()); 
		accountDAO.updatePassword(account.clone(AccountDO.class));   
	}
	
	/**
	 * 新增关联关系
	 * @param account 账号
	 * @throws Exception
	 */
	private void saveRelations(AccountDTO account) throws Exception {
		for(AccountRoleRelationshipDTO roleRelation : account.getRoleRelations()) { 
			roleRelation.setAccountId(account.getId()); 
			roleRelation.setGmtCreate(dateProvider.getCurrentTime()); 
			roleRelation.setGmtModified(dateProvider.getCurrentTime()); 
			roleRelationDAO.save(roleRelation.clone(AccountRoleRelationshipDO.class));  
		}
		
		for(AccountPriorityRelationshipDTO priorityRelation : account.getPriorityRelations()) { 
			priorityRelation.setAccountId(account.getId()); 
			priorityRelation.setGmtCreate(dateProvider.getCurrentTime()); 
			priorityRelation.setGmtModified(dateProvider.getCurrentTime()); 
			priorityRelationDAO.save(priorityRelation.clone(AccountPriorityRelationshipDO.class));  
		}
	}
	
	/**
	 * 删除账号
	 * @param id 账号id
	 * @return 处理结果
	 */
	@Override
	public void remove(Long id) throws Exception {
		roleRelationDAO.removeByAccountId(id); 
		priorityRelationDAO.removeByAccountId(id); 
		accountDAO.remove(id); 
		priorityCacheManager.remove(id);  
	}
	
}
