package dk.nemprogrammering.android.listeapp;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyViewHolder(ConstraintLayout l, TextView v)
        {
            super(l);
            mTextView = v;
        }
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        TextView v = (TextView) layout.findViewById(R.id.list_text);

        MyViewHolder vh = new MyViewHolder(layout, v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.mTextView.setText("Dette er en test");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
