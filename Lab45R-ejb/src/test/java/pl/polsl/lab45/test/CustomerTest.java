/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab45.test;

import java.util.List;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.polsl.lab45.model.Customer;
import pl.polsl.lab45.model.CustomerServiceBean;

/**
 * In charge of the customer test
 *
 * @author fabianokeke
 * @version 1.0
 */
public class CustomerTest {

    /**
     * Object of the EJB container
     */
    private static EJBContainer container;

    /**
     * Object of the Customer Service Bean 
     */
    private static CustomerServiceBean customerBean;

    /**
     * Establish connection before the class
     *
     * @throws NamingException if there is an error
     */
    @BeforeClass
    public static void initOnce() throws NamingException {
        Properties properties = new Properties();

        properties.put("xyz", "new://Resource?type=DataSource");

        properties.put("xyz.UserName", "root");
        properties.put("xyz.Password", "root");
        properties.put("xyz.JdbcUrl",
                "jdbc:mysql://localhost:3306/lab");
        properties.put("xyz.JdbcDriver", "com.mysql.cj.jdbc.Driver");
        properties.put("xyz.JtaManaged", "true");
        properties.put("xyz.ConnectionProperties",
                "useSSL=false;allowPublicKeyRetrieval=true");
        container = EJBContainer.createEJBContainer(properties);
        customerBean = (CustomerServiceBean) container.getContext().lookup("java:global/Lab45-ejb/CustomerServiceBean");
    }

    /**
     * In charge of testing if the customer is created or updated
     */
    @Test
    public void testCreate() {
        Customer customer = new Customer();
        customer.setFirstName("Fabian");
        customer.setLastName("Okeke");

        assertNull("Should be null", customer.getId());
        customerBean.createOrUpdateCustomer(customer);
        assertNotNull("Should be not null", customer.getId());
    }

    /**
     * In charge of testing if the customer is deleted
     */
    @Test
    public void testDelete() {
        Customer customer = new Customer();
        customer.setFirstName("Fabian");
        customer.setLastName("Okeke");
        assertNull("Id should be null", customer.getId());
        customer = customerBean.insert(customer);
        assertNotNull("Id should not be null", customer.getId());
        customerBean.deleteC(customer);
        customer = customerBean.findC(customer.getId());
        assertNull("Id should be null", customer);
    }

    /**
     * In charge of testing if the customer is updated
     */
    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setFirstName("Fabian");
        customer.setLastName("Okeke");
        customer = customerBean.insert(customer);
        assertNotNull("Id should not be null", customer.getId());
        customer.setFirstName("New name");
        customerBean.updateC(customer);
        assertEquals(customer.getFirstName(), "New name");
    }

    /**
     * To test if the customer is retrieved
     */
    @Test
    public void testRetrieve() {
        Customer customer = new Customer();
        customer.setFirstName("Fabian");
        customer.setLastName("Okeke");
        customer = customerBean.insert(customer);
        List<Customer> customers = customerBean.findAllCustomer();
        assertTrue(customers.size() > 0);

    }

    /**
     * Done after the class to close the whole process
     */
    @AfterClass
    public static void cleanUpOnce() {
        container.close();
    }

}
