package com.example.proyectoempresarial;

import android.content.Context;

import java.io.File;

public class FDP {

    public static boolean existeBD(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
