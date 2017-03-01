package akarsh.diffmapviews;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady=false;
    Button standard,satellite,hybrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        standard=(Button)findViewById(R.id.standard);
        satellite=(Button)findViewById(R.id.satellite);
        hybrid=(Button)findViewById(R.id.hybrid);
        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                }
            }
        });
        satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);//this is call back function executes parallely in background
    }

    @Override
    public void onMapReady(GoogleMap map) {
        m_map=map;
        mapReady=true;
        LatLng nallakunta=new LatLng(17.399193,78.505104);
        //target() is where camera should focus on, tilt() is by default 90 degrees, bearing() is rotating the map
        CameraPosition target=CameraPosition.builder().target(nallakunta).zoom(18).tilt(65).bearing(112).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        //CameraPosition target2=CameraPosition.builder().target(nallakunta).tilt(90).build();
        //m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target2),3000,null);

        //to add marker
        MarkerOptions home=new MarkerOptions().position(nallakunta).title("Home");
        m_map.addMarker(home);

        LatLng xyz=new LatLng(17.399270, 78.505251);
        MarkerOptions loc=new MarkerOptions().position(xyz).title("Sweet Store").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        m_map.addMarker(loc);

        PolylineOptions polylineOptions=new PolylineOptions().geodesic(true).add(nallakunta).add(xyz).width(15);
        m_map.addPolyline(polylineOptions);

        CircleOptions circleOptions=new CircleOptions().center(nallakunta).radius(20).strokeColor(Color.BLUE).fillColor(Color.CYAN);//radius in meters
        m_map.addCircle(circleOptions);
    }
}
