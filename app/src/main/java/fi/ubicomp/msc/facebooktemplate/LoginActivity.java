package fi.ubicomp.msc.facebooktemplate;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuanCamilo on 1/9/2016.
 */
public class LoginActivity extends Activity {

    //TODO: Don't change the Activity name
    //TODO: Don't change the package name
    public static final int MY_PERMISSIONS_INT = 13;

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions();
        FacebookSdk.sdkInitialize(getApplicationContext());
//        FacebookSdk.setApplicationId(getResources().getString(R.string.app_id));
        setContentView(R.layout.login_activity_layout);
        callbackManager = CallbackManager.Factory.create();


        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        List permissions = new ArrayList<>();
        permissions.add("public_profile");
        //TODO: Add other necessary permissions

        loginButton.setReadPermissions(permissions);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Code for a succesful login
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // Code for an unsuccesful login
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // Code for error on login
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.e("Facebook Exception", exception.toString());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void requestPermissions()
    {
        //TODO: Ask for permissions necessary for Marshmallow
        //First check the state of the permission and then ask for it
        //Example of Internet permission request
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = new String[1];

            permissions[0] = Manifest.permission.INTERNET;

            ActivityCompat.requestPermissions(this,
                    permissions,
                    MY_PERMISSIONS_INT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        //TODO: Handle callback of the permission request
        switch (requestCode) {
            case MY_PERMISSIONS_INT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Code of what happens when permission was granted
                } else {
                    //Code of what happens when permission was denied
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
