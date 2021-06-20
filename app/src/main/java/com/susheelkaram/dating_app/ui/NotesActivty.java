package com.susheelkaram.dating_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.badge.BadgeDrawable;
import com.susheelkaram.dating_app.R;
import com.susheelkaram.dating_app.databinding.ActivityNotesActivtyBinding;
import com.susheelkaram.dating_app.ui.components.ProfileGlimpseData;

public class NotesActivty extends AppCompatActivity {
    ImageView profileImg;
    ActivityNotesActivtyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes_activty);
        initializeUi();
    }

    void initializeUi() {
        binding.profileCard.setData(new ProfileGlimpseData("Meena, 23", "Tap to review 50+ notes", null, R.drawable.photo_1));
        binding.profileCardOne.setData(new ProfileGlimpseData("Teena", "", null, R.drawable.photo_2));
        binding.profileCardTwo.setData(new ProfileGlimpseData("Beena", "", null, R.drawable.photo_3));
        binding.bottomNavMain.getOrCreateBadge(R.id.menu_notes).setNumber(9);
        BadgeDrawable matchesBadge = binding.bottomNavMain.getOrCreateBadge(R.id.menu_matches);
        matchesBadge.setMaxCharacterCount(3);
        matchesBadge.setNumber(100);
    }
}