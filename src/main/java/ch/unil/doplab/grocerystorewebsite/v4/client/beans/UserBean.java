package ch.unil.doplab.grocerystorewebsite.v4.client.beans;

import ch.unil.doplab.grocertstorewebsite.v4.client.exceptions.AlreadyExistsException;
import ch.unil.doplab.grocertstorewebsite.v4.client.models.Users;
import ch.unil.doplab.grocerystorewebsite.v4.client.PersistenceClient;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private String email = "";
    private String username = "";
    private String firstName = "";
    private String lastName = "";
    private String password = "";
    private double amount = 0.0;

    public String createAUser() {
        try {
            boolean a = !PersistenceClient.getInstance().emailExists(email);
            boolean b = PersistenceClient.getInstance().getUsersByName(username) == null;
            if (a && b) {
                Users newUser = new Users();
                newUser.setUsername(username);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPassword(password.hashCode());
                PersistenceClient.getInstance().createUser(newUser);
            }
        } catch (AlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.email = "";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    public void increaseBalance() {
        Users user = LoginBean.getUserLoggedIn();
        user.setBalance(user.getBalance() + amount);
        PersistenceClient.getInstance().updateUser(user);
        // empty value
        this.amount = 0.0;
    }

    public void completeShopping() {
        Users user = LoginBean.getUserLoggedIn();
        PersistenceClient.getInstance().completeShopping(user.getUserId());
        LoginBean.setUserLoggedIn(PersistenceClient.getInstance().getUserById(user.getUserId()));
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getAmount() {
        return amount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
