package robo

import org.junit.Assert.assertNotNull
import org.junit.Test

class RoboCoreTest : BaseTestCase() {

    @Test
    fun instantiateDependencies() {
        val roboCore = RoboCore()
        assertNotNull(roboCore.output())
        assertNotNull(roboCore.input())
        assertNotNull(roboCore.data())
        assertNotNull(roboCore.pulse())
        assertNotNull(roboCore.session())
        assertNotNull(roboCore.terminal())
    }

}