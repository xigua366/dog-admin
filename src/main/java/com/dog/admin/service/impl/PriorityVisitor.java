package com.dog.admin.service.impl;

/**
 * 对权限树进行操作访问者接口
 * @author yangxi
 *
 */
public interface PriorityVisitor<T> { 
 
	/**
	 * 执行这个操作
	 * @param priority 权限
	 * @return 处理结果
	 * @throws Exception
	 */
	T visit(Priority priority) throws Exception;
	
}
