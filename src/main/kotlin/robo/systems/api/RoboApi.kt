package robo.systems.api

import robo.models.posts.KitPost
import robo.systems.output.Status

import java.util.concurrent.CompletableFuture

interface RoboApi {

    val isConnected: Boolean

    var status: Status

    fun send(kitPost: KitPost): CompletableFuture<KitPost>

    fun connect(key: String): CompletableFuture<Boolean>

}
