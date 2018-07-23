package com.commute.direction.cfg;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "commute")
@Setter
@Getter
public class SystemProperties {
    private final Security security = new Security();
    private final ServiceCfg serviceCfg = new ServiceCfg();
    private final Swagger swagger = new Swagger();

    @Setter
    @Getter
    public static class Security {
        private ApiKeyBased apiKeyBased = new ApiKeyBased();

        @Setter
        @Getter
        public static class ApiKeyBased {
            private final GoogleMaps googleMaps = new GoogleMaps();

            @Setter
            @Getter
            public static class GoogleMaps {
                private String apiKey;
            }
        }
    }

    @Setter
    @Getter
    public static class ServiceCfg {
    }

    @Setter
    @Getter
    public static class Swagger {
        private String title;
        private String description;
        private Boolean enabled;
    }

}
