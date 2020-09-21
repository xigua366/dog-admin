package com.dog.admin.dao.impl;

import com.dog.admin.dao.RolePriorityRelationshipDAO;
import com.dog.admin.domain.RolePriorityRelationshipDO;
import com.dog.admin.mapper.RolePriorityRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色和权限关系管理模块的DAO组件
 * @author zhonghuashishan
 *
 */
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO {
	
	/**
	 * 角色和权限关系管理模块的mapper组件
	 */
	@Autowired
	private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;
	
	/**
	 * 新增账号和权限的关联关系
	 * @param rolePriorityRelationshipDO
	 */
	@Override
	public void save(RolePriorityRelationshipDO rolePriorityRelationshipDO) throws Exception {
		rolePriorityRelationshipMapper.save(rolePriorityRelationshipDO); 
	}
	
	/**
	 * 根据权限id查询记录数
	 * @param priorityId 权限id
	 * @return 记录数
	 */
	@Override
	public Long countByPriorityId(Long priorityId) throws Exception {
		return rolePriorityRelationshipMapper.countByPriorityId(priorityId); 
	}
	
	/**
	 * 根据角色id查询角色和权限的关系
	 * @param roleId 角色id
	 * @return 角色权限关系DO对象集合
	 */
	@Override
	public List<RolePriorityRelationshipDO> listByRoleId(Long roleId) throws Exception {
		return rolePriorityRelationshipMapper.listByRoleId(roleId);
	}
	
	/**
	 * 根据角色id删除角色权限关联关系
	 * @param roleId 角色id
	 */
	@Override
	public void removeByRoleId(Long roleId) throws Exception {
		rolePriorityRelationshipMapper.removeByRoleId(roleId);
	}

}
