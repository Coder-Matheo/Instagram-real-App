package rob.instagramapprealdemo.TabLayoutPackage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> lstFragments = new ArrayList<>();
    List<String> lstTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager sfm) {
        super(sfm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lstFragments.get(position);
    }

    @Override
    public int getCount() {
        return lstTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String titleName){
        lstFragments.add(fragment);
        lstTitles.add(titleName);
    }



}
