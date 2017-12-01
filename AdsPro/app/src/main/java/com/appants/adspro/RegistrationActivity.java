package com.appants.adspro;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText dateEditText =null;
    private EditText referl,nameField,phoneField,passField;
    private String gender;
    private ApiInterface apiInterface;
    ProgressDialog progressDialog=null;

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

                    gender="M";
                }
                else if(i==R.id.female_btn)
                {
                    Log.i("TEST",": Female:"+i);
                    gender="F";
                }

            }
        });

        findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(gender.length()>0 &&nameField.getText().toString().length()>0&& phoneField.getText().toString().length()>9 && passField.getText().toString().length()>3)
                {

                    JSONObject paramObject = new JSONObject();
                    try {
                        paramObject.put("name",nameField.getText().toString()) ;
                        paramObject.put("dob",dateEditText.getText().toString()) ;
                        paramObject.put("password",passField.getText().toString()) ;
                        paramObject.put("gender",gender) ;
                        paramObject.put("mobile_no",phoneField.getText().toString()) ;
                        paramObject.put("referral_code",referl.getText().toString()) ;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    progressDialog=new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setMessage("Signing Up...");
                    progressDialog.show();

                    Call<String> userCall = apiInterface.getSignup(paramObject.toString());

                    userCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.i("Test","onResponse"+response);

                            progressDialog.dismiss();
                            if(response!=null)
                            {
                                try {
                                    JSONObject object=new JSONObject(response.body());

                                    if(object.getBoolean("success"))
                                    {

                                        finish();
                                        Intent loginActivity=new Intent(RegistrationActivity.this,LoginActivity.class);
                                        startActivity(loginActivity);
                                    }else {

                                        String error=object.getString("errors");

                                        Toast.makeText(RegistrationActivity.this,"Error"+error,Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.i("Test","onFailure"+t);
                            progressDialog.dismiss();
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
