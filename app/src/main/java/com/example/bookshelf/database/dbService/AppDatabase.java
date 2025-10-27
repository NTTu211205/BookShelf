package com.example.bookshelf.database.dbService;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bookshelf.database.models.BookDB;
import com.example.bookshelf.database.models.BookInfoDB;
import com.example.bookshelf.database.models.ImageLinkDB;

@Database(
        entities = {
                BookDB.class, BookInfoDB.class, ImageLinkDB.class
        },
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "BOOK_SHELF";
    public abstract BookDB bookDB();
    public abstract BookInfoDB bookInfoDB();
    public abstract ImageLinkDB imageLinkDB();

    private static volatile AppDatabase instance;

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
                    return instance;
                }
            }
        }
        return instance;
    }
}
