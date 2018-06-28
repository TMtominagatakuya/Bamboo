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
	 * ロール
	 */
	public static enum Role {
		manager, admin, staff
	}

	/*認可*/
	@Id
	@Enumerated(EnumType.STRING)
	private Role role;

	/*アカウント名*/
	private String name;

	/*ユーザセット*/
	@ManyToMany(mappedBy = "authorizationSet")
	private Set<User> userSet; 
}