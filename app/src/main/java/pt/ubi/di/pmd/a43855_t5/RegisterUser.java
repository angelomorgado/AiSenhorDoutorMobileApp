package pt.ubi.di.pmd.a43855_t5;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import java.util.List;

public class RegisterUser extends Activity {

    DataBase db;
    List<Client> list;
    Client newClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        Intent iCameFromMain = getIntent();

        db = DataBase.getInstance(getApplicationContext());

    }
}
