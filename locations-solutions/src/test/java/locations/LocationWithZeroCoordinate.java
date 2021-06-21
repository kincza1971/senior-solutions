package locations;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LocationWithZeroCoordinate extends TypeSafeMatcher<Location> {

    public static Matcher<Location> locationWithZeroCoordinate(Matcher<Double> matcher) {
        return new LocationWithZeroCoordinate(matcher);
    };

    private Matcher<Double> matcher;

    public LocationWithZeroCoordinate(Matcher<Double> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Location location) {
        return matcher.matches(location.getLon()) || matcher.matches(location.getLat()); //location.getLon() == 0.0 || location.getLat() == 0.0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" location with Zero coordinate ")
                .appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(Location item, Description mismatchDescription) {
        super.describeMismatchSafely(item, mismatchDescription);
        mismatchDescription.appendText(" location with zero coordinate (")
                .appendValue(item.getLat())
                .appendText("; ")
                .appendValue(item.getLon())
                .appendText(") ");
    }
}
