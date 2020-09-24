package com.example.ejercicio2.julissa_salinas49;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class list_view_articulos extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayAdapter adaptador;
    SearchView searchview;
    ListView listView;
    ArrayList<String>list;
    ArrayAdapter adapter;

    String[]version = {"Aestro","Blender","CupCake","Donut","Eclair","Froyo","GingerBread","HoneyComb","IceCream Sandwich","jelly Bean",
            "Kitkat","Lolipop","Marshmallow","Nought","Oreo"};
    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_articulos);

        listViewPersonas=(ListView)findViewById(R.id.listViewPersonas);
        searchview = (SearchView)findViewById(R.id.searchview);

        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,conexion.consultaListaArticulos1());
        listViewPersonas.setAdapter(adaptador);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s;
                adaptador.getFilter().filter(text);
                return false;
            }
        });
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion = "Codigo:"+conexion.consultaListaArticulos().get(pos).getDescripcion()+"\n";
                informacion+="Descripcion: "+conexion.consultaListaArticulos().get(pos).getDescripcion()+"\n";
                informacion+="Precio: "+conexion.consultaListaArticulos().get(pos).getPrecio();

                Dto articulos = conexion.consultaListaArticulos().get(pos);
                Intent intent = new Intent(ist_view_articulos.this, DetalleArticulos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("articulo",articulos);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
