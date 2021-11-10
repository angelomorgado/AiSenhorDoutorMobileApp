package pt.ubi.di.pmd.a43855_t5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Client.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    private static DataBase instance;

    public abstract MyDao myDao();

    public static synchronized DataBase getInstance(Context context){
        /** we want to instantiate the database
         * only if we don't have an instance, thus the if condition */
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DataBase.class, "DB")
                    .createFromAsset("database/DB.db")
                    .allowMainThreadQueries()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();
        }
        return instance;
    }
}
