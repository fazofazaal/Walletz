package com.fazo.walletz;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.jetbrains.annotations.Nullable;


public class Income extends Fragment {

    private DatabaseManager dbManager;
    TextView tv_test;
    EditText income_amount;

    private OnFragmentInteractionListener mListener;

    public Income() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mListener != null) {
            mListener.onFragmentInteraction("Income");
        }
        return inflater.inflate(R.layout.fragment_income, container, false);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        dbManager = new DatabaseManager(getActivity());

        income_amount = (EditText) view.findViewById(R.id.iETAmount);
        tv_test = (TextView) view.findViewById(R.id.tvTest);
        Button save_income = (Button) view.findViewById(R.id.buttonIncAdd);

        //set onClickListener to button
        save_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = income_amount.getText().toString();
                double amountDouble = Double.parseDouble(amount);

                IncomeModel income = new IncomeModel(amountDouble);
                dbManager.createIncome(income);

                BalanceModel balance = new BalanceModel(amountDouble);
                dbManager.createBalance(balance);

                /*IncomeModel response = dbManager.getData();

                String tvValue = Double.toString(response.income_amount);

                tv_test.setText(tvValue);*/
                //updateBalance();

                income_amount.setText("");
            }
        });



    }


    /*public void updateBalance(){
        //String dbString = dbManager.incometoString();
        //tv_test.setText(dbString);

    }*/

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