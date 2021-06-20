package com.susheelkaram.dating_app.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.susheelkaram.dating_app.ui.BlankFragment;
import com.susheelkaram.dating_app.ui.NotesFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class HomePagerAdapter extends
        FragmentStateAdapter {

    List<Fragment> screens = Arrays.asList(new NotesFragment(), new BlankFragment());

    public HomePagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return screens.get(0);
        } else return screens.get(1);
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
