package pereira.otavio.evelyn.lista.activity;

import static pereira.otavio.evelyn.lista.activity.MainActivity.NEW_ITEM_REQUEST;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import pereira.otavio.evelyn.lista.R;
import pereira.otavio.evelyn.lista.adapter.MyAdapter;
import pereira.otavio.evelyn.lista.model.MainActivityViewModel;
import pereira.otavio.evelyn.lista.model.MyItem;
import pereira.otavio.evelyn.lista.model.NewItemActivityViewModel;
import pereira.otavio.evelyn.lista.util.Util;

public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        //Obtenção de ImageButton
        ImageButton imgCL = findViewById(R.id.imbCl);
        //Definição de um ouvidor de cliques de imageButton
        imgCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Execução da abertura de galeria para escolha da foto
                //Intent implicito
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //Setar intent para informar que estamos interessados apenas em documentos com mimetype "image/*
                photoPickerIntent.setType("image/*");
                //Executar o intent através do método(O resultado é a imagem selecionada)
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });
        //Obtenção do button
        Button btnAddItem = findViewById(R.id.btnAddItem);
        //Setar um ouvidor de cliques
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verifica se os campos foram preenchidos pelos usúarios
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }
                //Como uma activity pode retornar dados para a Activity que chamou
                //Criação do Intent - Serve para guardar os dados a serem retornados para a MainActivity
                Intent i = new Intent();
                //Setamos o Uri da imagem escolhida dentro do intent
                i.setData(photoSelected);
                //Setamos o titulo e a descrição
                i.putExtra("title", title);
                i.putExtra("description", description);
                //usamos o método setResult para indicar o resultado da Activity - O código RESULT-OK indica que há dados de retorno
                setResult(Activity.RESULT_OK, i);
                //Activity é finalizada
                finish();
            }
        });
    }

    @Override
    //Passa três parâmetros (requestCode, resultCode, Intent data)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Verifica se o requestCode é um código de sucesso
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                //Obtenção da Uri da imagem e guardar no atributo de classe photoSelected
                photoSelected = data.getData();
                //Obtenção de ImageView
                ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
                //Setar o Uri no ImageView para que a foto seja exibida
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }
}




