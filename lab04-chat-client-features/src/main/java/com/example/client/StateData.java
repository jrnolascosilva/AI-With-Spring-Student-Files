package com.example.client;

//  TODO-11:  Add additional fields to the StateData record.
//  Add whatever fields you might like to know about a given state.
//  Examples might include:  
//  area in square miles, population, 
//  famous for, state bird, state flower, state motto, etc.

import org.springframework.context.annotation.Description;

public record StateData(
    @Description("Name of the state")
    String stateName,
    @Description("Name of the capital city")
    String capitalCity
    ) {}
