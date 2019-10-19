package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.app.Activity;
import android.os.Bundle;

public class EditMenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_manage_menu);
    }
}
