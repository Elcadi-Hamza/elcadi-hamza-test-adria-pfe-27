package com.hamza.accountservice.web.graphql;

public record AccountInput(
        String customerReference,
        String currency
) {}
