package com.example.utkarsh.utilitybaazar.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.utkarsh.utilitybaazar.Product;
import com.example.utkarsh.utilitybaazar.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    int position;
    ImageView imageViewProduct;
    TextView textViewTitle, textViewPrice;
    ArrayList<Product> productList;
    Context ctx;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ProductDetailsFragment(int position, ArrayList<Product> productList, Context ctx) {
        this.position = position;
        this.productList = productList;
        this.ctx = ctx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        imageViewProduct = (ImageView) view.findViewById(R.id.imageViewProduct);
        textViewTitle = (TextView) view.findViewById(R.id.textViewDetailsTitle);
        textViewPrice = (TextView) view.findViewById(R.id.textViewDetailsPrice);

        Product product = productList.get(position);

        Glide.with(ctx).load(product.getImage()).into(imageViewProduct);
        textViewTitle.setText(product.getTitle());
        textViewPrice.setText("Rs." + String.valueOf(product.getPrice()));

        return view;
    }

}
