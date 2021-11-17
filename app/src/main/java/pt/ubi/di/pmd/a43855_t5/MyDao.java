package pt.ubi.di.pmd.a43855_t5;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addClient(Client c);

    @Query("SELECT * FROM Client")
    public List<Client> getClients();

    @Query("SELECT * FROM Client WHERE SNS = :nSNS")
    public Client getClientBySNS(int nSNS);
}
