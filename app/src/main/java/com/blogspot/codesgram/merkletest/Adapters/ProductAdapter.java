package com.blogspot.codesgram.merkletest.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.codesgram.merkletest.Models.ProductModel;
import com.blogspot.codesgram.merkletest.ProductDetail;
import com.blogspot.codesgram.merkletest.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    Context context;
    ArrayList<ProductModel> arrayList;
    public ProductAdapter(Context context, ArrayList<ProductModel> arrayList) {
        this.context = context;
        this.arrayList=arrayList;

    }

    //inflate layout item
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_rv,parent,false);
        return new ViewHolder(view);
    }

    //on bind data to adapter
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductModel model = arrayList.get(position);
        holder.vTitle.setText(model.getTitle());
        holder.vCategory.setText(model.getCategory());
        String price = String.valueOf(model.getPrice());
        holder.vPrice.setText("$" + price);
        Glide.with(context)
                .load(model.getImage())
                .fitCenter()
                .into(holder.vImage);
        holder.ParentLayout.setOnClickListener(new View.OnClickListener() {
            //throw data from model api when clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("Product_Title",arrayList.get(position).title);
                intent.putExtra("Product_Desc",arrayList.get(position).description);
                intent.putExtra("Product_Price",arrayList.get(position).price);
                intent.putExtra("Product_Image",arrayList.get(position).image);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    //count amount list size
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vImage;
        TextView vTitle, vPrice, vCategory;
        CardView ParentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialized component to set component adapter
            ParentLayout = itemView.findViewById(R.id.cv_parent);
            vImage = itemView.findViewById(R.id.iv_product);
            vTitle = itemView.findViewById(R.id.tv_product);
            vPrice = itemView.findViewById(R.id.tv_price);
            vCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}