package net.sf.anathema.lib.textualdescription;

import static org.junit.Assert.*;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class SimpleTextualDescriptionTest {

  private SimpleTextualDescription description;

  @Before
  public void createDescription() {
    description = new SimpleTextualDescription();
  }
  
  @Test
  public void noTextChangeListenersAreRegistered() throws Exception {
    assertEquals(0, description.getTextChangeListenerCount());
  }
  
  @Test
  public void textChangeListenerCountIncreasesWithAddition() throws Exception {
    description.addTextChangedListener(createMockTextChangeListener());
    assertEquals(1, description.getTextChangeListenerCount());
  }

  @SuppressWarnings("unchecked")
  private IObjectValueChangedListener<String> createMockTextChangeListener() {
    return EasyMock.createMock(IObjectValueChangedListener.class);
  }
}