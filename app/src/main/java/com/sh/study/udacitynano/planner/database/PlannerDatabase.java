package com.sh.study.udacitynano.planner.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.sh.study.udacitynano.planner.constants.SHDebug;

/**
 * Database instance
 *
 * Adventure with Android Architecture Components wouldn't be possible without support from:
 * {@Link "https://github.com/udacity/ud851-Exercises/tree/student/Lesson09b-ToDo-List-AAC"}
 * {@link "https://github.com/googlecodelabs/android-build-an-app-architecture-components"}
 * {@link "http://www.zoftino.com/android-search-functionality-using-searchview-and-room"}
 *
 * @author Sławomir Hagiel
 * @version 1.0
 * @since 2018-07-13
 */
@Database(entities = {EventEntity.class, CategoryEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class PlannerDatabase extends RoomDatabase {
    private static final String CLASS_NAME = "PlannerDatabase";

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "planner";
    private static PlannerDatabase sInstance;

    public static PlannerDatabase getInstance(Context context) {
        SHDebug.debugTag(CLASS_NAME, "getInstance:start");
        if (sInstance == null) {
            synchronized (LOCK) {
                SHDebug.debugTag(CLASS_NAME, "getInstance:create");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        PlannerDatabase.class, PlannerDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return sInstance;
    }

    public abstract EventDao eventDao();

    public abstract CategoryDao categoryDao();
}
