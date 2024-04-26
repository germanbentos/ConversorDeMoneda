
package programas.java.conversondemoneda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorDeMoneda {
    private static final String API_KEY = "2dc590d61b3d5a0a3d9b0746";
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el monto que deseas cambiar:");
        double montoAingresar = entrada.nextDouble();
        entrada.nextLine(); // Consumir el salto de línea
        
        while (true) {
            System.out.println("\n*******************************");
            System.out.println("1) Dólar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dólar");
            System.out.println("3) Dólar => Real Brasileño");
            System.out.println("4) Real Brasileño => Dólar");
            System.out.println("5) Dólar => Peso Colombiano");
            System.out.println("6) Peso Colombiano => Dólar");
            System.out.println("7) SALIR");
            System.out.println("*******************************");
            
            int opcionConversion = entrada.nextInt();
            entrada.nextLine(); // Consumir el salto de línea

            if (opcionConversion == 7) {
                break;
            }

            String fromCurrency, toCurrency;
            switch (opcionConversion) {
                case 1:
                    fromCurrency = "USD";
                    toCurrency = "ARS";
                    break;
                case 2:
                    fromCurrency = "ARS";
                    toCurrency = "USD";
                    break;
                case 3:
                    fromCurrency = "USD";
                    toCurrency = "BRL";
                    break;
                case 4:
                    fromCurrency = "BRL";
                    toCurrency = "USD";
                    break;
                case 5:
                    fromCurrency = "USD";
                    toCurrency = "COP";
                    break;
                case 6:
                    fromCurrency = "COP";
                    toCurrency = "USD";
                    break;
                default:
                    System.out.println("Opción no válida.");
                    continue;
            }

            String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

             try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
                if (jsonResponse != null && jsonResponse.has("conversion_rate")) {
                    double conversionRate = jsonResponse.get("conversion_rate").getAsDouble();
                    double convertedAmount = montoAingresar * conversionRate;
                    System.out.printf("%.2f %s son %.2f %s%n", montoAingresar, fromCurrency, convertedAmount, toCurrency);
                } else {
                    System.out.println("Error al procesar la respuesta de la API o moneda no encontrada.");
                }
            } catch (Exception e) {
                System.err.println("Error al realizar la solicitud HTTP: " + e.getMessage());
            }
        }

        entrada.close();
        System.out.println("Gracias por usar el conversor de moneda.");
    }
}


