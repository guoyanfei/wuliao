package com.wuliao.dandan.model;

import java.util.Date;

public class Log {

	private Long id;

	private Long storehouseId;

	private String componentName;

	private String username;

	private Long tag;

	private Long quantity;

	private Date createdAt;

	@Override
	public String toString() {
		return "Log [id=" + id + ", storehouseId=" + storehouseId + ", componentName=" + componentName + ", username=" + username + ", tag=" + tag + ", quantity=" + quantity + ", createdAt=" + createdAt + "]";
	}

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStorehouseId() {
		return storehouseId;
	}

	public void setStorehouseId(Long storehouseId) {
		this.storehouseId = storehouseId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getTag() {
		return tag;
	}

	public void setTag(Long tag) {
		this.tag = tag;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Log(Long id, Long storehouseId, String componentName, String username, Long tag, Long quantity, Date createdAt) {
		super();
		this.id = id;
		this.storehouseId = storehouseId;
		this.componentName = componentName;
		this.username = username;
		this.tag = tag;
		this.quantity = quantity;
		this.createdAt = createdAt;
	}

}
