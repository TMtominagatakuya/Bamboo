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
	 * ���[���̗�
	 */
	public static enum Role {
		manager, admin, staff
	}

	/*���[��*/
	@Id
	@Enumerated(EnumType.STRING)
	private Role role;

	/*����*/
	private String name;

	/*���[�U*/
	@ManyToMany(mappedBy = "authorizationSet")
	private Set<User> userSet; 
}