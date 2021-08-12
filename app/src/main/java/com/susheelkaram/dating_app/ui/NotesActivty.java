package com.susheelkaram.dating_app.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.susheelkaram.dating_app.R;
import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.databinding.ActivityNotesActivtyBinding;
import com.susheelkaram.dating_app.ui.adapter.HomePagerAdapter;
import com.susheelkaram.dating_app.ui.viewmodel.NotesViewModel;
import com.susheelkaram.dating_app.ui.viewmodel.NotesViewModelFactory;

import org.jetbrains.annotations.NotNull;

public class NotesActivty extends AppCompatActivity {
    public static String ARG_TOKEN = "token";

    ImageView profileImg;
    ActivityNotesActivtyBinding binding;
    NotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes_activty);
        viewModel = new ViewModelProvider(this, new NotesViewModelFactory(UserRepository.getInstance())).get(NotesViewModel.class);
        String token = getIntent().getStringExtra(ARG_TOKEN);
        viewModel.token = token;
        initializeUi();
    }

    void initializeUi() {
        binding.pagerScreens.setAdapter(new HomePagerAdapter(this));
        binding.bottomNavMain.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_discover:
                                binding.pagerScreens.setCurrentItem(0, false);
                                break;
                            default:
                                binding.pagerScreens.setCurrentItem(1, false);
                                break;
                        }
                        return true;
                    }
                }
        );

        binding.bottomNavMain.getOrCreateBadge(R.id.menu_notes).setNumber(9);
        BadgeDrawable matchesBadge = binding.bottomNavMain.getOrCreateBadge(R.id.menu_matches);
        matchesBadge.setMaxCharacterCount(3);
        matchesBadge.setNumber(100);
    }
}