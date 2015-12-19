package com.example.timepay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.base.App;
import com.example.network.ApiRequests;
import com.example.network.GsonPutRequest;
import com.example.pojo.UserAccountModel;
import com.example.pojo.UsersRegistrationStatus;
import com.example.pojo.accountpojo.PublicAccount;
import com.example.pojo.accountpojo.VendorAccount;

import java.io.File;


public class VendorRegistration extends ActionBarActivity implements View.OnClickListener {


    private static final int CAPTURE_IMAGE_FROM_CAMERA = 0;
    private static final int LOAD_IMAGE_FROM_GALLERY=1;

    EditText companyName ,shopName ,accountNumber, ifscCode,panNo;
    Button uploadPAN ,continueBtn;
    ImageView imageOfPANCard;
    Intent builderIntent;
    TextView paymentGatewayLink,searchIFSCCode;
    UserAccountModel _UserAccountModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);
        initialize();
        uploadPAN.setOnClickListener(this);
        searchIFSCCode.setOnClickListener(this);
        continueBtn.setOnClickListener(this);
        paymentGatewayLink.setOnClickListener(this);
    }

    private void initialize() {

        uploadPAN=(Button)findViewById(R.id.btnUploadPAN);
        companyName=(EditText)findViewById(R.id.etRegisteredCompanyName);
        shopName=(EditText)findViewById(R.id.etShopPublicBrandName);
        accountNumber=(EditText)findViewById(R.id.etAccountNumber);
        ifscCode=(EditText)findViewById(R.id.etPVRIFSCCode);
        panNo=(EditText)findViewById(R.id.etPANNumber);
        imageOfPANCard=(ImageView)findViewById(R.id.ivPANImage);
        searchIFSCCode = (TextView)findViewById(R.id.tvPVRIFSCCode);
        continueBtn=(Button)findViewById(R.id.bContinue);
        paymentGatewayLink=(TextView)findViewById(R.id.paymentGatewayLink);
        _UserAccountModel = getIntent().getParcelableExtra("UserAccountModel");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general_public_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if(view ==uploadPAN){
            Log.i("VendorRegistration","onclick");
            final CharSequence[] uploadPanOptions={"Take a Picture","Choose From Gallery"};
            AlertDialog.Builder builder=new AlertDialog.Builder(VendorRegistration.this);

            builder.setTitle("Choose Options");
            builder.setItems(uploadPanOptions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (uploadPanOptions[i].equals("Take a Picture")){
                        builderIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(builderIntent, CAPTURE_IMAGE_FROM_CAMERA);

                    }else if (uploadPanOptions[i].equals("Choose From Gallery")){
                        builderIntent=new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(builderIntent, LOAD_IMAGE_FROM_GALLERY);
                    }
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }else if(view==paymentGatewayLink) {
            Intent intent = new Intent(VendorRegistration.this, Webview.class);
            intent.putExtra("URL", getString(R.string.wvIAree));
            intent.putExtra("Text",getString(R.string.Text_view_IFSC));
            startActivity(intent);
        }

        /*else if (view==expiryMonth)  {'
            final CharSequence[] mnth={"01(Jan)","02(Feb)","03(Mar)","04(Apr)","05(May)","06(Jun)","07(Jul)","08(Aug)","09(Sep)","10(Oct)","11(Nov)","12(Dec)"};
            AlertDialog.Builder builder = new AlertDialog.Builder(VendorRegistration.this);
            builder.setTitle("Select Month");
            builder.setItems(mnth, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                   // Toast.makeText(getApplicationContext(), "You have selected  " + mnth[which], Toast.LENGTH_LONG).show();
                    expiryMonth.setText(mnth[which]);

                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }
        else if (view==expiryYear) {
            final CharSequence[] yr={"2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026"};
            AlertDialog.Builder builder = new AlertDialog.Builder(VendorRegistration.this);
            builder.setTitle("Select Year");
            builder.setItems(yr, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                   // Toast.makeText(getApplicationContext(), "You have selected  " + yr[which], Toast.LENGTH_LONG).show();
                    expiryYear.setText(yr[which]);
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }*/

        else if(view == searchIFSCCode)
        {
            Intent intent = new Intent(VendorRegistration.this, Webview.class);
            intent.putExtra("URL", getString(R.string.IFSC));
            intent.putExtra("Text",getString(R.string.Text_view_IFSC));
            startActivity(intent);
        }else if (view == continueBtn){

            Validator validator=new Validator();
            String message= validator.validateVendorRegistration(companyName.getText()+"",
                    shopName.getText()+"", accountNumber.getText()+"",
                    ifscCode.getText()+"", panNo.getText()+"");

            if (message.equals("Completed")) {


                VendorAccount vendorAccount = new VendorAccount(companyName.getText() + "",
                        shopName.getText() + "",
                        accountNumber.getText() + "",
                        ifscCode.getText() + "",
                        panNo.getText() + "");

                _UserAccountModel.setVendorAccount(vendorAccount);

                final GsonPutRequest<UsersRegistrationStatus> gsonPutRequest =
                        ApiRequests.getPayObjectArrayWithPut
                                (
                                        new Response.Listener<UsersRegistrationStatus>() {
                                            @Override
                                            public void onResponse(UsersRegistrationStatus usersRegistrationStatus) {
                                                // Deal with the DummyObject here
                                                // mProgressBar.setVisibility(View.GONE);
                                                // mContent.setVisibility(View.VISIBLE);
                                                Log.i("ErrorMessage", "3" + usersRegistrationStatus);
                                                setData(usersRegistrationStatus);
                                            }
                                        }
                                        ,
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // Deal with the error here
                                                // mProgressBar.setVisibility(View.GONE);
                                                //mErrorView.setVisibility(View.VISIBLE);
                                                Log.i("ErrorMessage", "4");
                                                //setToast(error);
                                            }
                                        }
                                        ,
                                        _UserAccountModel,
                                        getString(R.string.sighnUpUrl)
                                );

                App.addRequest(gsonPutRequest, Accounts.sTag);

            }else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap;
        Bitmap resizedBitmap;
        if (requestCode == LOAD_IMAGE_FROM_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri selectedImageFromUri = data.getData();
            String pathOFImage = getRealPathFromURI(selectedImageFromUri);
            File imgFile = new File(pathOFImage);
            bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 45, false);
            imageOfPANCard.setImageBitmap(resizedBitmap);
        } else if (requestCode == CAPTURE_IMAGE_FROM_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 45, false);
            imageOfPANCard.setImageBitmap(resizedBitmap);
        }
    }
    private void setData(@NonNull final UsersRegistrationStatus usersRegistrationStatus) {
        //mTitle.setText(dummyObject.getPayLoad().get(0).getId());
        Log.i("ErrorMessage", "2222" + usersRegistrationStatus.getStatusMessage());
        Toast.makeText(this, usersRegistrationStatus.getStatusMessage(), Toast.LENGTH_LONG).show();
    }
    private String getRealPathFromURI(Uri selectedImageFromUri) {
        Cursor cursor = getContentResolver().query(selectedImageFromUri, null, null, null, null);
        if (cursor == null) {
            return selectedImageFromUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
