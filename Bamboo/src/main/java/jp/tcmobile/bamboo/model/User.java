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
	/*ユーザーID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;


	/*アカウント名*/
	@Column(name = "acount_name", length = 30, nullable=false,unique = true)
	private String acountName;

	/*名前*/
	@Column(name = "name", length = 30, nullable=false)
	private String name;
	/*パスワード*/
	@Column(name = "password", length = 50, nullable=false)
	private String password;

	/*メールアドレス*/
	@Column(name = "mailaddress", length = 50, nullable=false, unique = true)
	private String mailAddress;

	/*課題解答ID*/
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Article> articleList;

	@OneToOne(mappedBy = "user")
	private Authentication authentication;

	/*認可*/
	@ManyToMany
	@JoinTable(
			name = "userAuthorization",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "authorizationRole"))
	private Set<Authorization> authorizationSet;
}
