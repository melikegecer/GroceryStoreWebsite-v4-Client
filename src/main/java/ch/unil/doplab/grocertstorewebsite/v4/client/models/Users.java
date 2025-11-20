package ch.unil.doplab.grocertstorewebsite.v4.client.models;

import java.util.List;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class Users {

    private Integer userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer password;
    private Double balance = 0.0;
    private List<Foods> foodsList;

    public Users() {
    }

    public Double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Foods> getFoodsList() {
        return foodsList;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getPassword() {
        return password;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFoodsList(List<Foods> foodsList) {
        this.foodsList = foodsList;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
