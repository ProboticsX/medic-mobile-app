package com.example.john.medic;

import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;

public class problem2 extends AppCompatActivity {

    ArrayList<String> userdetails=new ArrayList<>();
    ArrayList<String> problemdetails=new ArrayList<>();
    private DatabaseReference mDatabase;
    ListView lvproblem2;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adaptor;
    public static final String TAG="problem2";

    TextView text_tip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        problemdetails=getIntent().getStringArrayListExtra("problemdetails");

        lvproblem2=findViewById(R.id.lvproblem2);
        text_tip=findViewById(R.id.text_tip);

        adaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        lvproblem2.setAdapter(adaptor);

        lvproblem2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final TextView textView=view.findViewById(android.R.id.text1);

                new AlertDialog.Builder(problem2.this)
                        .setMessage("see general information of this medicine ?")
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(problem2.this,gerealinfo.class);
                                problemdetails.set(4,textView.getText().toString());
                                intent.putStringArrayListExtra("userdetails",problemdetails);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .create().show();
            }
        });

        mDatabase.child("problems").child(problemdetails.get(5)).child("tip").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                text_tip.setText("TIP:"+dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mDatabase.child("problems").child(problemdetails.get(5)).child("medicines").addListenerForSingleValueEvent(new ValueEventListener() {
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
