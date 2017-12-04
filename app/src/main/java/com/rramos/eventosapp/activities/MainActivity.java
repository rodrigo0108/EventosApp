package com.rramos.eventosapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Trigger;
import com.rramos.eventosapp.R;
import com.rramos.eventosapp.models.Evento;
import com.rramos.eventosapp.services.ApiService;
import com.rramos.eventosapp.services.ApiServiceGenerator;
import com.rramos.eventosapp.services.MyJobService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // FirebaseJobDispatcher configuration
        // https://developer.android.com/training/monitoring-device-state/battery-monitoring.html
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        final Call<List<Evento>> evento = service.getEventos();
        // Cómo determinar el nivel de batería actual
        evento.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                int statusCode = response.code();
                Log.d(TAG, "Estado: "+statusCode);
                if(statusCode==503){
                    FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(MainActivity.this));
                    dispatcher.mustSchedule(
                            dispatcher.newJobBuilder()
                                    .setService(MyJobService.class)
                                    .setTag("MyJobService")
                                    .setRecurring(true)
                                    .build()
                    );
                }
            }
            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {

            }
        });


    }
}
