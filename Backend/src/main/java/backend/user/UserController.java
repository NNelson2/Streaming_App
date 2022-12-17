package backend.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The controller for the User class
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserRepository userRepository;

    private final String success = "{\"message\":\"success\"}";
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
        System.out.println(userRepository.findByUserName(userName).getId());
        return userRepository.findByUserName(userName);
    }

    /**
     * CREATE method for a new user
     * @param user
     * @return fail or success
     */
    @RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
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
        userRepository.save(request);
        return userRepository.findById(id);
    }


    /**
     * Method used for deleting a user, will also delete all items tied to user
     * @param id the userID
     * @return fail or success
     */
    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        User user = userRepository.findById(id);
        userRepository.deleteById(id);
        return success;
    }

}
