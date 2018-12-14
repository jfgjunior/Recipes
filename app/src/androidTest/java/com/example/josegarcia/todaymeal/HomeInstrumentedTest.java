package com.example.josegarcia.todaymeal;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.example.josegarcia.todaymeal.views.HomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class HomeInstrumentedTest {
    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(SimpleIdlingResource.getInstance());
    }

    @Test
    public void testOnRecipeClick() {
        onView(withId(R.id.recipe_list)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.collapsing_toolbar))))
                .check(matches(withText("Nutella Pie")));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(SimpleIdlingResource.getInstance());
    }
}
