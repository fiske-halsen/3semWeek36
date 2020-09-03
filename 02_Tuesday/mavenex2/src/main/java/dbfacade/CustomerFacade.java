
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

   
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer addCustomer(String name, String lastName){
        Customer customer = new Customer(name,lastName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
    public Customer findCustomerById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class,id);
            return customer;
        }finally {
            em.close();
        }
    }
    
    public List findCustomerByLastName(String name){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT c From Customer c WHERE c.lastName = :name",Customer.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally{
            em.close();
        }
    }
    
   
   

}
