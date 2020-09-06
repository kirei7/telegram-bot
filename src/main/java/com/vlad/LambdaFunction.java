package com.vlad;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import io.mikael.urlbuilder.UrlBuilder;
import lombok.SneakyThrows;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class LambdaFunction implements RequestHandler<Map, Void> {

    private static final String TELEGRAM_TOKEN = System.getenv("TELEGRAM_TOKEN");
    private HttpClient httpClient = HttpClient.newBuilder().build();
    private TelegramBot bot = new TelegramBot(TELEGRAM_TOKEN);
    private Gson gson = new Gson();

    @SneakyThrows
    @Override
    public Void handleRequest(Map input, Context context) {
        Message message = gson.fromJson((String) input.get("body"), Update.class).message();
        System.out.println((String) input.get("body"));
        System.out.println(gson.toJson(message));
        httpClient.send(
                HttpRequest.newBuilder()
                        .uri(UrlBuilder.fromString("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendMessage")
                                .addParameter("chat_id", message.chat().id().toString())
                                .addParameter("text", "Спитайте Саню, він пригожий ♂BOY♂ ")
                                .toUri())
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        return null;
    }
}
