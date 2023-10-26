/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Address;
import model.Customer;

/**
 *
 * @author kotchanika
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 /*
//        Customer customer = new Customer(1L, "Antony", "Balla", "tballa@mail.com"); 
//        Address address = new Address(1L, "Ritherdon Rd", "London", "8QE", "UK"); 
//        address.setCustomerFk(customer);
//        customer.setAddressId(address); 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Customer customer = em.getReference(Customer.class, 1L);
//        Customer customer = em.find(Customer.class, 1L);
        //customer.setFirstname("Prayuth");
        //customer.setAddressId(null);
        //Address address = em.find(Address.class, 1L);
        //address.setCustomerFk(customer);
        //Address address = customer.getAddressId();
        //address.setCity("Gotham");
        //address.setCustomerFk(null);
        
        em.getTransaction().begin();
        try {
//            em.persist(address);
//            em.persist(customer);
            //em.flush();
            //em.persist(address);
            //em.persist(customer);
            //em.refresh(customer);
            //em.detach(customer);
            //em.persist(customer);
            //em.merge(customer);
            em.remove(customer);
            //em.persist(customer);
            //em.remove(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
*/
        createData(1L, "John", "Henry", "jh@mail.com", 1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH");
        createData(2L, "Marry", "Jane", "mj@mail.com", 2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
        createData(3L, "Peter", "Parker", "pp@mail.com", 3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
        createData(4L, "Bruce", "Wayn", "bw@mail.com", 4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");
        System.out.println("== printAllCustomer ==");
        printAllCustomer();
        System.out.println("== printCustomerByCity ==");
        printCustomerByCity("Bangkok");
        
    }
    
    public static void createData(
            Long cusId,
            String fName,
            String lName,
            String mail,
            Long addrId,
            String street,
            String city,
            String zipCode,
            String country
    ) {
        Customer customer = new Customer(cusId, fName, lName, mail); 
        Address address = new Address(addrId, street, city, zipCode, country);
        address.setCustomerFk(customer);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.persist(address);
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void printAllCustomer(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        List<Address> addrList = em.createNamedQuery("Address.findAll").getResultList();
        for(Address addr : addrList){
            Customer cus = addr.getCustomerFk();
            System.out.println(cus);
            System.out.println(addr);
            System.out.println("---------------------------");
        }
    }
    
    public static void printCustomerByCity(String city){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.findByCity");
        query.setParameter("city", city);
        List<Address> addrList = query.getResultList();
        for(Address addr : addrList){
            Customer cus = addr.getCustomerFk();
            System.out.println(cus);
            System.out.println(addr);
            System.out.println("---------------------------");
        }
        
    }
    
}
