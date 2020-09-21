package com.dog.admin.dao.impl;

import com.dog.admin.dao.RoleDAO;
import com.dog.admin.domain.RoleDO;
import com.dog.admin.domain.RoleQuery;
import com.dog.admin.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色管理模块DAO组件
 * @author zhonghuashishan
 *
 */
@Repository
public class RoleDAOImpl implements RoleDAO {
	
	/**
	 * 角色管理模块mapper组件
	 */
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 分页查询角色
	 * @param query 查询条件
	 * @return 角色DO对象集合
	 */
	@Override
	public List<RoleDO> listByPage(RoleQuery query) throws Exception {
		return roleMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询角色DO对象
	 * @param id 角色 id 
	 * @return 角色DO对象
	 */
	@Override
	public RoleDO getById(Long id) throws Exception {
		return roleMapper.getById(id);
	}
	
	/**
	 * 新增角色
	 * @param role 角色DO对象
	 */
	@Override
	public Long save(RoleDO role) throws Exception {
		roleMapper.save(role);
		return role.getId();
	}
	
	/**
	 * 更新角色
	 * @param role 角色DO对象
	 */
	@Override
	public void update(RoleDO role) throws Exception {
		roleMapper.update(role); 
	}
	
	/**
	 * 删除角色
	 * @param id 角色id
	 */
	@Override
	public void remove(Long id) throws Exception {
		roleMapper.remove(id);
	}

}
