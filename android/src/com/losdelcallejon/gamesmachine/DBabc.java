package com.losdelcallejon.gamesmachine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Justiniano on 10-Dec-16.
 */

public class DBabc extends SQLiteOpenHelper {

    private SQLiteDatabase DB;
    private boolean esNuevaBD=false;

    public DBabc(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           String Sql = "CREATE TABLE usuarios(" +
                   "id INTEGER," +
                   "username VARCHAR(100)," +
                   "sexo VARCHAR(100))";
           sqLiteDatabase.execSQL(Sql);
           Sql = "CREATE TABLE unidades(" +
                   "id INTEGER," +
                   "nivel INTEGER," +
                   "nombre VARCHAR(100)," +
                   "descripcion VARCHAR(100))";
           sqLiteDatabase.execSQL(Sql);
           Sql = "CREATE TABLE cursados(" +
                   "id INTEGER," +
                   "fecha VARCHAR(100)," +
                   "unidad_id INTEGER)";
           sqLiteDatabase.execSQL(Sql);
           Sql = "CREATE TABLE palabras(" +
                   "id INTEGER," +
                   "letra VARCHAR(100)," +
                   "unidad_id INTEGER)";
           sqLiteDatabase.execSQL(Sql);
           this.esNuevaBD = true;
    }

    public boolean esNuevaBD()
    {
        return esNuevaBD;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS usuarios");
        DB.execSQL("DROP TABLE IF EXISTS unidades");
        DB.execSQL("DROP TABLE IF EXISTS cursados");
        DB.execSQL("DROP TABLE IF EXISTS palabras");

        onCreate(DB);
    }

    public void OpenDatabase() {

        DB = this.getWritableDatabase();
    }

    public void CloseDatabase() {
        DB.close();
    }


    public void insertarUsuario(int id,String username,String sexo)
    {
        String sentencia="INSERT INTO productos (id,sexo) VALUES ("
                + id +",'"+ username+"'," +",'"+sexo+"')";
        DB.execSQL(sentencia);
    }
    public void insertarUnidades(int id,int nivel,String nombre,String descripcion)
    {
        String sentencia="INSERT INTO productos (id,nivel,nombre,descripcion) VALUES ("
                + id +","+ nivel +",'"+nombre+"','"+descripcion+"')";
        DB.execSQL(sentencia);
    }

    public void insertarPalabras(int id,String letras,int unidad_id)
    {
        String sentencia="INSERT INTO productos (id,letras,unidad_id) VALUES ("
                + id + ",'"+letras+"',"+unidad_id+")";
        DB.execSQL(sentencia);
    }

    public void insertarCursados(int id,String fecha,int unidad_id)
    {
        String sentencia="INSERT INTO productos (id,fecha,unidad_id) VALUES ("
                + id + ",'"+fecha+"',"+unidad_id+")";
        DB.execSQL(sentencia);
    }

    public boolean esNuevoUsuario(){
        String s = "";
        Cursor c = DB.rawQuery("SELECT * FROM usuarios", null);
        if (c.moveToFirst()) {
            return false;
        }
        return true;
    }


    public String obtenerNombreUsuario() {
        String s = "";
        Cursor c = DB.rawQuery("SELECT * FROM usuarios", null);
        if (c.moveToFirst()) {
            return c.getString(1);
        }
        return null;
    }

    public List<Integer> obtenerUnidadesDelUsuario(){
        String s = "";
        Cursor c = DB.rawQuery("SELECT * FROM cursados WHERE id=" + getIdUsuario(), null);
        List<Integer> listUnidades = new ArrayList<>();
        while (c.moveToNext()){
            Integer unidad = c.getInt(2);
            listUnidades.add(unidad);
        }
        if(listUnidades.isEmpty()){
            listUnidades.add(1); //Agrega la primer unidad
        }
        return listUnidades;
    }

    public int getIdUsuario(){
        String s = "";
        Cursor c = DB.rawQuery("SELECT * FROM usuarios", null);
        if (c.moveToFirst()) {
            return c.getInt(0);
        }
        return -1;
    }

    public int obtenerUsuarioID() {
        int s=-1;
        Cursor c = DB.rawQuery("SELECT * FROM usuarios", null);
        if (c.moveToFirst()) {
            return c.getInt(0);
        }
        return s;
    }

    public String obtenerSexoUsuario() {
        String s = "";
        Cursor c = DB.rawQuery("SELECT * FROM usuarios", null);
        if (c.moveToFirst()) {
            return c.getString(2);
        }
        return null;
    }
}
