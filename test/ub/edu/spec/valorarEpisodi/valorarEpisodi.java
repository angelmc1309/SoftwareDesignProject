package ub.edu.spec.valorarEpisodi;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import controller.Controller;

@RunWith(ConcordionRunner.class)
public class valorarEpisodi {
    private Controller controlador;
    @BeforeExample
    public void init() {
        controlador = Controller.getInstance();
    }
    public String valorarEpisodiAmbThumb(String client,String usuari,String idSerie, int numTemporada,
                                         String idEpisodi, int valoracio){

        return controlador.valorarEpisodiAmbThumb(client,usuari,idSerie,numTemporada,idEpisodi,valoracio);
    }
    public String valorarEpisodiAmbEstrelles(String client,String usuari,String idSerie, int numTemporada,
                                         String idEpisodi, int valoracio){

        return controlador.valorarEpisodiAmbEstrella(client,usuari,idSerie,numTemporada,idEpisodi,valoracio);
    }


}
