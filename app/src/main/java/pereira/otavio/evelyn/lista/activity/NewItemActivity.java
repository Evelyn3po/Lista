package pereira.otavio.evelyn.lista.activity;

import static pereira.otavio.evelyn.lista.activity.MainActivity.NEW_ITEM_REQUEST;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pereira.otavio.evelyn.lista.R;
import pereira.otavio.evelyn.lista.adapter.MyAdapter;
import pereira.otavio.evelyn.lista.model.MainActivityViewModel;
import pereira.otavio.evelyn.lista.model.MyItem;
import pereira.otavio.evelyn.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(
                NewItemActivityViewModel.class);
        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        if (selectPhotoLocation != null) {
            ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);

        }
        ImageButton imgCL = findViewById(R.id.imbCl);

        RecyclerView rvItens = findViewById(R.id.rvItens);
        MainActivityViewModel vm = new ViewModelProvider(this).get(
                MainActivityViewModel.class);
        List<MyItem> items = vm.getItens();

        myAdapter = new MyAdapter(this, itens);
        rvItens.setAdapter(myAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable
    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
                imvfotoPreview.setImageURI(photoSelected);
                NewItemActivityViewModel vm = new ViewModelProvider(this
                ).get(NewItemActivityViewModel.class);
                vm.setSelectPhotoLocation(selectedPhoto);
            }
        }
    }
}



