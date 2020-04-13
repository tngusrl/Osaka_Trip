package com.example.osaka_trip;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AdView mAdView;

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    public static final String ROOT_DIR = "/data/data/com.example.osaka_trip/databases/";
    public SQLiteDatabase db;
    public Cursor cursor;

    String strNames;
    String strNumbers, strNumbers1, strNumbers2, strNumbers3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, getString(R.string.admob_app_id));


        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);

        // 광고가 제대로 로드 되는지 테스트 하기 위한 코드입니다.

        mAdView.setAdListener(new AdListener() {

            @Override

            public void onAdLoaded() {
            }


            @Override

            public void onAdFailedToLoad(int errorCode) {
            }


            @Override

            public void onAdOpened() {

                // Code to be executed when an ad opens an overlay that

                // covers the screen.

            }


            @Override

            public void onAdClicked() {

                // Code to be executed when the user clicks on an ad.

            }


            @Override

            public void onAdLeftApplication() {

                // Code to be executed when the user has left the app.

            }


            @Override

            public void onAdClosed() {

                // Code to be executed when the user is about to return

                // to the app after tapping on an ad.

            }

        });


        // 앱 최초 실행시 asset 폴더의 DB를 불러옴
        setDB(this, "osakaTrip.db", "osakaTrip10.db"); // 회화, 캘린더 저장
        setDB(this, "osakaTrip7.db", "osakaTrip17.db"); // 여행정보

        db = this.openOrCreateDatabase("osakaTrip10.db", MODE_PRIVATE, null);

        cursor = db.rawQuery("SELECT * FROM Conv WHERE class = '공항'", null); //쿼리문

        int a = cursor.getCount();

        if (cursor != null)
            cursor.moveToFirst();

        do {
            strNames = cursor.getString(0);
            strNumbers = cursor.getString(1);
            strNumbers1 = cursor.getString(2);
            strNumbers2 = cursor.getString(3);
            strNumbers3 = cursor.getString(4);
        } while (cursor.moveToNext());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new MainFragment();

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
            // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
            // 2000 milliseconds = 2 seconds
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
            // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
            // 현재 표시된 Toast 취소
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                finish();
                toast.cancel();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_날씨) {
            fragment = new WeatherFragment();

        } else if (id == R.id.nav_여행정보) {

            fragment = new InfolistFragment();

        } else if (id == R.id.nav_팁) {

            fragment = new TipFragment();

        } else if (id == R.id.nav_회화) {

            fragment = new ConversationFragment();

        } else if (id == R.id.nav_준비물) {

            fragment = new PreparationFragment();

        } else if (id == R.id.nav_즐겨찾기) {

            Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_캘린더) {

            fragment = new CalendarFragment();

        } else if (id == R.id.nav_지도) {

            Uri gmmIntentUri = Uri.parse("geo:34.687233, 135.515796");
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    gmmIntentUri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);

        } else if (id == R.id.nav_지하철) {

            fragment = new SubwayFragment();

        } else if (id == R.id.nav_업데이트버전) {

            fragment = new VersionFragment();

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setDB(Context ctx, String inputfile, String outputfile) {
        File folder = new File(ROOT_DIR);
        if (folder.exists()) {
        } else {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets();
        File outfile = new File(ROOT_DIR + outputfile); // File outfile = new File(ROOT_DIR+"osakaTrip2.db");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open(inputfile, AssetManager.ACCESS_BUFFER); //  is = assetManager.open("osakaTrip.db", AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            byte[] tempdata = new byte[(int) filesize];
            is.read(tempdata);
            is.close();

            if (outfile.exists()) {
                outfile.delete();
            }
            fo = new FileOutputStream(outfile);
            fo.write(tempdata);
            fo.close();

        } catch (IOException e) {
        }
    }


    class ProductDBHelper extends SQLiteOpenHelper {  //새로 생성한 adapter 속성은 SQLiteOpenHelper이다.
        public ProductDBHelper(Context context) {
            super(context, "osakaTrip.db", null, 1);    // db명과 버전만 정의 한다.
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }
}
