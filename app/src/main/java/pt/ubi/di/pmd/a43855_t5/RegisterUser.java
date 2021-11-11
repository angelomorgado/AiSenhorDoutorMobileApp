package pt.ubi.di.pmd.a43855_t5;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterUser extends Activity {

    DataBase db;
    List<Client> list;
    Client newClient;

    EditText sns;
    EditText name;
    EditText email;
    EditText password;
    EditText retypePassword;
    Button confirm;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        Intent iCameFromMain = getIntent();

        db = DataBase.getInstance(getApplicationContext());
        list = db.myDao().getClients();

        sns = (EditText) findViewById(R.id.snsEdit);
        name = (EditText) findViewById(R.id.nameEdit);
        email = (EditText) findViewById(R.id.emailEdit);
        password = (EditText) findViewById(R.id.passwordEdit);
        retypePassword = (EditText) findViewById(R.id.retypePasswordEdit);
        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);

    }

    //Retorna true se o utilizador for válido e false se algumas informações forem inválidas ou o user já existir
    private Boolean checkUser(List<Client> list, Client c)
    {
        //Verificar se a password tem mais de 7 chars
        if(c.getPassword().length() < 8)
        {
            Toast.makeText(RegisterUser.this,
                    "Password must have more than 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Verificar se não contém nenhum espaço em branco ou se está vazia
        if(c.getName().isEmpty() || c.getEmail().contains(" ") || c.getEmail().isEmpty() || c.getPassword().contains(" "))
        {
            Toast.makeText(RegisterUser.this,
                    "Invalid credentials", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Verificar se o user já existe seja o seu nº de sns como o seu email
        for(Client cl : list)
        {
            if(cl.getSNS() == c.getSNS() || cl.getEmail().equals(c.getEmail())) {
                Toast.makeText(RegisterUser.this,
                        "User already exists", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    public void confirmAction(View v)
    {
        String strSNS = sns.getText().toString();
        int numSNS;

        //Verificar se o inteiro é mesmo inteiro
        try {
            numSNS = Integer.parseInt(strSNS);
        } catch (NumberFormatException e) {
            Toast.makeText(RegisterUser.this,
                    "Invalid credentials", Toast.LENGTH_SHORT).show();
            return;
        }

        String strName = name.getText().toString();
        String strEmail = email.getText().toString();
        String strPass = password.getText().toString();
        String strRePass = retypePassword.getText().toString();

        //Verifica se a password é igual à que reescreveu
        if(!strPass.equals(strRePass))
        {
            Toast.makeText(RegisterUser.this,
                    "Passwords don't match", Toast.LENGTH_SHORT).show();

            return;
        }


        newClient = new Client(numSNS,strName,strEmail,strPass);

        if(checkUser(list, newClient))
        {
            //db.myDao().addClient(newClient);
            Toast.makeText(RegisterUser.this,
                    "Success", Toast.LENGTH_SHORT).show();
        }
        System.out.println(newClient.toString());
    }

    public void goBack(View v)
    {
        super.finish();
    }

}
