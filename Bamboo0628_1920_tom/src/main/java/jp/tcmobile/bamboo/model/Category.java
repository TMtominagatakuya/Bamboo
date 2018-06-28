package jp.tcmobile.bamboo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Getter
@Setter
@Entity
@Table(name="categories")
public class Category {
/*カテゴリーID*/
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Integer id;

/*カテゴリー名*/
@Column(name = "name", length =20, nullable=false)
private String name;

@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
private List<Test> testList;
	
	public List<Test>getTestList() {
		if(testList==null) {
			testList = new ArrayList<Test>();
		}
		return testList;
	}

}