/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package kasogg.com.gankcenter.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kasogg.com.gankcenter.R;
import kasogg.com.gankcenter.entity.Meizhi;
import kasogg.com.gankcenter.widget.RatioImageView;

/**
 * Created by drakeet on 6/20/15.
 */
public class MeizhiListAdapter extends RecyclerView.Adapter<MeizhiListAdapter.ViewHolder> {

    private List<Meizhi> mDataList;
    private Context mContext;

    public MeizhiListAdapter(Context context, List<Meizhi> dataList) {
        mDataList = dataList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizhi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Meizhi item = mDataList.get(position);
        int limit = 48;
        String text = item.desc.length() > limit ? item.desc.substring(0, limit) + "..." : item.desc;
        viewHolder.tv.setText(text);
        Picasso.with(mContext).load(item.url).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setAndRefresh(List<Meizhi> dataList) {
        this.mDataList = dataList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        RatioImageView iv;
        @BindView(R.id.tv)
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            iv.setOriginalSize(50, 50);
        }
    }
}
