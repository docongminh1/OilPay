package com.example.oilpay;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oilpay.Config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    PayAdapter oilPayAdapter;
    int sum;

    ArrayList<Oil> DsDocdulieu = new ArrayList<Oil>();
    ArrayList<Oil> DsTinhtoan = new ArrayList<Oil>();

    Button btnThanhtoan,btnThanhtoanOnline;

    String amount = "" ;

    public static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration configuration =
            new PayPalConfiguration()
                    .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                    .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        listView = findViewById(R.id.lvPay);
        textView = findViewById(R.id.txtTongTien);
        oilPayAdapter = new PayAdapter(PayActivity.this,R.layout.layout_pay);
        listView.setAdapter(oilPayAdapter);

        Intent intent = getIntent();
        DsDocdulieu = (ArrayList<Oil>)intent.getSerializableExtra("listDetail");
        //doc du lieu bang id
//        for (int i = 1; i < 7; i++){
//            Oil oil = (Oil) intent.getSerializableExtra(i+"");
//            if (oil.getQuantity() > 0){
//                arrayList.add(oil);
//            }
//        }

        for (Oil oil1:DsDocdulieu){
            if(oil1.getQuantity()>0)
                DsTinhtoan.add(oil1);
        }
        oilPayAdapter.addAll(DsTinhtoan);
        sum =0;
        for(Oil oil2:DsTinhtoan){
            sum = sum +oil2.getPriceOil()*oil2.getQuantity();
        }
        textView.setText(sum + "");

        btnThanhtoan = findViewById(R.id.btnThanhtoan);
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sum > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);

                    builder.setTitle("Thanh toan hoa don");
                    builder.setIcon(R.drawable.ic_launcher_background);
                    builder.setMessage("Chap nhan thanh toan hoa don " + sum + "?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(PayActivity.this, "Ban da thanh toan thanh cong so tien " + sum, Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);

                    builder.setTitle("Thanh toan hoa don");
                    builder.setIcon(R.drawable.ic_launcher_background);
                    builder.setMessage("Hoa don khong hop le");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        //Start Paypal Service
        Intent intent1 = new Intent(this,PayPalService.class);
        intent1.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        startService(intent1);

        btnThanhtoanOnline = findViewById(R.id.btnThanhtoanOnline);
        btnThanhtoanOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });
    }

    private void processPayment() {
        amount = textView.getText().toString();
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(amount)
                ,"USD","Donate for OilPay"
                ,PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);

        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if(resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null){
                    try{
                        String paymentDetails = confirmation.toJSONObject().toString(4);

                        startActivity(new Intent(this,PaymentDetails.class)
                                .putExtra("PaymentDetails",paymentDetails)
                                .putExtra("PaymentAmount",amount)
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }
}
