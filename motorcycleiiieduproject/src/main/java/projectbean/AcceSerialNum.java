package projectbean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"acceType"} ) })
public class AcceSerialNum {
	
	private String acceSerialNum;
	private String acceType  ;
	


	@Id
    @GeneratedValue(generator="acceid")
    @GenericGenerator(name="acceid",strategy="assigned") 
	public String getAcceSerialNum() {
		return acceSerialNum;
	}
	public void setAcceSerialNum(String acceSerialNum) {
		this.acceSerialNum = acceSerialNum;
	}
	public String getAcceType() {
		return acceType;
	}
	public void setAcceType(String acceType) {
		this.acceType = acceType;
	}
	

}
