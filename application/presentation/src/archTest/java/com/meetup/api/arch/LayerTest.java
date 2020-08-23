package com.meetup.api.arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

public class LayerTest {
  private JavaClasses importedClasses;

  @BeforeEach
  void setUp() {
    importedClasses = new ClassFileImporter().importPackages("com.meetup.api");
  }

  @Test
  void everyRestControllerShouldLiveAtControllerNamedPackage() {
    ArchRule controllerRule =
        classes()
            .that()
            .areAnnotatedWith(RestController.class)
            .should()
            .resideInAPackage("..controller..");

    controllerRule.check(importedClasses);
  }

  @Test
  void everyRestControllerShouldHaveFinalFields() {
    ArchRule controllerRule =
        classes().that().areAnnotatedWith(RestController.class).should().haveOnlyFinalFields();

    controllerRule.check(importedClasses);
  }
}
