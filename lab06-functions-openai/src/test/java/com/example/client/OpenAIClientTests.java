package com.example.client;

import com.example.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//  TODO-13: Define this test class as a Spring Boot test.
@SpringBootTest
public class OpenAIClientTests {

    @Autowired
    private AIClient client;

    private String samplePrompt = "Provide a 50-100 word overview of company NVDA, including today's trading information such as price and volume.";
    private String[] sampleResults = {"NVDA", StockService.price + "", StockService.volume + ""};

    @Test
    void testCallApi() {
        String response = client.callApi(samplePrompt);
        assertThat(response).isNotNull();
        assertThat(response).contains(sampleResults);
        System.out.println("The response is: " + response);
    }
}
