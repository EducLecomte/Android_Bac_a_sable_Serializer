package com.example.bts.bac_a_sable_serializer;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public abstract class Serializer {

    public static void serialize(String filename, Object object, Context context) {
        try {
            FileOutputStream file = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oss;
            try {
                oss = new ObjectOutputStream(file);
                oss.writeObject(object);
                oss.flush();
                oss.close();
            } catch (IOException e) {
                //erreur dans la sérialisation
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // fichier non trouvé
            e.printStackTrace();
        }
    }

    public static Object deserialize(String filename, Context context) {
        try {
            FileInputStream file = context.openFileInput(filename);
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(file);
                try{
                    Object object = ois.readObject();
                    ois.close();
                    return object;
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}


