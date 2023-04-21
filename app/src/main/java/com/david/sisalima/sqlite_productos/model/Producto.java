package com.david.sisalima.sqlite_productos.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Producto {

    private String codigo_producto;
    private String nombre;
    private String precio;

    //Metodos para la base
    public boolean GuardarProducto(Context mc){
        try {
            SQLHelper tiendaSQLHelper=new SQLHelper(mc);
            String sql;
            sql="INSERT INTO productos (codigo_producto,nombre,precio)" +
                    " VALUES ('"+getCodigo_producto()+"','"+getNombre()+"','"+getPrecio()+"');";
            //Toast.makeText(mc, "Guardado Correctamente", Toast.LENGTH_SHORT).show();
            tiendaSQLHelper.getWritableDatabase().execSQL(sql);
            return true;
        }catch (Exception e){
            //e.toString();
            return false;
        }

    }

    //LISTAR
    public static Cursor listaProductos(Context mc){
        SQLHelper tiendaSQLHelper=new SQLHelper(mc);
        String sql = "SELECT codigo_producto AS _id, * FROM productos";
        //Toast.makeText(mc.getApplicationContext(), ""+sql,Toast.LENGTH_SHORT).show();
        return tiendaSQLHelper .getReadableDatabase().rawQuery(sql,null);
    }

    //ACTUALIZAR
    /*
    public void ActualizarProducto(Context mc){
        SQLHelper tiendaSQLHelper=new SQLHelper(mc);
        String sql;
        sql="UPDATE productos";
        sql +="SET nombre= "+getNombre()+",precio="+getPrecio()+"";
        tiendaSQLHelper.getWritableDatabase().execSQL(sql);
    }*/

    public boolean ActualizarProducto(Context producto, String codigo_producto) {
        try {
            SQLHelper tiendaSQLHelper=new SQLHelper(producto);
            String sql = "UPDATE productos set nombre='" + getNombre() + "', precio='" + getPrecio() + "' where codigo_producto = '" + codigo_producto + "'";
            //Toast.makeText(person, ""+sql, Toast.LENGTH_SHORT).show();
            tiendaSQLHelper.getWritableDatabase().execSQL(sql);
            return true;
        } catch (Exception e) {
            //e.toString();
            return false;
        }
    }

    //ELIMINAR
    /*
    public void EliminaProducto(Context mc) {
        SQLHelper tiendaSQLHelper = new SQLHelper(mc);
        String sql;
        sql = "DELETE FROM productos WHERE codigo_producto='" + getCodigo_producto() + "'";
        tiendaSQLHelper.getWritableDatabase().execSQL(sql);
    }
*/
    public boolean EliminarProducto (Context producto, String codigo_producto){
        try {
            SQLHelper tiendaSQLHelper = new SQLHelper(producto);
            SQLiteDatabase db = tiendaSQLHelper.getWritableDatabase();
            String sql = "DELETE FROM productos WHERE codigo_producto = '" + codigo_producto + "';";
            Toast.makeText(producto, "Se elimino correctamente" + sql, Toast.LENGTH_SHORT).show();
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            //e.toString();
            return false;
        }
    }

    public List<Producto> Listar (Context producto){
        List<Producto> listarProductos = new ArrayList<>();
        SQLHelper tiendaSQLHelper = new SQLHelper(producto);
        //SQLiteDatabase cursorbd = cp.getReadableDatabase();
        Cursor cursobd = tiendaSQLHelper.getReadableDatabase().rawQuery("select codigo_producto,nombre,precio from productos", null);
        if (cursobd.moveToFirst()) {
            do {
                Producto per = new Producto();
                per.setCodigo_producto(cursobd.getString(0));
                per.setNombre(cursobd.getString(1));
                per.setPrecio(cursobd.getString(2));
                listarProductos.add(per);
            } while (cursobd.moveToNext());
        }
        return listarProductos;
    }

    public List<Producto> BuscarxCod(Context producto, String codigo){
        List<Producto> lista = new ArrayList<>();
        SQLHelper tiendaSQLHelper = new SQLHelper(producto);
        Cursor cursobd = tiendaSQLHelper.getReadableDatabase().rawQuery("SELECT codigo_producto, nombre, precio FROM productos WHERE LOWER(codigo_producto) = LOWER('" + codigo + "') OR LOWER(nombre) = LOWER('" + codigo + "')", null);
        if(cursobd.moveToFirst()){
            do {
                Producto per = new Producto();
                per.setCodigo_producto(cursobd.getString(0));
                per.setNombre(cursobd.getString(1));
                per.setPrecio(cursobd.getString(2));
                lista.add(per);
            }while (cursobd.moveToNext());
        }else{
            Toast.makeText(producto.getApplicationContext(), "No existe el producto con codigo ingresado", Toast.LENGTH_SHORT).show();
        }
        return lista;
    }

    public List<Producto> BuscarxNombre(Context producto, String nombre){
        List<Producto> lista = new ArrayList<>();
        SQLHelper tiendaSQLHelper = new SQLHelper(producto);
        Cursor cursobd = tiendaSQLHelper.getReadableDatabase().rawQuery("SELECT codigo_producto, nombre, precio FROM productos WHERE LOWER(nombre) = LOWER('" + nombre + "')", null);
        if(cursobd.moveToFirst()){
            Toast.makeText(producto.getApplicationContext(), "ENTRO", Toast.LENGTH_SHORT).show();
            do {
                Producto per = new Producto();
                Toast.makeText(producto, "salida->"+cursobd.getString(1), Toast.LENGTH_SHORT).show();
                per.setCodigo_producto(cursobd.getString(0));
                per.setNombre(cursobd.getString(1));
                per.setPrecio(cursobd.getString(2));
                lista.add(per);
            }while (cursobd.moveToNext());
        }else{
            Toast.makeText(producto.getApplicationContext(), "No existe el producto con nombre '"+nombre+"' ingresado", Toast.LENGTH_SHORT).show();
        }
        return lista;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
