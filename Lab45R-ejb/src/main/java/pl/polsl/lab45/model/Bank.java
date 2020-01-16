package pl.polsl.lab45.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Bank entity holding all fields needed to create a bank in the database
 *
 * @author fabianokeke
 * @version 1.0
 */
@Entity
@Table(name = "bank")
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b")
})

/**
 * Bank class with all the parameters needed to create a new bank
 */
public class Bank implements Serializable {

    /**
     * Constructor for the bank class
     *
     * @param name name of the bank
     * @param location of the bank
     */
    public Bank(String name, String location) {

        this.name = name;
        this.location = location;
    }

    /**
     * Default Constructor
     */
    public Bank() {
    }

    /**
     * Primary key value
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Bank's name column
     */
    @Column(name = "name")
    private String name;

    /**
     * location of the banks
     */
    private String location;

    /**
     * List of customers one to many relationship and cascade showing that if
     * bank is deleted, then the customers goes also
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bank")
    private List<Customer> customers;

    /**
     * id getter
     *
     * @return current id of the Bank
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set an id of the Bank
     *
     * @param id new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * name getter
     *
     * @return current name of the Bank
     */
    public String getName() {
        if (name != null) {
            return name;
        } else {
            return "";
        }
    }

    /**
     * name setter
     *
     * @param name :new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get location of Banks
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * set location of Banks
     *
     * @param location of banks
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get all the customers present
     *
     * @return a list of Customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Set customers present
     *
     * @param customers list of customers
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * override of base hashCode() method
     *
     * @return int hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * override of base equals() method
     *
     * @param obj other object to compare this object with
     * @return true if objects are equal or false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bank other = (Bank) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Override of toString() method
     *
     * @return a composite String containing all the fields
     */
    @Override
    public String toString() {
        return "Bank{" + "id=" + id + ", name=" + name + ", location=" + location + '}';
    }

}
