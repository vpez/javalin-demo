package com.hackfront.demo;

import com.hackfront.demo.bean.Welcome;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppServer implements InitializingBean {
    private final Javalin javalin;

    @Value("${application.port}")
    private int port;

    @Autowired
    public AppServer() {
        javalin = Javalin.create(config -> config.addStaticFiles("static", Location.CLASSPATH));
        javalin.get("/welcome", this::welcomeHandler);
    }

    private void welcomeHandler(Context context) {
        context.json(new Welcome("Javalin", "Welcome!"));
    }

    @Override
    public void afterPropertiesSet() {
        javalin.start(port);
    }
}
