package com.example.photoblender;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.List;

public class BgAdapter extends RecyclerView.Adapter<BgAdapter.ListViewHolder>{
    Context context;
    private List<Background> backgroundList;

    iClickListener mClick;
    public interface iClickListener {
        void onClickView(Background background);
    }
    public BgAdapter(Context context, List<Background> backgroundList,iClickListener iClickListener) {
        this.context = context;
        this.backgroundList = backgroundList;
        this.mClick = iClickListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit,parent,false);
        return new ListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Background background = backgroundList.get(position);
        int bg = background.getImage();
        Glide.with(context).load(bg).into(holder.ig_Image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClickView(background);
            }
        });

    }


    @Override
    public int getItemCount() {
        if(backgroundList!=null){
            return backgroundList.size();
        }
        return 0;
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{

        ImageView ig_Image;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ig_Image = itemView.findViewById(R.id.ig_Image);

        }
    }

}
