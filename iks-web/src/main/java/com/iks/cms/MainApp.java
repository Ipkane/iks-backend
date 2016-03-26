package com.iks.cms;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

/**
 * @author Igor Kaynov
 */
@Configuration
@EnableAutoConfiguration
//@EnableConfigurationProperties( { DbProperties.class } )
//@ComponentScan(basePackages = "com.evolvedbits.video.onboarding.updater")
//@Import( { COwnerConfiguration.class, CSecurityConfig.class, CIntegrationConfig.class, CServiceConfig.class, CMigratorConfig.class, CVideoPlatformConfig.class } )
@SpringBootApplication
public class MainApp {
  public static void main( String[] args ) {
    SpringApplication app = new SpringApplication( MainApp.class );
    app.setBannerMode( Banner.Mode.CONSOLE );
    app.setWebEnvironment( true );
    app.run( args );
  }
}
