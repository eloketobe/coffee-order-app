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

import java.text.NumberFormat;

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
        String priceMessage = getString(R.string.order_summary_name, getUserName());
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantityOfCoffee);
        if (checkWhippedCreamTopping()) {
            priceMessage += "\n" + getString(R.string.whipped_cream_added_notifier);
        }
        if (checkChocolateTopping()) {
            priceMessage += "\n" + getString(R.string.chocolate_added_notifier);
        }
        priceMessage += "\n" + getString(R.string.total_coffee_order_price, NumberFormat.getCurrencyInstance().format(calculatePrice()));
        priceMessage += "\n" + getString(R.string.thank_you_message);
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
        Toast.makeText(getApplicationContext(), R.string.order_confirmation, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject, getUserName()));
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
            Toast.makeText(getApplicationContext(), getString(R.string.increment_limit_toast_text), Toast.LENGTH_LONG).show();
        }
    }

    public void decrement(View view) {
        if (quantityOfCoffee > 1) {
            quantityOfCoffee--;
            displayCoffeeQuantity(quantityOfCoffee);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.decrement_limit_toast_text), Toast.LENGTH_LONG).show();
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