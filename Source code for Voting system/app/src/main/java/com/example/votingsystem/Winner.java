package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Winner extends AppCompatActivity {
    DatabaseHelper db;
    int androidvotes;
    int iosvotes;
    TextView winner;
    TextView perand;
    TextView perios;
    TextView totalvotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        winner=findViewById(R.id.winner);
        perand=findViewById(R.id.perand);
        perios=findViewById(R.id.perios);
        totalvotes=findViewById(R.id.totalvotes);
        db = new DatabaseHelper(this);

        androidvotes = db.resultsandroid();
        iosvotes= db.resultsios();

        double and= (androidvotes*1.0/(androidvotes+iosvotes))*100;
        double ios= (iosvotes*1.0/(androidvotes+iosvotes))*100;
        if(androidvotes>iosvotes){
            winner.setText("Android won by "+(androidvotes-iosvotes) + " votes");
        }
        else if(iosvotes>androidvotes){
            winner.setText("iOS won by "+(iosvotes-androidvotes));

        }
        else
            winner.setText("Both Android and iOS have equal number of votes");
        totalvotes.setText((androidvotes+iosvotes) + " users participated in voting");
        perand.setText(String.format("Android got %.2f",and) + "% of votes");
        perios.setText(String.format("iOS got %.2f",ios) + "% of votes");
    }



}