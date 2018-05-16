package com.yendry.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yendry.mvp.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyVH> {

    List<String> list = new ArrayList<>();
    private String title;

    public void setList(List<String> list, String title) {
        this.list = list;
        this.title = title;
        notifyDataSetChanged();
    }

    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);


        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(MyVH holder, int position) {
        holder.text.setText(list.get(position));
        holder.title.setText("Meaning of: "+title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView text, title;
        public MyVH(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.result_text_id);
            title = (TextView) itemView.findViewById(R.id.result_title_id);
        }
    }
}
