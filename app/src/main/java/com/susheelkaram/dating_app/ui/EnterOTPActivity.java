package com.susheelkaram.dating_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.susheelkaram.dating_app.MainActivity;
import com.susheelkaram.dating_app.R;
import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.data.api.model.AuthResponse;
import com.susheelkaram.dating_app.data.api.model.GenericResponse;
import com.susheelkaram.dating_app.databinding.ActivityEnterOtpActivityBinding;
import com.susheelkaram.dating_app.databinding.ActivityMainBinding;
import com.susheelkaram.dating_app.ui.model.UiEvent;
import com.susheelkaram.dating_app.ui.model.UiState;
import com.susheelkaram.dating_app.ui.viewmodel.MainViewModel;
import com.susheelkaram.dating_app.ui.viewmodel.MainViewModelFactory;
import com.susheelkaram.dating_app.ui.viewmodel.VerifyOtpViewModel;
import com.susheelkaram.dating_app.ui.viewmodel.VerifyOtpViewModelFactory;

public class EnterOTPActivity extends AppCompatActivity {
    public static String ARG_PHONE = "argPhone";
    ActivityEnterOtpActivityBinding binding;
    VerifyOtpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_otp_activity);
        viewModel = new ViewModelProvider(this, new VerifyOtpViewModelFactory(UserRepository.getInstance())).get(VerifyOtpViewModel.class);
        binding.setViewmodel(viewModel);

        String phone = getIntent().getStringExtra(ARG_PHONE);
        viewModel.phone = phone;

        viewModel.getState().observe(this, new Observer<UiState<AuthResponse>>() {
            @Override
            public void onChanged(UiState<AuthResponse> authResponseUiState) {
                switch (authResponseUiState.getState()) {
                    case Loading:
                        binding.progressHorizontal.setVisibility(View.VISIBLE);
                        break;
                    case Success:
                        binding.progressHorizontal.setVisibility(View.GONE);
                        break;
                    case Failure:
                        binding.progressHorizontal.setVisibility(View.GONE);
                        break;
                }
            }
        });

        viewModel.getEventsLiveData().observe(this, new Observer<UiEvent<String>>() {
            @Override
            public void onChanged(UiEvent<String> uiEvent) {
                switch (uiEvent.uiEventType)  {
                    case Toast:
                        Toast.makeText(EnterOTPActivity.this, uiEvent.data, Toast.LENGTH_SHORT).show();
                        break;
                    case Navigate:
                        Intent openOtpActivityIntent = new Intent(EnterOTPActivity.this, NotesActivty.class);
                        openOtpActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        openOtpActivityIntent.putExtra(NotesActivty.ARG_TOKEN, uiEvent.data);
                        startActivity(openOtpActivityIntent);
                }
            }
        });
    }
}