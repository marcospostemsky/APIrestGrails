package com.mercadolibre.itacademy

import grails.converters.JSON

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
        def hotel1 = new Hotel(name: 'Hotel Vista').save(flush:true) //flush true es como un force
        def hotel2 = new Hotel(name: 'Premium Tower').save(flush:true)
        hotel1.addToRooms(new Room(number: 201)).save()
        hotel1.addToRooms(new Room(number: 202)).save() //no hace falta el flush
        hotel1.addToRooms(new Room(number: 203)).save()

        hotel2.addToRooms(new Room(number: 301)).save()
        hotel2.addToRooms(new Room(number: 302)).save() //no hace falta el flush
        hotel2.addToRooms(new Room(number: 303)).save()

        def marca1 = new Marca(name: 'Nike').save(flush:true)
        def marca2 = new Marca(name: 'Adidas').save(flush:true)
        def marca3 = new Marca(name: 'Philips').save(flush:true)

        marca1.addToArticulos(new Articulo(name:"Zapatillas numero 40",total_items_in_this_category:10,picture: "https://static.netshoes.com.ar/produtos/zapatillas-nike-md-runner-2-sued/02/001-6016-002/001-6016-002_zoom1.jpg"))
        marca1.addToArticulos(new Articulo(name:"Gorras",total_items_in_this_category:25,picture: "https://static.netshoes.com.ar/produtos/zapatillas-nike-md-runner-2-sued/02/001-6016-002/001-6016-002_zoom1.jpg" ))
        marca1.addToArticulos(new Articulo(name:"Pantalones",total_items_in_this_category:115,picture: "https://static.netshoes.com.ar/produtos/zapatillas-nike-md-runner-2-sued/02/001-6016-002/001-6016-002_zoom1.jpg"))
        marca1.addToArticulos(new Articulo(name:"Mochila", total_items_in_this_category:98,picture: "https://static.netshoes.com.ar/produtos/zapatillas-nike-md-runner-2-sued/02/001-6016-002/001-6016-002_zoom1.jpg"))

        marca2.addToArticulos(new Articulo(name:"Remera",total_items_in_this_category:67,picture: "https://essential.vteximg.com.br/arquivos/ids/209652-454-423/266-5577_1.jpg?v=636791164559900000"))
        marca2.addToArticulos(new Articulo(name:"Short",total_items_in_this_category:34,picture: "https://static.netshoes.com.ar/produtos/zapatillas-nike-md-runner-2-sued/02/001-6016-002/001-6016-002_zoom1.jpg"))


        marshaler()
        marshaler1()
    }
    def destroy = {
    }

    private void marshaler() {
        JSON.registerObjectMarshaller(Marca) {
            marca ->[
                    id: marca.id,
                    name: marca.name,
            ]
        }
        JSON.registerObjectMarshaller(Articulo) {
            articulo -> [
                    id: articulo.id,
                    name: articulo.name,
                    picture: articulo.picture,
                    nameMarca: articulo.marca.name,
                    idMarca: articulo.marca.id,
                    total_items_in_this_category: articulo.total_items_in_this_category,
                    children_categories: []
            ]
        }
    }

    private void marshaler1() {
        JSON.registerObjectMarshaller(Hotel) {
            hotel ->[
                    id: hotel.id,
                    name: hotel.name,
                    rooms: hotel.rooms.collect {
                        room -> [
                                id: room.id,
                                number: room.number
                        ]
                    }
            ]
        }
        JSON.registerObjectMarshaller(Room) {
            room -> [
                    id: room.id,
                    number: room.number,
                    date: new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                    hotel: room.hotel.name
            ]
        }
    }

}
