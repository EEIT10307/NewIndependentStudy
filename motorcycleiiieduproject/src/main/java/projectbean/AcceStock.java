package projectbean;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "acceName" }) })
public class AcceStock implements Serializable {

	private static final long serialVersionUID = 1L;
	private String acceStockSerialNum;
	private String acceName;
	private BranchDetail branchName;
	private Integer acceNum;
	private AcceSerialNum acceType;
	private Integer acceePrice;

	public AcceStock() {

	}

	public AcceStock(String acceStockSerialNum, String acceName, BranchDetail branchName, Integer acceNum,
			AcceSerialNum acceType, Integer acceePrice) {
		super();
		this.acceStockSerialNum = acceStockSerialNum;
		this.acceName = acceName;
		this.branchName = branchName;
		this.acceNum = acceNum;
		this.acceType = acceType;
		this.acceePrice = acceePrice;
	}

	@Id
	@GeneratedValue(generator = "assid")
	@GenericGenerator(name = "assid", strategy = "assigned")
	public String getAcceStockSerialNum() {
		return acceStockSerialNum;
	}

	public void setAcceStockSerialNum(String acceStockSerialNum) {
		this.acceStockSerialNum = acceStockSerialNum;
	}

	@Column(nullable = false)
	public String getAcceName() {
		return acceName;
	}

	public void setAcceName(String acceName) {
		this.acceName = acceName;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName = "branchName", nullable = false)

	public BranchDetail getBranchName() {
		return branchName;
	}

	public void setBranchName(BranchDetail branchName) {
		this.branchName = branchName;
	}

	@Column(nullable = false)
	public Integer getAcceNum() {
		return acceNum;
	}

	public void setAcceNum(Integer acceNum) {
		this.acceNum = acceNum;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName = "acceType", nullable = false)
	public AcceSerialNum getAcceType() {
		return acceType;
	}

	public void setAcceType(AcceSerialNum acceType) {
		this.acceType = acceType;
	}

	@Column(nullable = false)
	public Integer getAcceePrice() {
		return acceePrice;
	}

	public void setAcceePrice(Integer acceePrice) {
		this.acceePrice = acceePrice;
	}



}
