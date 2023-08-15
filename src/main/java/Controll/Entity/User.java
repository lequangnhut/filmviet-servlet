package Controll.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import Controll.Contanst.NamedStored;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = NamedStored.STORED_FIND_USER_LIKED_VIDEO_BY_VIDEO_HREF,
                procedureName = "SP_SelectUsersLikedVideoByVideoHref",
                resultClasses = {User.class},
                parameters = @StoredProcedureParameter(name = "videoHref", type = String.class)
        ),
        @NamedStoredProcedureQuery(
                name = NamedStored.STORED_FIND_USER_SHARE_VIDEO_BY_HREF,
                procedureName = "SP_UserShareVideoByHref",
                resultClasses = {User.class},
                parameters = @StoredProcedureParameter(name = "videoHref", type = String.class)
        ),
        @NamedStoredProcedureQuery(
                name = NamedStored.STORED_FIND_USER_PAYMENT_VNPAY_BY_HREF,
                procedureName = "SP_PaymentVnpay",
                resultClasses = {User.class},
                parameters = @StoredProcedureParameter(name = "videoHref", type = String.class)
        )
})

@Entity
@Table(name = "`User`")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`password`")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "isAdmin")
    private Boolean isAdmin;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "token")
    private String token;
    
    @OneToMany(mappedBy = "user")
    private List<Share> share;
    
    @OneToMany(mappedBy = "user")
    private List<Hoadon> hoadon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return fullname;
    }

    public void setName(String name) {
        this.fullname = name;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<Share> getShare() {
		return share;
	}

	public void setShare(List<Share> share) {
		this.share = share;
	}

	public List<Hoadon> getHoadon() {
		return hoadon;
	}

	public void setHoadon(List<Hoadon> hoadon) {
		this.hoadon = hoadon;
	}
	
}
