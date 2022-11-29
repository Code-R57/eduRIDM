package com.ridm.eduRIDM;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ridm.eduRIDM.databinding.ActivityMainBinding;
import com.ridm.eduRIDM.model.firebase.FirebaseQueries;
import com.ridm.eduRIDM.model.room.RoomRepository;
import com.ridm.eduRIDM.screen.onboarding.WelcomeScreenFragment;

public class MainActivity extends AppCompatActivity implements WelcomeScreenFragment.Listener {

    public static RoomRepository roomRepository;
    public static GoogleSignInClient mGoogleSignInClient;
    public static int RC_SIGN_IN = 57;
    public static FirebaseAuth mAuth;
    public static GoogleSignInAccount account;
    public static FirebaseQueries firebaseQueries;
    public static DocumentSnapshot userInfo;
    public BottomNavigationView bottomView;
    public Toolbar toolbar;
    NavController navController;
    FirebaseFirestore database;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Google Sign In Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            database.collection("users").document(account.getEmail())
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()) {
                                DocumentSnapshot taskResult = task.getResult();

                                if(!taskResult.contains("Name")) {
                                    navController.navigate(R.id.registerFragment);
                                }
                                else {
                                    userInfo = taskResult;
                                    navController.navigate(R.id.homeScreenFragment);
                                }
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Firebase Firestore
        database = FirebaseFirestore.getInstance();

        firebaseQueries = new FirebaseQueries(database, this);

        // Google Sign In
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        roomRepository = new RoomRepository(getApplication());

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        toolbar = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);

        NavigationUI.setupActionBarWithNavController(this, navController);

        bottomView = findViewById(R.id.bottomNav);
        NavigationUI.setupWithNavController(bottomView, navController);
        NavigationUI.setupWithNavController(toolbar, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.homeScreenFragment || navDestination.getId() == R.id.plannerFragment || navDestination.getId() == R.id.myAcadsFragment) {
                    bottomView.setVisibility(View.VISIBLE);
                } else {
                    bottomView.setVisibility(View.GONE);
                }
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {

                toolbar.setTitle("eduRIDM");
                if (navDestination.getId() == R.id.welcomeScreenFragment) {
                    toolbar.setVisibility(View.GONE);
                } else if (navDestination.getId() == R.id.stopwatchScreenFragment || navDestination.getId() == R.id.registerFragment || navDestination.getId() == R.id.addTimetableFragment) {
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.app_primary)));
                    toolbar.setNavigationIcon(null);
                } else if (navDestination.getId() == R.id.homeScreenFragment || navDestination.getId() == R.id.plannerFragment || navDestination.getId() == R.id.myAcadsFragment) {
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                    toolbar.setNavigationIcon(null);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        switch (item.getItemId()) {
            case R.id.settingsFragment:
                navController.navigate(R.id.settingsFragment);
                return true;
            case R.id.profileScreenFragment:
                navController.navigate(R.id.profileScreenFragment);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.stopwatchScreenFragment || navDestination.getId() == R.id.registerFragment || navDestination.getId() == R.id.addTimetableFragment) {
                    MenuItem item1 = menu.findItem(R.id.settingsFragment);
                    if (item1 != null) item1.setVisible(false);

                    MenuItem item2 = menu.findItem(R.id.profileScreenFragment);
                    if (item2 != null) item2.setVisible(false);
                } else if (navDestination.getId() == R.id.profileScreenFragment) {
                    MenuItem item1 = menu.findItem(R.id.settingsFragment);
                    if (item1 != null) item1.setVisible(true);

                    MenuItem item2 = menu.findItem(R.id.profileScreenFragment);
                    if (item2 != null) item2.setVisible(false);
                } else if (navDestination.getId() == R.id.settingsFragment) {
                    MenuItem item1 = menu.findItem(R.id.settingsFragment);
                    if (item1 != null) item1.setVisible(false);

                    MenuItem item2 = menu.findItem(R.id.profileScreenFragment);
                    if (item2 != null) item2.setVisible(true);
                } else {
                    MenuItem item1 = menu.findItem(R.id.settingsFragment);
                    if (item1 != null) item1.setVisible(true);

                    MenuItem item2 = menu.findItem(R.id.profileScreenFragment);
                    if (item2 != null) item2.setVisible(true);
                }
            }
        });
        return true;
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnSuccessListener(this, authResult -> {
            boolean isNewUser = authResult.getAdditionalUserInfo().isNewUser();
            if (isNewUser) {
                navController.navigate(R.id.registerFragment);
            } else {
                navController.navigate(R.id.homeScreenFragment);
            }
        }).addOnFailureListener(this, e -> Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void signInClicked() {
        Intent intent = MainActivity.mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, MainActivity.RC_SIGN_IN);
    }
}