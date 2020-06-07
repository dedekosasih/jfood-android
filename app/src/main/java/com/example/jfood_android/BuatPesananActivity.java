package com.example.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuatPesananActivity extends AppCompatActivity {

    private static final String TAG = "BuatPesananActivity";

    private int currentUserId;
    private int id_food;
    private String foodName;
    private String foodCategory;
    private int foodPrice;
    private String promoCode;
    private String promoCodeRequest;
    private String selectedPayment;
    private int foodPriceList;
    private int priceRequest;
    private String newFoodList;
    private String foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            currentUserId = extras.getInt("currentUserId");
            id_food = extras.getInt("id_food");
            foodName = extras.getString("foodName");
            foodCategory = extras.getString("foodCategory");
            foodPrice = extras.getInt("foodPrice");
        }

        Log.d("id", String.valueOf(currentUserId));

        final EditText vPromoCode = findViewById(R.id.promo_code);
        final TextView vCode = findViewById(R.id.textCode);
        final TextView vFoodName = findViewById(R.id.food_name);
        final TextView vFoodCategory = findViewById(R.id.food_category);
        final TextView vFoodPrice = findViewById(R.id.food_price);
        final TextView vTotalPrice = findViewById(R.id.total_price);
        final RadioGroup vVia = findViewById(R.id.radioGroup);
        final Button vCount = findViewById(R.id.hitung);
        final Button vOrder = findViewById(R.id.pesan);

        vOrder.setVisibility(View.GONE);
        vCode.setVisibility(View.GONE);
        vPromoCode.setVisibility(View.GONE);
        vCount.setEnabled(false);

        vFoodName.setText(foodName);
        vFoodCategory.setText(foodCategory);
        vFoodPrice.setText(String.valueOf(foodPrice));
        vTotalPrice.setText("0");

        foodPriceList = getIntent().getExtras().getInt("foodPriceList");
        foodPriceList = foodPriceList + foodPrice;
        Log.d("Total Harga", foodPriceList+"");

        foodList = getIntent().getExtras().getString("foodList");
        if(foodList == null){
            foodList = "";
        }
        newFoodList = foodList + id_food + ",";
        Log.d("Ini list food", newFoodList);

        vVia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton vRadioButton = findViewById(i);
                String selected = vRadioButton.getText().toString();
                switch (selected){
                    case "Via CASH":
                        vCode.setVisibility(View.GONE);
                        vPromoCode.setVisibility(View.GONE);
                        vCount.setEnabled(true);
                        break;

                    case "Via CASHLESS":
                        vCode.setVisibility(View.VISIBLE);
                        vPromoCode.setVisibility(View.VISIBLE);
                        vCount.setEnabled(true);
                        break;
                }
            }
        });

        vCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = vVia.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioId);
                String selected = radioButton.getText().toString();
                promoCode = vPromoCode.getText().toString();
                switch (selected){
                    case "Via CASH":
                        vTotalPrice.setText(String.valueOf(foodPriceList));
                        break;

                    case "Via CASHLESS":
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonResponse = new JSONArray(response);
                                    for(int i = 0; i <jsonResponse.length(); i++){
                                        JSONObject promo = jsonResponse.getJSONObject(i);
                                        if(vPromoCode.getText().toString().equals(promo.getString("code")) && promo.getBoolean("active")){
                                            if(foodPriceList > promo.getInt("minPrice")){
                                                priceRequest = promo.getInt("discount");
                                                vTotalPrice.setText(String.valueOf(foodPriceList - priceRequest));
                                            }
                                        }
                                    }
                                }
                                catch (JSONException e){
                                    Log.d(TAG, "Load data failed.");
                                }
                            }
                        };

                        CheckPromoRequest promoRequest = new CheckPromoRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                        queue.add(promoRequest);

                        break;
                }

                vCount.setVisibility(View.GONE);
                vOrder.setVisibility(View.VISIBLE);
            }
        });

        vOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = vVia.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioId);
                String selected = radioButton.getText().toString();
                promoCode = vPromoCode.getText().toString();
                BuatPesananRequest pesananRequest = null;

                Log.d(TAG, selected);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (response != null){
                                Toast.makeText(BuatPesananActivity.this, "Order successful", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            Toast.makeText(BuatPesananActivity.this, "Order failed", Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }
                    }
                };

                if(selected.equals("Via CASH")){
                    pesananRequest = new BuatPesananRequest(newFoodList.substring(0, newFoodList.length()-1), currentUserId+"", responseListener);
                }
                else if(selected.equals("Via CASHLESS")){
                    pesananRequest = new BuatPesananRequest(newFoodList.substring(0, newFoodList.length()-1), currentUserId+"", vPromoCode.getText().toString(), responseListener);
                }


                RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(pesananRequest);

            }
        });
    }
}
