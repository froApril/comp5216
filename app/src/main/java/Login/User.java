package Login;

import com.google.firebase.database.IgnoreExtraProperties;



// [START blog_user_class]

@IgnoreExtraProperties

public class User {



    private String username;
    private String email;
    private boolean isrestaurant;



    public User() {

        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }


    public User(String username, String email,boolean isrestaurant) {

        this.username = username;

        this.email = email;

        this.isrestaurant= isrestaurant;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsRestaurant(boolean isrestaurant) {
        this.isrestaurant = isrestaurant;
    }

    public boolean getIsRestaurant(){
        return isrestaurant;
    }



}
