package dk.nemprogrammering.android.listeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<ListElementEntity> mDataset;
    private ItemClickListener itemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView headerText;
        public TextView descText;
        public ImageView img;
        public View layout;

        public MyViewHolder(ConstraintLayout layout)
        {
            super(layout);
            this.layout = layout;
            headerText = (TextView) layout.findViewById(R.id.list_text_header);
            descText = (TextView) layout.findViewById(R.id.list_text_desc);
            img = layout.findViewById(R.id.imageView);
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

        layout.setOnClickListener((View view) -> {
            int uid = (int) layout.getTag();
            if (this.itemClickListener != null)
            {
                this.itemClickListener.onItemClick(view, uid);
            }
        });

        MyViewHolder vh = new MyViewHolder(layout);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        ListElementEntity entity = this.mDataset.get(position);
        holder.headerText.setText(entity.header);
        holder.descText.setText(entity.desc);
        holder.layout.setTag(entity.uid);

        File img = new File(entity.img);
        if (img.exists()) {
            Bitmap imgBitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
            holder.img.setImageBitmap(imgBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return this.mDataset.size();
    }

    public void setOnItemClickListener(ItemClickListener listener)
    {
        this.itemClickListener = listener;
    }

}
