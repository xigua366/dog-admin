package com.dog.admin.service.impl;

import lombok.Data;
import org.dogframework.boot.commons.util.AbstractObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 权限
 * @author yangxi
 *
 */
@Data
public class Priority extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 权限编号
	 */
	private String code;
	/**
	 * 权限URL
	 */
	private String url;
	/**
	 * 权限备注
	 */
	private String priorityComment;
	/**
	 * 权限类型
	 */
	private Integer priorityType;
	/**
	 * 父权限id
	 */
	private Long parentId;
	/**
	 * 权限的创建时间
	 */
	private Date gmtCreate;
	/**
	 * 权限的修改时间
	 */
	private Date gmtModified;
	/**
	 * 子权限节点
	 */
	private List<Priority> children = new ArrayList<Priority>();
	
	/**
	 * 接收一个权限树访问者
	 * @param visitor 权限树访问者
	 */
	public <T> T accept(PriorityVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
	
}
