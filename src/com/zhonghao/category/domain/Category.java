package com.zhonghao.category.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zhonghao.categorysecond.domain.CategorySecond;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;
	
	private String cname;
	
	// ��Ҫ�ƶ� mappedBy ���ԣ����򽫴������ӱ�
	@OneToMany(targetEntity=CategorySecond.class,fetch=FetchType.EAGER,mappedBy="category",cascade=CascadeType.ALL)
	private Set<CategorySecond> categorySecond= new HashSet<CategorySecond>();

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

	public Set<CategorySecond> getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(Set<CategorySecond> categorySecond) {
		this.categorySecond = categorySecond;
	}

}
