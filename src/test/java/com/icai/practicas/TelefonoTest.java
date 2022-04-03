package com.icai.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.icai.practicas.model.Telefono;

import org.junit.jupiter.api.Test;

public class TelefonoTest {

    @Test
    public void testDNI()
    {
        //Creamos 3 teléfonos distintos para comprobar los distintos casos
        Telefono telefonoEsp = new Telefono("619321455");
        Telefono telefonoInt = new Telefono("+447887636994");
        Telefono telefonoMalo = new Telefono("12345678987654321");

        //Comprobamos que cumple los requisitos de telefono
        boolean telefonoEspOk = telefonoEsp.validar();
        boolean telefonoIntOk = telefonoInt.validar();
        boolean telefonoMaloOk = telefonoMalo.validar();

        //Vemos que los 2 primeros teléfonos son correctos, mientras que el tercero no
        assertEquals(true,telefonoEspOk);
        assertEquals(true,telefonoIntOk);
        assertEquals(false,telefonoMaloOk);
        
    }
    
}
