package com.fivelabs.myfuelcloud.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fivelabs.myfuelcloud.R;
import com.fivelabs.myfuelcloud.api.refuel;
import com.fivelabs.myfuelcloud.api.vehicle;
import com.fivelabs.myfuelcloud.helpers.ItemClickSupport;
import com.fivelabs.myfuelcloud.helpers.RVAdapterRefuels;
import com.fivelabs.myfuelcloud.helpers.SpinnerAdapter;
import com.fivelabs.myfuelcloud.model.Refuel;
import com.fivelabs.myfuelcloud.model.Vehicle;
import com.fivelabs.myfuelcloud.util.Common;
import com.fivelabs.myfuelcloud.util.Global;
import com.fivelabs.myfuelcloud.util.Session;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RefuelListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RefuelListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RefuelListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mRecyclerView;
    private View mProgressView;

    private View myInflatedView;
    private RecyclerView rv;

    private Spinner spinnerVehicle;
    private SpinnerAdapter adapter;

    public RefuelListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RefuelListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RefuelListFragment newInstance(String param1, String param2) {
        RefuelListFragment fragment = new RefuelListFragment();
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

            mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRecyclerView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

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
            mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
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

    private void modifyRefuelDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View dialogViewEdit = LayoutInflater.from(this.getActivity()).inflate(R.layout.modify_refuel, null, true);
        builder.setView(dialogViewEdit);

        //TODO setup spinnerVehicle
        spinnerVehicle = (Spinner) dialogViewEdit.findViewById(R.id.dialog_vehicle);

        final EditText editTextDate = (EditText) dialogViewEdit.findViewById(R.id.dialog_date);
        final EditText editTextGasPrice = (EditText) dialogViewEdit.findViewById(R.id.dialog_gas_price);
        final EditText editTextGasStation = (EditText) dialogViewEdit.findViewById(R.id.dialog_gas_station);
        final EditText editTextPriceAmount = (EditText) dialogViewEdit.findViewById(R.id.dialog_price_amount);
        final EditText editTextFuelAmount = (EditText) dialogViewEdit.findViewById(R.id.dialog_fuel_amount);
        final EditText editTextPreviousDistance = (EditText) dialogViewEdit.findViewById(R.id.dialog_previous_distance);

        final String date = Session.getsRefuels().get(position).getDate();
        final Double gasPrice = Session.getsRefuels().get(position).getGas_price();
        final String gasStation = Session.getsRefuels().get(position).getGas_station();
        final Double priceAmount = Session.getsRefuels().get(position).getPrice_amount();
        final Double fuelAmount = Session.getsRefuels().get(position).getFuel_amount();
        final Double previousDistance = Session.getsRefuels().get(position).getPrevious_distance();
        final String id = Session.getsRefuels().get(position).get_id();

        editTextDate.setText(date);
        editTextGasPrice.setText(gasPrice.toString());
        editTextGasStation.setText(gasStation);
        editTextPriceAmount.setText(priceAmount.toString());
        editTextFuelAmount.setText(fuelAmount.toString());
        editTextPreviousDistance.setText(previousDistance.toString());

        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String date = editTextDate.getText().toString();
                final Double gasPrice = Double.valueOf(editTextGasPrice.getText().toString());
                final String gasStation = editTextGasStation.getText().toString();
                final Double priceAmount = Double.valueOf(editTextPriceAmount.getText().toString());
                final Double fuelAmount = Double.valueOf(editTextFuelAmount.getText().toString());
                final Double previousDistance = Double.valueOf(editTextPreviousDistance.getText().toString());

                updateRefuel(id, Double.valueOf(date), gasPrice, gasStation, priceAmount, fuelAmount, previousDistance, "VEHICLEID HERE");
            }
        });

        builder.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteRefuel(id);
            }
        });

        builder.create();
        builder.show();
    }

    private void addRefuelDialog() {

        final Vehicle[] vehicle = new Vehicle[1];

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View addRefuelView = inflater.inflate(R.layout.dialog_add_refuel, null);
        builder.setView(addRefuelView);

        if (Session.getsVehicles() != null && Session.getsVehicles().size() > 0) {
            adapter = new SpinnerAdapter(getActivity(),
                    android.R.layout.simple_spinner_item,
                    Session.getsVehicles());

            spinnerVehicle = (Spinner) addRefuelView.findViewById(R.id.dialog_vehicle);
            spinnerVehicle.setAdapter(adapter);

            spinnerVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view,
                                           int position, long id) {

                    vehicle[0] = adapter.getItem(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapter) {
                }
            });

        } else {

            Toast.makeText(getActivity().getApplicationContext(), R.string.add_vehicle_first, Toast.LENGTH_SHORT).show();
        }

        builder.setPositiveButton(getActivity().getString(R.string.add), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                EditText editTextGasPrice = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_gas_price);
                EditText editTextGasStation = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_gas_station);
                EditText editTextPriceAmount = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_price_amount);
                EditText editTextFuelAmount = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_fuel_amount);
                EditText editTextPreviousDistance = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_previous_distance);

                String gasPrice = editTextGasPrice.getText().toString();
                String gasStation = editTextGasStation.getText().toString();
                String priceAmount = editTextPriceAmount.getText().toString();
                String fuelAmount = editTextFuelAmount.getText().toString();
                String previousDistance = editTextPreviousDistance.getText().toString();

                if (gasPrice.matches("") || gasStation.matches("") || priceAmount.matches("") || fuelAmount.matches("") || previousDistance.matches("")) {

                } else {

                    addRefuel(Common.getCurrentTimestamp(),
                            Double.valueOf(gasPrice), gasStation,
                            Double.valueOf(priceAmount),
                            Double.valueOf(fuelAmount),
                            Double.valueOf(previousDistance),
                            vehicle[0].get_id());
                }
            }
        });

        builder.setNegativeButton(getActivity().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        builder.create();
        builder.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_refuel_list, container, false);

        mRecyclerView = myInflatedView.findViewById(R.id.rv);
        mProgressView = myInflatedView.findViewById(R.id.login_refuels_progress);

        rv = (RecyclerView) myInflatedView.findViewById(R.id.rv);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                modifyRefuelDialog(position);

            }
        }).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {

                return true;
            }
        });


        FloatingActionButton floatingActionButtonAdd = (FloatingActionButton) myInflatedView.findViewById(R.id.fab);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addRefuelDialog();
            }
        });

        loadVehicles();

        return myInflatedView;
    }

    private void loadVehicles() {

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

        vehicle vehicle = restAdapter.create(vehicle.class);

        vehicle.getVehicles(new Callback<List<Vehicle>>() {
            @Override
            public void success(List<Vehicle> vehicles, Response response) {
                Session.setsVehicles(vehicles);
                loadRefuels();
                showProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                showProgress(false);

            }
        });
    }


    private void loadRefuels() {

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

        refuel refuel = restAdapter.create(refuel.class);

        refuel.getRefuels(new Callback<List<Refuel>>() {
            @Override
            public void success(List<Refuel> refuels, Response response) {
                Session.setsRefuels(refuels);

                rv.setHasFixedSize(true);

                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                rv.setLayoutManager(llm);

                RVAdapterRefuels adapter = new RVAdapterRefuels(Session.getsRefuels());
                rv.setAdapter(adapter);

                showProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                showProgress(false);
            }
        });
    }

    private void addRefuel(Long date, Double gas_price, String gas_station, Double price_amount, Double fuel_amount, Double previous_distance, String vehicle) {

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

        refuel refuel = restAdapter.create(refuel.class);

        refuel.addRefuel(date, gas_price, gas_station, price_amount, fuel_amount, previous_distance, vehicle, new Callback<Refuel>() {

            @Override
            public void success(Refuel refuel, Response response) {
                loadRefuels();
                Toast.makeText(getActivity().getApplicationContext(), R.string.refuel_added, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                Toast.makeText(getActivity().getApplicationContext(), R.string.refuel_not_added, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteRefuel(String id) {
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

        refuel refuel = restAdapter.create(refuel.class);

        refuel.deleteRefuel(id, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                loadRefuels();
                Toast.makeText(getActivity().getApplicationContext(), R.string.refuel_deleted, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                Toast.makeText(getActivity().getApplicationContext(), R.string.refuel_not_deleted, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateRefuel(String id, Double date, Double gas_price, String gas_station, Double price_amount, Double fuel_amount, Double previous_distance, String vehicle) {

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

        refuel refuel = restAdapter.create(refuel.class);

        refuel.updateRefuel(id, date, gas_price, gas_station, price_amount, fuel_amount, previous_distance, Session.getsUser().getId(), vehicle, new Callback<Refuel>() {

            @Override
            public void success(Refuel refuel, Response response) {
                loadRefuels();
                Toast.makeText(getActivity().getApplicationContext(), R.string.refuel_updated, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                Toast.makeText(getActivity().getApplicationContext(), R.string.refuel_not_updated, Toast.LENGTH_LONG).show();
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
        public void onFragmentInteraction(Uri uri);
    }

}
