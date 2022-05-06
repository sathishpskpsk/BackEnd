package com.te.learn.hibernate.two.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmpLeaveInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveID;
	private String leaveDate;
	private String learveStatus;
	@ManyToOne
	private EmpDB emp;
	@Override
	public String toString() {
		return "EmpLeaveInfo [leaveID=" + leaveID + ", leaveDate=" + leaveDate + ", learveStatus=" + learveStatus + "]";
	}
	

}
