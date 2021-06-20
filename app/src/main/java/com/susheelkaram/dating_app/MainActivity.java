package com.susheelkaram.dating_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.susheelkaram.dating_app.data.UserRepository;
import com.susheelkaram.dating_app.data.api.model.GenericResponse;
import com.susheelkaram.dating_app.databinding.ActivityMainBinding;
import com.susheelkaram.dating_app.ui.EnterOTPActivity;
import com.susheelkaram.dating_app.ui.model.UiEvent;
import com.susheelkaram.dating_app.ui.model.UiState;
import com.susheelkaram.dating_app.ui.viewmodel.MainViewModel;
import com.susheelkaram.dating_app.ui.viewmodel.MainViewModelFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new MainViewModelFactory(UserRepository.getInstance())).get(MainViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.getState().observe(this, new Observer<UiState<GenericResponse>>() {
            @Override
            public void onChanged(UiState<GenericResponse> genericResponseUiState) {
                switch (genericResponseUiState.getState()) {
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
                        Toast.makeText(MainActivity.this, uiEvent.data, Toast.LENGTH_SHORT).show();
                        break;
                    case Navigate:
                        Intent openOtpActivityIntent = new Intent(MainActivity.this, EnterOTPActivity.class);
                        openOtpActivityIntent.putExtra(EnterOTPActivity.ARG_PHONE, uiEvent.data);
                        startActivity(openOtpActivityIntent);
                        break;
                }
            }
        });
    }
}