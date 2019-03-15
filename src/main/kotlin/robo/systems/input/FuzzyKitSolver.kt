package robo.systems.input

import robo.abstracts.Kit
import robo.models.Param
import robo.models.Result

class FuzzyKitSolver(incomingKits: Set<Kit>) {

    private var quickMode: Boolean = false
    private var kits: MutableList<Kit> = ArrayList(incomingKits)
    private var params: MutableList<Param> = ArrayList()
    private var paramResults: MutableList<Param> = ArrayList()
    private var kitResult: Kit? = null

    fun getParamResults(): MutableList<Param> { return paramResults }

    fun solveIntendedKit(incomingParams: List<Param>): Kit? {
        params = ArrayList(incomingParams)
        if (containsRoboQuery(incomingParams)) filterResults()
        return kitResult
    }

    private fun containsRoboQuery(incomingParams: List<Param>): Boolean {
        val firstParam: String = incomingParams[0].toString()
        if (firstParam.equals("robo", ignoreCase = true)) return true
        else if (firstParam.substring(0, 2).equals(".r", ignoreCase = true)) {
            filterParamsForQuickMode(firstParam)
            return true
        } else return false
    }

    private fun filterResults() {
        for (i in 1 until params.size) {
            val foundKits = findKit(params[i])
            if (foundKits.isEmpty()) paramResults.add(params[i])
            else {
                kitResult = foundKits[0]
                kits = ArrayList(kitResult!!.getSubKits())
            }
        }
    }

    private fun filterParamsForQuickMode(firstParam: String) {
        quickMode = true
        // Redefine params with additional quickMode sigs at the beginning
        val quickParams = ArrayList<Param>()
        quickParams.add(Param(".r"))
        for (i in 2 until firstParam.length) quickParams.add(Param(firstParam.substring(i, i + 1)))
        params = ArrayList(quickParams)
    }

    private fun findKit(param: Param): List<Kit> {
        val finder: FuzzyFinder<Kit>
        if (!quickMode) finder = FuzzyFinder{entity -> Result(entity, entity.getQuery())}
        else finder = FuzzyFinder{entity -> Result(entity, entity.getQuery().substring(0, 1))}
        return finder.getResultsUsing(kits, param).map { result -> result.entity }
    }

}

