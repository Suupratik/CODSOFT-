//CURRENCY CONVERTER(TASK 4)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_KEY = "be974aa1d2c1ff068a8448ba"; 
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            
            String urlString = API_URL + baseCurrency;
            @SuppressWarnings("deprecation")
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            
            String responseBody = response.toString();
            int startIndex = responseBody.indexOf(targetCurrency) + targetCurrency.length() + 3;  
            int endIndex = responseBody.indexOf(",", startIndex); 
            if (endIndex == -1) {
                endIndex = responseBody.indexOf("}", startIndex); 
            }

            String rateString = responseBody.substring(startIndex, endIndex);
            return Double.parseDouble(rateString);
        } catch (Exception e) {
            System.out.println("Error fetching exchange rates: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");
        System.out.print("Enter the base currency (e.g., USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency (e.g., INR, GBP): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == -1) {
            System.out.println("Unable to fetch exchange rates. Please try again later.");
        } else {
            double convertedAmount = amount * exchangeRate;
            System.out.printf("Converted amount: %.2f %s\n", convertedAmount, targetCurrency);
        }
    }
}
