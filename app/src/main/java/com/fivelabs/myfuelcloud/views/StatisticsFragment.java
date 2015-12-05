package com.fivelabs.myfuelcloud.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fivelabs.myfuelcloud.R;
import com.fivelabs.myfuelcloud.api.statistic;
import com.fivelabs.myfuelcloud.model.Statistic;
import com.fivelabs.myfuelcloud.util.Global;
import com.fivelabs.myfuelcloud.util.Session;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatisticsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mProgressView;
    private View myInflatedView;

    private TextView textViewPriceAmountAverage;
    private TextView  textViewFuelAmountAverage;
    private TextView  textViewFuelConsumptionAverage;
    private TextView  textViewCostPerKilometer;
    private TextView  textViewPreviousDistanceAverage;
    private TextView  textViewGasPriceAverage;

    private OnFragmentInteractionListener mListener;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticsFragment newInstance(String param1, String param2) {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_statistics, container, false);
        mProgressView = myInflatedView.findViewById(R.id.login_vehicles_progress);

        textViewPriceAmountAverage = (TextView) myInflatedView.findViewById(R.id.price_amount_average);
        textViewFuelAmountAverage = (TextView) myInflatedView.findViewById(R.id.fuel_amount_average);
        textViewFuelConsumptionAverage = (TextView) myInflatedView.findViewById(R.id.fuel_consumption_average);
        textViewCostPerKilometer = (TextView) myInflatedView.findViewById(R.id.cost_per_kilometer);
        textViewPreviousDistanceAverage = (TextView) myInflatedView.findViewById(R.id.previous_distance_average);
        textViewGasPriceAverage = (TextView) myInflatedView.findViewById(R.id.gas_price_average);

        loadStatistics();

        // Inflate the layout for this fragment
        return myInflatedView;
    }

    private void loadStatistics(){

        showProgress(true);

        RestAdapter restAdapter = (new RestAdapter.Builder())
                .setEndpoint(Global.API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Authorization", Session.getsUser().getToken());
                    }
                })
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i("RETROFIT", msg);
                    }
                }).build();

        statistic statistic = restAdapter.create(statistic.class);

        statistic.getStatistics(new Callback<Statistic>() {
            @Override
            public void success(Statistic statistic, Response response) {
                Session.setsStatistic(statistic);

                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);

                textViewPriceAmountAverage.setText(" " + df.format(Session.getsStatistic().getPrice_amount_average()) + " €");
                textViewFuelAmountAverage.setText(" " + df.format(Session.getsStatistic().getFuel_amount_average()) + " L");
                textViewFuelConsumptionAverage.setText(" " + df.format(Session.getsStatistic().getFuel_consumption_average()) + " L/100Km");
                textViewCostPerKilometer.setText(" " + df.format(Session.getsStatistic().getCost_per_kilometer()) + " €");
                textViewPreviousDistanceAverage.setText(" " + df.format(Session.getsStatistic().getPrevious_distance_average()) + " Km");
                textViewGasPriceAverage.setText(" " + df.format(Session.getsStatistic().getGas_price_average()) + " €");

                showProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                showProgress(false);

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
