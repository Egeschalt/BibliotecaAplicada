package com.example.appbibliotecaeapis;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import Model.Libro;


import VistaModelo.VMLibro;




public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolder> {
    Context context;
    VMLibro vmLibro;
    private static LayoutInflater inflater = null;
    OnLibroClickListener onLibroClickListener;


    public LibroAdapter(Context context, VMLibro vmLibro) {
        this.context = context;
        this.vmLibro = vmLibro;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public interface OnLibroClickListener {
        void onLibroClick(int position, Libro libro);
    }


    public void setOnLibroClickListener(OnLibroClickListener onLibroClickListener) {
        this.onLibroClickListener = onLibroClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_libro, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Libro libro = vmLibro.obtenerLibro(position);


        holder.tvTitulo.setText(libro.getTitulo());
        holder.tvAutor.setText(libro.getAutor());
        holder.tvFecha.setText(libro.getFechaPublicacion());
        holder.tvIdioma.setText(libro.getIdioma());
        holder.ivLibro.setImageBitmap(decodificarByteBitMap(libro.getImgLibro()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLibroClickListener != null) {
                    onLibroClickListener.onLibroClick(position, libro);
                }
            }
        });
    }


    private Bitmap decodificarByteBitMap(byte[] imagen) {
        return BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
    }


    @Override
    public int getItemCount() {
        return vmLibro.contarLibros();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvAutor, tvIdioma, tvFecha;
        ImageView ivLibro;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_tituloItem);
            tvAutor = itemView.findViewById(R.id.tv_autorItem);
            tvIdioma = itemView.findViewById(R.id.tv_idiomaItem);
            tvFecha = itemView.findViewById(R.id.tv_fechaPublicaItem);
            ivLibro = itemView.findViewById(R.id.iv_itemlibro);
        }
    }
}



