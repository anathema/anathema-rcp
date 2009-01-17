package net.sf.anathema.charms.extension;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChild;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.duration.DurationDto;

import org.junit.Test;

public class DurationReader_KeywordTest {

  @Test
  public void returnsKeywordDuration() throws Exception {
    IExtensionElement text = createKeywordElement("Instant"); //$NON-NLS-1$
    assertThat(readDuration(text).keyword, is("Instant")); //$NON-NLS-1$
  }

  @Test
  public void readsKeywordDuration() throws Exception {
    IExtensionElement text = createKeywordElement("Indefinite"); //$NON-NLS-1$
    assertThat(readDuration(text).keyword, is("Indefinite")); //$NON-NLS-1$
  }
  
  private DurationDto readDuration(IExtensionElement duration) {
    return new DurationReader(duration).read();
  }

  public static IExtensionElement createKeywordElement(String keyword) throws ExtensionException {
    MockStringAttribute keywordAttribute = new MockStringAttribute("keyword", keyword); //$NON-NLS-1$
    IExtensionElement text = DurationElementObjectMother.createNamedElement("text", keywordAttribute);
    return DurationElementObjectMother.createDuration(new MockNamedChild("text", text), new MockChildren(text)); //$NON-NLS-1$
  }
}