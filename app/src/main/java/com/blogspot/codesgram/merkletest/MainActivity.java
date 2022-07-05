package com.blogspot.codesgram.merkletest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import com.blogspot.codesgram.merkletest.Adapters.ProductAdapter;
import com.blogspot.codesgram.merkletest.ConfigAPI.Client;
import com.blogspot.codesgram.merkletest.ConfigAPI.ProductAPI;
import com.blogspot.codesgram.merkletest.Models.ProductModel;

import java.util.ArrayList;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ProductModel> arrayList;
    ProductAPI productAPI;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Customized action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);

        //initialized component
        recyclerView = findViewById(R.id.rv_product);

        //call method
        getProducts();

    }
    //get product from api using retrofit
    public void getProducts(){

        //initialized and setup api
        productAPI = Client.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getMyProduct().enqueue(new Callback<ArrayList<ProductModel>>() {


            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0) {
                    arrayList=response.body();
                    adapter = new ProductAdapter(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(adapter); //set recycler to adapter
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                } else {
                    Toast.makeText(getApplicationContext(),"Error in Fetching Data", Toast.LENGTH_LONG).show();
                }
            }

            //notifivation false getting data
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}