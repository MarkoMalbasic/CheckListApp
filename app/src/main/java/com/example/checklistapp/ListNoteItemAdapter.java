package com.example.checklistapp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListNoteItemAdapter extends RecyclerView.Adapter<ListNoteItemAdapter.ViewHolder>{

    private final Context context;
    Activity activity;
    private final ArrayList<ItemModel> mItemList;

    public ListNoteItemAdapter(Context context, Activity activity, ArrayList<ItemModel> itemList){
        this.context = context;
        this.activity = activity;
        this.mItemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.checklist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(mItemList.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClick(position);
            }
        });
//        holder.mImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //onItemListImgClick(position);
//            }
//        });
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stu
                //getTextViewColorChanged(holder, isChecked);
                new ITextView() {
                    @Override
                    public void getTextViewColorChanged(ListNoteSectionAdapter.ViewHolder vh, Boolean bool) {
                        if(bool == true){

                        }
                    }
                };
            }
        });
    }

    public void onUpdateClick(int pPosition) {
        Log.i("CHKLST", " onUpdateClick("+pPosition);
        Intent intent = new Intent(context, UpdateItemActivity.class);
        intent.putExtra("ID", String.valueOf(mItemList.get(pPosition)));
        intent.putExtra("ITEM", String.valueOf(mItemList.get(pPosition)));
        intent.putExtra("SECTIONS_ID", String.valueOf(mItemList.get(pPosition)));
        activity.startActivityForResult(intent, 1);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

//    @Override
//    public void getTextViewColorChanged(ViewHolder vh, Boolean bool) {
//        if(bool == true){
//            vh.textView.setTextColor(R.string.yourhint);
//        }else {
//            Color color = new Color();
//            vh.textView.setTextColor(color.BLACK);
//        }
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        CardView mainLayout;
        //ImageView mImageView;
        CheckBox mCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            mainLayout = itemView.findViewById(R.id.cardView);
            //mImageView = itemView.findViewById(R.id.imageView);
            mCheckBox= itemView.findViewById(R.id.checkBox);
        }
    }
}
