package com.mercadolibre.itacademy

class Articulo {
    String name
    String picture
    int total_items_in_this_category
    static belongsTo = [marca:Marca]

    static constraints = {
        name blank: false, nullable: false
        picture blank:false, nullable: false
        total_items_in_this_category min:0
    }
}
