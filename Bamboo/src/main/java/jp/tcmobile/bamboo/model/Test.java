package jp.tcmobile.bamboo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Getter
@Setter
@Entity
@Table(name="tests")
public class Test {
	/*テストID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	/*テスト名*/
	@Column(name = "name", length =50, nullable=false)
	@NotEmpty(message = "テスト名は必ず入力してください。")
	private String name;

	/*削除フラグ*/
	@Column(name = "is_deleted", nullable=false)
	private byte isDeleted;

	/*カテゴリーID*/
	//MERGEを指定することで、毎回新しく登録しない。
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id")
	private Category category;

	/*問題ID*/
	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
	private List<Problem> problemList;

	/*課題解答ID*/
	@OneToMany(mappedBy = "test", cascade = CascadeType.REMOVE)
	private List<Article> articlelist;

	public List<Problem> getProblemList(){
		if(problemList==null) {
			problemList = new ArrayList<Problem>();
		}
		return problemList;
	}

}