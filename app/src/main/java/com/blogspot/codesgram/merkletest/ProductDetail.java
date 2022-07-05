package com.blogspot.codesgram.merkletest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProductDetail extends AppCompatActivity {
    TextView vTitle, vPrice, vDesc;
    ImageView vImage;
    String ProdTitle, ProdDesc, ProdImage;
    double ProdPrice;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //Customized action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_detail);

        //initialized component
        vTitle = findViewById(R.id.tv_prodtail);
        vPrice = findViewById(R.id.tv_pricetail);
        vDesc = findViewById(R.id.tv_desctail);
        vImage = findViewById(R.id.iv_prodtail);
        //call method
        getData();
        setData();

    }

    //get data from intent
    private void getData() {
        if (getIntent().hasExtra("Product_Title") && getIntent().hasExtra("Product_Desc")
                && getIntent().hasExtra("Product_Price") && getIntent().hasExtra("Product_Image")) {

            ProdTitle=getIntent().getStringExtra("Product_Title");
            ProdDesc=getIntent().getStringExtra("Product_Desc");
            ProdPrice=getIntent().getDoubleExtra("Product_Price",1);
            ProdImage=getIntent().getStringExtra("Product_Image");
        }
        else {//notification if there is no data or false throw
            Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
        }
    }

    //set data to variable component
    private void setData() {
        vTitle.setText(ProdTitle);
        vDesc.setText(ProdDesc);
        String price = String.valueOf(ProdPrice);
        vPrice.setText("$" + price);
        Glide.with(getApplicationContext())
                .load(ProdImage)
                .fitCenter()
                .into(vImage);
    }
}