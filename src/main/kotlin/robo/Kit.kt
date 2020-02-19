package robo

import robo.models.Param
import robo.systems.api.ApiJavacord


/**
 * Primary class for the RoboCore library, and [Kit] operations management.
 *
 * @author  aze
 * @since   0.0.2
 *
 */
abstract class Kit(vararg queries: String) { 

    private val queries: Set<String> = setOf(*queries)
    private val subKits: MutableSet<Kit> = mutableSetOf() // FIXME: MAKE THIS NOT CAUSE A STACK OVERFLOW

    internal fun installSubKits(vararg incomingKits: Kit) = this.subKits.addAll(incomingKits)
    internal fun getSubKits(): MutableSet<Kit> = subKits

    internal fun getQuery(): String = queries.firstOrNull() ?: "" // TODO: FIND A WAY TO CONSOLIDATE getQuery & getQueries
    internal fun getQueries(): Set<String> = queries


    internal val api: ApiJavacord = ApiJavacord()

    open fun onStart() {}
    open fun onPulse(current: Long = -1) {}
    open fun onQuery(params: List<Param>) {}

}
