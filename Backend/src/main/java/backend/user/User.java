package backend.Users;

import backend.Chats.Chat;
import backend.Items.Item;
import backend.Ratings.Rating;

import javax.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * class used for storing all the attributes of a user
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String state;
    private String city;
    private int zipcode;
    private String dob;
    private UserType userType;
    private String userName;
    private String password;
    @Column(length = 65555)
    private String pfp;
    private double averageRating = 0;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<Item> items = new LinkedList<Item>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Rating> reviewsGotten = new LinkedList<Rating>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_giving_id")
    private List<Rating> reviewsGiven = new LinkedList<Rating>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Chat> chats = new LinkedList<Chat>();



    public User(String firstName, String lastName, String state, String city, int zipcode, String dob, UserType userType, String userName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.dob = dob;
        this.userType = userType;
        this.password = password;
        this.userName = userName;
        this.email = email;
        pfp = null;
}

    public User(String firstName, String lastName, String state, String city, int zipcode, String dob, UserType userType, String userName, String password, String pfp, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.dob = dob;
        this.userType = userType;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.pfp = pfp;
    }




    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                ", dob=" + dob +
                ", userType=" + userType +
                '}';
    }

    /**
     *
     * @return the userID
     */
    public int getId() {
        return id;
    }

    /**
     *  sets the userID
     * @param id the new userID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return users first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the users first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the users last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the users last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return the state the user is from
     */
    public String getState() {
        return state;
    }

    /**
     * sets the state for the user
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return the city the user is from
     */
    public String getCity() {
        return city;
    }

    /**
     * sets the city for the user
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return the users zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * sets the users zipcode
     * @param zipcode
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     *
     * @return the users date of birth
     */
    public String getDob() {
        return dob;
    }

    /**
     * sets the users date of birth
     * @param dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     *
     * @return the user type as an enum (ex: ADMIN)
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * sets the user type
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     *
     * @return a list of all items the user has
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * used to add an item to the user
     * @param item
     */
    public void addItem(Item item) {
        item.setUser(this);
        this.items.add(item);

    }

    /**
     * used to set multiple items for the user
     * @param items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPfp() {
        return pfp;
    }


    public void setPfp(String pfp) {
        this.pfp = pfp;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return a list of all items the user has
     */

    /**
     * used to add an item to the user
     * @param review
     */
    public void addReview(Rating review) {
        review.setUser(this);
        this.reviewsGotten.add(review);

    }

    /**
     * used to set multiple items for the user
     * @param reviewsGotten
     */
    public void setReviewsGotten(List<Rating> reviewsGotten) {
        this.reviewsGotten = reviewsGotten;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Rating> getReviewsGotten() {
        return reviewsGotten;
    }

    public List<Rating> getReviewsGiven() {
        return reviewsGiven;
    }

    public void setReviewsGiven(List<Rating> reviewsGiven) {
        this.reviewsGiven = reviewsGiven;
    }

    public void addReviewGiving(Rating review) {
        this.reviewsGiven.add(review);

    }


    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }
}
