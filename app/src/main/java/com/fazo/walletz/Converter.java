package com.fazo.walletz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

public class Converter extends Fragment {

    EditText dollarField;


    private OnFragmentInteractionListener mListener;

    public Converter() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mListener != null) {
            mListener.onFragmentInteraction("Currency Converter");
        }
        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState ) {
        dollarField = (EditText) view.findViewById(R.id.dollarField);
        Button convert = (Button) view.findViewById(R.id.btnConvert);
        //set onClickListener to button

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double dollarAmount = Double.parseDouble(dollarField.getText().toString());
                Double myrAmount = dollarAmount * 4;

                Toast.makeText(getActivity(), "MYR " + myrAmount.toString(), Toast.LENGTH_LONG).show();

            }
        });



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}