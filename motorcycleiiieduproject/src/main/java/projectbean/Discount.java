package projectbean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"discountName"}) })
public class Discount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer discountSerialNum ; 
	private String discountName; 
	private Double discountParameter ;  
	private String remark ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getDiscountSerialNum() {
		return discountSerialNum;
	}
	public void setDiscountSerialNum(Integer discountSerialNum) {
		this.discountSerialNum = discountSerialNum;
	}
	@Column(nullable = false)
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	@Column(nullable = false)
	public Double getDiscountParameter() {
		return discountParameter;
	}
	public void setDiscountParameter(Double discountParameter) {
		this.discountParameter = discountParameter;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
