package com.te.learn.hibernate.two.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpDB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empID;
	private String empName;
	private String empType;
	private String empEmail;
	private String empPwd;
	@OneToMany(mappedBy ="emp",cascade = CascadeType.ALL)
	private List<EmpLeaveInfo> info;
	@Override
	public String toString() {
		return "EmpDB [empID=" + empID + ", empName=" + empName + ", empType=" + empType + ", empEmail=" + empEmail
				+ ", empPwd=" + empPwd + "]";
	}
	
}
