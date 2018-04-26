package com.androidarchitecturecomponentsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidarchitecturecomponentsample.R;
import com.androidarchitecturecomponentsample.interfaces.OnItemClickListener;
import com.androidarchitecturecomponentsample.models.IndentDetails;

import java.util.List;

/**
 * @author :Shubham Gupta
 */
public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.CustomViewHolder> {


    private Context context;
    private List<IndentDetails> indentDetails;
    private OnItemClickListener onItemClickListener;

    public ProductRecyclerViewAdapter(OnItemClickListener onItemClickListener, Context context, List<IndentDetails> indentDetails) {
        this.indentDetails = indentDetails;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
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
