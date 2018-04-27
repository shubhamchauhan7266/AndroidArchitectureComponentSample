package com.androidarchitecturecomponentsample.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidarchitecturecomponentsample.R;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.OnItemClickListener;

import java.util.List;

/**
 * @author :Shubham Gupta
 */
public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.CustomViewHolder> {

    private List<Product> indentDetails;
    private OnItemClickListener onItemClickListener;

    public ProductRecyclerViewAdapter(OnItemClickListener onItemClickListener,List<Product> indentDetails) {
        this.indentDetails = indentDetails;
        this.onItemClickListener = onItemClickListener;
    }

    public void setIndentDetails(List<Product> indentDetails) {
        this.indentDetails = indentDetails;
    }

    /**
     * it contains the object of CustomView Class
     *
     * @param viewGroup viewGroup
     * @param viewType viewType
     * @return
     */
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new CustomViewHolder(view);
    }

    /**
     * this method is used to  bind the view with CustomViewHolder class according to the position.
     *
     * @param customViewHolder : it contains the object of CustomViewHolder Class
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder customViewHolder, @SuppressLint("RecyclerView") final int position) {
        Product product = indentDetails.get(position);
        customViewHolder.textViewProductId.setText(product.getProductCode());
        customViewHolder.textViewProductName.setText(product.getProductName());
        customViewHolder.textViewProductPrice.setText(String.valueOf(product.getProductPrice()));

        customViewHolder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }

        });
        customViewHolder.listItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onItemLongPressedListener(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != indentDetails ? indentDetails.size() : 0);
    }


    /**
     * View holder class for RecyclerView
     */
    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProductName, textViewProductPrice, textViewProductId;
        View listItem;

        CustomViewHolder(View view) {
            super(view);
            textViewProductName = view.findViewById(R.id.textViewProductName);
            textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
            textViewProductId = view.findViewById(R.id.textViewProductId);
            listItem = view.findViewById(R.id.list_item);
        }
    }

}
