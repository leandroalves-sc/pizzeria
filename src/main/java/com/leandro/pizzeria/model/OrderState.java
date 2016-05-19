package com.leandro.pizzeria.model;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by leandroalves on 5/15/16.
 */
public enum OrderState {

    ORDERED,
    PROCESSING,
    DELIVERED,
    CANCELLED;

    public OrderState getNextState(){

        OrderState nextState = null;

        switch (this){
            case ORDERED:
                nextState = PROCESSING;
                break;
            case PROCESSING:
                nextState = DELIVERED;
                break;
        }

        return nextState;
    }

    public String prettyName(){
        return WordUtils.capitalize(name().toLowerCase());
    }
}
