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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "choices")
public class Choice {
	/*選択肢ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;
	/*選択肢内容*/
	@Column(name = "content", nullable=false, length = 255)
	//@NotEmpty
	private String content;

	/*正解選択肢*/
	@Column(name = "is_correct", nullable=false)
	private byte is_correct;

	/*問題ID*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "problem_id")
	private Problem problem;

}