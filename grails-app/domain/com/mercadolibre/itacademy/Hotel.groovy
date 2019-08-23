package com.mercadolibre.itacademy

import grails.rest.Resource

@Resource(uri='/hotels')
class Hotel {

    String name
    static hasMany = [rooms:Room] // es para libernos de responsabilidades y que directamente hibernet se haga cargo

    //Ver en documentación de Grails cuales son los constraints
    static constraints = {
        name blank: false, nullable: false
    }

}
