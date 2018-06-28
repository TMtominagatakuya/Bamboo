//package jp.tcmobile.bamboo.model;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "users_answers")
//public class UserAnswer {
//	
//	/*問題ID*/
//
//	@Column(name = "problem_id", nullable = false, precision = 11)
//	private Integer problem_id;
//	
//	/*ユーザーID*/
//	@Column(name = "user_id", nullable = false, precision = 11)
//	private Integer user_id;
//	
//	/*選択肢ID*/
//	@Column(name = "choice_id", nullable = true, precision = 11)
//	private Integer choice_id;
//	
//	/*記述答案*/
//	@Column(name = "description", nullable=true)
//	private String description;
//	
//
//}