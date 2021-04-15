
package com.cg.stockapp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "company")
public class Company {
    
    @Id
    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId;
    
    @Column(name = "COMPANY_NAME")
    @Size(min = 4, max = 25)
    private String companyName;
    
    @Column(name = "CATEGORY")
//    @Size(min = 3, max = 7, message = "Invalid category")
    private String category;
    
    @OneToMany
    private List<Stock> stocks = new ArrayList<>();
    
	public Company(String companyId, String companyName, String category) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.category = category;
	}
	public Company() {
		super();
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", category=" + category + "]";
	}
    
}