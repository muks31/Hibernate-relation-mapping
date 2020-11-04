package com.mukscode.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mukscode.hibernate.entity.Instructor;
import com.mukscode.hibernate.entity.InstructorDetail;


public class DeleteEntry {

public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try 
		{
			
			//start transaction
			session.beginTransaction();
			
			//get instructor by primary key
			int theId = 29;
			Instructor theInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found Instructor: "+ theInstructor);
			
			//delete the instructor
			if(theInstructor !=null) {
				System.out.println("Deleting: "+theInstructor);
				
				//NOTE: This will also delete Instructor detail cause of cascade
				session.delete(theInstructor);
			}
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		}
		finally 
		{
			factory.close();
		}
		
	}

}
