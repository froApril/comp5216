package Login;

import com.google.firebase.database.IgnoreExtraProperties;



// [START blog_user_class]

@IgnoreExtraProperties

public class User {



    public String username;
    public String email;
    public boolean isrestaurant;



    public User() {

        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }


    public User(String username, String email,boolean isrestaurant) {

        this.username = username;

        this.email = email;

        this.isrestaurant= isrestaurant;

    }

    public boolean getIsRestrant(){
        return isrestaurant;
    }



}
