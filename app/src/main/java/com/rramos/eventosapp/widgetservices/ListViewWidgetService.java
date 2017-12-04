package com.rramos.eventosapp.widgetservices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.rramos.eventosapp.R;
import com.rramos.eventosapp.models.Evento;
import com.rramos.eventosapp.services.ApiService;
import com.rramos.eventosapp.services.ApiServiceGenerator;
import com.rramos.eventosapp.widgetproviders.MyWidgetProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RODRIGO on 30/11/2017.
 */

public class ListViewWidgetService extends RemoteViewsService {



    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new ListviewRemoteViewsFactory(this.getApplicationContext(), intent);

    }

    private class ListviewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context mContext;
        private ArrayList<Evento> datos;

        public ListviewRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
        }

        @Override
        public void onCreate() {
            //Fuente de datos
            datos=new ArrayList<Evento>();

        }

        @Override
        public void onDataSetChanged() {
        //Inicializar datos
            ApiService service = ApiServiceGenerator.createService(ApiService.class);
            final Call<List<Evento>> evento = service.getEventos();

            evento.enqueue(new Callback<List<Evento>>() {
                @Override
                public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                    int statusCode = response.code();
                    List<Evento> eventos = response.body();
                    datos= (ArrayList<Evento>) eventos;
                }

                @Override
                public void onFailure(Call<List<Evento>> call, Throwable t) {

                }
            });
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return datos.size();
        }

        @Override
        public RemoteViews getViewAt(int i) {
            // position will always range from 0 to getCount() - 1.

            // Construct a RemoteViews item based on the app widget item XML file, and set the

            // text based on the position.

            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.item_evento);

            // feed row

            Evento data=datos.get(i);

            rv.setTextViewText(R.id.prioridad_text, data.getPrioridad());
            rv.setTextViewText(R.id.fecha_text,data.getFecha());
            rv.setTextViewText(R.id.mensaje_text,data.getMensaje());



            // Return the RemoteViews object.

            return rv;
        }



        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}