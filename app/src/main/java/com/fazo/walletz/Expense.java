package com.fazo.walletz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.Nullable;


public class Expense extends Fragment {

    private DatabaseManager dbManager;
    EditText expense_amount;
    EditText expense_cat;

    private OnFragmentInteractionListener mListener;

    public Expense() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onFragmentInteraction("Expense");
        }
        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        dbManager = new DatabaseManager(getActivity());

        expense_amount = (EditText) view.findViewById(R.id.etExpAmt);
        expense_cat = (EditText) view.findViewById(R.id.etExpCat);
        Button save_expense = (Button) view.findViewById(R.id.btnAddExp);

        //set onClickListener to button
        save_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount = expense_amount.getText().toString();
                double amountDouble = Double.parseDouble(amount);

                String category = expense_cat.getText().toString();

                ExpenseModel expense = new ExpenseModel(amountDouble, category);
                dbManager.createExpense(expense);

                BalanceModel balance = new BalanceModel(amount);
                dbManager.createBalance(balance);

                expense_amount.setText("");
                expense_cat.setText("");
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
