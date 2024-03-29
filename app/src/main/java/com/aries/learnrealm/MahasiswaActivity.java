package com.aries.learnrealm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MahasiswaActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    MahasiswaAdapter mahasiswaAdapter;
    List<MahasiswaModel> mahasiswaModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_mahasiswa);

        recyclerView = findViewById (R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager (layoutManager);

        //Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder ( ).build ( );
        realm = Realm.getInstance (configuration);

        realmHelper = new RealmHelper (realm);
        mahasiswaModels = new ArrayList<> ( );

        mahasiswaModels = realmHelper.getAllMahasiswa ( );

        show ( );
    }

    @Override
    protected void onRestart() {
        super.onRestart ( );
        mahasiswaAdapter.notifyDataSetChanged ( );
        show ( );
    }

    private void show() {
        mahasiswaAdapter = new MahasiswaAdapter (this, mahasiswaModels);
        recyclerView.setAdapter (mahasiswaAdapter);
    }
}
