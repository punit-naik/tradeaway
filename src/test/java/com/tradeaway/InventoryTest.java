/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tradeaway;

import com.tradeaway.model.SellerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SellerTest {

    private String sellerMockData = "{\"name\":\"starkSeller\", \"email\":\"blah.blah.seller@xyz.com\",\"userName\":\"team_stark_seller\",\"password\":\"blahblah\",\"address\":\"asdsad, asdasd,asda\",\"pan\":\"azuij1254f\",\"experience\":\"2\"}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homePage() throws Exception {
        // N.B. jsoup can be useful for asserting HTML content
        mockMvc.perform(get("/index.html"))
                .andExpect(content().string(containsString("Welcome to TradeAway")));
    }

    @Autowired
    private SellerRepository sellerRepository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
        sellerRepository.deleteAll();
    }

    @Test
    public void shouldCreateEntity() throws Exception {

        mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("seller/")));
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.name").value("starkSeller"));
    }

    @Test
    public void shouldQueryEntity() throws Exception {

        mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated());

        mockMvc.perform(
                get("/seller/search/findByName?name={name}", "starkSeller")).andExpect(
                status().isOk()).andExpect(
                jsonPath("$._embedded.seller[0].name").value(
                        "starkSeller"));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(put(location).content(sellerMockData)).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.name").value("starkSeller"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(
                patch(location).content("{\"name\": \"stark_2\"}")).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.name").value("stark_2"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }

}
