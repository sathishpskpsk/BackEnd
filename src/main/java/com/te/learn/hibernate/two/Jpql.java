package com.te.learn.hibernate.two;

import javax.persistence.Query;

import com.te.learn.hibernate.two.dto.EmpDB;
import com.te.learn.hibernate.two.dto.EmpLeaveInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Jpql {
	public void register() {

	}

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU01");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Scanner scanner = new Scanner(System.in);
		int a;
		String b = "", c = "";
		boolean end = true;
		do {
			System.out.println("1. Register Employee");
			System.out.println("2. Login");
			System.out.println("3. Exit");

			System.out.println("Enter the Option:");
			a = scanner.nextInt();

			switch (a) {
			case 1: {
				System.out.println("Enter Employee Name:");
				String name = scanner.next();
				System.out.println("Enter Employee Type:");
				String type = scanner.next();
				System.out.println("Enter Employee EmailID:");
				String email = scanner.next();
				System.out.println("Enter Employee Password:");
				String pwd = scanner.next();

				EmpDB db = new EmpDB();
				db.setEmpName(name);
				db.setEmpType(type);
				db.setEmpEmail(email);
				db.setEmpPwd(pwd);

				entityTransaction.begin();
				entityManager.persist(db);
				entityTransaction.commit();
				System.out.println("Register is Successful Your ID is " + db.getEmpID());
				break;
			}
			case 2: {
				System.out.println("Enter Employee ID:");
				int id = scanner.nextInt();
				System.out.println("Enter Employee Password:");
				String pwd = scanner.next();

				String str = "from EmpDB where empID=:n";
				Query query = entityManager.createQuery(str);
				query.setParameter("n", id);
				List<Object> DB = query.getResultList();
				EmpDB db = (EmpDB) DB.get(0);

				if (db.getEmpID() == id) {
					System.out.println("id pass");
					if (db.getEmpPwd().equals(pwd)) {
						System.out.println("passs");
						if (db.getEmpType().toLowerCase().equals("manager")) {
							boolean stage = true;
							do {
								System.out.println("1.Showe All leave Request ");
								System.out.println("2.Approve/Rejact Leave ");
								System.out.println("3.Exit ");
								System.out.println("Enter The Option:");
								int option = scanner.nextInt();
								switch (option) {
								case 1: {
									String str1 = "from EmpLeaveInfo";
									Query query1 = entityManager.createQuery(str1);
									List resultLists = query1.getResultList();
									for (Object object : resultLists) {
										EmpLeaveInfo info = (EmpLeaveInfo) object;
										entityManager.refresh(info);
									}
									for (Object object : resultLists) {
										EmpLeaveInfo info = (EmpLeaveInfo) object;
										System.out.println(info);
									}
									break;
								}
								case 2: {
									System.out.println("Enter the LeaveID:");
									int lId = scanner.nextInt();
									System.out.println("Enter the LeaveState:");
									String status = scanner.next();
									String str2 = "update EmpLeaveInfo set learveStatus=:s where leaveID=:id";
									Query query2 = entityManager.createQuery(str2);
									query2.setParameter("s", status);
									query2.setParameter("id", lId);
									entityTransaction.begin();
									int status1 = query2.executeUpdate();
									if (status1 < 1) {
										System.out.println("Enter Valid LeaveID....");
									} else {
										System.out.println("Status Updated....");
									}
									entityTransaction.commit();
									break;
								}
								case 3: {
									stage = false;
									break;
								}
								default:
									break;
								}
							} while (stage);
						} else {

							do {
								System.out.println("1.Showe All Ststus");
								System.out.println("2.Request for leave");
								System.out.println("3.Exit");
								System.out.println("Enter Option:");
								int option = scanner.nextInt();
								switch (option) {
								case 1: {
									String str1 = "from EmpLeaveInfo where emp_empID=(from EmpDB where empID=:i)";
									Query query1 = entityManager.createQuery(str1);
									query1.setParameter("i", db.getEmpID());
					
									List resultLists = query1.getResultList();
//									for (Object object : resultLists) {
//										EmpLeaveInfo info = (EmpLeaveInfo) object;
//										entityManager.refresh(info);
//									}
									for (Object object : resultLists) {
										EmpLeaveInfo info = (EmpLeaveInfo) object;
										System.out.println(info);
									}
									
									
									break;
								}
								case 2: {
									List<EmpLeaveInfo> empLeaveInfos=new ArrayList<EmpLeaveInfo>();
									
									String emp="from EmpLeaveInfo";
									Query query1=entityManager.createQuery(emp);
									List resultList = query1.getResultList();
									for (Object object : resultList) {
										EmpLeaveInfo epmleave=(EmpLeaveInfo)object;
										empLeaveInfos.add(epmleave);
									}
									
									System.out.println("Enter Leave Date:");
									String date=scanner.next();
									
									EmpLeaveInfo newemp=new EmpLeaveInfo();
									newemp.setLearveStatus("Pending..");
									newemp.setLeaveDate(date);
									newemp.setEmp(db);
									
									empLeaveInfos.add(newemp);
									entityTransaction.begin();
									entityManager.persist(newemp);
									entityTransaction.commit();
									break;
								}
								case 3: {
									end=false;
									break;
								}
								default:
									break;
								}

							} while (end);
						}
					}
				}
				break;
			}
			case 3: {
				end = false;
				break;
			}

			default:
				System.out.println("Enter valid Option...!!!");
				break;
			}

		} while (end);
	}
}
