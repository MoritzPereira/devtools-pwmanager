module devtools.components {
  exports de.hhn.it.devtools.components.example.coffeemakerservice.provider;
    exports de.hhn.it.devtools.components.paint;
  requires org.slf4j;
  requires devtools.apis;
  provides  de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService
      with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides  de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService
      with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides de.hhn.it.devtools.apis.pwmanager.PwManagerService
      with de.hhn.it.devtools.components.pwmanager.PwManagerService;
}

