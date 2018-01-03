package com.example.food8.rotice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by food8 on 2017-12-27.
 */
public class Computer_Adapter extends BaseAdapter implements Filterable {
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private ListFilter listFilter;
    private ArrayList<Computer_Notice> glist;
    private ArrayList<Computer_Notice> filteredList;

    public Computer_Adapter(Context context, ArrayList<Computer_Notice> list){
        // MainActivity 에서 데이터 리스트를 넘겨 받는다.
        this.glist = list;
        filteredList = glist;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.computer_list,null);
            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.computer_label);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 각 셀에 넘겨받은 텍스트 데이터를 넣는다.
        viewHolder.label.setText( filteredList.get(position).getTitle() );
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }
    private class ListFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint == null||constraint.length() ==0) {
                results.values = glist;
                results.count = glist.size();
            }else{
                ArrayList<Computer_Notice> notices = new ArrayList<Computer_Notice>();
                for(Computer_Notice notice : glist){
                    if(notice.getTitle().toUpperCase().contains(constraint.toString().toUpperCase())){
                        notices.add(notice);
                    }
                }
                results.values = notices;
                results.count = notices.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Computer_Notice>) results.values;

            if(results.count > 0){
                notifyDataSetChanged();
            } else{
                notifyDataSetInvalidated();
            }
        }
    }

    class ViewHolder{
        public TextView label;
    }
}