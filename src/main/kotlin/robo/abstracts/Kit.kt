package robo.abstracts

import robo.Core
import robo.models.Param


abstract class Kit(private var query: String, val core: Core) {

    private val subKits: MutableSet<Kit> = mutableSetOf()

    fun getSubKits(): MutableSet<Kit> = subKits
    fun getQuery(): String = query

    internal fun installKits(incomingKits: Set<Kit>) = this.subKits.addAll(incomingKits)

    open fun onStart() {}
    open fun onPulse(current: Long = -1) {}
    open fun onQuery(params: List<Param>) {}


}
