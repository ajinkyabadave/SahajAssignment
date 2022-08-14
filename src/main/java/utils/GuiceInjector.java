package utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import controller.Driver;

public class GuiceInjector {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BindingModule());
        Driver driver = injector.getInstance(Driver.class);
        driver.run();
    }
}
