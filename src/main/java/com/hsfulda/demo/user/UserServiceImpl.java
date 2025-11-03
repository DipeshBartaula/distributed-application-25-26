package com.hsfulda.demo.user;

import com.hsfulda.demo.products.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //Hardcoded data
    public static List<User> userList = new ArrayList<>();
    static {
        userList.addAll(List.of(
                new User(1, "Rahul", "Chaudhary", new Address("Petersberg","Fulda","Hessen","36039"), "rahul.chaudary@gmail.com"),
                new User(2, "Anjali", "Sharma", new Address("Kassel","Hessen","Hessen","34117"), "anjali.sharma@gmail.com"),
                new User(3, "Siddharth", "Mehta", new Address("Frankfurt","Hessen","Hessen","60311"), "siddharth.mehta@gmail.com"),
                new User(4, "Priya", "Kumar", new Address("Wiesbaden","Hessen","Hessen","65183"), "priya.kumar@gmail.com"),
                new User(5, "Rohan", "Verma", new Address("Offenbach","Hessen","Hessen","63065"), "rohan.verma@gmail.com"),
                new User(6, "Neha", "Gupta", new Address("Darmstadt","Hessen","Hessen","64283"), "neha.gupta@gmail.com")
        ));
    }
    public List<User> getUsersList() {
        return userList;
    }

    @Override
    public User getUserById(int id) {
        return getUsersList().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
