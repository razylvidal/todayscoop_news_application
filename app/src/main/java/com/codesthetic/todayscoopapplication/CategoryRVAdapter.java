package com.codesthetic.todayscoopapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private ArrayList<CategoryRVModal> categoryRVModals;
    private Context context;
    private CategoryClickInterface categoryClickInterface;
    int selectedPosition = 0;


    public CategoryRVAdapter(ArrayList<CategoryRVModal> categoryRVModals, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVModals = categoryRVModals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
        CategoryRVModal categoryRVModal = categoryRVModals.get(position);
        holder.categoryTV.setText(categoryRVModal.getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position= holder.getAdapterPosition();
                categoryClickInterface.onCategoryClick(position);
                // update position
                selectedPosition=position;
                // notify
                notifyDataSetChanged();
            }
        });
        // check conditions
        if(selectedPosition==position)
        {
            // When current position is equal
            // to selected position
            // set red background color
            holder.categoryRV.setBackgroundColor(Color.RED);
            // set white text color
            holder.categoryTV.setTextColor(Color.WHITE);

        }
        else
        {
            // when current position is different
            // set white background
            holder.categoryRV.setBackgroundColor(Color.WHITE);
            // set black text color
            holder.categoryTV.setTextColor(Color.BLACK);

        }
    }

    @Override
    public int getItemCount() {
        return categoryRVModals.size();
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private RelativeLayout categoryRV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryRV = itemView.findViewById(R.id.idCVCategory);
            categoryTV = itemView.findViewById(R.id.idTVCategory);

        }
    }
}
