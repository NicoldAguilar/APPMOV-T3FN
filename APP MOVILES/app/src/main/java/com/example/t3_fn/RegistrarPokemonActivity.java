package com.example.t3_fn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t3_fn.Clases.ImageResponse;
import com.example.t3_fn.Clases.ImageToSave;
import com.example.t3_fn.Clases.Pokemon;
import com.example.t3_fn.Services.PokemonService;
import com.example.t3_fn.Services.T4Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarPokemonActivity extends AppCompatActivity {

    private static final int OPEN_CAMERA_REQUEST = 1001;
    private static final int OPEN_GALLERY_REQUEST = 1002;
    private static final String urlFotoApi= "https://demo-upn.bit2bittest.com/";
    private ImageView ivAvatar;

    private LocationManager mlocationManager;
    private TextView TVlatitud, TVlongitud;
    private static final int LOCATION_PERMISSION_REQUEST = 1003;

    TextView enviarUrlCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pokemon);

        Button registrarP = findViewById(R.id.btnRegistrarPoke);

        EditText regPNom = findViewById(R.id.etNombreP);
        //EditText regPNum = findViewById(R.id.etNumeroP);
        EditText regPTipo = findViewById(R.id.etTipoP);

        ivAvatar = findViewById(R.id.ivAvatar);
        Button btnCamera = findViewById(R.id.btnCamara);
        Button btnGallery = findViewById(R.id.btnGaleria);
        enviarUrlCamara = findViewById(R.id.tvUrlImg);

        TVlatitud = findViewById(R.id.tvLatitud);
        TVlongitud = findViewById(R.id.tvLongitud);
        Button btnUbicacion = findViewById(R.id.btnCargarUbicación);

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerCoordenadas();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOpenCamera();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                else {
                    String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 2000);
                }
            }
        });

        registrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = regPNom.getText().toString();
                //String numero = regPNum.getText().toString();
                String tipo = regPTipo.getText().toString();
                String latt = TVlatitud.getText().toString();
                String longg = TVlongitud.getText().toString();

                Pokemon pokemon = new Pokemon();
                pokemon.setNombre(nombre);
                pokemon.setTipo(tipo);
                //pokemon.setNumero(numero);
                //pokemon.setFoto("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numero + ".png");
                pokemon.setUrlCamara(enviarUrlCamara.getText().toString());
                pokemon.setFoto(pokemon.getUrlCamara());
                pokemon.setLatitud(latt);
                pokemon.setLongitud(longg);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PokemonService services = retrofit.create(PokemonService.class);
                Call<Void> call = services.create(pokemon);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent =  new Intent(RegistrarPokemonActivity.this, ListadoPokemonesActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OPEN_CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivAvatar.setImageBitmap(photo);
            //Convertir a base64
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            String imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            ImageToSave imgB64 = new ImageToSave(imgBase64);
            enviarImagen(imgB64);
        }

        if(requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); // close cursor

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            ivAvatar.setImageBitmap(bitmap);
            //Convertir a base64
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            String imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            ImageToSave imgB64 = new ImageToSave(imgBase64);
            enviarImagen(imgB64);
        }

    }

    private void enviarImagen (ImageToSave imgB64){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlFotoApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T4Service service = retrofit.create(T4Service.class);
        Call<ImageResponse> call = service.subirImagen(imgB64);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if(response.isSuccessful()){
                    Log.i("MAIN_APP", "Si se subió");
                    enviarUrlCamara.setText(urlFotoApi  + response.body().getUrl());
                    Log.i("MAIN_APP", urlFotoApi  + response.body().getUrl());
                }
                else{
                    Log.i("MAIN_APP", "No se subió");
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });

    }

    private void handleOpenCamera() {
        if(checkSelfPermission(Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED)
        {
            // abrir camara
            Log.i("MAIN_APP", "Tiene permisos para abrir la camara");
            abrirCamara();
        } else {
            // solicitar el permiso
            Log.i("MAIN_APP", "No tiene permisos para abrir la camara, solicitando");
            String[] permissions = new String[] {Manifest.permission.CAMERA};
            requestPermissions(permissions, 1000);
        }
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, OPEN_CAMERA_REQUEST);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }

    // ALMACENAR COORDENADAS
    void obtenerCoordenadas(){

        mlocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    double latitud = location.getLatitude();
                    double longitud = location.getLongitude();
                    Log.i("MAIN_APP", "Latitud" + latitud);
                    Log.i("MAIN_APP", "Longitud" + longitud);
                    TVlatitud.setText(String.valueOf(latitud));
                    TVlongitud.setText(String.valueOf(longitud));

                    mlocationManager.removeUpdates(this);
                }
            };

            mlocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
        }
        else{
            String[] permissions = new String[] {Manifest.permission.ACCESS_FINE_LOCATION};
            Log.i("MAIN_APP", "No hay permisos pa esta webada");
            requestPermissions(permissions, 1000);
        }
    }
}