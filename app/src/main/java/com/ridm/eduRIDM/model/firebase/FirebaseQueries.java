package com.ridm.eduRIDM.model.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseQueries {

    public final FirebaseFirestore database;
    private final Context context;

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
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public List<QueryDocumentSnapshot> getCourses() {
        CollectionReference collectionReference = database.collection("courses");

        List<QueryDocumentSnapshot> coursesList = new ArrayList<>();

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot taskResult = task.getResult();
                    for (QueryDocumentSnapshot document : taskResult) {
                        coursesList.add(document);
                    }
                } else {
                    Toast.makeText(context, "Unable to get courses.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return coursesList;
    }

    public List<QueryDocumentSnapshot> getAllSections(String course) {
        CollectionReference collectionReference = database.collection("courses").document(course).collection("Sections");

        List<QueryDocumentSnapshot> sectionsList = new ArrayList<>();

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot taskResult = task.getResult();
                    for (QueryDocumentSnapshot document : taskResult) {
                        sectionsList.add(document);
                    }
                } else {
                    Toast.makeText(context, "Unable to get sections.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return sectionsList;
    }

    public DocumentSnapshot getSectionDetails(String course, String section) {
        DocumentReference documentReference = database.collection("courses").document(course)
                .collection("Sections").document(section);

        final DocumentSnapshot[] sectionDetails = new DocumentSnapshot[1];

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    sectionDetails[0] = task.getResult();
                } else {
                    Toast.makeText(context, "Unable to get section details.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return sectionDetails[0];
    }

    public void addCourseToUserTimeTable(String userKey, CourseClass courseClass, String sectionType) {
        Map<String, Object> course = new HashMap<>();
        String courseCollection = "";

        course.put("CourseCode", courseClass.getCourseCode());
        course.put("DeptCode", courseClass.getDeptCode());

        switch (sectionType) {
            case "Lecture":
                courseCollection = courseClass.getDeptCode() + " " + courseClass.getCourseCode() + " " + courseClass.getLecture();
                course.put("Section", courseClass.getLecture());
                course.put("Days", courseClass.getLectureDetail().getDays());
                course.put("Duration", courseClass.getLectureDetail().getDuration());
                course.put("Time", courseClass.getLectureDetail().getTime());
                break;

            case "Tutorial":
                courseCollection = courseClass.getDeptCode() + " " + courseClass.getCourseCode() + " " + courseClass.getTutorial();
                course.put("Section", courseClass.getTutorial());
                course.put("Days", courseClass.getTutorialDetail().getDays());
                course.put("Duration", courseClass.getTutorialDetail().getDuration());
                course.put("Time", courseClass.getTutorialDetail().getTime());
                break;

            case "Lab":
                courseCollection = courseClass.getDeptCode() + " " + courseClass.getCourseCode() + " " + courseClass.getLab();
                course.put("Section", courseClass.getLab());
                course.put("Days", courseClass.getLabDetail().getDays());
                course.put("Duration", courseClass.getLabDetail().getDuration());
                course.put("Time", courseClass.getLabDetail().getTime());
                break;
        }

        database.collection("users").document(userKey).collection("Courses")
                .document(courseCollection)
                .set(course)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Section not added to Database", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
    }
}
