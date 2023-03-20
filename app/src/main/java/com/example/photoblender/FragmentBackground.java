package com.example.photoblender;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FragmentBackground extends Fragment {
    View view;
    List<Background> backgroundList = new ArrayList<>();
    BgAdapter bgAdapter;
    RecyclerView rcy_bg;
    ImageView ig_Bg,ig_left,ig_right;
    TextView tv_text;

    Uri imageUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bg,container,false);
        rcy_bg = view.findViewById(R.id.rcy_bg);
        ig_Bg = view.findViewById(R.id.ig_Bg);
        ig_left = view.findViewById(R.id.ig_left);
        ig_right = view.findViewById(R.id.ig_right);
        tv_text = view.findViewById(R.id.tv_Text);
        ig_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity(), RecyclerView. HORIZONTAL, false);
        rcy_bg.setLayoutManager (linearLayoutManager);
        bgAdapter = new BgAdapter(getActivity(), backgroundList, new BgAdapter.iClickListener() {
            @Override
            public void onClickView(Background background) {
                Glide.with(getActivity()).load(background.getImage()).into(ig_Bg);

                imageUri = Uri.parse(String.valueOf(background.getImage()));
                FragmentImage fragmentImage = new FragmentImage();
                Bundle bundle = new Bundle();
                bundle.putParcelable("imageUri", imageUri);
                fragmentImage.setArguments(bundle);

            }
        });
        if (backgroundList!=null){
            backgroundList.clear();
        }
        backgroundList.add(new Background(R.drawable.color_pick));
        backgroundList.add(new Background(R.drawable.album));
        backgroundList.add(new Background(R.drawable.bg_app));
        backgroundList.add(new Background(R.drawable.bg_app));
        backgroundList.add(new Background(R.drawable.bg_app));
        backgroundList.add(new Background(R.drawable.bg_app));
        backgroundList.add(new Background(R.drawable.bg_app));
        backgroundList.add(new Background(R.drawable.bg_app));
        backgroundList.add(new Background(R.drawable.bg_app));
        backgroundList.add(new Background(R.drawable.bg_app));
        rcy_bg.setAdapter(bgAdapter);
        bgAdapter.notifyDataSetChanged();


        tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialogBuilder
                        .with(getActivity())
                        .setTitle("Choose Background color")
                        .initialColor(R.layout.textcolor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {

                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                changeBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });


        return view;


    }

    private void changeBackgroundColor(int selectedColor) {
        tv_text.setTextColor(selectedColor);
        ig_Bg.setBackgroundColor(selectedColor);
    }
}
