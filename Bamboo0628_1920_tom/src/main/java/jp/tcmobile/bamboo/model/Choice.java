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
	/*驕ｸ謚櫁いID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;
	/*驕ｸ謚櫁い蜀�螳ｹ*/
	@Column(name = "content", nullable=false, length = 255)
	//@NotEmpty
	private String content;

	/*驕ｸ謚櫁い豁｣隗｣繝輔Λ繧ｰ*/
	@Column(name = "is_correct", nullable=false)
	private byte is_correct;

	/*蝠城｡栗D*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "problem_id")
	private Problem problem;

}