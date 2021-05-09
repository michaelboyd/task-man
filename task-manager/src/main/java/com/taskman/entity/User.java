package com.taskman.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userTasks")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
   
//    @OneToMany(mappedBy = "pk.user")
//    @Valid
//    private List<UserTask> userTasks = new ArrayList<>(); 
    
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Task> tasks;    
    
    @NotNull(message = "User name is required.")
    @Basic(optional = false)
    private String name;
    
    @JsonFormat(pattern = "dd/MM/yyyy") 
    private LocalDate createDate;
    
    @JsonFormat(pattern = "dd/MM/yyyy") 
    private LocalDate updateDate;
    
	public User() {
		super();
	}

	public User(@NotNull(message = "User name is required.") String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public List<UserTask> getUserTasks() {
//		return userTasks;
//	}
//
//	public void setUserTasks(List<UserTask> userTasks) {
//		this.userTasks = userTasks;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

 
    
    

}
