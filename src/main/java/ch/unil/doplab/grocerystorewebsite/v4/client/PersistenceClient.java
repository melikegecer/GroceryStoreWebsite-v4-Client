package ch.unil.doplab.grocerystorewebsite.v4.client;

import ch.unil.doplab.grocertstorewebsite.v4.client.exceptions.AlreadyExistsException;
import ch.unil.doplab.grocertstorewebsite.v4.client.exceptions.DoesNotExistException;
import ch.unil.doplab.grocertstorewebsite.v4.client.models.Foods;
import ch.unil.doplab.grocertstorewebsite.v4.client.models.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class PersistenceClient {

    private static final String FOODS_URL = "http://localhost:8080/GroceryStoreWebsite-v4-Service/resources/food";
    private static final String USERS_URL = "http://localhost:8080/GroceryStoreWebsite-v4-Service/resources/user";

    private static Client client;
    private static PersistenceClient instance;

    private PersistenceClient() {
        PersistenceClient.client = ClientBuilder.newClient();
    }

    public static PersistenceClient getInstance() {
        if (instance == null) {
            instance = new PersistenceClient();
        }
        return instance;
    }

    public void completeShopping(int id) {
        client.target(USERS_URL + "/completeShopping/" + id).request().get();
    }

    public void removeFromShoppingCart(int uId, int fId) {
        client.target(USERS_URL + "/removeFromShoppingCart/" + uId + "/" + fId).request().get();
    }

    public void addToShoppingCart(int uId, int fId) {
        client.target(USERS_URL + "/addToShoppingCart/" + uId + "/" + fId).request().get();
    }

    public List<Foods> getAllFoodsInShoppingCart(int id) {
        return parseFoodList(client.target(USERS_URL + "/getShoppingCart/" + id).request().get(String.class));
    }

    public Users checkPassword(String username, int password) throws DoesNotExistException {
        Users u = getUsersByName(username);
        if (u.getUsername().equals(username) & u.getPassword() == password) {
            return u;
        }
        throw new DoesNotExistException("Users " + username + " does not exist.");
    }

    public boolean emailExists(String email) throws AlreadyExistsException {
        return client.target(USERS_URL + "/emailExists/" + email).request().get().readEntity(Boolean.class);
    }

    public void createUser(Users user) {
        WebTarget target = client.target(USERS_URL + "/create");
        Entity theEntity = Entity.entity(user, "application/json");
        Response response = target.request().post(theEntity);
    }

    public void updateUser(Users user) {
        client.target(USERS_URL + "/edit/" + user.getUserId()).request().put(Entity.entity(user, "application/json"));
    }

    public void removeUser(int id) {
        client.target(USERS_URL + "/remove/" + id).request().get().readEntity(String.class);
    }

    public Users getUserById(int id) {
        return parseUser(client.target(USERS_URL + "/find/" + id).request().get().readEntity(String.class));
    }

    public Users getUsersByName(String username) {
        Users u = parseUser(client.target(USERS_URL + "/findByName/" + username).request().get(String.class));
        return u;
    }

    public List<Users> getAllUsers() {
        return parseUserList(client.target(USERS_URL + "/findAll").request().get(String.class));
    }

    private List<Users> parseUserList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Users>>() {
        }.getType());
    }

    private Users parseUser(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, Users.class);
    }

    public void createFood(Foods food) {
        client.target(FOODS_URL + "/create").request().post(Entity.entity(food, "application/json"));
    }

    public void updateFood(Foods food) {
        client.target(FOODS_URL + "/edit/" + food.getFoodId()).request().put(Entity.entity(food, "application/json"));
    }

    public void removeFood(int id) {
        client.target(FOODS_URL + "/remove/" + id).request().get().readEntity(String.class);
    }

    public Foods getFoodById(int id) {
        return parseFood(client.target(FOODS_URL + "/find/" + id).request().get().readEntity(String.class));
    }

    public Foods getFoodByName(String foodName) throws DoesNotExistException {
        Foods f = parseFood(client.target(FOODS_URL + "/findByName/" + foodName).request().get(String.class));
        if (f != null) {
            return f;
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    public List<Foods> getAllFoods() {
        return parseFoodList(client.target(FOODS_URL + "/findAll").request().get(String.class));
    }

    private List<Foods> parseFoodList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Foods>>() {
        }.getType());
    }

    private Foods parseFood(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, Foods.class);
    }

}
