package rob.instagramapprealdemo.storyPackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import rob.instagramapprealdemo.R;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    int[] images;

    public StoryAdapter(int[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        holder.image_view_story.setBackgroundResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view_story;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view_story = itemView.findViewById(R.id.image_view_story);
        }
    }
}
