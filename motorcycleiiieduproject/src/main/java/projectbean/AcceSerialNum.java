package projectbean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"acceType"} ) })
public class AcceSerialNum implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String acceSerialNum;
	private String acceType ;

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
