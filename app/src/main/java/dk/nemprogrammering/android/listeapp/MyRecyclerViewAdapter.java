package dk.nemprogrammering.android.listeapp;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<ListElementEntity> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView headerText;
        public TextView descText;

        public MyViewHolder(ConstraintLayout layout)
        {
            super(layout);
            headerText = (TextView) layout.findViewById(R.id.list_text_header);
            descText = (TextView) layout.findViewById(R.id.list_text_desc);
        }
    }

    public MyRecyclerViewAdapter()
    {
        this.mDataset = new ArrayList<ListElementEntity>();
    }

    public void setmDataset(List<ListElementEntity> d)
    {
        this.mDataset = d;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);

        MyViewHolder vh = new MyViewHolder(layout);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        ListElementEntity entity = this.mDataset.get(position);
        holder.headerText.setText(entity.header);
        holder.descText.setText(entity.desc);
    }

    @Override
    public int getItemCount() {
        return this.mDataset.size();
    }
}
