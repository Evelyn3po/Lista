package pereira.otavio.evelyn.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pereira.otavio.evelyn.lista.R;
import pereira.otavio.evelyn.lista.activity.MainActivity;
import pereira.otavio.evelyn.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity; // Referência à atividade principal
    List<MyItem> itens; // Lista de itens a serem exibidos
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item da lista
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list, parent, false);
        // Retorna uma nova instância de MyViewHolder
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Obtém o objeto MyItem da posição atual
        MyItem myItem = itens.get(position);
        // Obtém a visualização do item
        View v = holder.itemView;
        // Obtém a referência para a ImageView da foto
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        // Define a imagem da foto no ImageView
        imvPhoto.setImageBitmap(myItem.photo);
        // Obtém a referência para o TextView do título
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        // Define o título no TextView
        tvTitle.setText(myItem.title);
        // Obtém a referência para o TextView da descrição
        TextView tvDesc = v.findViewById(R.id.tvDesc);
        // Define a descrição no TextView
        tvDesc.setText(myItem.description);
    }

    @Override
    // Retorna o número total de itens na lista
    public int getItemCount() {
        return itens.size();
    }
}
