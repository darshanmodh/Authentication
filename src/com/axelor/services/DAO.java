package com.axelor.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.axelor.hibernate.HiberUtils;
import com.axelor.model.User;


public class DAO {
	
	public void insert(User user) throws Exception {
		
		Session session = HiberUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
	
	public int update(User user) throws Exception {
		
		Session session = HiberUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(User.class);
		cri = cri.add(Restrictions.eq( "userName", user.getUserName() ) );
		List<User> list = cri.list();
		int rows = list.size();
		Iterator<User> itr = list.iterator();
		
		if( itr.hasNext() ) {
			User us = itr.next();
			us.setName(user.getName());
			us.setMobile(user.getMobile());
			us.setEmail(user.getEmail());
			us.setCity(user.getCity());
			us.setBirthDate(user.getBirthDate());
			session.update(us);
			
			
		}	
		session.getTransaction().commit();
		
       	return rows;
	}
	
	public String getDbPassword(String userName) throws Exception {
		
		String dbPassword = null;
		User us = null;
		
		Session session = HiberUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//The above query can also be achieved with Criteria & Restrictions API.
		Criteria cri = session.createCriteria(User.class);
		cri = cri.add(Restrictions.eq("userName", userName));
		List<User> list = cri.list();
		Iterator<User> itr = list.iterator();
		
		if( itr.hasNext() ) {
			us = itr.next();
		}				
		session.getTransaction().commit();		
		dbPassword = us.getPassword();
		
		return dbPassword;
	}
	
	public User retrive(String userName) throws Exception {
		
		User user = null;
		Session session = HiberUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(User.class);
		cri = cri.add(Restrictions.eq("userName", userName));
		List<User> list = cri.list();
		Iterator<User> itr = list.iterator();
		
		if( itr.hasNext() ) {
			user = itr.next();
		}				
		session.getTransaction().commit();		
		
		return user;
        

	}
		
    public boolean check(String columnName, String columnValue) throws Exception {
		 
		 	boolean checkFlag = false;
		 
		 	Session session = HiberUtils.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria cri = session.createCriteria(User.class);
			cri = cri.add(Restrictions.eq(columnName, columnValue));
			List<User> list = cri.list();
			int rows = list.size();
			session.getTransaction().commit();
			
			if ( rows > 0 ) {
				checkFlag = true;
			} 
		 	return checkFlag;	
	}	
    
    public boolean unlink(User user) throws Exception {
    	
       		Session session = HiberUtils.getSessionFactory().openSession();
    		Transaction tx = session.getTransaction();
    		tx.begin();
    		
    		@SuppressWarnings("unchecked")
    		List<User> list = session.createCriteria("com.axelor.model.User")
    				.add(Restrictions.eq("userName", user.getUserName() ))
    				.add(Restrictions.eq("password", user.getPassword() )).list();
    		if (!list.isEmpty()) {
    			user = list.get(0);
    			session.delete(user);
    			tx.commit();
    			System.out.println("Employee is deleted !!");
    			return true;
    		} else {
    			System.out.println("Record is not found !!");
    			tx.commit();
    			return false;
    		}
    }
    
    public boolean changePass(User user, String newPassword ) throws Exception {
    	
    	boolean flag = false;    	
    	Session session = HiberUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(User.class);
		cri = cri.add(Restrictions.eq("userName", user.getUserName() ));
		List<User> list = cri.list();
		if ( list.size() > 0 ) {
			flag = true;
		}
		user = list.iterator().next();
		user.setPassword(newPassword);
		session.update(user);
		session.getTransaction().commit();
    	
    	
    	return flag;
    	
    }
    
    public List<User> setAllUser() {
    	
    	Session session = HiberUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List<User> allUserList = session.createCriteria(User.class).list();
		System.out.println(" No. of User : " + allUserList.size() );
		
		session.getTransaction().commit();
		
		return allUserList;
    	
    }
    
}

