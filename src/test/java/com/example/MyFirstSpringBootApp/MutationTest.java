package com.example.MyFirstSpringBootApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MutationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void smallerThanOrEqualToFiftyMessage() throws Exception {
    this.mockMvc
        .perform(get("/compareToFifty/50")) // example of mutant: compare with 49
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("Smaller than or equal to 50"));
  }

  @Test
  public void greaterThanFiftyMessage() throws Exception {
    this.mockMvc
        .perform(get("/compareToFifty/51"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("Greater than 50"));
  }

  @Test
  public void increment() throws Exception {
    this.mockMvc
        .perform(get("/increment/5"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(
            content().string("6")); // example of mutant: do not check if the value was incremented
  }
}
