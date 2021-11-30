package rob.instagramapprealdemo.TabLayoutPackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import rob.instagramapprealdemo.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {
    Context mContext;
    List<struct> mData;
    List<String> postMessageForFilterList;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();


    public RecyclerViewAdapter(Context mContext, List<struct> mData, RecyclerViewClickInterface recyclerViewClickInterface1) {
        this.mContext = mContext;
        this.mData = mData;
        this.recyclerViewClickInterface=recyclerViewClickInterface1;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_username.setText(mData.get(position).getUsername());
        holder.tv_post_message.setText(mData.get(position).getPostMessage());
        holder.img_post.setImageBitmap(byteImageToBitmap(mData.get(position).getImagePosts()));
        Log.i(TAG, "onBindViewHolder: "+mData.get(position).getPostMessage());

    }

    public Bitmap byteImageToBitmap(byte[] imgOfByteParam){
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgOfByteParam, 0, imgOfByteParam.length);
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on Background thread

        //postMessageForFilterList.add(mData.get(position).getPostMessage());
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filterList = new ArrayList<>();
            for (int i = 0; i < mData.size(); i++) {
                postMessageForFilterList.add(mData.get(i).getPostMessage());
            }
            if (charSequence.toString().isEmpty()){
                filterList.addAll(postMessageForFilterList);
            }else{
                for (String postMessage : postMessageForFilterList){
                    if (postMessage.toLowerCase().contains(charSequence.toString().toLowerCase(Locale.ROOT))){
                        filterList.add(postMessage);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            postMessageForFilterList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public  class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView tv_username;
        TextView tv_post_message;
        ImageView img_post;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_username = itemView.findViewById(R.id.user_item_id);
            tv_post_message = itemView.findViewById(R.id.post_message_id);
            img_post = itemView.findViewById(R.id.img_post_item);
            linearLayout = itemView.findViewById(R.id.linear_item_post_id);




            linearLayout.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    recyclerViewClickInterface.onItemClickInterface(getAdapterPosition());
                }
            });



        }
    }
}
