package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)// 프로젝트에서 연결된 db의 넘버링 전략을 따라감
    private int id; //auto_increment
	
	@Column(nullable = false,length = 200)
	private String content;
	
	private String username;
	
	@ManyToOne
	@JoinColumn(name="boardid")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	
	@CreationTimestamp
	private Timestamp createDate;
}
