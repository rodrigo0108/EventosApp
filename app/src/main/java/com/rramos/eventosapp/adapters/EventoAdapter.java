package com.rramos.eventosapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rramos.eventosapp.R;
import com.rramos.eventosapp.models.Evento;
import com.rramos.eventosapp.services.ApiService;
import com.rramos.eventosapp.services.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RODRIGO on 27/11/2017.
 */

public class EventoAdapter extends ArrayAdapter<Evento>
{
    private Context context;
    private ArrayList<Evento> datos;

    public EventoAdapter(Context context, ArrayList<Evento> datos) {
        super(context, R.layout.item_evento, datos);
        // Guardamos los parámetros en variables de clase.
        this.context = context;
        this.datos = datos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView. Para ello primero creamos el

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.item_evento,
                    parent,
                    false);
        }
        TextView prioridad = (TextView) convertView.findViewById(R.id.prioridad_text);
        TextView fecha = (TextView) convertView.findViewById(R.id.fecha_text);
        TextView mensaje = (TextView) convertView.findViewById(R.id.mensaje_text);

        // Recogemos el TextView para mostrar el nombre y establecemos el
        // nombre.
        Evento evento = getItem(position);

        assert evento != null;
        prioridad.setText(evento.getPrioridad());
        fecha.setText(evento.getFecha());
        mensaje.setText(evento.getMensaje());

        // Devolvemos la vista para que se muestre en el ListView.
        return convertView;
    }
}
