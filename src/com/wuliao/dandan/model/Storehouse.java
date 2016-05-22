package com.wuliao.dandan.model;

public class Storehouse {

	private Long id;

	private String componentName;

	private String producer;

	private String value;

	private String agency;

	private String price;

	private String shortName;

	private String potting;

	private String remark;

	private Long quantity;

	private Long redline;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPotting() {
		return potting;
	}

	public void setPotting(String potting) {
		this.potting = potting;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getRedline() {
		return redline;
	}

	public void setRedline(Long redline) {
		this.redline = redline;
	}

	@Override
	public String toString() {
		return "Storehouse [id=" + id + ", componentName=" + componentName + ", producer=" + producer + ", value=" + value + ", agency=" + agency + ", price=" + price + ", shortName=" + shortName + ", potting=" + potting + ", remark=" + remark + ", quantity=" + quantity + ", redline=" + redline + "]";
	}

	public Storehouse() {
		super();
	}

	public Storehouse(Long id, String componentName, String producer, String value, String agency, String price, String shortName, String potting, String remark, Long quantity, Long redline) {
		super();
		this.id = id;
		this.componentName = componentName;
		this.producer = producer;
		this.value = value;
		this.agency = agency;
		this.price = price;
		this.shortName = shortName;
		this.potting = potting;
		this.remark = remark;
		this.quantity = quantity;
		this.redline = redline;
	}

}
