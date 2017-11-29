package com.fazo.walletz;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;


public class Income extends Fragment {

    private DatabaseManager dbManager;
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
        Button save_income = (Button) view.findViewById(R.id.buttonIncAdd);

        //set onClickListener to button
        save_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = income_amount.getText().toString();
                double amountDouble = Double.parseDouble(amount);

                IncomeModel income = new IncomeModel(amountDouble);
                dbManager.createIncome(income);


                BalanceModel update =  dbManager.getBalData();

                //get previous balance amount and calculate new balance
                String balupdated = Double.toString( update.getBalance_amount() );
                System.out.println("retreived = "+update.getBalance_amount());

                Double newBalance = update.getBalance_amount() + amountDouble;


                BalanceModel balance = new BalanceModel(newBalance);
                dbManager.createBalance(balance);

                //Create objects of IncomeModel and BalanceModel classes and assign DatabaseManager getData methods.
                IncomeModel response = dbManager.getIncData();


                /*//Create a new Bundle object and put string balance amount to bundle
                Bundle bundle = new Bundle();
                bundle.putString( "Balance Amount", balupdated );

                //Declare new FragmentManager object that will manage the fragment transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                //create a new dashboard object and set the arguments to the object
                Dashboard dashboard = new Dashboard();
                dashboard.setArguments(bundle);

                //Change the fragment to dashboard once button pressed
                fragmentTransaction.replace(R.id.mainFrame, dashboard);
                fragmentTransaction.commit();
*/
                //get income amount
                String toastValue = Double.toString( response.getIncome_amount() );
                System.out.println( "retreived = "+response.getIncome_amount() );

                //Show toast for income add and clear EditText
                Toast.makeText(getActivity(), "Added an income of RM " + toastValue, Toast.LENGTH_SHORT).show();
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