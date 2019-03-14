package robo.models

class Result<T>(val entity: T, val name: String) : Comparable<T> {
    var score: Float = 0.0f

    fun addScore(deltaScore: Float): Result<T> {
        if (score == 0.0f)
            score = deltaScore
        else if (score != deltaScore) {
            val lastScore = score!!.toInt()
            this.score = (lastScore + deltaScore) / 2
        }
        return this
    }

    override fun compareTo(o: T): Int {
        return score.toInt()
    }

}