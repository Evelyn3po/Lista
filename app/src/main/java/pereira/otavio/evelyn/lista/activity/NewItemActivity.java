package pereira.otavio.evelyn.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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

import pereira.otavio.evelyn.lista.R;
import pereira.otavio.evelyn.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;

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
        ImageButton imgCI = findViewById(R.id.imbCl);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                photoSelected = data.getData();
                ImageView imvfotoPreview =
                        findViewById(R.id.imvfotoPreview);
                imvfotoPreview.setImageURI(photoSelected);
                NewItemActivityViewModel vm = new ViewModelProvider( this).get( NewItemActivityViewModel.class );
                vm.setSelectPhotoLocation(selectedPhoto);
            }
        }
    }
}

