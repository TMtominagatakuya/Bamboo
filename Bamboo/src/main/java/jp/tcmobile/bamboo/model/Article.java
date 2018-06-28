package jp.tcmobile.bamboo.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "articles",uniqueConstraints = @UniqueConstraint(
		columnNames = {"test_id", "user_id"}))
public class Article {
	// 課題解答情報ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;

	// 問題ID
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "test_id")
	private Test test;

	//ユーザID
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;
			
	// ステータスID
	@Column(name = "status_id", nullable=false, precision = 11)
	private Integer statusId;
}