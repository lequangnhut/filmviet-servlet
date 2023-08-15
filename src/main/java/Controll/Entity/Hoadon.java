package Controll.Entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Hoadon")
public class Hoadon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String vnp_TxnRef;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "videoId", referencedColumnName = "id")
	private Video video;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	private String vnp_OrderInfo;

	private String vnp_ResponseCode;

	private String vnp_TransactionNo;

	private String vnp_BankCode;

	private String vnp_Amount;

	private Timestamp vnp_PayDate;

	public String getVnp_TxnRef() {
		return vnp_TxnRef;
	}

	public void setVnp_TxnRef(String vnp_TxnRef) {
		this.vnp_TxnRef = vnp_TxnRef;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVnp_OrderInfo() {
		return vnp_OrderInfo;
	}

	public void setVnp_OrderInfo(String vnp_OrderInfo) {
		this.vnp_OrderInfo = vnp_OrderInfo;
	}

	public String getVnp_ResponseCode() {
		return vnp_ResponseCode;
	}

	public void setVnp_ResponseCode(String vnp_ResponseCode) {
		this.vnp_ResponseCode = vnp_ResponseCode;
	}

	public String getVnp_TransactionNo() {
		return vnp_TransactionNo;
	}

	public void setVnp_TransactionNo(String vnp_TransactionNo) {
		this.vnp_TransactionNo = vnp_TransactionNo;
	}

	public String getVnp_BankCode() {
		return vnp_BankCode;
	}

	public void setVnp_BankCode(String vnp_BankCode) {
		this.vnp_BankCode = vnp_BankCode;
	}

	public String getVnp_Amount() {
		return vnp_Amount;
	}

	public void setVnp_Amount(String vnp_Amount) {
		this.vnp_Amount = vnp_Amount;
	}

	public Timestamp getVnp_PayDate() {
		return vnp_PayDate;
	}

	public void setVnp_PayDate(Timestamp vnp_PayDate) {
		this.vnp_PayDate = vnp_PayDate;
	}

}
