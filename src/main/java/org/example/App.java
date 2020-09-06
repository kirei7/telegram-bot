package org.example;

import com.pengrad.telegrambot.TelegramBot;
import io.mikael.urlbuilder.UrlBuilder;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Hello world!
 */
public class App {
    private static final String TELEGRAM_TOKEN = null;
    private HttpClient httpClient = HttpClient.newBuilder().build();
    private TelegramBot bot = new TelegramBot(TELEGRAM_TOKEN);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        App app = new App();
        app.run();
    }

    @SneakyThrows
    public void run() {
        httpClient.send(
                HttpRequest.newBuilder()
                        .uri(UrlBuilder.fromString("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendMessage")
                                .addParameter("chat_id", "492920819")
                                .addParameter("text", "hello from java")
                                .toUri())
                .build(),
                HttpResponse.BodyHandlers.ofString()
        );
    }
}
