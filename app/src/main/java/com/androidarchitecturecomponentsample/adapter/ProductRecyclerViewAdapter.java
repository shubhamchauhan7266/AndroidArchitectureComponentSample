package com.androidarchitecturecomponentsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidarchitecturecomponentsample.R;
import com.androidarchitecturecomponentsample.models.IndentDetails;

import java.util.List;

/**
 * @author :Shubham Gupta
 */
public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.CustomViewHolder> {


    private List<IndentDetails> indentDetails;

    public ProductRecyclerViewAdapter(Context context, List<IndentDetails> indentDetails) {
        this.indentDetails = indentDetails;
    }

    /**
     * it contains the object of CustomView Class
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new CustomViewHolder(view);
    }

    /**
     * this method is used to  bind the view with CustomViewHolder class according to the position.
     *
     * @param customViewHolder : it contains the object of CustomViewHolder Class
     * @param position
     */
    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int position) {
        IndentDetails details = indentDetails.get(position);
        customViewHolder.textViewProductId.setText(details.getItemCode());
        customViewHolder.textViewProductName.setText(details.getItemName());
        customViewHolder.textViewProductPrice.setText(String.valueOf(details.getDealerPrice()));
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

        CustomViewHolder(View view) {
            super(view);
            textViewProductName = view.findViewById(R.id.textViewProductName);
            textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
            textViewProductId = view.findViewById(R.id.textViewProductId);
        }
    }

}
