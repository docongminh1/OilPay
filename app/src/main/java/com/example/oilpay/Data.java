package com.example.oilpay;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Data {
    Context context;

    public Data(Context context) {
        this.context = context;
    }

    static String filename="database";

    static ArrayList<Oil> oilHistory = new ArrayList<>();

    public void addOilHistorry(Oil oil) {
        if(oilHistory.indexOf(oil) > 0) {
            this.oilHistory.add(0, oil);
        }
    }

    public ArrayList<Oil> getOilHistory(){
        return this.oilHistory;
    }

    public void WriteToFileInternal(ArrayList<Oil> arrayList){
        try {
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Oil> LoadFileInternal(){
        ArrayList<Oil> arrayList = new ArrayList<>();
        try {
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayList = (ArrayList<Oil>) objectInputStream.readObject();
            Log.d("OilPayApp", arrayList.size() + "");

            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Oil> getData(){
        ArrayList<Oil> arrayList = new ArrayList<>();
        arrayList.add(new Oil(1,R.drawable.castrol,"Castrol",500000));
        arrayList.add(new Oil(2,R.drawable.amiloil,"Amiloil",600000));
        arrayList.add(new Oil(3,R.drawable.evolution,"Evolution",700000));
        arrayList.add(new Oil(4,R.drawable.extra4,"Extra 4",800000));
        arrayList.add(new Oil(5,R.drawable.mobil1,"Mobil 1",900000));
        arrayList.add(new Oil(6,R.drawable.motul300v,"Motul 300v",5500000));
        arrayList.add(new Oil(7,R.drawable.castrol,"Castrol 1",500000));
        arrayList.add(new Oil(8,R.drawable.amiloil,"Amiloil 1",600000));
        arrayList.add(new Oil(9,R.drawable.evolution,"Evolution 1",700000));
        arrayList.add(new Oil(10,R.drawable.extra4,"Extra",800000));
        arrayList.add(new Oil(11,R.drawable.mobil1,"Mobil",900000));
        arrayList.add(new Oil(12,R.drawable.motul300v,"Motul 400v",5500000));
        return arrayList;
    }

}
