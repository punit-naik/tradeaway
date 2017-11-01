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

import com.tradeaway.model.Inventory;
import com.tradeaway.model.InventoryRepository;
import com.tradeaway.model.ItemRepository;
import com.tradeaway.model.SellerRepository;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryTest {

    private String sellerMockData = "{\"name\":\"starkSeller\", \"email\":\"blah.blah.seller@xyz.com\",\"userName\":\"team_stark_seller\",\"password\":\"blahblah\",\"address\":\"asdsad, asdasd,asda\",\"pan\":\"azuij1254f\",\"experience\":\"2\"}";

    private String itemMockData = "{\"name\":\"Iphone 8 plus\",\"category\":\"ELECTRONICS\",\"shortDesc\":\"blah blah\"}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
        inventoryRepository.deleteAll();
        sellerRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    public void shouldCreateEntity() throws Exception {

        // Seller

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        String responseData = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseData);
        String sellerLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        // Item

        mvcResult = mockMvc.perform(post("/item").content(itemMockData)).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        responseData = mvcResult.getResponse().getContentAsString();

        jsonObject = new JSONObject(responseData);
        String itemLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("quantity",2);
        m.put("price",1000);
        m.put("seller",sellerLink);
        m.put("item",itemLink);

        JSONObject inventoryMockData = new JSONObject(m);

        mockMvc.perform(post("/inventory").content(inventoryMockData.toString())).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("inventory/")));

    }

    @Test
    public void shouldRetrieveEntity() throws Exception {

        // Seller

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        String responseData = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseData);
        String sellerLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        // Item

        mvcResult = mockMvc.perform(post("/item").content(itemMockData)).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        responseData = mvcResult.getResponse().getContentAsString();

        jsonObject = new JSONObject(responseData);
        String itemLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("quantity",2);
        m.put("price",1000);
        m.put("seller",sellerLink);
        m.put("itemId",itemLink);

        JSONObject inventoryMockData = new JSONObject(m);

        mvcResult = mockMvc.perform(post("/inventory").content(inventoryMockData.toString())).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.quantity").value("2"));
    }

    @Test
    @Ignore
    public void shouldQueryEntity() throws Exception {

        // Seller

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        String responseData = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseData);
        String sellerLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        // Item

        mvcResult = mockMvc.perform(post("/item").content(itemMockData)).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        responseData = mvcResult.getResponse().getContentAsString();

        jsonObject = new JSONObject(responseData);
        String itemLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("quantity",2);
        m.put("price",1000);
        m.put("seller",sellerLink);
        m.put("item",itemLink);

        JSONObject inventoryMockData = new JSONObject(m);

        mvcResult = mockMvc.perform(post("/inventory").content(inventoryMockData.toString())).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(post("/inventory").content(inventoryMockData.toString())).andExpect(
                status().isCreated());

        mockMvc.perform(
                get("/inventory/search/findByItemId?item_id={item_id}", itemLink)).andExpect(
                status().isOk()).andExpect(
                jsonPath("$.content[0].item_id").value(
                        itemLink));

        mockMvc.perform(
                get("/inventory/search/findBySellerId?seller_id={seller_id}", sellerLink)).andExpect(
                status().isOk()).andExpect(
                jsonPath("$.content[0].seller_id").value(
                        sellerLink));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        // Seller

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        String responseData = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseData);
        String sellerLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        // Item

        mvcResult = mockMvc.perform(post("/item").content(itemMockData)).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        responseData = mvcResult.getResponse().getContentAsString();

        jsonObject = new JSONObject(responseData);
        String itemLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("quantity",2);
        m.put("price",1000);
        m.put("seller",sellerLink);
        m.put("item",itemLink);

        JSONObject inventoryMockData = new JSONObject(m);

        mvcResult = mockMvc.perform(post("/inventory").content(inventoryMockData.toString())).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(put(location).content(inventoryMockData.toString())).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.quantity").value("2"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {

        // Seller

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        String responseData = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseData);
        String sellerLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        // Item

        mvcResult = mockMvc.perform(post("/item").content(itemMockData)).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        responseData = mvcResult.getResponse().getContentAsString();

        jsonObject = new JSONObject(responseData);
        String itemLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("quantity",2);
        m.put("price",1000);
        m.put("seller",sellerLink);
        m.put("item",itemLink);

        JSONObject inventoryMockData = new JSONObject(m);

        mvcResult = mockMvc.perform(post("/inventory").content(inventoryMockData.toString())).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(
                patch(location).content("{\"quantity\": \"3\"}")).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.quantity").value("3"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {

        // Seller

        MvcResult mvcResult = mockMvc.perform(post("/seller").content(sellerMockData)).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        String responseData = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseData);
        String sellerLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        // Item

        mvcResult = mockMvc.perform(post("/item").content(itemMockData)).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");

        mvcResult= mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(anything()))
                .andReturn();

        responseData = mvcResult.getResponse().getContentAsString();

        jsonObject = new JSONObject(responseData);
        String itemLink = ((JSONObject)jsonObject.getJSONArray("links").get(0)).getString("href");

        Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("quantity",2);
        m.put("price",1000);
        m.put("seller",sellerLink);
        m.put("item",itemLink);

        JSONObject inventoryMockData = new JSONObject(m);

        mvcResult = mockMvc.perform(post("/inventory").content(inventoryMockData.toString())).andExpect(
                status().isCreated()).andReturn();

        location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }

}
