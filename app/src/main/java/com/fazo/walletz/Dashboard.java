package com.fazo.walletz;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

public class Dashboard extends Fragment {

    DatabaseManager dbManager;
    TextView balanceAmount;

    // NOTE: Removed Some unwanted Boiler Plate Codes
    private OnFragmentInteractionListener mListener;

    public Dashboard() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);


        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("Dashboard");
        }

        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        dbManager = new DatabaseManager(getActivity());

        balanceAmount = (TextView) view.findViewById(R.id.textViewAmount);

        //get balance data from database
        BalanceModel balance = dbManager.getBalData();
        String balupdated = Double.toString( balance.getBalance_amount() );
        System.out.println("retreived = "+balance.getBalance_amount());


        balanceAmount.setText(balupdated);


    }

    /*public void updateBalance(){
        String dbString = dbManager.balancetoString();
        showBalance.setText(dbString);
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            // NOTE: This is the part that usually gives you the error
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Be sure to add (implements) class.OnFragmentInteractionListener to implements in main activity
    //or app will crash
    public interface OnFragmentInteractionListener {
        // NOTE : We changed the Uri to String.
        void onFragmentInteraction(String title);
    }
}

