package com.dmd.letutors.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dmd.letutors.R;
import com.dmd.letutors.bean.Subjectlevel;

import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class SeekOneAdapter extends BaseAdapter {
    private List<Subjectlevel> list;
    private Context context;

    public SeekOneAdapter(List<Subjectlevel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Subjectlevel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.seek_menu_class_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getSubjectLevelName());
        return convertView;
    }

    class Holder {
        TextView name;
    }
}
