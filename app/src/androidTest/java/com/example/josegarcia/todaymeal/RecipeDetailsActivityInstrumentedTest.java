package com.example.josegarcia.todaymeal;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.josegarcia.todaymeal.model.Ingredient;
import com.example.josegarcia.todaymeal.model.Recipe;
import com.example.josegarcia.todaymeal.model.Step;
import com.example.josegarcia.todaymeal.views.RecipeDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.josegarcia.todaymeal.views.RecipeListFragment.RECIPE_KEY;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<RecipeDetailsActivity> activity =
            new ActivityTestRule<>(RecipeDetailsActivity.class, false, false);

    @Before
    public void startActivity() {
        Recipe mockedRecipe = new Recipe(
                1,
                "Nutella Pie",
                Arrays.asList(new Ingredient(2, "CUP", "Grahm Cracker crumbs")),
                Arrays.asList(new Step(0, "Recipe Introduction", "Recipe Introduction",
                        "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
                        "")),
                9,
                ""
        );

        Intent intent = new Intent();
        intent.putExtra(RECIPE_KEY, mockedRecipe);
        activity.launchActivity(intent);
    }

    @Test
    public void testIfVideoIsShowingUp() {
        onView(withId(R.id.steps_container)).check(matches(isDisplayed()));
        onView(withId(R.id.steps_container))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.step_content)).check(matches(isDisplayed()));
    }
}
