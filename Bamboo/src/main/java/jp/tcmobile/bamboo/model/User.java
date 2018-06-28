package jp.tcmobile.bamboo.model;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	/*���[�U�[ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;


	/*�A�J�E���g��*/
	@Column(name = "acount_name", length = 30, nullable=false,unique = true)
	private String acountName;

	/*���O*/
	@Column(name = "name", length = 30, nullable=false)
	private String name;
	/*�p�X���[�h*/
	@Column(name = "password", length = 50, nullable=false)
	private String password;

	/*���[���A�h���X*/
	@Column(name = "mailaddress", length = 50, nullable=false, unique = true)
	private String mailAddress;

	/*�ۑ��ID*/
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Article> articleList;

	@OneToOne(mappedBy = "user")
	private Authentication authentication;

	/*�F��*/
	@ManyToMany
	@JoinTable(
			name = "userAuthorization",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "authorizationRole"))
	private Set<Authorization> authorizationSet;
}
