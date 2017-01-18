package fi.iki.jka;

import org.junit.Before;
import org.junit.Test;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.jmock.Expectations;

import java.awt.event.ActionEvent;

public class JPhotoFrameTest
{
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private JPhotoFrame jPhotoFrame = null;
    private JPhotoActionsInterface jPhotoActionsInterface;
    private JPhotoList list;
    private JPhotoCollection photos;

    @Before
    public void setUp() throws Exception
    {
        this.jPhotoActionsInterface = context.mock(JPhotoActionsInterface.class);
        this.photos = new JPhotoCollection();
        this.list = new JPhotoList(this.photos, 100);

        this.jPhotoFrame = new JPhotoFrame((String)null, photos, list);
        this.jPhotoFrame.setjPhotoActions(jPhotoActionsInterface);
    }

    @Test
    public void testShowSlideShow() {
        ActionEvent actionEvent = new ActionEvent((Object)this, 1, (String)JPhotoMenu.A_SLIDESHOW);

        context.checking(new Expectations() {{
            exactly(1).of(jPhotoActionsInterface).slideShow(photos, list, 5000);
        }});

        jPhotoFrame.actionPerformed(actionEvent);
    }

    @Test
    public void testShowSlideShowFast() {
        ActionEvent actionEvent = new ActionEvent((Object)this, 1, (String)JPhotoMenu.A_SLIDESHOW_FAST);

        context.checking(new Expectations() {{
            exactly(1).of(jPhotoActionsInterface).slideShow(photos, list, 100);
        }});

        jPhotoFrame.actionPerformed(actionEvent);
    }
}
