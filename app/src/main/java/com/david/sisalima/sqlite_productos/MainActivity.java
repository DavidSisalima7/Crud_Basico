package com.david.sisalima.sqlite_productos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.david.sisalima.sqlite_productos.model.Producto;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText codigo_pro,nom_pro,precio_pro,busqueda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignacion_controles();
        Button buttonGuardar = (Button) findViewById(R.id.btn_crear);
        buttonGuardar.setOnClickListener(guardarListener);
        Button buttonListar=(Button) findViewById(R.id.btn_listar);
        buttonListar.setOnClickListener(Listar);
        Button buttonEliminar = (Button) findViewById(R.id.btn_eliminar);
        buttonEliminar.setOnClickListener(Eliminar);
        Button buttonActualizar = (Button) findViewById(R.id.btn_actualizar);
        buttonActualizar.setOnClickListener(Actualizar);
        Button buttonBuscar=(Button) findViewById(R.id.btn_buscar);
        buttonBuscar.setOnClickListener(BuscarProducto);
    }

    View.OnClickListener Listar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ListView listView = (ListView) findViewById(R.id.listProducto);
            Cursor cursor = Producto.listaProductos(getApplicationContext());
            String[] desde = new String[]{"_id", "codigo_producto", "nombre", "precio"};
            int[] hasta = new int[]{R.id.txt_lnombre, R.id.txt_lcodigo, R.id.txt_lprecio};

            CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                    getApplicationContext(),
                    R.layout.detalle_producto,
                    cursor,
                    desde,
                    hasta, 0
            );
            listView.setAdapter(cursorAdapter);
        }
    };


    public void LimpiarDatos() {
        EditText codigo = (EditText) findViewById(R.id.txt_codigo);
        EditText nombre = (EditText) findViewById(R.id.txt_nombre);
        EditText precio = (EditText) findViewById(R.id.txt_precio);
        codigo.setText("");
        nombre.setText("");
        precio.setText("");
    }

    private void asignacion_controles(){
        busqueda=(EditText) findViewById(R.id.txt_buscar);
        codigo_pro = (EditText) findViewById(R.id.txt_codigo);
        nom_pro = (EditText) findViewById(R.id.txt_nombre);
        precio_pro = (EditText) findViewById(R.id.txt_precio);
    }

    View.OnClickListener Actualizar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Producto producto = new Producto();
            producto.setCodigo_producto(codigo_pro.getText().toString());
            producto.setNombre(nom_pro.getText().toString());
            producto.setPrecio(precio_pro.getText().toString());

            if (producto.ActualizarProducto(getApplicationContext(), codigo_pro.getText().toString())) {
                Toast.makeText(getApplicationContext(), "El producto se edito correctamente", Toast.LENGTH_SHORT).show();
                LimpiarDatos();
            } else {
                Toast.makeText(getApplicationContext(), "Error al modificar", Toast.LENGTH_SHORT).show();
            }
        }
    };


    View.OnClickListener Eliminar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Producto producto = new Producto();
            if (producto.EliminarProducto(getApplicationContext(), codigo_pro.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Se elimino correctamente el producto", Toast.LENGTH_SHORT).show();
                //              limpiarVista();
            } else {
                Toast.makeText(getApplicationContext(), "Error al elminar la persona", Toast.LENGTH_SHORT).show();
            }
        }
    };


        View.OnClickListener BuscarProducto = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto persona = new Producto();
                List<Producto> listap = persona.BuscarxCod(getApplicationContext(), busqueda.getText().toString());
                for (int i = 0; i < listap.size(); i++) {
                    //Toast.makeText(getApplicationContext(), listap.get(i).getCodigo_producto() + "  " + listap.get(i).getNombre() + " " + listap.get(i).getPrecio(), Toast.LENGTH_SHORT).show();
                    codigo_pro.setText(listap.get(i).getCodigo_producto());
                    nom_pro.setText(listap.get(i).getNombre());
                    precio_pro.setText(listap.get(i).getPrecio());
                }
            }
        };

    View.OnClickListener guardarListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Producto producto = new Producto();
            producto.setCodigo_producto(codigo_pro.getText().toString());
            producto.setNombre(nom_pro.getText().toString());
            producto.setPrecio(precio_pro.getText().toString());
            if (producto.GuardarProducto(getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "El producto se creo correctamente", Toast.LENGTH_SHORT).show();
               LimpiarDatos();
            } else {
                Toast.makeText(getApplicationContext(), "No se pudo crear el producto.", Toast.LENGTH_SHORT).show();
            }
        }

    };

}