package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.t3_fn.Adapters.AnimeAdapter;
import com.example.t3_fn.Clases.Animes;

import java.util.ArrayList;
import java.util.List;

public class ListarAnimesActivity extends AppCompatActivity {

    List<Animes> listaAnime = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_animes);

        AnimeAdapter ctAp = new AnimeAdapter(data(),this);
        RecyclerView rvLista =  findViewById(R.id.rvListaSimple);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(ctAp);
    }

    public List<Animes> data (){
        List<Animes> animes = new ArrayList<>();
        Animes A01 = new Animes("Oshi no ko", "Anime de idols", "https://cdn.jkdesu.com/assets/images/animes/image/oshi-no-ko.jpg", true);
        animes.add(A01);
        Animes A02 = new Animes("Spy x family", "Anime de espias", "https://www.crunchyroll.com/imgsrv/display/thumbnail/480x720/catalog/crunchyroll/689e2efcf9f192ba6c0f7a538ee99027.jpe", false);
        animes.add(A02);
        Animes A03 = new Animes("Jujutsu Kaizen", "Anime de hechiceros", "https://www.crunchyroll.com/imgsrv/display/thumbnail/480x720/catalog/crunchyroll/47efe819e954f83cf0b8e022c39488ce.jpe", false);
        animes.add(A03);
        Animes A04 = new Animes("Kimetsu no yaiba", "Anime de demonios", "https://www.crunchyroll.com/imgsrv/display/thumbnail/480x720/catalog/crunchyroll/9b22fdc9b3c0a5e0c6373adba8b7ac61.jpe", false);
        animes.add(A04);
        Animes A05 = new Animes("Haikyuu", "Anime de voley", "https://www.crunchyroll.com/imgsrv/display/thumbnail/480x720/catalog/crunchyroll/af5f2304138a2ebe9caf5b7d0b1e2f01.jpe", false);
        animes.add(A05);
        return animes;
    }
}