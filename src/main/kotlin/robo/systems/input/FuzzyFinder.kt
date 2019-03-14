package robo.systems.input

import robo.models.Param
import robo.models.Result

import java.util.ArrayList
import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * FuzzyFinder class for searching through a List of entities.
 *
 * @param T the type of Objects that will populate FuzzyFinder for parsing. */
class FuzzyFinder<T> (private val lookup: LookupEntity<T>) {
    private val ACCEPTANCE_RANGE = 0.5f
    private val entities: MutableList<Result<T>> = ArrayList()

    fun getResultsUsing(incomingEntities: List<T>, query: Param): List<Result<T>> {
        initEntityResults(incomingEntities)

        val outList = entities.stream()
            .flatMap(fun(entity: Result<T>): Stream<Result<T>>? {
                return Stream.of<Result<T>>(
                    entity.addScore(
                        getScore(
                            query.toString(),
                            entity.name
                        )
                    )
                )
            } as Function<Result<T>, Stream<Result<T>>>).sorted { o1, o2 -> o2.score.toInt() - o1.score.toInt() }
            .collect(Collectors.toList())
        return filterAcceptanceRange(outList).stream().collect(Collectors.toList())
    }

    private fun initEntityResults(incomingEntities: List<T>) {
        incomingEntities.stream()
            .flatMap(fun(entity: T): Stream<Result<T>>? {
                return Stream.of(lookup(entity))
            } as Function<T, Stream<Result<T>>>)
            .forEach { entityResult -> if (entityResult != null) entities.add(entityResult) }
    }

    private fun getScore(queryText: String, entityText: String): Float {
        var score = 0.0f
        for (first in 0 until queryText.length)
            for (last in first + 2 until queryText.length + 1)
                if (entityText.toUpperCase().contains(queryText.substring(first, last).toUpperCase()))
                    score += getPointValue(queryText.substring(first, last), entityText.replace(" ", ""))
        return score
    }

    private fun getPointValue(searchSubstring: String, objectName: String): Int {
        val searchText = searchSubstring.toUpperCase()
        val entityText = objectName.toUpperCase()
        val first = entityText.indexOf(searchText)
        val last = searchText.length
        var points: Int
        val val1 = last * last * 9
        val val2 =
            (java.lang.Double.valueOf(last.toDouble()) / java.lang.Double.valueOf(entityText.length.toDouble()) * 10).toInt() * 7
        val val3 = (Math.pow(last.toDouble(), 4.0) - first).toInt() * 5
        val val4 = last * 3
        points = val1 + val2 + val3 + val4
        if (entityText == searchText) points = points * 3
        return points
    }

    private fun filterAcceptanceRange(entities: List<Result<T>>): List<Result<T>> {
        val outList = ArrayList<Result<T>>()
        val topScore = if (entities.isEmpty()) 0f
        else entities[0].score

        entities.stream().forEach { result ->
            if (result.score / topScore == 1.0f) outList.add(result)
            else if (result.score / topScore > ACCEPTANCE_RANGE) outList.add(result)
        }
        return outList
    }

}

typealias LookupEntity<T> = (T) -> Result<T>
