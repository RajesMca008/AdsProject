package com.appants.adspro;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.appants.adspro.rest.ApiClient;
import com.appants.adspro.rest.ApiInterface;
import com.appants.adspro.rest.Register;
import com.appants.adspro.rest.SignUpResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText dateEditText =null;
    private EditText referl,nameField,phoneField,passField;
    private String gender;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        nameField=(EditText)findViewById(R.id.name);
        phoneField=(EditText)findViewById(R.id.phone);
        passField=(EditText)findViewById(R.id.password);
        referl=(EditText)findViewById(R.id.referalCode);
        gender="";

         dateEditText =(EditText)  findViewById(R.id.date_id);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate= Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        dateEditText.setText(""+a);

                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });


        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i==R.id.male_btn)
                {
                    Log.i("TEST",":Male:"+i);

                    gender="Male";
                }
                else if(i==R.id.female_btn)
                {
                    Log.i("TEST",": Female:"+i);
                    gender="Female";
                }

            }
        });

        findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(gender.length()>0 &&nameField.getText().toString().length()>0&& phoneField.getText().toString().length()>9 && passField.getText().toString().length()>3)
                {
                    SignupTask signupTask=new SignupTask();
                    signupTask.execute();

                    Register register=new Register();
                    register.dob="2017-11-18";
                    register.name="Raj";
                    register.password="123456";
                    register.gender="M";
                    register.mobile_no="9030789643";
                    register.referral_code="179908";
                    Call<SignUpResponse> rest = apiInterface.getTopRatedMovies(register);
                    rest.enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            Log.i("TEST","onResponse"+response);
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Log.i("TEST","onFailure"+t.getMessage());
                        }
                    });


                }else {
                    Toast.makeText(RegistrationActivity.this,"Invalid values",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    class SignupTask extends AsyncTask<Void,Void,Void>
    {

        ProgressDialog progressDialog=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(RegistrationActivity.this);
            progressDialog.setMessage("Signing Up...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
