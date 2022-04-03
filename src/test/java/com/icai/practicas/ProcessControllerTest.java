package com.icai.practicas;

import com.icai.practicas.controller.ProcessController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //LoginControllerE2ETest del Github de ejemplos
    @Test
    public void given_app_when_login_using_right_credentials_then_ok() {

        // Given
        String address = "http://localhost:" + port + "/api/v1/process-step1";

        //Datos
        ProcessController.DataRequest datos = new ProcessController.DataRequest("Miguel Gonzalez", "06618034Z", "645342766");
        
        //Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(datos, headers);

        // When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void given_app_when_login_using_bad_credentials_then_KO() throws Exception {
        
        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1";

        //Datos con fallos
        ProcessController.DataRequest datosDNIMalo = new ProcessController.DataRequest("Miguel Gonzalez", "","645342766");
        ProcessController.DataRequest datosTelefonoMalo = new ProcessController.DataRequest("Miguel Gonzalez", "06618034Z", "");

        // Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProcessController.DataRequest> requestErrorDNI = new HttpEntity<>(datosDNIMalo, headers);
        HttpEntity<ProcessController.DataRequest> requestErrorTelf = new HttpEntity<>(datosTelefonoMalo, headers);

        // Response
        ResponseEntity<String> resultErrorDNI = this.restTemplate.postForEntity(address, requestErrorDNI, String.class);
        ResponseEntity<String> resultErrorTelf = this.restTemplate.postForEntity(address, requestErrorTelf,
                String.class);

        //Comprobacion de codigos
        then(resultErrorDNI.getBody()).isEqualTo("{\"result\":\"KO\"}");
        then(resultErrorTelf.getBody()).isEqualTo("{\"result\":\"KO\"}");

        then(resultErrorDNI.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(resultErrorTelf.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }   

    @Test
    public void given_app_when_login_using_right_credentials_then_ok_legacy() {
        
        // Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        //Test valido 
        MultiValueMap<String, String> data1 = new LinkedMultiValueMap<>();
        data1.add("fullName", "Miguel Gonzalez");
        data1.add("dni", "06618034Z");
        data1.add("telefono", "645342766");

        //Construimos el request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //Request test 1
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data1, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        
        //Comprobamos que es correcto
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        
    }
    @Test
    public void given_app_when_login_using_bad_credentials_then_KO_legacy() {
        
        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        //Test nombre inválido
        MultiValueMap<String, String> data2 = new LinkedMultiValueMap<>();
        data2.add("fullName", "Javier Gonzalez");
        data2.add("dni", "06618034Z");
        data2.add("telefono", "645342766");

        //Test DNI invalido 
        MultiValueMap<String, String> data3 = new LinkedMultiValueMap<>();
        data3.add("fullName", "Miguel Gonzalez");
        data3.add("dni", "06618034Y");
        data3.add("telefono", "645342766");

        //Test telefono invalido
        MultiValueMap<String, String> data4 = new LinkedMultiValueMap<>();
        data3.add("fullName", "Miguel Gonzalez");
        data3.add("dni", "06618034Z");
        data3.add("telefono", "645342222");

        // Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //Request test 2
        HttpEntity<MultiValueMap<String, String>> request_2 = new HttpEntity<>(data2, headers);
        ResponseEntity<String> result_2 = this.restTemplate.postForEntity(address, request_2, String.class);
    
        //Request test 3
        HttpEntity<MultiValueMap<String, String>> request_3 = new HttpEntity<>(data3, headers);
        ResponseEntity<String> result_3 = this.restTemplate.postForEntity(address, request_3, String.class);

        //Request test 4
        HttpEntity<MultiValueMap<String, String>> request_4 = new HttpEntity<>(data4, headers);
        ResponseEntity<String> result_4 = this.restTemplate.postForEntity(address, request_4, String.class);

        //Comprobamos que coinciden los codigos de error con los esperados
        then(result_2.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result_3.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result_4.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
