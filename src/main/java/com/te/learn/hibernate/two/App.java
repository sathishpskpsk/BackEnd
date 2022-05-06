package com.te.learn.hibernate.two;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.te.learn.hibernate.two.dto.EmpDB;
import com.te.learn.hibernate.two.dto.EmpLeaveInfo;


public class App {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU01");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		//Employee Info
		EmpDB db=new EmpDB();
		db.setEmpName("Sathish");
		db.setEmpEmail("sathish@gamil.com");
		db.setEmpPwd("1234");
		db.setEmpType("Manager");
		
		EmpDB db1=new EmpDB();
		db1.setEmpName("PSK");
		db1.setEmpEmail("PSK@gamil.com");
		db1.setEmpPwd("1234");
		db1.setEmpType("Employee");
		
		ArrayList<EmpDB> dbs=new ArrayList<EmpDB>();
		dbs.add(db);
		dbs.add(db1);
		
		

		entityTransaction.begin();
		entityManager.persist(db);
		entityManager.persist(db1);
		entityTransaction.commit();

	}
}
