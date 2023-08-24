package com.example.insphiredapp.EmployerActivity;

import static com.example.insphiredapp.R.color.skyBlue;
import static com.example.insphiredapp.R.color.white;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.insphiredapp.EmployerFragment.FavouriteFragment;
import com.example.insphiredapp.EmployerFragment.HomeFragment;
import com.example.insphiredapp.EmployerFragment.PaymentsFragment;
import com.example.insphiredapp.EmployerFragment.ProfileFragment;
import com.example.insphiredapp.R;
import com.example.insphiredapp.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;
    TextView heading,homeIconText,favouriteIconText,walletIconText,profileIconText;
    ImageView homeIcon,favouriteIcon,walletIcon,profileIcon;
    ConstraintLayout container;
    String Default,EmployrrID;
    private int term_condition=1;

LinearLayoutCompat home_layout,favourite_layout,wallet_layout,profile_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);
        inits();
        default_Load();
        homeIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.home));
        homeIcon.setColorFilter(getApplication().getResources().getColor(skyBlue));

        wallet_Fragment();
        profile_Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        Default = getIntent().getStringExtra("strDefault");

        Log.e( "Default: ", ""+ Default  );

        binding.homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragment homeFragment = new HomeFragment();
                replace_fragment(homeFragment);
                homeIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.home));
                homeIcon.setColorFilter(getApplication().getResources().getColor(skyBlue));
                favouriteIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.appoinment1));
                favouriteIcon.setColorFilter(getApplication().getResources().getColor(white));
                walletIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.wallet1));
                walletIcon.setColorFilter(getApplication().getResources().getColor(white));
                profileIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.profile1));
                profileIcon.setColorFilter(getApplication().getResources().getColor(white));



            }
        });

        binding.favouriteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FavouriteFragment favouriteFragment = new FavouriteFragment();
                replace_fragment(favouriteFragment);
                favouriteIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.appoinment));
                favouriteIcon.setColorFilter(getApplication().getResources().getColor(skyBlue));
                homeIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.house1));
                homeIcon.setColorFilter(getApplication().getResources().getColor(white));
                walletIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.wallet1));
                walletIcon.setColorFilter(getApplication().getResources().getColor(white));
                profileIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.profile1));
                profileIcon.setColorFilter(getApplication().getResources().getColor(white));

            }
        });


        binding.walletLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentsFragment paymentsFragment = new PaymentsFragment();
                replace_fragment(paymentsFragment);

                walletIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.wallet));
                walletIcon.setColorFilter(getApplication().getResources().getColor(skyBlue));;
                homeIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.house1));
                homeIcon.setColorFilter(getApplication().getResources().getColor(white));
                favouriteIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.appoinment1));
                favouriteIcon.setColorFilter(getApplication().getResources().getColor(white));
                profileIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.profile1));
                profileIcon.setColorFilter(getApplication().getResources().getColor(white));;


            }
        });




        binding.profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment = new ProfileFragment();
                replace_fragment(profileFragment);

                profileIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.profile));
                profileIcon.setColorFilter(getApplication().getResources().getColor(skyBlue));;
                homeIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.house1));
                homeIcon.setColorFilter(getApplication().getResources().getColor(white));;
                favouriteIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.appoinment1));
                favouriteIcon.setColorFilter(getApplication().getResources().getColor(white));
                walletIcon.setBackground(getApplication().getResources().getDrawable(R.drawable.wallet1));
                walletIcon.setColorFilter(getApplication().getResources().getColor(white));;


            }
        });

    }

    private void profile_Fragment() {
        ProfileFragment profileFragment =new ProfileFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,profileFragment);
        ft.commit();

    }

    private void wallet_Fragment() {
        PaymentsFragment paymentsFragment =new PaymentsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, paymentsFragment);
        ft.commit();


    }

    private void favourite_Fragment() {
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,favouriteFragment);
    }

    private void default_Load() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, homeFragment);
        ft.commit();

    }

    private void replace_fragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();


    }

    private void inits() {
        container = findViewById(R.id.container);
        home_layout = findViewById(R.id.home_layout);
        favourite_layout = findViewById(R.id.favourite_layout);
        wallet_layout = findViewById(R.id.wallet_layout);
        profile_layout = findViewById(R.id.profile_layout);
        homeIcon = findViewById(R.id.homeIcon);
        favouriteIcon = findViewById(R.id.favouriteIcon);
        walletIcon = findViewById(R.id.walletIcon);
        profileIcon = findViewById(R.id.profileIcon);
        homeIconText = findViewById(R.id.homeIconText);
        favouriteIconText = findViewById(R.id.favouriteIconText);
        walletIconText = findViewById(R.id.walletIconText);
        profileIconText = findViewById(R.id.profileIconText);
    }
}