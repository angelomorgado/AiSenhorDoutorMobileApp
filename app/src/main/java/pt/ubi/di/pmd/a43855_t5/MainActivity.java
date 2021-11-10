package pt.ubi.di.pmd.a43855_t5;

import androidx.room.Database;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    EditText identification;
    EditText password;
    Button confirmation;
    Button registration;
    RelativeLayout cover;
    TextView title;

    DataBase db;
    List<Client> list;

    Boolean userIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Irá inicializar a view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DataBase.getInstance(getApplicationContext());
        list = db.myDao().getClients();

        identification = (EditText) findViewById(R.id.identificationBox);
        password = (EditText) findViewById(R.id.passwordBox);
        confirmation = (Button) findViewById(R.id.confirm);
        cover = (RelativeLayout) findViewById(R.id.cover);
        title = (TextView) findViewById(R.id.welcomeText);
        registration = (Button) findViewById(R.id.register);

        Handler h =new Handler() ;
        h.postDelayed(new Runnable() {
            public void run() {
                Animation animation = new TranslateAnimation(0, 0,0, -1000);
                animation.setDuration(800);
                animation.setFillAfter(true);
                cover.startAnimation(animation);
                cover.setVisibility(View.GONE);


                identification.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                confirmation.setVisibility(View.VISIBLE);
                registration.setVisibility(View.VISIBLE);
            }

        }, 2500);

        /*confirmation.setOnClickListener(
                oView -> {
                    String id = identification.getText().toString();
                    String pass = password.getText().toString();

                    for (Client cl : list) {
                       try {
                           if ((Integer.parseInt(id) == cl.getSNS() || id.equals(cl.getEmail())) && (pass.equals(cl.getPassword()))) {
                               System.out.println("Entrou!");
                           }
                       }catch (Exception e){
                           break;
                       }
                    }
                    Toast.makeText(MainActivity.this,
                            "Wrong credentials", Toast.LENGTH_LONG).show();

                }
        );*/
    }

    public void verifyLogin(View v)
    {
        String id = identification.getText().toString();
        String pass = password.getText().toString();

        for (Client cl : list) {
            System.out.println(cl.getSNS());
            System.out.println(cl.getPassword());
        }

        for (Client cl : list) {
            //Tentar não usar try | Arranjar forma mais otimizada
            try {
                if ((Integer.parseInt(id) == cl.getSNS() || id.equals(cl.getEmail())) && (pass.equals(cl.getPassword()))) {
                    Intent iInitialPage = new Intent(this, InitialPage.class);
                    userIn = true;
                    startActivity(iInitialPage);
                }
            }catch (Exception e){
                System.out.println("ERROU!");
                break;
            }
        }
        if(!userIn) {
            Toast.makeText(MainActivity.this,
                    "Wrong credentials", Toast.LENGTH_LONG).show();
        }

    }

    public void goToRegister(View v)
    {
        Intent iInitialPage = new Intent(this, RegisterUser.class);
        userIn = true;
        startActivity(iInitialPage);
    }

}