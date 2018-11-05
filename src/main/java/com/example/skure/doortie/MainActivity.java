package com.example.skure.doortie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.color.holo_green_light;
import static android.R.color.holo_red_light;

public class MainActivity extends AppCompatActivity{

    ImageView statImage;

    TextView statText;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mStatusRef = mRootRef.child("status");
     //true = dont come in, false = come in

  /*  public  void changeStatus(boolean Stat) {
        if (!Stat) {
            statImageGreen.setVisibility(View.INVISIBLE);
            statImageRed.setVisibility(View.VISIBLE);
            statText.setText("dont come in");
            stat = true;
        } else {
            statImageGreen.setVisibility(View.VISIBLE);
            statImageRed.setVisibility(View.INVISIBLE);
            statText.setText("come in");
            stat = false;
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart(){
        super.onStart();
        statText = (TextView) findViewById(R.id.statusText);                    // declare ui elements
        statImage = (ImageView) findViewById(R.id.statusImage);

        Button statButton = (Button) findViewById(R.id.statusButton);         //button listener
        statButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mStatusRef.addListenerForSingleValueEvent(new ValueEventListener() {  //sets DB value
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean status = dataSnapshot.getValue(boolean.class);
                        if(status){
                            mStatusRef.setValue(false);
                        }
                        else mStatusRef.setValue(true);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });




        mStatusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {            //constant DB listener
                boolean status = dataSnapshot.getValue(boolean.class);
                if(status){

                    statImage.setImageResource(holo_green_light);            // reads DB value
                    statText.setText("come in");

                }
                else{
                    statImage.setImageResource(holo_red_light);
                    statText.setText("dont come in");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
