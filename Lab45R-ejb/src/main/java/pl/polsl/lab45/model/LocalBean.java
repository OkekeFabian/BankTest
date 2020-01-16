
package pl.polsl.lab45.model;

import java.util.List;
import javax.ejb.Local;


/**
 * Here we specify all the methods that will be used
 * @author Okeke Fabian
 * @version 1.0
 */
@Local
public interface LocalBean {
    
    /**
     * This method looks for all the Bank objects in the database.
     * @return List of all banks in the database.
     */
    public List<Bank> findAll();
    
    /**
     * This method will be used for update the Bank information in the database.
     * @param bank The parameter a will be updated in the database.
     * @return The updated version of the Bank object.
     */
    public Bank update(Bank bank);
    
    /**
     * This method is used for deleting an Bank object in the database.
     * @param bank This parameter is the one that will be searched for an bank
     * and if it is founded it will be deleted from the database.
     */
    public void delete(Bank bank);
    
    /**
     * This method add an Bank to the database.
     * @param bank This parameter is the one that will be added to the database.
     * @return The Bank object added to the database.
     */
    public Bank insert(Bank bank);
    
    /**
     * This method finds the Bank by its id
     * @param id The unique number of the Bank in the database.
     * @return The Bank that matches the id of the parameter.
     */
    public Bank find(Integer id);
    
    /**
     * This method looks for all the Customer objects in the database.
     * @return List of all Customers in the database.
     */
    public List<Customer> findAllCustomer();
    
    /**
     * This method finds the Customer by its id
     * @param id The unique number of the Customer in the database.
     * @return The Customer that matches the id of the parameter.
     */
    public Customer findC(Integer id);
    
    /**
     * This method add an Customer to the database.
     * @param customer This parameter is the one that will be added to the database.
     * @return The Customer object added to the database.
     */
    public Customer insert(Customer customer);
    
    /**
     * This method is used for deleting an Customer object in the database.
     * @param customer This parameter is the one that will be searched for an Customer
     * and if it is founded it will be deleted from the database.
     */
    public void deleteC(Customer customer);
    
    /**
     * This method will be used for update the Customer information in the database.
     * @param customer The parameter a will be updated in the database.
     * @return The updated version of the Customer object.
     */
    public Customer updateC(Customer customer);
}
