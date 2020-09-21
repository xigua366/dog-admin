package com.dog.admin.service.impl;

import com.dog.admin.dao.PriorityDAO;
import com.dog.admin.domain.PriorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 删除权限操作
 * @author yangxi
 *
 */
@Component
@Scope("prototype")
public class RemovePriorityVisitor implements PriorityVisitor<Boolean> {

	/**
	 * 权限管理模块的DAO组件
	 */
	@Autowired
	private PriorityDAO priorityDAO;
	
	/**
	 * 访问权限树节点
	 * @param node 权限树节点
	 */
	@Override
	public Boolean visit(Priority node) throws Exception {

		// 如果存在子节点，先设置权限树子节点也绑定当前访问者对象
		List<PriorityDO> priorityDOs = priorityDAO.listChildPriorities(node.getId());
		if(priorityDOs != null && !priorityDOs.isEmpty()) {
			for(PriorityDO priorityDO : priorityDOs) {
				Priority priorityNode = priorityDO.clone(Priority.class);
				priorityNode.accept(this);
			}
		}

		// 移除当前权限树节点
		removePriority(node);
		
		return true;
	}
	
	/**
	 * 删除权限
	 * @param node 权限树节点
	 */
	private void removePriority(Priority node) throws Exception {
		priorityDAO.removePriority(node.getId());
	}

}
