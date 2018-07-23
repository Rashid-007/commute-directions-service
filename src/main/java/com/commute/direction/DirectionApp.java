package com.commute.direction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
@EnableResourceServer
public class DirectionApp {

    /**
     * default entry point
     *
     * @param args unused
     * @throws UnknownHostException
     */
    @SuppressWarnings("squid:S1313") // IP addresses should not be hardcoded => In this case it is ok
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(DirectionApp.class);
        Environment env = app.run(args).getEnvironment();

        log.info(
                "\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs: \n\t"
                        + "Local: \t\thttp://127.0.0.1:{} \n\t" + "External: \thttp://{}:{}"
                        + "\n----------------------------------------------------------",
                env.getProperty("spring.application.name"), env.getProperty("server.port"), InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
}
