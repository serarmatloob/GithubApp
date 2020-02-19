package com.matloob.githubapp.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.matloob.githubapp.R;
import com.matloob.githubapp.databinding.ActivityDetailsBinding;
import com.matloob.githubapp.models.CommitResponse;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            CommitResponse commitResponse = (CommitResponse) bundle.getSerializable("object");

            if(commitResponse != null){
                binding.setViewModel(commitResponse);
            }
        }
    }
}
