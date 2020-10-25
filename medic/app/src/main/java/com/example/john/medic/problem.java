package com.example.john.medic;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

public class problem extends AppCompatActivity {

    private DatabaseReference mDatabase;
    ArrayList<String> arrayList = new ArrayList<>();
    public static final String TAG="problem";
problemadaptor adaptor;
ArrayList<String> userdetails=new ArrayList<>();
ArrayList<String> problemdetails=new ArrayList<>();
    ListView lvproblem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        userdetails=getIntent().getStringArrayListExtra("userdetails");
        problemdetails.add(0,userdetails.get(0));
        problemdetails.add(1,userdetails.get(1));

        problemdetails.add(2,userdetails.get(2));
        problemdetails.add(3,userdetails.get(3));
        problemdetails.add(4,"");



        lvproblem=findViewById(R.id.lvproblem);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        adaptor=new problemadaptor(arrayList,this);
        lvproblem.setAdapter(adaptor);

        lvproblem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final TextView textView= view.findViewById(R.id.diseasename);

                new AlertDialog.Builder(problem.this)
                        .setMessage("view medicines and tips related to this problem ?")
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(problem.this,problem2.class);
                                problemdetails.add(5,textView.getText().toString());
                                intent.putStringArrayListExtra("problemdetails",problemdetails);
                                startActivity(intent);
                            }
                        }).create().show();


            }
        });



        mDatabase.child("problems").addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    arrayList.add(postSnapshot.getKey().toString());
                    adaptor.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        }
}
