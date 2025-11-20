package ch.unil.doplab.grocertstorewebsite.v4.client.models;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class Foods {

    private Integer foodId;
    private String foodName;
    private Double foodPrice;
    private String ingredients;
//    private List<Users> usersList;

    public Foods() {
    }

    public Integer getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public String getIngredients() {
        return ingredients;
    }

//    public List<Users> getUsersList() {
//        return usersList;
//    }
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

//    public void setUsersList(List<Users> usersList) {
//        this.usersList = usersList;
//    }
}
