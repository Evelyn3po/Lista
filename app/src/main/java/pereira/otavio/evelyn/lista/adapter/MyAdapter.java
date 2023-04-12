package pereira.otavio.evelyn.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pereira.otavio.evelyn.lista.R;
import pereira.otavio.evelyn.lista.activity.MainActivity;
import pereira.otavio.evelyn.lista.model.MyItem;


public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;
    public MyAdapter(MainActivity mainActivity, List<MyItem>
            itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup
        parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mainActivity);
            View v = inflater.inflate(R.layout.item_list,parent,false);
            return new MyViewHolder(v);

            @Override
            public void onBindViewHolder(@NonNull
                    RecyclerView.ViewHolder holder, int position) {
                MyItem myItem = itens.get(position);
                View v = holder.itemView;
                ImageView imvfoto = v.findViewById(R.id.imvfoto);
                imvfoto.setImageURI(myItem.foto);
                TextView tvTitle = v.findViewById(R.id.tvTitle);
                tvTitle.setText(myItem.title);
                TextView tvDesc = v.findViewById(R.id.tvDescesc);
                tvDesc.setText(myItem.description);
            }
        }
    }
}
