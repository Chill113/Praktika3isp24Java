import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EquipmentRegistrationREST {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Создание клиента HTTP для отправки запросов
        HttpClient client = HttpClient.newHttpClient();

        // Создание запроса на регистрацию оборудования
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com/api/equipment"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\": \"Компьютер 1\", \"serial_number\": \"123456\", \"type\": \"ПК\"}"))
                .build();

        // Отправка запроса и получение ответа
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Проверка кода ответа
        if (response.statusCode() == 201) {
            // Оборудование успешно зарегистрировано
            System.out.println("Оборудование успешно зарегистрировано!");
        } else {
            // Ошибка регистрации оборудования
            System.out.println("Ошибка регистрации оборудования: " + response.body());
        }
    }
}

