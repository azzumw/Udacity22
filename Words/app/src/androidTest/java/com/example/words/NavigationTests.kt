package com.example.words

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTests {
    private lateinit var letterListScenario: FragmentScenario<LetterListFragment>
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        navController = TestNavHostController(appContext)

        letterListScenario = launchFragmentInContainer(themeResId = R.style.Theme_Words)

        //which nav graph
        letterListScenario.onFragment{
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(it.requireView(),navController)
        }
    }

    @Test
    fun navigate_to_words_nav_component() {

        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12, click()))

        assertEquals(navController.currentDestination?.id,R.id.wordListFragment)
    }
}