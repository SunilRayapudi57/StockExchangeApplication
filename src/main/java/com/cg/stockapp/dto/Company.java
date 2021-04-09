
package com.cg.stockapp.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
    
    @Id
    private String companyId;
    private String companyName;
    private String category;
    @OneToOne(mappedBy="company")
    private Manager manager;
    @OneToOne(mappedBy="companyId")
    private Stock stock;
	public Company(String companyId, String companyName, String category) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.category = category;
	}
	public Company() {}
	
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