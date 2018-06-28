package jp.tcmobile.bamboo.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "problems")
public class Problem {
	/*問題ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer id;

	/*問題文*/
	@Column(name = "question", nullable=false)
	//@NotEmpty
	@Lob//文字数の多い文字列のマッピング
	private String question;

	/*問題形式ID*/
	@Column(name = "type_id", nullable=false, precision = 11)
	private byte typeId;

	/*テストID*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "test_id")
	private Test test;

	/*問題ID*/
	@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
	private List<Choice> choiceList;

	/*記述*/
	@OneToOne(mappedBy = "problem", cascade = CascadeType.ALL)
	//@NotEmpty
	private Description description;

	public List<Choice> getChoiceList(){
		if(choiceList==null) {
			choiceList = new ArrayList<Choice>();
		}
		return choiceList;
	}
}