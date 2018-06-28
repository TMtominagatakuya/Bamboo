package jp.tcmobile.bamboo.model;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Authorization {
	/*
	 * ロールの列挙
	 */
	public static enum Role {
		manager, admin, staff
	}

	/*ロール*/
	@Id
	@Enumerated(EnumType.STRING)
	private Role role;

	/*名称*/
	private String name;

	/*ユーザ*/
	@ManyToMany(mappedBy = "authorizationSet")
	private Set<User> userSet; 
}