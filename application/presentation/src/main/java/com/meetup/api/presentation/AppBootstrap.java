/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.meetup.api.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SuppressWarnings("PMD")
@SpringBootApplication
@EnableAspectJAutoProxy
public class AppBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(AppBootstrap.class, args);
  }
}
