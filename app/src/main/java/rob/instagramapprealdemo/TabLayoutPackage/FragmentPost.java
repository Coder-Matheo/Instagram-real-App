package rob.instagramapprealdemo.TabLayoutPackage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import rob.instagramapprealdemo.R;
import rob.instagramapprealdemo.roomDatabase.InstaObj;
import rob.instagramapprealdemo.roomDatabase.InstaViewModel;


public class FragmentPost extends HelperFragmentPost implements RecyclerViewClickInterface{

    private static final String TAG = FragmentPost.class.getSimpleName();
    View view;
    private RecyclerView recyclerView;
    private List<struct> lstPostData;

    InstaViewModel instaViewModel;



    public FragmentPost(Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_posting, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstPostData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        initFlaotActionButton(view, getActivity());

        // Inflate the layout for this fragment
        return view;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setValueToRecycler();
        insertPostsFun();


        //initial the List Array for putting Value for RecyclerAdapter
        lstPostData = new ArrayList<>();
        lstPostData.add(new struct("Matt", "Heute war so Cool Programming Day", intImageToImageByteArray(R.drawable.sqlite_icon) ));
    }




    @Override
    public void onItemClickInterface(int position) {
        Toast.makeText(getContext(), "Item Interface"+position+ lstPostData.get(position).getUsername(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClickInterface(int position) {
        Toast.makeText(getContext(), "Long Interface", Toast.LENGTH_SHORT).show();
    }




    private void setValueToRecycler() {
        instaViewModel = ViewModelProviders.of(this).get(InstaViewModel.class);
        instaViewModel.getAllPosts().observe(this, new Observer<List<InstaObj>>() {
            @Override
            public void onChanged(List<InstaObj> instaObjs) {


                for (int i = 0; i < instaObjs.size(); i++){
                    lstPostData.add(new struct(instaObjs.get(i).getUsername(), instaObjs.get(i).getComments(), instaObjs.get(i).getInstaImage()));
                }

            }
        });


    }





}


