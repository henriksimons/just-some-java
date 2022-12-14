package assignments.four.endpoint;

import assignments.four.AccountServiceImpl;
import assignments.four.PersonServiceImpl;
import assignments.four.Utils;
import assignments.four.endpoint.model.CreateAccountRequestModel;
import assignments.four.endpoint.model.GetAccountRequestModel;
import assignments.four.endpoint.model.GetPersonRequestModel;
import assignments.one.Account;
import assignments.one.AccountFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Set;
import java.util.logging.Logger;

public class ApiServlet {

    private static final Logger LOGGER = Logger.getLogger(ApiServlet.class.getName());
    private final ApiService apiService;

    public ApiServlet() {
        this.apiService = new ApiServiceImpl(new AccountServiceImpl(AccountFactory.getInstance()), PersonServiceImpl.getInstance());
        LOGGER.info("Starting RestService...");
    }

    public void start() throws IOException {

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

        server.setExecutor(null);
        server.start();
    }

    private static void getAccountsForPerson(ApiService apiService, HttpExchange exchange) throws IOException {
        String bodyJsonString = getBodyJsonString(exchange);
        // TODO: 2022-09-12 Check if something is null before parsing...
        GetPersonRequestModel requestModel = Utils.getParser().fromJson(bodyJsonString, GetPersonRequestModel.class);
        Set<Account> accounts = apiService.getAccounts(requestModel.getId());
        String response = accounts.toString();

        sendOkResponse(exchange, response);
    }

    private static String getBodyJsonString(HttpExchange exchange) {
        InputStream bodyInputStream = getRequestBody(exchange);
        return Utils.readInputStream(bodyInputStream);
    }

    private static void createAccountForPerson(ApiService apiService, HttpExchange exchange) throws IOException {
        String bodyJsonString = getBodyJsonString(exchange);
        CreateAccountRequestModel requestModel = Utils.getParser().fromJson(bodyJsonString, CreateAccountRequestModel.class);
        if (requestModel.getAccountId() == null || requestModel.getAccountId().length() == 0) {
            sendBadRequestResponse(exchange, "Request parameter accountId can not be missing.");
        } else {
            boolean created = apiService.createAccount(requestModel.getAccountId(), requestModel.getPersonId());
            if (!created) {
                sendForbiddenResponse(exchange, "Account with id " + requestModel.getAccountId() + " already exists.");
            }
            Account account = apiService.getAccount(requestModel.getAccountId());
            String response = account.toString();
            sendOkResponse(exchange, response);
        }
    }

    private static boolean isGET(HttpExchange exchange) {
        return HttpMethod.GET.name().equals(exchange.getRequestMethod());
    }

    private static void sendBadRequestResponse(HttpExchange exchange, String message) throws IOException {
        exchange.sendResponseHeaders(400, message.length());
        OutputStream output = exchange.getResponseBody();
        output.write(message.getBytes());
        output.flush();
    }

    private static void sendForbiddenResponse(HttpExchange exchange, String message) throws IOException {
        exchange.sendResponseHeaders(403, message.length());
        OutputStream output = exchange.getResponseBody();
        output.write(message.getBytes());
        output.flush();
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

    private static void getAccountById(ApiService apiService, HttpExchange exchange) throws IOException {
        String bodyJsonString = getBodyJsonString(exchange);
        GetAccountRequestModel requestModel = Utils.getParser().fromJson(bodyJsonString, GetAccountRequestModel.class);
        if (requestModel.getId() == null || requestModel.getId().length() == 0) {
            sendBadRequestResponse(exchange, "Request parameter id can not be missing.");
        } else {
            Account account = apiService.getAccount(requestModel.getId());
            String response = account.toString();
            sendOkResponse(exchange, response);
        }
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