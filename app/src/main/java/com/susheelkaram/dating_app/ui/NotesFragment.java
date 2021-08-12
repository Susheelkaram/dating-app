package com.susheelkaram.dating_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.susheelkaram.dating_app.R;
import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.data.api.model.ProfileResponse;
import com.susheelkaram.dating_app.data.api.model.profile.Likes;
import com.susheelkaram.dating_app.data.api.model.profile.ProfilesItem;
import com.susheelkaram.dating_app.databinding.FragmentNotesBinding;
import com.susheelkaram.dating_app.ui.components.ProfileGlimpseData;
import com.susheelkaram.dating_app.ui.model.UiEvent;
import com.susheelkaram.dating_app.ui.model.UiState;
import com.susheelkaram.dating_app.ui.viewmodel.NotesViewModel;
import com.susheelkaram.dating_app.ui.viewmodel.NotesViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotesFragment extends Fragment {
    FragmentNotesBinding binding;
    NotesViewModel viewModel;

    public NotesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity(), new NotesViewModelFactory(UserRepository.getInstance())).get(NotesViewModel.class);


        viewModel.getEventsLiveData().observe(getActivity(), new Observer<UiEvent<String>>() {
            @Override
            public void onChanged(UiEvent<String> uiEvent) {
                switch (uiEvent.uiEventType)  {
                    case Toast:
                        Toast.makeText(getActivity(), uiEvent.data, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        viewModel.getState().observe(getActivity(), new Observer<UiState<ProfileResponse>>() {
            @Override
            public void onChanged(UiState<ProfileResponse> profileResponseUiState) {
                switch (profileResponseUiState.getState()) {
                    case Loading:
//                        binding.progressHorizontal.setVisibility(View.VISIBLE);
                        break;
                    case Success:
                        setData(profileResponseUiState.getData());
//                        binding.progressHorizontal.setVisibility(View.GONE);
                        break;
                    case Failure:
//                        binding.progressHorizontal.setVisibility(View.GONE);
                        break;
                }

            }
        });
        initializeUi();
    }

    void setData(ProfileResponse profileResponse) {
        List<ProfilesItem> profilesItemList = profileResponse == null ? null : profileResponse.getInvites().getProfiles();
        if (profilesItemList != null || !profilesItemList.isEmpty()) {
            ProfilesItem profilesItem = profilesItemList.get(0);
            String name = profilesItem.getGeneralInformation().getFirstName();
            binding.profileCard.setData(new ProfileGlimpseData(name, "Tap to review 50+ notes", profilesItem.getPhotos().get(0).getPhoto(), 0));

            Likes likes = profileResponse.getLikes();
            if(likes.getLikesReceivedCount() >= 2) {
                List<ProfilesItem> likesProfiles = likes.getProfiles();
                ProfilesItem profileOne = likesProfiles.get(0);
                ProfilesItem profileTwo = likesProfiles.get(1);
                binding.profileCardOne.setData(new ProfileGlimpseData(profileOne.getFirstName(), null, profileOne.getAvatar(), 0));
                binding.profileCardTwo.setData(new ProfileGlimpseData(profileTwo.getFirstName(), null, profileTwo.getAvatar(), 0));
            }
        }
        else {

        }
    }

    void initializeUi() {
        viewModel.loadProfiles();

//        binding.profileCard.setData(new ProfileGlimpseData("Meena, 23", "Tap to review 50+ notes", null, R.drawable.photo_1));
//        binding.profileCardOne.setData(new ProfileGlimpseData("Teena", "", null, R.drawable.photo_2));
//        binding.profileCardTwo.setData(new ProfileGlimpseData("Beena", "", null, R.drawable.photo_3));
    }
}