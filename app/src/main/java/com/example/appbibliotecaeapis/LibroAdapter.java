package com.example.appbibliotecaeapis;

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
    public LibroAdapter(Context context, VMLibro vmLibro) {
        this.context = context;
        this.vmLibro = vmLibro;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    OnLibroClickListener onLibroClickListener;
    public interface OnLibroClickListener{
        void onPlatoClick(Libro libro);
    }

    public void setOnPlatoClickListener(OnLibroClickListener onLibroClickListener){
        this.onLibroClickListener = onLibroClickListener;
    }

    OnImagenClickListener onImagenClickListener;
    public interface OnImagenClickListener{
        void onImagenClick(Libro Libro);
    }
    public void setOnImagenClickListener(OnImagenClickListener onImagenClickListener){
        this.onImagenClickListener = onImagenClickListener;
    }

    @NonNull
    @Override
    //Cual va a ser el contenedor de datos, será el layout que hemos creado
    public LibroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_libro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroAdapter.ViewHolder holder, int position) {
        Libro libro = vmLibro.obtenerLibro(position);

        holder.tvTitulo.setText(libro.getTitulo());
        holder.tvAutor.setText(libro.getAutor());
        holder.tvFecha.setText(libro.getFechaPublicacion());
        holder.tvIdioma.setText(libro.getIdioma());
        holder.ivLibro.setImageBitmap(decodificarByteBitMap(libro.getImgLibro()));

    }
    //Decodificar una imagen (arreglo de bytes) a bitmap para poder usarlo en la base de datos
    private Bitmap decodificarByteBitMap(byte[] imagen){
        return BitmapFactory.decodeByteArray(imagen,0,imagen.length);
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
