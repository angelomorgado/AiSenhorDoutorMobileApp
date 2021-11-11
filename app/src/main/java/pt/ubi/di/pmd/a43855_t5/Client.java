package pt.ubi.di.pmd.a43855_t5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "Client")
public class Client {
    @PrimaryKey
    private int SNS;

    @ColumnInfo
    private String Name;

    @ColumnInfo
    private String Email;

    @ColumnInfo
    private String Password;

    public Client()
    {

    }

    public Client(int sns, String name, String email, String password)
    {
        this.SNS = sns;
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "SNS=" + SNS +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public int getSNS() {
        return SNS;
    }

    public void setSNS(int SNS) {
        this.SNS = SNS;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
