package assignments.four.endpoint;

import assignments.four.Util;
import assignments.one.Account;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Set;

public class RestService {

    public static void start() throws IOException {

        ApiService apiService = new ApiService();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/person", (exchange -> {
            try {
                if (isGET(exchange)) {
                    getAccountsForPerson(apiService, exchange);
                } else {
                    sendMethodNotAllowedResponse(exchange);
                }
            } catch (Exception e) {
                sendInternalServerErrorResponse(exchange, e);
            }
            exchange.close();
        }));

        server.createContext("/account", (exchange -> {
            try {
                if (isGET(exchange)) {
                    getAccountById(apiService, exchange);
                } else if (isPOST(exchange)) {
                    createAccountForPerson(apiService, exchange);
                } else {
                    sendMethodNotAllowedResponse(exchange);
                }
            } catch (Exception e) {
                sendInternalServerErrorResponse(exchange, e);
            }
            exchange.close();
        }));

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private static void getAccountsForPerson(ApiService apiService, HttpExchange exchange) throws IOException {
        String bodyJsonString = getBodyJsonString(exchange);
        GetPersonRequestModel requestModel = Util.getParser().fromJson(bodyJsonString, GetPersonRequestModel.class);
        Set<Account> accounts = apiService.getAccounts(requestModel.getId());
        String response = accounts.toString();

        sendOkResponse(exchange, response);
    }

    private static void createAccountForPerson(ApiService apiService, HttpExchange exchange) throws IOException {
        String bodyJsonString = getBodyJsonString(exchange);
        CreateAccountRequestModel requestModel = Util.getParser().fromJson(bodyJsonString, CreateAccountRequestModel.class);
        apiService.createAccount(requestModel.getAccountId(), requestModel.getPersonId());
        Account account = apiService.getAccount(requestModel.getAccountId());
        String response = account.toString();
        sendOkResponse(exchange, response);
    }

    private static void getAccountById(ApiService apiService, HttpExchange exchange) throws IOException {
        String bodyJsonString = getBodyJsonString(exchange);
        GetAccountRequestModel requestModel = Util.getParser().fromJson(bodyJsonString, GetAccountRequestModel.class);
        Account account = apiService.getAccount(requestModel.getId());
        String response = account.toString();
        sendOkResponse(exchange, response);
    }

    private static boolean isGET(HttpExchange exchange) {
        return HttpMethod.GET.name().equals(exchange.getRequestMethod());
    }

    private static String getBodyJsonString(HttpExchange exchange) {
        InputStream bodyInputStream = getRequestBody(exchange);
        return Util.readInputStream(bodyInputStream);
    }

    private static void sendOkResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream output = exchange.getResponseBody();
        output.write(response.getBytes());
        output.flush();
    }

    private static void sendMethodNotAllowedResponse(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(405, -1);
    }

    private static void sendInternalServerErrorResponse(HttpExchange exchange, Exception e) throws IOException {
        exchange.sendResponseHeaders(500, e.getMessage().length());
        OutputStream output = exchange.getResponseBody();
        output.write(e.getMessage().getBytes());
        output.flush();
    }

    private static boolean isPOST(HttpExchange exchange) {
        return HttpMethod.POST.name().equals(exchange.getRequestMethod());
    }

    private static InputStream getRequestBody(HttpExchange exchange) {
        return exchange.getRequestBody();
    }
}