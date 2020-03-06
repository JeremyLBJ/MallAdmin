package com.lhd.sys.API.apiEntity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;

import io.swagger.annotations.ApiModelProperty;

public class GoodsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value="页数",name="page")
	private Integer page ;
	
	@ApiModelProperty(value="行数",name="limit")
	private Integer limit ;
	
	 @ApiModelProperty(value="描述" ,name="detail")
	 private String detail;
	
	
	  //第一个价格
	  @ApiModelProperty(value="最低价格" ,name="priceOne")
	  @TableField(exist=false)
	  private Double priceOne ;
	    
	  //第二个价格
	  @ApiModelProperty(value="最高价格" ,name="priceTow")
	  @TableField(exist=false)
	  private Double priceTow ; 
	  
	  @ApiModelProperty(value="链接商品图片" ,name="cid")
	  private Integer cid;
	  
	  @ApiModelProperty(value="商品名字" ,name="cname")
	  private String cname;

	  @ApiModelProperty(value="商品库存" ,name="inventory")
	  private Double inventory;
	    
	  @ApiModelProperty(value="商品价格" ,name="price")
	  private Double price;

	    
	  @ApiModelProperty(value="商品品牌" ,name="brand")
	  private String brand;


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getLimit() {
		return limit;
	}


	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public Double getPriceOne() {
		return priceOne;
	}


	public void setPriceOne(Double priceOne) {
		this.priceOne = priceOne;
	}


	public Double getPriceTow() {
		return priceTow;
	}


	public void setPriceTow(Double priceTow) {
		this.priceTow = priceTow;
	}


	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public Double getInventory() {
		return inventory;
	}


	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	@Override
	public String toString() {
		return "GoodsEntity [page=" + page + ", limit=" + limit + ", detail=" + detail + ", priceOne=" + priceOne
				+ ", priceTow=" + priceTow + ", cid=" + cid + ", cname=" + cname + ", inventory=" + inventory
				+ ", price=" + price + ", brand=" + brand + "]";
	}
	  

}
