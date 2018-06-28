package jp.tcmobile.bamboo.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "descriptions")
public class Description {
	/*記述ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;

	/*記述答案*/
	@Column(name = "description_answer", nullable=false)
	//@NotEmpty
	private String description_answer;
	
	/*問題ID*/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "problem_id")
	private Problem problem;
}