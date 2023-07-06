package pereira.otavio.evelyn.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import pereira.otavio.evelyn.lista.R;
import pereira.otavio.evelyn.lista.adapter.MyAdapter;
import pereira.otavio.evelyn.lista.model.MainActivityViewModel;
import pereira.otavio.evelyn.lista.model.MyItem;
import pereira.otavio.evelyn.lista.util.Util;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST = 1;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                // Cria um novo objeto MyItem
                MyItem myItem = new MyItem();
                // Obtém os dados do Intent resultante da atividade de criação de um novo item
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();
                 try {
                     // Converte a URI da foto selecionada em um Bitmap usando um utilitário
                     Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100);
                     myItem.photo = photo;
                 } catch (FileNotFoundException e) {
                     e.printStackTrace();
                 }
                // Obtém a instância do ViewModel MainActivityViewModel
                 MainActivityViewModel vm = new ViewModelProvider( this ).get(
                        MainActivityViewModel.class );
                // Obtém a lista de itens do ViewModel
                 List<MyItem> itens = vm.getItens();
                // Adiciona o novo item à lista
                 itens.add(myItem);
                // Notifica o adaptador que um novo item foi inserido na última posição
                 myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Configuração do FloatingActionButton para adicionar um novo item
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um Intent para abrir a atividade NewItemActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                // Inicia a atividade e aguarda um resultado
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
        // Configuração do RecyclerView para exibir a lista de itens
        RecyclerView rvItens = findViewById(R.id.rvItens);
        // Obtém a instância do ViewModel MainActivityViewModel
        MainActivityViewModel vm = new ViewModelProvider( this ).get(
                MainActivityViewModel.class );
        // Obtém a lista de itens do ViewModel
        List<MyItem> itens = vm.getItens();
        // Cria uma instância do adaptador MyAdapter, passando a referência à atividade e a lista de itens
        myAdapter = new MyAdapter(this, itens);
        // Define o adaptador no RecyclerView
        rvItens.setAdapter(myAdapter);
        rvItens.setHasFixedSize(true);
        // Define o layout manager para o RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        // Adiciona uma decoração de divisória entre os itens do RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(),
                DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
    }
}
