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
import android.widget.EditText;
import android.widget.Toast;

import com.fivelabs.myfuelcloud.R;
import com.fivelabs.myfuelcloud.api.vehicle;
import com.fivelabs.myfuelcloud.helpers.ItemClickSupport;
import com.fivelabs.myfuelcloud.helpers.RVAdapter;
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
 * {@link VehicleListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VehicleListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleListFragment extends Fragment {
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

    public VehicleListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehicleListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VehicleListFragment newInstance(String param1, String param2) {
        VehicleListFragment fragment = new VehicleListFragment();
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

    private void modifyVehicleDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View dialogViewEdit = LayoutInflater.from(this.getActivity()).inflate(R.layout.modify_vehicle,null,true);
        builder.setView(dialogViewEdit);

        final EditText editTextBrand = (EditText) dialogViewEdit.findViewById(R.id.dialog_brand);
        final EditText editTextModel = (EditText) dialogViewEdit.findViewById(R.id.dialog_model);
        final EditText editTextYear = (EditText) dialogViewEdit.findViewById(R.id.dialog_year);

        String brand = Session.getsVehicles().get(position).getBrand();
        final String model = Session.getsVehicles().get(position).getModel();
        final String year = String.valueOf(Session.getsVehicles().get(position).getYear());
        final String id = Session.getsVehicles().get(position).get_id();

        editTextBrand.setText(brand);
        editTextModel.setText(model);
        editTextYear.setText(year);

        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String brand = editTextBrand.getText().toString();
                String model = editTextModel.getText().toString();
                int year = Integer.parseInt(editTextYear.getText().toString());

                updateVehicle(id, brand, model, year);
            }
        });

        builder.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteVehicle(id);
            }
        });

        builder.create();
        builder.show();
    }

    private void addVehicleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_add_vehicle, null));
        builder.setPositiveButton(getActivity().getString(R.string.add), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                EditText editTextBrand = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_brand);
                EditText editTextModel = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_model);
                EditText editTextYear = (EditText) ((AlertDialog) dialog).findViewById(R.id.dialog_year);

                String brand = editTextBrand.getText().toString();
                String model = editTextModel.getText().toString();
                String year = editTextYear.getText().toString();

                if (brand.matches("") || model.matches("") || year.matches("")) {

                } else {
                    addVehicle(brand, model, Integer.parseInt(year));
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

        myInflatedView = inflater.inflate(R.layout.fragment_vehicle_list, container, false);

        mRecyclerView = myInflatedView.findViewById(R.id.rv);
        mProgressView = myInflatedView.findViewById(R.id.login_vehicles_progress);

        rv = (RecyclerView) myInflatedView.findViewById(R.id.rv);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                modifyVehicleDialog(position);

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
                addVehicleDialog();
            }
        });

        loadVehicles();

        return myInflatedView;
    }


    private void loadVehicles(){

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


                rv.setHasFixedSize(true);

                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                rv.setLayoutManager(llm);

                RVAdapter adapter = new RVAdapter(Session.getsVehicles());
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

    private void addVehicle(String brand, String model, int year) {
        int timestamp = Common.getCurrentTimestamp();

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

        vehicle.addVehicle(brand, model, year, timestamp, Session.getsUser().getId(), new Callback<Vehicle>() {

            @Override
            public void success(Vehicle vehicle, Response response) {
                loadVehicles();
                Toast.makeText(getActivity().getApplicationContext(), R.string.vehicle_added, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                Toast.makeText(getActivity().getApplicationContext(), R.string.vehicle_not_added, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteVehicle(String id){
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

        vehicle.deleteVehicle(id, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                loadVehicles();
                Toast.makeText(getActivity().getApplicationContext(), R.string.vehicle_deleted, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                Toast.makeText(getActivity().getApplicationContext(), R.string.vehicle_not_deleted, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateVehicle(String id, String brand, String model, int year) {
        int timestamp = Common.getCurrentTimestamp();

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

        vehicle.updateVehicle(id, brand, model, year, timestamp, Session.getsUser().getId(), new Callback<Vehicle>() {

            @Override
            public void success(Vehicle vehicle, Response response) {
                loadVehicles();
                Toast.makeText(getActivity().getApplicationContext(), R.string.vehicle_updated, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                Toast.makeText(getActivity().getApplicationContext(), R.string.vehicle_not_updated, Toast.LENGTH_LONG).show();
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
