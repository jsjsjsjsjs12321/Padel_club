package com.example.proyectoempresarial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Layers.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ClienteViewHolder> {

    private List<Usuario> listaOriginal;
    private List<Usuario> listaAlterable;
    private List<Integer> iconos;

    public UsersAdapter(ArrayList<Usuario> listaAlterable) {
        this.listaAlterable = listaAlterable;
        this.listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaAlterable);
        this.iconos = Arrays.asList(R.drawable.ic_outline_boy_24, R.drawable.ic_outline_boy_24_2,
                R.drawable.ic_outline_boy_24_3, R.drawable.ic_outline_boy_24_4, R.drawable.ic_outline_boy_24_5);
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, null, false);
        ImageView img_cliente = view.findViewById(R.id.imageView);
        img_cliente.setImageResource(iconos.get(FDP.randomInt(0, iconos.size() - 1)));
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewNombre.setText(listaAlterable.get(position).getNombre());
        holder.viewDNI.setText(listaAlterable.get(position).getDni());
    }

    //Filtramos clientes por su DNI
    public void filtrarClientes(@NonNull final String textoIntroducido) {
        if (textoIntroducido.isEmpty()) {
            listaAlterable.clear();
            listaAlterable.addAll(listaOriginal);
        } else {
            listaAlterable.clear();
            for (Usuario u : listaOriginal) {
                if (textoIntroducido.length() <= u.getDni().length()) {
                    if (u.getDni().substring(0, textoIntroducido.length()).equalsIgnoreCase(textoIntroducido)) {
                        listaAlterable.add(u);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaAlterable.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        private TextView viewNombre, viewDNI;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDNI = itemView.findViewById(R.id.viewDNI);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, PersonalUserFileActivity.class);
                    intent.putExtra("ID", listaAlterable.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
