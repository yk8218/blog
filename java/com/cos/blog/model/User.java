package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



 // User 클래스가 MySQL에 테이블이 생성이 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert// insert시에 null인 필드 제외
public class User {
   
	
	@Id//Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	//unique(아이디중복안되게)
	
	@Column(nullable = false, length=100, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length=100)
	private String password;
	    
	@Column(nullable = false, length=50)
	private String email;
	
	/* @ColumnDefault("'user'") */
	@Enumerated(EnumType.STRING)
	private RoleType role;// Enum을 쓰는게 좋음 // ADMIN, USER
	
	private String oauth; //kakao, google로 가입한사람은 kakao, google 로 들어가게함
	
	private String rule;
	
	private int Gid;
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
