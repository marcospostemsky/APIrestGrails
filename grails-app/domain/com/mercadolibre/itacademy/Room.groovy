package com.mercadolibre.itacademy

import grails.rest.Resource //Este paquete hace las veces de controlador, si hacemos bien la estructura podemos hacer todo con esto.

@Resource(uri="/rooms")
class Room {

    int number
    static belongsTo = [hotel:Hotel]

    static constraints = {
        number min:100, max:530
    }
}
