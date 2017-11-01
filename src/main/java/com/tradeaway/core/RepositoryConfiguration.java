package com.tradeaway.core;

import com.tradeaway.model.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Buyer.class);
        config.exposeIdsFor(Seller.class);
        config.exposeIdsFor(Item.class);
        config.exposeIdsFor(Inventory.class);
        config.exposeIdsFor(Orders.class);
    }
}
