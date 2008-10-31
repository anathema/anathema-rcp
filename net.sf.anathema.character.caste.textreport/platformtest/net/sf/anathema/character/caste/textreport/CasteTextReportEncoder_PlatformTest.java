package net.sf.anathema.character.caste.textreport;

import static org.junit.Assert.*;

import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

import org.junit.Test;

public class CasteTextReportEncoder_PlatformTest {

  @Test
  public void testname() throws Exception {
    List<ITextReportEncoder> encoders = new TextEncoderExtensionPoint().getEncoders();
    assertTrue(CollectionUtilities.contains(encoders, new IPredicate<ITextReportEncoder>() {
      @Override
      public boolean evaluate(ITextReportEncoder encoder) {
        return encoder instanceof CasteTextReportEncoder;
      }
    }));
  }
}