import org.junit.Test;

import play.twirl.api.Content;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;

public class ViewTest {
    @Test
    public void renderTemplate() {
        Content html = views.html.index.render();
        assertEquals("text/html", html.contentType());
        assertTrue(contentAsString(html).contains("Twitter App"));
    }
}
