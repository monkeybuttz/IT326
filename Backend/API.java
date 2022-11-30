package Backend;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class API {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/greeting", ((exchange) -> {
        //     System.out.print("hello");
        //     System.out.println(exchange.getRequestBody().read());
            if ("GET".equals(exchange.getRequestMethod())) {

                // InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                // BufferedReader br = new BufferedReader(isr);
                // String value = br.readLine();
                String responseText = "Hello World! from our framework-less REST API\n";
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseText.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));

        server.setExecutor(null); // creates a default executor
        server.start();

    }
}
