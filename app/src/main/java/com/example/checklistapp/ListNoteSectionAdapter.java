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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListNoteSectionAdapter extends RecyclerView.Adapter<ListNoteSectionAdapter.ViewHolder> implements ITextView{

    private final Context context;
    private final Cursor mCursor;
    Activity activity;
    private final ArrayList<SectionModel> mSectionList;
    public ITextView iTextView;

    public ListNoteSectionAdapter(Context context, Cursor cursor, Activity activity, ArrayList<SectionModel> itemList){
        this.context = context;
        this.activity = activity;
        this.mSectionList = itemList;
        this.mCursor = cursor;
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
//        if(mCursor.move(position)){
//            return;
//        }
//        String id = mCursor.getString(mCursor.getColumnIndexOrThrow("ID"));
//        holder.getItemId();
        holder.textView.setText(String.valueOf(mSectionList.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClick(position);
            }
        });
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSectionListImgClick(position);
            }
        });

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stu
                getTextViewColorChanged(holder, isChecked);
            }
        });
    }

    public void onUpdateClick(int pPosition) {
        Log.i("CHKLST", " onUpdateClick("+pPosition);
        Intent intent = new Intent(context, UpdateSectionActivity.class);
        intent.putExtra("ID", String.valueOf(mSectionList.get(pPosition).getSectionID()));
        intent.putExtra("SECTION", String.valueOf(mSectionList.get(pPosition)));
        activity.startActivityForResult(intent, 1);
    }

    public void onSectionListImgClick(int pPosition) {

        Log.i("CHKLST", "onItemListImgClick("+pPosition);
        Intent intent = new Intent(context, ItemActivity.class);
        intent.putExtra("sectionID", String.valueOf(mSectionList.get(pPosition).getSectionID()));
        activity.startActivityForResult(intent, 1);
    }

    @Override
    public int getItemCount() {
        return mSectionList.size();
    }

    @Override
    public void getTextViewColorChanged(ViewHolder vh, Boolean bool) {
        if(bool == true){
            vh.textView.setTextColor(R.string.yourhint);
        }else {
            Color color = new Color();
            vh.textView.setTextColor(color.BLACK);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView textView;
        CardView mainLayout;
        ImageView mImageView;
        CompoundButton mCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            mainLayout = itemView.findViewById(R.id.cardView);
            mImageView = itemView.findViewById(R.id.imageView);
            mCheckBox = itemView.findViewById(R.id.checkBox);
        }


    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
