package com.example.osaka_trip;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherFragment extends Fragment {

    TextView temp1;
    TextView temp2;
    TextView temp3;
    TextView temp4;
    TextView temp5;
    TextView temp6;

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;

    ImageView image[] = new ImageView[7];

    public WeatherFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();

        CheckTypesTask task = new CheckTypesTask();
        task.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setTitle("날씨");

        temp1 = v.findViewById(R.id.temp1);
        temp2 = v.findViewById(R.id.temp2);
        temp3 = v.findViewById(R.id.temp3);
        temp4 = v.findViewById(R.id.temp4);
        temp5 = v.findViewById(R.id.temp5);
        temp6 = v.findViewById(R.id.temp6);

        image1 = v.findViewById(R.id.image1);
        image2 = v.findViewById(R.id.image2);
        image3 = v.findViewById(R.id.image3);
        image4 = v.findViewById(R.id.image4);
        image5 = v.findViewById(R.id.image5);
        image6 = v.findViewById(R.id.image6);

        for (int i = 1; i < 7; i++) {
            image[i] = v.findViewById(R.id.image + i);
        }


        return v;
    }

    public interface ApiService {
        //베이스 Url
        String BASEURL = "https://api.openweathermap.org/";
        String APPKEY = "weather_appKey";

        //get 메소드를 통한 http rest api 통신
        @GET("data/2.5/forecast")
        Call<JsonObject> getHourly(
                @Query("lat") double lat, @Query("lon") double lon, @Query("units") String units, @Query("appid") String appid);

    }

    private void getWeather(double latitude, double longitude) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<JsonObject> call = apiService.getHourly(latitude, longitude, "metric", ApiService.APPKEY);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    //날씨데이터를 받아옴

                    temp1.setText(parsing(response, 0)[0]); //  Integer.parseInt(parsing(response,0)[1])
                    temp2.setText(parsing(response, 1)[0]);
                    temp3.setText(parsing(response, 2)[0]);
                    temp4.setText(parsing(response, 3)[0]);
                    temp5.setText(parsing(response, 4)[0]);
                    temp6.setText(parsing(response, 5)[0]);

                    for (int i = 1; i < 7; i++) {

                        switch (Integer.parseInt(parsing(response, i - 1)[1]) / 100) {
                            case 2:
                                image[i].setImageResource(R.drawable.ic_cloud_lightning);
                                break;

                            case 3:
                                image[i].setImageResource(R.drawable.ic_umbrella_drizzle);
                                break;

                            case 5:
                                image[i].setImageResource(R.drawable.ic_cloud_rain);
                                break;

                            case 6:
                                image[i].setImageResource(R.drawable.ic_snow);
                                break;

                            case 7:
                                image[i].setImageResource(R.drawable.ic_cloud_rain_lightning_moon);
                                break;

                            case 8:
                                if (Integer.parseInt(parsing(response, i - 1)[1]) == 800)
                                    image[i].setImageResource(R.drawable.ic_sun);
                                else
                                    image[i].setImageResource(R.drawable.ic_cloud);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

                Toast.makeText(getActivity(), "로딩실패, 잠시후 다시 시도해주세요", Toast.LENGTH_LONG).show();
            }
        });

    }

    public String[] parsing(Response<JsonObject> response, int i) {

        JsonObject object = response.body();

        String str = object.toString();
        JsonElement jelement = new JsonParser().parse(str);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("list");

        jobject = jarray.get(i).getAsJsonObject();
        JsonObject jobject1 = jobject.getAsJsonObject("main");

        String Current_temp = jobject1.get("temp").toString();

        JsonArray jarray1 = jobject.getAsJsonArray("weather");
        jobject = jarray1.get(0).getAsJsonObject();

        String Current_weather = jobject.get("id").toString();

        String a[] = new String[2];
        a[0] = Current_temp;
        a[1] = Current_weather;

        return a;
    }

    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                getWeather(34.6936, 135.502);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            asyncDialog.dismiss();
            super.onPostExecute(result);
        }
    }

}