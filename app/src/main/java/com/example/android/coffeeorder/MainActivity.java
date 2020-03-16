/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.coffeeorder;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {
    int quantityOfCoffee = 1;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    private String createOrderSummary() {
        String priceMessage = "Name: " + getUserName();
        priceMessage += "\nQuantity :" + quantityOfCoffee;
        if (checkWhippedCreamTopping()) {
            priceMessage += "\nWhipped Cream added";
        }
        if (checkChocolateTopping()) {
            priceMessage += "\nChocolate added";
        }
        priceMessage += "\nTotal: $" + calculatePrice();
        priceMessage += "\nThank You";
        return priceMessage;
    }

    private String getUserName() {
        EditText nameTextInput = (EditText) findViewById(R.id.name_input_text);
        userName = nameTextInput.getText().toString();
        return userName;
    }

    private boolean checkWhippedCreamTopping() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        return whippedCreamCheckBox.isChecked();
    }

    private boolean checkChocolateTopping() {
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        return chocolateCheckBox.isChecked();
    }

    private int calculatePrice() {
        int basePrice = 5;
        int totalPrice;
        if (checkWhippedCreamTopping()) {

            basePrice += 1;
        }
        if (checkChocolateTopping()) {
            basePrice += 2;
        }
        totalPrice = basePrice * quantityOfCoffee;

        return totalPrice;
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        Toast.makeText(getApplicationContext(), "order placed", Toast.LENGTH_LONG).show();
createOrderSummary();
        Intent intent = new Intent (Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + userName);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (quantityOfCoffee < 100) {
            quantityOfCoffee++;
            displayCoffeeQuantity(quantityOfCoffee);
        } else {
            Toast.makeText(getApplicationContext(), "order quantity cannot be greater than 100", Toast.LENGTH_LONG).show();
        }
    }

    public void decrement(View view) {
        if (quantityOfCoffee > 1) {
            quantityOfCoffee--;
            displayCoffeeQuantity(quantityOfCoffee);
        } else {
            Toast.makeText(getApplicationContext(), "order quantity cannot be less than 1", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayCoffeeQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}