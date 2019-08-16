package DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

import databaseBeans.UserBean;

public class SignupDAO {
    public static boolean checkSignup(String username, String password, Context context){
        List<UserBean> userBeans = LitePal.where("username =?"
                ,username).find(UserBean.class);

        if(userBeans.size()!=0){
            Toast.makeText(context,"Used username",Toast.LENGTH_SHORT).show();
            return false;
        }

        //todo password checking and account checking

        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.save();

        return true;
    }

}
