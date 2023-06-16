package com.company.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@Column(name = "contegory_id", length = 36, nullable = false, updatable = false)
	private String id;

	@Column(nullable = false, length = 150, unique = true)
	private String name;
	
	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "boolean default true")
	private Boolean enabled;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at", updatable = false)
	private Date createdAt;
	
	public void setEnabled(Boolean enabled) {
		if( enabled != null) {
			this.enabled = enabled;
			
		}else {
			this.enabled = true;
		}
		
	}
}
