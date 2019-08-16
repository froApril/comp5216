package DAO;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

import databaseBeans.UserBean;
import mobileproject.au.edu.sydney.comp5216.mobileproject.MainActivity;
import mobileproject.au.edu.sydney.comp5216.mobileproject.R;

public class LoginDAO {
    public static boolean loginCheck(String username, String password){
        List<UserBean> userBean = LitePal.where("username=?"
                ,username).find(UserBean.class);
        for(UserBean u: userBean){
            if(password.equals(u.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
