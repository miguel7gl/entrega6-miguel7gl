package com.icai.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.icai.practicas.model.DNI;
import org.junit.jupiter.api.Test;

public class DNITest {

    @Test
    public void testDNI()
    {
        //Creamos un DNI bueno y uno malo
        DNI dniBueno = new DNI("06679111A");
        DNI dniMalo = new DNI("1234567891");

        //Comprobamos que cumple los requisitos de DNI
        boolean dniBuenoOk = dniBueno.validar();
        boolean dniMaloOk = dniMalo.validar();

        //Vemos que el primer DNI es ebueno y el otro es malo
        assertEquals(true,dniBuenoOk);
        assertEquals(false,dniMaloOk);
    }
    
}
