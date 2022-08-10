package com.swagata.smartcheck;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swagata.smartcheck.ModalClass.GridModalClass;

import java.util.ArrayList;
public class GridAdapter extends ArrayAdapter<GridModalClass> {
    public GridAdapter(@NonNull Context context, ArrayList<GridModalClass> ModelArrayList) {
        super(context, 0, ModelArrayList);
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.single_view_of_grid_view, parent, false);
        }
        GridModalClass gridModalClass = getItem(position);
        ImageView courseIV = listitemView.findViewById(R.id.iv_icons);
        courseIV.setImageResource(gridModalClass.getImageView());
        return listitemView;
    }
}
