package rob.instagramapprealdemo.TabLayoutPackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rob.instagramapprealdemo.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<struct> mData;
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();


    public RecyclerViewAdapter(Context mContext, List<struct> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        holder.img_post.setImageResource(mData.get(position).getImagePosts());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_username;
        TextView tv_post_message;
        ImageView img_post;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_username = itemView.findViewById(R.id.user_item_id);
            tv_post_message = itemView.findViewById(R.id.post_message_id);
            img_post = itemView.findViewById(R.id.img_post_item);
            

        }
    }
}
