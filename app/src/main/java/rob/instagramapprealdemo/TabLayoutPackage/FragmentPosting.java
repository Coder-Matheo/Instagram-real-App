package rob.instagramapprealdemo.TabLayoutPackage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rob.instagramapprealdemo.R;
import rob.instagramapprealdemo.roomDatabase.InstaObj;
import rob.instagramapprealdemo.roomDatabase.InstaViewModel;


public class FragmentPosting extends Fragment implements RecyclerViewClickInterface{

    private static final String TAG = FragmentPosting.class.getSimpleName();
    View view;
    private RecyclerView recyclerView;
    private List<struct> lstPostData;
    InstaViewModel instaViewModel;
    List<String> usernameToInitRecycler;
    List<String> commentsToInitRecycler;
    List<String> postMessageToInitRecycler;
    List<String> dateTimeToInitRecycler;
    List<byte[]> imagePostToInitRecycler;


    public FragmentPosting() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_posting, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstPostData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //initial the List Array for putting Value for RecyclerAdapter
        usernameToInitRecycler = new ArrayList<>();
        commentsToInitRecycler = new ArrayList<>();
        postMessageToInitRecycler = new ArrayList<>();
        dateTimeToInitRecycler = new ArrayList<>();
        imagePostToInitRecycler = new ArrayList<>();
        lstPostData = new ArrayList<>();

        setValueToRecycler();
        lstPostData.add(new struct("Matt", "Heute war so Cool Programming Day", R.drawable.fran1));
        getAllPostsFun();






    }



    @Override
    public void onItemClickInterface(int position) {
        Toast.makeText(getContext(), "Item Interface"+position+ lstPostData.get(position).getUsername(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClickInterface(int position) {
        Toast.makeText(getContext(), "Long Interface", Toast.LENGTH_SHORT).show();
    }



    public void getAllPostsFun() {



    }


    private void setValueToRecycler() {
        instaViewModel = ViewModelProviders.of(this).get(InstaViewModel.class);
        instaViewModel.getAllPosts().observe(this, new Observer<List<InstaObj>>() {
            @Override
            public void onChanged(List<InstaObj> instaObjs) {

                for (int i = 0; i < instaObjs.size(); i++){

                    usernameToInitRecycler.add(instaObjs.get(i).getUsername());
                    commentsToInitRecycler.add(instaObjs.get(i).getComments());
                    postMessageToInitRecycler.add(instaObjs.get(i).getPostMessage());
                    dateTimeToInitRecycler.add(instaObjs.get(i).getDateTime());
                    imagePostToInitRecycler.add(instaObjs.get(i).getInstaImage());

                }
                Log.i(TAG, "Value of Database: in Fragment "+ usernameToInitRecycler.toString());
                for (int i = 0; i < usernameToInitRecycler.size(); i++){
                    lstPostData.add(new struct(usernameToInitRecycler.get(i), commentsToInitRecycler.get(i).trim(), R.drawable.fran2));
                }

            }
        });
        //lstPostData.add(new struct("Matt", "Heute war so Cool Programming Day", R.drawable.fran1));
        //lstPostData.add(new struct("Shahram", "Heute war so Cool Programming Day", R.drawable.fran2));
        //lstPostData.add(new struct("Ali", "Heute war so Cool Programming Day", R.drawable.fran3));
        //lstPostData.add(new struct("Mattheo", "Heute war so Cool Programming Day", R.drawable.fran4));

    }


}