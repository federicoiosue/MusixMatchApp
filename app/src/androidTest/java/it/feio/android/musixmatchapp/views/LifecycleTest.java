package it.feio.android.musixmatchapp.views;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import it.feio.android.musixmatchapp.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class LifecycleTest {

	@Rule
	public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

	@Test
	public void lifecycleTest() {
		ViewInteraction imageView = onView(
				allOf(withId(R.id.track_album_cover), withContentDescription("Track image"),
						childAtPosition(
								childAtPosition(
										withId(R.id.track_list),
										0),
								0),
						isDisplayed()));
		imageView.check(matches(isDisplayed()));

		ViewInteraction textView = onView(
				allOf(withId(R.id.track_name), withText("Chosen"),
						childAtPosition(
								childAtPosition(
										IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
										1),
								0),
						isDisplayed()));
		textView.check(matches(isDisplayed()));

		ViewInteraction recyclerView = onView(
				allOf(withId(R.id.track_list),
						childAtPosition(
								withId(R.id.frameLayout),
								0)));
		recyclerView.perform(actionOnItemAtPosition(0, click()));

		ViewInteraction imageView2 = onView(
				allOf(withId(R.id.detail_cover),
						childAtPosition(
								allOf(withId(R.id.toolbar_layout),
										childAtPosition(
												withId(R.id.app_bar),
												0)),
								0),
						isDisplayed()));
		imageView2.check(matches(isDisplayed()));

		ViewInteraction textView2 = onView(
				allOf(withId(R.id.track_detail), withText("Yeah this is chosen\nIs it clear enough?\n'Cause it begins" +
								" " +
								"like\nI started when I was seventeen and now I'm here bra yeah ha\nYou don't even " +
								"know what it means so let me explain ya, ah ha\nI go far very far from my dreams and" +
								" " +
								"now I believe that, that I\nI don't need no money in my jeans to be an artist\nOne." +
								" " +
								"Two. Three\nThis is no music this is life this is what I live for\nSo let me " +
								"introduce myself cause I can't take no more\nHere there's too many stupid people and" +
								" " +
								"they have control\nI've a plan in my mind and I won't let go\nI've got a pistol in " +
								"my" +
								" hand pow pow pow shot\nAnd I don't want to hear you so please shut up and " +
								"now\nMmmhh" +
								" mmh mh\nFollow me follow me yeah\nSo Mmmhh mmh mh\nFollow me follow me yeah\nSo " +
								"Mmmhh mmh mh\n"),
						childAtPosition(
								allOf(withId(R.id.track_detail_container),
										childAtPosition(
												IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
												1)),
								0),
						isDisplayed()));
		textView2.check(matches(isDisplayed()));

		ViewInteraction appCompatImageButton = onView(
				allOf(withContentDescription("Navigate up"),
						childAtPosition(
								allOf(withId(R.id.detail_toolbar),
										childAtPosition(
												withId(R.id.toolbar_layout),
												1)),
								1),
						isDisplayed()));
		appCompatImageButton.perform(click());

		ViewInteraction textView3 = onView(
				allOf(withId(R.id.track_name), withText("Chosen"),
						childAtPosition(
								childAtPosition(
										IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
										1),
								0),
						isDisplayed()));
		textView3.check(matches(isDisplayed()));

	}

	private static Matcher<View> childAtPosition(
			final Matcher<View> parentMatcher, final int position) {

		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}

			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent)
						&& view.equals(((ViewGroup) parent).getChildAt(position));
			}
		};
	}
}
