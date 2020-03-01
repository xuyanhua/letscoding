package com.yanhua.javabase.net.http;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new AbstractHandler() {
            @Override
            public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                request.setHandled(true);
                response.getWriter().println("<h1>Hello World</h1>");
                System.out.println(httpServletRequest.getMethod());
            }
        });
        server.start();
        server.join();
    }
}
