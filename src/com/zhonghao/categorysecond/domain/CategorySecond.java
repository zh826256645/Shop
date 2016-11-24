package com.zhonghao.categorysecond.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zhonghao.category.domain.Category;
import com.zhonghao.product.domain.Product;

@Entity
@Table(name="categorysecond")
public class CategorySecond {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer csid;
	
	private String csname;
	
	@ManyToOne(targetEntity=Category.class,fetch=FetchType.LAZY)
	@JoinColumn(name="cid",referencedColumnName="cid",nullable=false)
	private Category category;
	
	@OneToMany(targetEntity=Product.class,mappedBy="categorySecond",cascade=CascadeType.ALL)
	private Set<Product> product = new HashSet<Product>();

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
