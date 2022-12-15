package backend.Users;


import backend.Items.Item;
import backend.Items.ItemRepository;
import backend.Ratings.Rating;
import backend.Ratings.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

/**
 * The controller for the User class
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RatingRepository ratingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    /**
     * GET method for all users
     * @return a list of all the users
     */
    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * GET method for a single user
     * @param id the users id
     * @return User
     */
    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    /**
     * GET method for a single user using a username
     * @param userName
     * @return the user found
     */
    @GetMapping(path = "/users/username/{userName}")
    User getUserByUserName(@PathVariable String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * CREATE method for a new user
     * @param user
     * @return fail or success
     */
    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }

    /**
     * UPDATE method for updating a user
     * @param id the userID
     * @param request the new User data in JSON format
     * @return the updated user
     */
    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        request.setId(id);
        request.setItems(user.getItems());
        userRepository.save(request);
        return userRepository.findById(id);
    }

    /**
     * Method used to add an item to a user
     * @param userId the userID
     * @param itemID the itemID
     * @return fail or success
     */
    String assignItemToUser(int userId, int itemID){
        User user = userRepository.findById(userId);
        Item item = itemRepository.findById(itemID);
        if(user == null || item == null)
            return failure;
        item.setUser(user);
        user.addItem(item);
        item.setUserID(userId);
        userRepository.save(user);
        itemRepository.save(item);
        return success;
    }

    /**
     * Method used to create an item
     * @param userId the user ID of the item owner
     * @param item the Item in form JSON
     * @return fail or success
     */
    @PostMapping("/users/{userId}/items")
    String createItem(@PathVariable int userId, @RequestBody Item item) {
        User user = userRepository.findById(userId);
        if (item == null)
            return failure;
        item.setUserID(userId);
        item.setCity(user.getCity());
        item.setState(user.getState());
        itemRepository.save(item);
        return assignItemToUser(userId, item.getId());
    }

    /**
     * Method used for deleting a user, will also delete all items tied to user
     * @param id the userID
     * @return fail or success
     */
    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        User user = userRepository.findById(id);
        for (int x = 0; x < user.getItems().size(); x++) {
            itemRepository.deleteById(user.getItems().get(x).getId());
        }
        userRepository.deleteById(id);
        return success;
    }

    @PostMapping(path = "/users/rating/{userID}/{reviewersID}/{rating}/{reviewText}")
    String addRatringWithReview(@PathVariable("userID") int userID, @PathVariable("reviewersID") int reviewersID, @PathVariable("rating") int rating, @PathVariable("reviewText") String review) {
        User user = userRepository.findById(userID);
        User reviewer = userRepository.findById(reviewersID);
        if (review == null) {
            Rating newReview = new Rating(rating, reviewersID);
            user.addReview(newReview);
            newReview.setUser(user);
            newReview.setUserGiving(reviewer);
            ratingRepository.save(newReview);

        } else {
            Rating newReview = new Rating(rating, review,reviewersID);
            user.addReview(newReview);
            newReview.setUser(user);
            newReview.setUserGiving(reviewer);
            ratingRepository.save(newReview);
        }
        int avg = 0;
        for (int x = 0; x < user.getReviewsGotten().size(); x++) {
            avg += user.getReviewsGotten().get(x).getRating();
        }
        avg /= user.getReviewsGotten().size();
        user.setAverageRating(avg);
        userRepository.save(user);
        return success;
    }

    @PostMapping(path = "/users/rating/{userID}/{reviewersID}/{rating}")
    String addRatingNoReview(@PathVariable("userID") int userID, @PathVariable("reviewersID") int reviewersID, @PathVariable("rating") int rating) {
        User user = userRepository.findById(userID);
        User reviewer = userRepository.findById(reviewersID);
        Rating newReview = new Rating(rating,reviewersID);
        user.addReview(newReview);
        newReview.setUser(user);
        newReview.setUserGiving(reviewer);
        ratingRepository.save(newReview);

        int avg = 0;
        for (int x = 0; x < user.getReviewsGotten().size(); x++) {
            avg += user.getReviewsGotten().get(x).getRating();
        }
        avg /= user.getReviewsGotten().size();
        user.setAverageRating(avg);
        userRepository.save(user);
        return success;
    }





}
