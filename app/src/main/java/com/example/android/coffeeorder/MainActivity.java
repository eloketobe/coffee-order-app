/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.coffeeorder;


import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {
    int quantityOfCoffee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private String createOrderSummary(int price) {
        String priceMessage = "Name: Eloke Tobe";
        priceMessage += "\nQuantity :" + quantityOfCoffee;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank You";
        return priceMessage;
    }

    private int calculatePrice() {

        return quantityOfCoffee * 5;
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage =createOrderSummary(price);

        displayMessage(priceMessage);
    }

    public void increment(View view) {
        quantityOfCoffee++;
        displayCoffeeQuantity(quantityOfCoffee);

    }

    public void decrement(View view) {
        quantityOfCoffee--;
        displayCoffeeQuantity(quantityOfCoffee);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayCoffeeQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}