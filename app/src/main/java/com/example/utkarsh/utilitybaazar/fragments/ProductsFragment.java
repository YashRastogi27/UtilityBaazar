package com.example.utkarsh.utilitybaazar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.utkarsh.utilitybaazar.MainActivity;
import com.example.utkarsh.utilitybaazar.Product;
import com.example.utkarsh.utilitybaazar.ProductAdapter;
import com.example.utkarsh.utilitybaazar.R;
import com.example.utkarsh.utilitybaazar.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {


    public ProductsFragment() {
        // Required empty public constructor
    }

    private static final String PRODUCT_URL="https://projectgroupd6.000webhostapp.com/json_fetch.php";
    RecyclerView recyclerView;
    ProductAdapter adapter;
    ArrayList<Product> productList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);


        //initializing the product list
        productList = new ArrayList<>();

        //getting the RecyclerView from XML
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        loadProducts();

        return view;
    }

    private void loadProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);

                            for (int i=0; i<products.length(); i++){
                                JSONObject productObject = products.getJSONObject(i);

                                String title = productObject.getString("prod_name");
                                int price = productObject.getInt("prod_price");
                                double rating = productObject.getDouble("prod_rating");
                                String image = productObject.getString("prod_image");
                                int id = productObject.getInt("prod_id");

                                Product product = new Product(id, title, price, image, rating);
                                productList.add(product);
                            }

                            adapter = new ProductAdapter(getActivity().getBaseContext(), productList);
                            recyclerView.setAdapter(adapter);
                            adapter.SetOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {

                                    Utils.switchFragment(R.id.map,
                                            new ProductDetailsFragment(position, productList, getActivity().getBaseContext()),
                                            ((MainActivity) (getContext())));
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(getActivity().getBaseContext()).add(stringRequest);

    }


}
