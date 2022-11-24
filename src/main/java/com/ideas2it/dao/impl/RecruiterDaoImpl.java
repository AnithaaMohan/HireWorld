package com.ideas2it.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dao.RecruiterDao;
import com.ideas2it.exception.HireWorldException;
import com.ideas2it.logger.HireWorldLogger;
import com.ideas2it.model.Applicant;
import com.ideas2it.model.Recruiter;
import com.ideas2it.util.HibernateUtil;


public class RecruiterDaoImpl implements RecruiterDao {
    private SessionFactory factory = HibernateUtil.getSessionFactory(); 
    
    /**
     *{@inheritDoc}
     */
    public Recruiter insertRecruiter(Recruiter recruiter) throws HireWorldException {
        Session session = null;

         try {
            session = factory.openSession();
	        session.beginTransaction();
	        session.saveOrUpdate(recruiter);
	        session.getTransaction().commit();
	        
            if (0 != recruiter.getId()) {
            	HireWorldLogger.displayInfo(recruiter.getName() + " Recruiter Inserted ");
            	//throw new HireWorldException(recruiter.getName() + " Recruiter Inserted ");
            }
        } catch (HibernateException hibernateException) {
        	System.out.println(hibernateException);
        	session.getTransaction().rollback();
            throw new HireWorldException("Error occoured while trying to insert " + recruiter.getName());
        } finally {
            try {

                if (null != session) {
                session.close();
                }
            } catch (HibernateException hibernateException) {
		        HireWorldLogger.displayError("Error occoured while trying to close the session");
	        }
	    }
	    return recruiter;
    }

    /**
     *{@inheritDoc}
     */
    public List<Recruiter> retrieveRecruiters() throws HireWorldException {
        List<Recruiter> recruiters = null;
        Session session = null; 	

        try {
            session = factory.openSession();
            recruiters =(List<Recruiter>) session.createQuery("FROM Recruiter").list();
            HireWorldLogger.displayInfo("Displaying all the Recruiters");
        } catch (HibernateException hibernateException) {
            throw new HireWorldException("Error occoured while trying to fetch all Recruiters ");
        } finally {
            try {

                if (null != session) {
                session.close();
                }
            } catch (HibernateException hibernateException) {
		        HireWorldLogger.displayError("Error occoured while trying to close the session");
	        }
	    }
	    return recruiters;
    }
    
    /**
     *{@inheritDoc}
     */
     public Recruiter retrieveRecruiterById(int recruiterId) throws HireWorldException {    
        Recruiter recruiter = null;
        Session session = null;    	

        try {
            session = factory.openSession();
            recruiter = (Recruiter) session.get(Recruiter.class, recruiterId);
        } catch (HibernateException hibernateException) {
            throw new HireWorldException("Error occoured while trying to fetch " + recruiter.getName());
        } finally {
            try {

                if (null != session) {
                session.close();
                }
            } catch (HibernateException hibernateException) {
		HireWorldLogger.displayError("Error occoured while trying to close the session");
	    }
	}
	    return recruiter;
    }  
    /**
     *{@inheritDoc}
     */
    public Recruiter updateRecruiterById(Recruiter updatedRecruiter) throws HireWorldException {
        //boolean isUpdated = false;
        Session session = null;
        Transaction transaction = null;     	

        try {
			//System.out.println(updatedRecruiter);
			session = factory.openSession();
		 	transaction = session.beginTransaction();
			session.update(updatedRecruiter);
			transaction.commit();
			return updatedRecruiter;
			 //isUpdated = true;
		} catch (HibernateException hibernateException) {
			if (null != transaction) {
			    transaction.rollback();
			}
			throw new HireWorldException(hibernateException.getMessage());
		} finally {
			if (null != session) {
				session.close();
			}
		}		
	}
    
    /**
     *{@inheritDoc}
     */
    public boolean removeRecruiterById (int recruiterId) throws HireWorldException {
    	Recruiter recruiter = null;
    	Session session = null;
        Transaction transaction = null;
     	boolean isRemoved = false;
        
        try {
            session = factory.openSession();
	        session.beginTransaction();
            recruiter = (Recruiter) session.get(Recruiter.class, recruiterId );
            recruiter.setDeleted(true);
            System.out.println(recruiter);
            session.update(recruiter);
            transaction = session.getTransaction();
			transaction.commit();
						
            if ( transaction != null ) {
		        isRemoved = true;
	        }
        } catch (HibernateException hibernateException) {
            session.getTransaction().rollback();
            throw new HireWorldException("Error occoured while trying to delete " + recruiter.getName());
        } finally {
            try {

                if (null != session) {
                session.close();
                }
            } catch (HibernateException hibernateException) {
		HireWorldLogger.displayError("Error occoured while trying to close the session");
	    }
	}
	return isRemoved;
    }

    /**
     *{@inheritDoc}
     */
    public List<Recruiter> retriveRecruitersByApplicantId(int applicantId) throws HireWorldException {
        List<Recruiter> recruiters = new ArrayList<>();   
        Session session = null;

        try {
            session = factory.openSession();
            session.beginTransaction();
            StringBuilder hqlQuery = new StringBuilder();
            hqlQuery.append("select recruiter from Recruiter recruiter ")
                     .append("JOIN recruiter.applicants applicant ")
                     .append("where applicant.id = :applicantId ")
                     .append("and recruiter.isDeleted = false "); 
            Query<Recruiter> query = session.createQuery(hqlQuery.toString());
            recruiters = query.setParameter("applicantId" , applicantId).getResultList();	    
        } catch (HibernateException hibernateException) {
            throw new HireWorldException("Error occoured while trying to fetch recruiters assigned" );
        } finally {
            try {

                if (null != session) {
                session.close();
                }
            } catch (HibernateException hibernateException) {
                HireWorldLogger.displayError("Error occoured while trying to close the session");
	    }
        }
	return recruiters;
    }
}
    



         
    

        
    












  
  