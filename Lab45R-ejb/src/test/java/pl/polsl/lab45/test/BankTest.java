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
import pl.polsl.lab45.model.Bank;
import pl.polsl.lab45.model.BankServiceBean;

/**
 * In charge of conducting the bank test
 *
 * @author fabianokeke
 * @version 1.0
 */
public class BankTest {

    /**
     * Initializing the EjB container
     */
    private static EJBContainer container;

    /**
     * initializing the bank service bean
     */
    private static BankServiceBean bankBean;

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
        bankBean = (BankServiceBean) container.getContext().lookup("java:global/Lab45-ejb/BankServiceBean");
    }

    /**
     * In charge of testing if the bank is created or updated
     */
    @Test
    public void testCreate() {
        Bank bank = new Bank("Fabian", "Gliwice");
        assertNull("Should be null", bank.getId());
        bankBean.createOrUpdateBank(bank);
        assertNotNull("Should be not null", bank.getId());
    }

    /**
     * In charge of testing if the bank is deleted
     */
    @Test
    public void testDelete() {
        Bank bank = new Bank();
        bank.setName("Fabian");
        bank.setLocation("Gliwice");
        assertNull("Id should be null", bank.getId());
        bank = bankBean.insert(bank);
        assertNotNull("Id should not be null", bank.getId());
        bankBean.delete(bank.getId());
        bank = bankBean.find(bank.getId());
        assertNull("Id should be null", bank);
    }

    /**
     * In charge of testing if the bank is updated
     */
    @Test
    public void testUpdate() {
        Bank bank = new Bank();
        bank.setName("Fabian");
        bank.setLocation("Gliwice");

        bank = bankBean.insert(bank);
        assertNotNull("Id should not be null", bank.getId());
        bank.setName("New name");
        bankBean.update(bank);
        assertEquals(bank.getName(), "New name");
    }

    /**
     * To test if the bank is retrieved
     */
    @Test
    public void testRetrieve() {
        Bank bank = new Bank();
        bank.setName("Fabian");
        bank.setLocation("Gliwice");
        bank = bankBean.insert(bank);
        List<Bank> banks = bankBean.findAll();
        assertTrue(banks.size() > 0);

    }

    /**
     * done after the class to close the whole process
     */
    @AfterClass
    public static void cleanUpOnce() {
        container.close();
    }
}
