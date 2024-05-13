import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Scanner;

public class Convertir {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Error al obtener la respuesta: " + response);
            }

            String responseData = response.body().string();
            JsonObject json = new Gson().fromJson(responseData, JsonObject.class);

            // Obtener las tasas de cambio
            JsonObject rates = json.getAsJsonObject("rates");

            Scanner scanner = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("Elija una opción:");
                System.out.println("1) Dólar a Peso Argentino");
                System.out.println("2) Peso Argentino a Dólar");
                System.out.println("3) Dólar a Real Brasileño");
                System.out.println("4) Real Brasileño a Dólar");
                System.out.println("5) Dólar a Peso Colombiano");
                System.out.println("6) Peso Colombiano a Dólar");
                System.out.println("7) Salir");
                System.out.print("Opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese la cantidad de dólares: ");
                        double dolaresArg = scanner.nextDouble();
                        double tasaArg = rates.get("ARS").getAsDouble();
                        System.out.println("Cantidad en Peso Argentino: " + (dolaresArg * tasaArg));
                        break;
                    case 2:
                        System.out.print("Ingrese la cantidad de pesos argentinos: ");
                        double pesosArg = scanner.nextDouble();
                        double tasaDolArg = rates.get("ARS").getAsDouble();
                        System.out.println("Cantidad en Dólares: " + (pesosArg / tasaDolArg));
                        break;
                    case 3:
                        System.out.print("Ingrese la cantidad de dólares: ");
                        double dolaresBrl = scanner.nextDouble();
                        double tasaBrl = rates.get("BRL").getAsDouble();
                        System.out.println("Cantidad en Real Brasileño: " + (dolaresBrl * tasaBrl));
                        break;
                    case 4:
                        System.out.print("Ingrese la cantidad de reales brasileños: ");
                        double realesBrl = scanner.nextDouble();
                        double tasaDolBrl = rates.get("BRL").getAsDouble();
                        System.out.println("Cantidad en Dólares: " + (realesBrl / tasaDolBrl));
                        break;
                    case 5:
                        System.out.print("Ingrese la cantidad de dólares: ");
                        double dolaresCol = scanner.nextDouble();
                        double tasaCol = rates.get("COP").getAsDouble();
                        System.out.println("Cantidad en Peso Colombiano: " + (dolaresCol * tasaCol));
                        break;
                    case 6:
                        System.out.print("Ingrese la cantidad de pesos colombianos: ");
                        double pesosCol = scanner.nextDouble();
                        double tasaDolCol = rates.get("COP").getAsDouble();
                        System.out.println("Cantidad en Dólares: " + (pesosCol / tasaDolCol));
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            } while (opcion != 7);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
