package com.ridm.eduRIDM.model.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ridm.eduRIDM.MainActivity;

import java.util.Map;

public class FirebaseQueries {

    private FirebaseFirestore database;
    private Context context;

    public FirebaseQueries(FirebaseFirestore database, Context context) {
        this.database = database;
        this.context = context;
    }

    public void createUser(String userKey, Map<String, Object> user) {
        database.collection("users").document(userKey)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
