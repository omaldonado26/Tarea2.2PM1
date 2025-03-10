package com.example.app2prestapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import com.example.app2prestapi.R;
import java.util.List;

public class PostAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> titles;

    public PostAdapter(Context context, List<String> titles) {
        super(context, R.layout.item_post, titles);
        this.context = context;
        this.titles = titles;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(titles.get(position));

        return convertView;
    }
}
