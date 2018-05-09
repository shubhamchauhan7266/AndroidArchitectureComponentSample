package com.androidarchitecturecomponentsample.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
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
public class ProductRecyclerViewAdapter extends PagedListAdapter<Product, ProductRecyclerViewAdapter.CustomViewHolder> {

    private OnItemClickListener onItemClickListener;

    public ProductRecyclerViewAdapter(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
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

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Product product = getItem(position);
        if (product != null) {
            holder.bindTo(product,position);
        }/* else {
            // Null defines a placeholder item - PagedListAdapter will automatically invalidate
            // this row when the actual object is loaded from the database
        }*/
    }

    public static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldProduct.getProductId() == newProduct.getProductId();
                }
                @Override
                public boolean areContentsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldProduct.equals(newProduct);
                }
            };

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

        public void bindTo(Product product, final int position) {
            textViewProductId.setText(product.getProductCode());
            textViewProductName.setText(product.getProductName());
            textViewProductPrice.setText(String.valueOf(product.getProductPrice()));

            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }

            });
            listItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongPressedListener(position);
                    return true;
                }
            });
        }
    }

}
