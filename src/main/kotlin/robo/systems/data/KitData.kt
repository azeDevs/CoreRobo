package robo.systems.data

import robo.Kit


/**
 * Maintains connection to a MySQL database
 */
class KitData : Kit() {

//    fun putData() {
//        val form = "jdbc:mysql://104.131.111.9:3306/%s?user=%s&password=%s"
//        val jdbiData =
//            Jdbi.create(String.format(form, "robo_data", "botsdebug", Az.getTokenFromFile("keys", "systems-coreData")))
//        jdbiData.useHandle({ handle -> handle.execute("create table contacts (id int primary key, name varchar(100))") })
//        jdbiData.useHandle({ handle -> handle.execute("insert into contacts (id, name) values (?, ?)", 1, "Alice") })
//        jdbiData.useHandle({ handle -> handle.execute("insert into contacts (id, name) values (?, ?)", 2, "Bob") })
//    }
//
//    fun getData() {
//        val form = "jdbc:mysql://104.131.111.9:3306/%s?user=%s&password=%s"
//        val jdbiData =
//            Jdbi.create(String.format(form, "robo_data", "botsdebug", Az.getTokenFromFile("keys", "systems-coreData")))
//        val names = jdbiData.withHandle({ handle ->
//            handle.createQuery("select name from contacts")
//                .mapTo(String::class.java)
//                .list()
//        })
//        names.forEach { s -> println(s) }
//    }

    override fun onPulse(current: Long) {}

}
