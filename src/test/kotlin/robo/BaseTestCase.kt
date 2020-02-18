package robo

import org.junit.Before
import org.mockito.MockitoAnnotations

open class BaseTestCase {

    /**
     *  Given [initial context], when [event occurs], then [ensure some outcomes]
     */
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

}