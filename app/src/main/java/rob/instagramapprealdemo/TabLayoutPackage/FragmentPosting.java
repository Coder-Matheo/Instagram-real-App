package rob.instagramapprealdemo.TabLayoutPackage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rob.instagramapprealdemo.R;


public class FragmentPosting extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<struct> lstPostData;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_posting, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstPostData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstPostData = new ArrayList<>();
        lstPostData.add(new struct("Mattheo", "Heute war so Cool Programming Day", R.drawable.fran1));
        lstPostData.add(new struct("Shahram", "Heute war so Cool Programming Day", R.drawable.fran2));
        lstPostData.add(new struct("Ali", "Heute war so Cool Programming Day", R.drawable.fran3));
        lstPostData.add(new struct("Mattheo", "Heute war so Cool Programming Day", R.drawable.fran4));


    }
}