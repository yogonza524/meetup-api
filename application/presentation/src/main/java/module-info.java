module com.meetup.api.presentation {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.web;
  requires spring.beans;
  requires spring.context;
  requires com.meetup.api.business;
  requires com.meetup.api.infratructure.weather;
  requires io.swagger.v3.core;
  requires io.swagger.v3.oas.annotations;
  requires io.swagger.v3.oas.models;
  requires org.slf4j;
  requires org.aspectj.weaver;
  requires org.apache.tomcat.embed.core;

  exports com.meetup.api.presentation;
}
