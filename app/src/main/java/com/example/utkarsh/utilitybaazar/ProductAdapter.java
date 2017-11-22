package com.example.utkarsh.utilitybaazar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Yash on 22-11-2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context mCtx;
    private ArrayList<Product> productList;
    private OnItemClickListener clickListener;


    public ProductAdapter(Context mCtx, ArrayList<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.products_list_layout, null);
        ProductViewHolder holder = new ProductViewHolder(view, mCtx, productList);


        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.textViewTitle.setText(product.getTitle());
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        Glide.with(mCtx).load(product.getImage()).into(holder.productImage);
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView textViewTitle, textViewPrice, textViewRating;
        Button button;
        ArrayList<Product> productList = new ArrayList<Product>();
        Context mCtx;


        public ProductViewHolder(View itemView, final Context mCtx, ArrayList<Product> products) {
            super(itemView);

            this.productList = productList;
            this.mCtx = mCtx;

            productImage = (ImageView) itemView.findViewById(R.id.imageView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            textViewRating = (TextView) itemView.findViewById(R.id.textViewRating);
            button = (Button) itemView.findViewById(R.id.button4);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    clickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }

    }

}
