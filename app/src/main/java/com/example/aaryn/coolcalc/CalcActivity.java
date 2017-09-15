package com.example.aaryn.coolcalc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;        //does not work
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import Fragments.NightModeFragment;

import static com.example.aaryn.coolcalc.R.id.fragment_container;

public class CalcActivity extends Activity implements View.OnClickListener, NightModeFragment.OnFragmentInteractionListener {

    TextView resultsView;


    public enum Operation {
        ADD,SUBTRACT,DIVIDE,MULTIPLY,EQUAL
    }

    String runningNumber = "";
    String leftValueStr ="";
    String rightValueStr = "";

    Operation currentOperation;
    int result = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {//like viewdidload ios
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
//
//        setContentView(R.layout.activity_calc);
//        FragmentManager manager = getSupportFragmentManager();
//        Fragment fragment = manager.findFragmentById(fragment_container);
//
//        if (fragment == null) {             //need this or get crash
//            fragment = new Fragment();
//            FragmentTransaction transaction =  manager.beginTransaction();
//            transaction.add(fragment_container,fragment);
//            transaction.commit();
//        }

        Button oneBtn = (Button)findViewById(R.id.oneBtn);
        Button twoBtn = (Button)findViewById(R.id.twoBtn);
        Button threeBtn = (Button)findViewById(R.id.threeBtn);
        Button fourBtn = (Button)findViewById(R.id.fourBtn);
        Button fiveBtn = (Button)findViewById(R.id.fiveBtn);
        Button sixBtn = (Button)findViewById(R.id.sixBtn);
        Button sevenBtn = (Button)findViewById(R.id.sevenBtn);
        Button eightBtn = (Button)findViewById(R.id.eightBtn);
        Button nineBtn = (Button)findViewById(R.id.nineBtn);
        Button zeroBtn = (Button)findViewById(R.id.zeroBtn);


//        Button nmBtn = (Button)findViewById(R.id.nightModeBtn);
//        nmBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  NightModeFragment nmFragment = new NightModeFragment();
//              //  this.getSupportFragmentManager().beginTransaction().add(nmFragment, "fragment_container").commit();
//                //  CalcActivity mainActivity = (CalcActivity) getActivity();
//                //load next screen
//                // mainActivity.load;
//            }
//        });

        ImageButton calcBtn = (ImageButton) findViewById(R.id.calcBtn);
        ImageButton divideBtn = (ImageButton) findViewById(R.id.divideBtn);
        ImageButton multiplyBtn = (ImageButton) findViewById(R.id.multiplyBtn);
        ImageButton addBtn = (ImageButton) findViewById(R.id.addBtn);
        ImageButton subtractBtn = (ImageButton) findViewById(R.id.subtractBtn);

        Button clearBtn = (Button) findViewById(R.id.clearBtn);

        resultsView = (TextView) findViewById(R.id.resultsText);
        resultsView.setText("");


        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            numberPressed(1);
            }
        });
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            numberPressed(2);
            }
        });
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(3);
            }
        });
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(4);
            }
        });
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(5);
            }
        });
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(6);
            }
        });
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(7);
            }
        });
        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(8);
            }
        });
        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(9);
            }
        });
        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                numberPressed(0);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                processOperation(Operation.ADD);
            }
        });
        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                processOperation(Operation.SUBTRACT);
            }
        });
        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                processOperation(Operation.MULTIPLY);
            }
        });
        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                processOperation(Operation.DIVIDE);
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                leftValueStr="";
                rightValueStr="";
                result=0;
                runningNumber="";
                currentOperation=null;
                resultsView.setText("0");
            }
        });
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                processOperation(Operation.EQUAL);
            }
        });
    }

    void processOperation(Operation operation){
        if(currentOperation!=null){
            if(runningNumber!= ""){
                rightValueStr = runningNumber;
                runningNumber = "";

                switch (currentOperation){
                    case ADD:
                        result = Integer.parseInt(leftValueStr)+Integer.parseInt(rightValueStr);
                        break;
                    case SUBTRACT:
                        result = Integer.parseInt(leftValueStr)-Integer.parseInt(rightValueStr);
                        break;
                    case MULTIPLY:
                        result = Integer.parseInt(leftValueStr)*Integer.parseInt(rightValueStr);
                        break;
                    case DIVIDE:
                        result = Integer.parseInt(leftValueStr)/Integer.parseInt(rightValueStr);
                        break;
                }
                leftValueStr = String.valueOf(result);
                resultsView.setText(leftValueStr);
            }
        } else {
            leftValueStr = runningNumber;
            runningNumber = "";
        }
        currentOperation = operation;
    }

    void numberPressed(int number){
        runningNumber+= String.valueOf(number);
        resultsView.setText(runningNumber);
    }

    public void loadPickPowerScreen(){
      //  NightModeFragment nmFragment = new NightModeFragment();
        //this will add to backstack or replace it - good way
        //this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,nmFragment).addToBackStack(null).commit();
        //this will add to the screen - good for puttin ontop of existing screen
        // this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,nmFragment).commit();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }//
}
