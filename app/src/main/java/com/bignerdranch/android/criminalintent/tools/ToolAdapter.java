package com.bignerdranch.android.criminalintent.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bignerdranch.android.criminalintent.R;

import java.util.List;

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Tool> tools;

    ToolAdapter(Context context, List<Tool> tools) {
        this.tools = tools;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ToolAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_tool, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToolAdapter.ViewHolder holder, int position) {
        Tool tool = tools.get(position);
        holder.nameView.setText(tool.getToolName());
        holder.capitalView.setText(tool.getCount());
    }

    @Override
    public int getItemCount() {
        return tools.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, capitalView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.tool_name);
            capitalView = view.findViewById(R.id.tool_count);
        }
    }
}
