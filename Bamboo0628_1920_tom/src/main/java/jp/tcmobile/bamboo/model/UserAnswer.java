package jp.tcmobile.bamboo.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_answers",uniqueConstraints = @UniqueConstraint(
		columnNames = {"problem_id", "user_id"}))
public class UserAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;
	
	/*問題ID*/
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "problem_id")
	private Problem problem;
	
	/*ユーザーID*/
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;
	
	/*選択肢ID*/
	@Column(name = "choice_id", nullable = true, precision = 11)
	private Integer choice_id;
	
	/*記述答案*/
	@Column(name = "description", nullable=true)
	private String description;
	

}