/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author bouyo
 */
public class Prod {
   public int Stock ;  

    public Prod(int Stock) {
        this.Stock = Stock;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
   
}
