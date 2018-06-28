package jp.tcmobile.bamboo.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authentication {
	
	@Id
	private String id;
	private String password;
	private java.sql.Date validDate;
	@OneToOne(optional = false)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
}