package com.atgl.g5api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)//permet de configurer la maniere dont on va exeuter les options necessaires pour les tests
@CucumberOptions(
        plugin = {"pretty","json:target/json/results.json"},
        //pretty -> mode d'affichage,
        //json... -> pour specifier où doit on retrouver les resultats de tests generes
        features = {"src/test/java/com/atgl/g5api"},
        glue = {""},//pour les chemins necessaires a l'excution des steps
        tags = ""
)
public class CucumberTests {
}
