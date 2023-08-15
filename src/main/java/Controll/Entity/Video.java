package Controll.Entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Video")
public class Video {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "href")
	private String href;

	@Column(name = "poster")
	private String poster;

	@Column(name = "`views`")
	private Integer views;

	@Column(name = "shares")
	private Integer shares;

	@Column(name = "description")
	private String description;

	@Column(name = "daodien")
	private String daodien;

	@Column(name = "dienvien")
	private String dienvien;

	@Column(name = "theloai")
	private String theloai;

	@Column(name = "mota")
	private String mota;

	@Column(name = "price")
	private int price;

	@Column(name = "isActive")
	private Boolean isActive;

	@Column(name = "addDate")
	private Timestamp addDate;

	@OneToMany(mappedBy = "video")
	private List<Hoadon> hoadon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getShares() {
		return shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDaodien() {
		return daodien;
	}

	public void setDaodien(String daodien) {
		this.daodien = daodien;
	}

	public String getDienvien() {
		return dienvien;
	}

	public void setDienvien(String dienvien) {
		this.dienvien = dienvien;
	}

	public String getTheloai() {
		return theloai;
	}

	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getAddDate() {
		return addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public List<Hoadon> getHoadon() {
		return hoadon;
	}

	public void setHoadon(List<Hoadon> hoadon) {
		this.hoadon = hoadon;
	}

}
