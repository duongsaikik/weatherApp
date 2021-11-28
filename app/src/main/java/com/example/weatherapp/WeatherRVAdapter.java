package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherRVModal> ArrayWeatherRVModals;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModal> weatherRVModals) {
        this.context = context;
        this.ArrayWeatherRVModals = weatherRVModals;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.weather_rv_item,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {

        WeatherRVModal modal = ArrayWeatherRVModals.get(position);
        if (modal != null){
            holder.temperatureTVDT.setText(modal.getTemperature() + "°C");
        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.conditionIVDT);
        holder.windTVDT.setText(modal.getWindSpeed() + "Km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try{
            Date t =input.parse(modal.getTime());
            holder.timeTVDT.setText(output.format(t));
        }catch (ParseException e){
            e.printStackTrace();
        }
        }

    }

    @Override
    public int getItemCount() {
        return ArrayWeatherRVModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView windTVDT,temperatureTVDT, timeTVDT;
        private ImageView conditionIVDT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTVDT = itemView.findViewById(R.id.idTVWindSpeed);
            temperatureTVDT = itemView.findViewById(R.id.idTVTemperatureDT);
            timeTVDT = itemView.findViewById(R.id.idTVTimeDT);
            conditionIVDT = itemView.findViewById(R.id.idIVConditionDT);
        }
    }
}
