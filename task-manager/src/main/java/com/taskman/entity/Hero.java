package com.taskman.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Hero {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
   
    @NotNull(message = "User name is required.")
    @Basic(optional = false)
    private String name;
    
	public Hero() {
		super();
		name = "unknown";
	}

	public Hero(@NotNull(message = "User name is required.") String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hero [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	
    

}
