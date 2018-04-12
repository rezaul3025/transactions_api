package com.trans.api.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.trans.api.TransactionApiApplication;
import com.trans.api.event.TransactionEvent;
import com.trans.api.utils.Constant;

/**
 * @author rkarim
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionApiApplication.class)
@WebAppConfiguration
public class TransactionApiControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  private MockMvc mockMvc;

  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();

  }

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

    assertNotNull("the JSON message converter must not be null",
        this.mappingJackson2HttpMessageConverter);
  }

  protected String json(Object o) throws IOException {
    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON,
        mockHttpOutputMessage);
    return mockHttpOutputMessage.getBodyAsString();
  }
  
  

  @Test
  public void transactionCreateTest() throws IOException, Exception {

    Long testLocalTime = LocalDateTime.now(ZoneId.of(Constant.UTC_TIME_ZONE_IND)).minusSeconds(40)
        .toEpochSecond(ZoneOffset.UTC) * 1000;

    TransactionEvent transactionEventCreated = new TransactionEvent(12.3, testLocalTime);

    mockMvc.perform(post("/api/v1/transaction/create").content(this.json(transactionEventCreated))
        .contentType(contentType)).andExpect(status().isCreated());

    TransactionEvent transactionEventNoContent = new TransactionEvent(12.3, 1478192204000L);
    mockMvc.perform(post("/api/v1/transaction/create").content(this.json(transactionEventNoContent))
        .contentType(contentType)).andExpect(status().isNoContent());

  }

  @Test
  public void getTransactionStatisticsTest() throws IOException, Exception {

    Long localTimeStart = LocalDateTime.now(ZoneId.of(Constant.UTC_TIME_ZONE_IND)).minusSeconds(60)
        .toEpochSecond(ZoneOffset.UTC);
    Long localTimeEnd =
        LocalDateTime.now(ZoneId.of(Constant.UTC_TIME_ZONE_IND)).toEpochSecond(ZoneOffset.UTC);

    double amount = 20.5;

    for (long i = localTimeStart; i < localTimeEnd; i++) {

      amount = amount + 2.0;

      TransactionEvent transactionEventCreated = new TransactionEvent(amount, i * 1000);

      mockMvc.perform(post("/api/v1/transaction/create").content(this.json(transactionEventCreated))
          .contentType(contentType)).andExpect(status().isCreated());
    }

    mockMvc.perform(get("/api/v1/transaction/statistics")).andExpect(status().isOk())
        .andExpect(content().contentType(contentType));

  }

}
