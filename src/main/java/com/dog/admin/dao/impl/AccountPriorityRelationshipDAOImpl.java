package com.dog.admin.dao.impl;

import com.dog.admin.dao.AccountPriorityRelationshipDAO;
import com.dog.admin.domain.AccountPriorityRelationshipDO;
import com.dog.admin.mapper.AccountPriorityRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号和权限关系管理模块的DAO组件
 * @author yangxi
 *
 */
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {
	
	/**
	 * 账号和权限关系管理模块的mapper组件
	 */
	@Autowired
	private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;
	
	/**
	 * 根据权限id查询记录数
	 * @param priorityId 权限id
	 * @return 记录数
	 */
	@Override
	public Long countByPriorityId(Long priorityId) {
		return accountPriorityRelationshipMapper.countByPriorityId(priorityId); 
	}
	
	/**
	 * 根据账号id查询账号和权限的关联关系
	 * @param accountId 账号id
	 * @return 账号和权限的关联关系
	 */
	@Override
	public List<AccountPriorityRelationshipDO> listByAccountId(Long accountId) {
		return accountPriorityRelationshipMapper.listByAccountId(accountId);
	}
	
	/**
	 * 新增账号和权限的关联关系
	 * @param accountPriorityRelationshipDO
	 */
	@Override
	public void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO) {
		accountPriorityRelationshipMapper.save(accountPriorityRelationshipDO); 
	}
	
	/**
	 * 根据账号id删除账号和权限的关联关系
	 * @param accountId 账号id
	 */
	@Override
	public void removeByAccountId(Long accountId) {
		accountPriorityRelationshipMapper.removeByAccountId(accountId);
	}

}
