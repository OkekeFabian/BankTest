/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab45.model;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;

import javax.persistence.criteria.Root;

/**
 * BankServiceBean class responsible for changes or transactions done in or to
 * the bank class
 *
 *
 * @author fabianokeke
 * @version 1.0
 *
 */
@Stateless
@LocalBean
public class BankServiceBean extends AbstractDAO<Bank> {


    /**
     * To create or update the bank list
     *
     * @param bank the bank to be updated or created
     * @return the bank that has been worked upon
     */
    public Bank createOrUpdateBank(Bank bank) {
        if (bank.getId() == null) {
            em.persist(bank);
        } else {
            em.merge(bank);
        }
        return bank;
    }

    /**
     * To see the list of Banks in the database
     *
     * @return the bank list before and after changes are made
     */
    public List<Bank> findAllBanks() {
        return em.createNamedQuery("Bank.findAll").getResultList();

    }

    /**
     * This method add an Bank to the database.
     *
     * @param bank This parameter is the one that will be added to the database.
     * @return The Bank object added to the database.
     */
    public Bank insert(Bank bank) {
        em.persist(bank);
        return bank;
    }

    /**
     * This method is used for deleting an Bank object in the database.
     *
     * @param id This parameter is the one that will be searched for and if it
     * is found it will be deleted from the database.
     */
    public void delete(Integer id) {
        Bank bank = find(id);
        if (bank != null) {
            em.remove(bank);
        }
    }

    /**
     * Finding Bank by providing ID
     *
     * @param id:Finding bank by the id provided by the user
     * @return the bank(s) found after the search
     */
    public Bank findByBankId(Integer id) {
        return (Bank) (em.find(Bank.class, id));

    }

    /**
     * This method finds the Bank by its id
     *
     * @param id The unique number of the Bank in the database.
     * @return The Bank that matches the id of the parameter.
     */
    public Bank find(Integer id) {
        return em.find(Bank.class, id);
    }

    /**
     * This method will be used for update the Bank information in the database.
     *
     * @param bank that is too be updated
     * @return The updated version of the Bank object.
     */
    public Bank update(Bank bank) {
        return em.merge(bank);
    }

    /**
     * This method looks for all the Bank objects in the database.
     *
     * @return List of all Banks in the database.
     */
    public List<Bank> findAll() {
        return em.createQuery("SELECT b FROM Bank b").getResultList();
    }

    /**
     * Overloaded constructor of getting list of banks
     *
     * @param name: name of the bank that the user is trying to find
     * @param location:location of the bank that the user is trying to find
     * @return list of banks also when the user is trying to search and so has
     * to provide name and location
     *
     */
    public List<Bank> getBankList(String name, String location) {

        return em.createQuery(getCriteriaQuery(name, location)).getResultList();
    }

    /**
     * To delete a bank by providing id
     *
     * @param id the user has to enter to delete the bank
     *
     */
    public void removeBank(int id) {
        Bank bank = findByBankId(id);

        if (bank != null) {
            em.remove(bank);
        }

    }

    /**
     * create a bank
     *
     * @param bank to be created
     *
     */
    public void createBank(Bank bank) {
        em.persist(bank);
    }

    /**
     * To update the banks available
     *
     * @param bank to be updated
     */
    public void updateBank(Bank bank) {

        em.merge(bank);
    }

    /**
     * Criteria query responsible for finding Bank after providing name and
     * location
     *
     *
     * @param name providing the name of the bank to be queried
     * @param location: user providing location to be able to access and query
     * the desired bank
     * @return the bank found
     */
    private CriteriaQuery<Bank> getCriteriaQuery(String name, String location) {
        Expression expr; // refers to the attributes of entity class
        Root<Bank> queryRoot; // entity/table from which the selection is performed
        CriteriaQuery<Bank> queryDefinition; // query being built
        List<Predicate> predicates = new ArrayList<>(); // list of conditions in the where clause

        CriteriaBuilder builder; // creates predicates
        builder = em.getCriteriaBuilder();

        queryDefinition = builder.createQuery(Bank.class);
        // defines the from part of the query
        queryRoot = queryDefinition.from(Bank.class);
        // defines the select part of the query
        // at this point we have a query select s from Student s (select * from student in SQL)
        queryDefinition.select(queryRoot);
        if (name != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("name");
            predicates.add(builder.like(expr, name));
        }

        if (location != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("location");
            // creates condition of the form s.average >= average
            predicates.add(builder.equal(expr, location));
        }
        // if there are any conditions defined
        if (!predicates.isEmpty()) {
            // build the where part in which we combine the conditions using AND operator
            queryDefinition.where(
                    builder.or(predicates.toArray(
                            new Predicate[predicates.size()])));
        }
        return queryDefinition;
    }

    /**
     * To find the bank by name
     *
     * @param name of the bank to be found
     * @return the found bank Throws exception as it isnt used yet
     */
    @Override
    public List<Bank> find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
