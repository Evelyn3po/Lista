package pereira.otavio.evelyn.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    // Variável para armazenar a localização da foto selecionada
    Uri selectPhotoLocation = null;
    // Retorna a localização da foto selecionada
    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }
    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        // Define a localização da foto selecionada
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
