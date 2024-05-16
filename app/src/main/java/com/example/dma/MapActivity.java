package com.example.dma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.ScreenPoint;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.List;

public class MapActivity extends AppCompatActivity {
    /**
     * Replace "your_api_key" with a valid developer key.
     * You can get it at the https://developer.tech.yandex.ru/ website.
     */
    private final String MAPKIT_API_KEY = "your_api_key";
    MapView mapView;
    PlacemarkMapObject point1, point2, point3, point4,point5, point6, point7, point8, point9, point10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        setContentView(R.layout.map_view);
        mapView = findViewById(R.id.mapview);
        mapView.getMapWindow().getMap().move(new CameraPosition(new Point(55.75310274946766, 37.62172152700798), 11.0f, 0.0f, 0.0f), new Animation(Animation.Type.SMOOTH, 0), null);

        point1 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.814997733290475, 37.73401054541827), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point1.setText("Бульвар Рокосовского ");

        point2 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.796580339696376, 37.71558938516443), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point2.setText("Преображенская площадь");

        point3 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.789026780916004, 37.67986474600537), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point3.setText("Сокольники");

        point4 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.76924529940744, 37.64910754603959), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point4.setText("Красные ворота ");

        point5 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.759940192920894, 37.627987485422196), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point5.setText("Лубянка");

        point6 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.75126974499394, 37.61020219493854), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point6.setText("Библиотека им. Ленина");

        point7 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.72674433177487, 37.58093340516247), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point7.setText("Фрузенская");

        point8 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.69218423715387, 37.537781129853016), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point8.setText("Университет ");

        point9 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.60152598687735, 37.407559006608615), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point9.setText("Филатов Луг");

        point10 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.5600175004479, 37.4689632824525), ImageProvider.fromResource(this, R.drawable.geo_mark));
        point10.setText("Коммунарка ");
    }
}