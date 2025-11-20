package ch.unil.doplab.grocerystorewebsite.v4.client.beans;

import ch.unil.doplab.grocertstorewebsite.v4.client.exceptions.DoesNotExistException;
import ch.unil.doplab.grocertstorewebsite.v4.client.models.Foods;
import ch.unil.doplab.grocertstorewebsite.v4.client.models.Users;
import ch.unil.doplab.grocerystorewebsite.v4.client.PersistenceClient;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
@Named(value = "foodBean")
@SessionScoped
public class FoodBean implements Serializable {

    private String foodName = "";

    public void addFoodToShoppingCart() {
        Users user = LoginBean.getUserLoggedIn();
        try {
            Foods f = PersistenceClient.getInstance().getFoodByName(foodName);
            PersistenceClient.getInstance().addToShoppingCart(user.getUserId(), f.getFoodId());
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.foodName = "";
    }

    public void removeFoodFromShoppingCart() {
        Users user = LoginBean.getUserLoggedIn();
        List<Foods> foodList = PersistenceClient.getInstance().getAllFoodsInShoppingCart(user.getUserId());
        for (Foods foods : foodList) {
            if (foods.getFoodName().equals(foodName)) {
                PersistenceClient.getInstance().removeFromShoppingCart(user.getUserId(), foods.getFoodId());
            }
        }
        // empty values
        this.foodName = "";
    }

    public List<Foods> getFoodsInShoppingCart() {
        Users user = LoginBean.getUserLoggedIn();
        return PersistenceClient.getInstance().getAllFoodsInShoppingCart(user.getUserId());
    }

    public List<Foods> getFoods() {
        return PersistenceClient.getInstance().getAllFoods();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
