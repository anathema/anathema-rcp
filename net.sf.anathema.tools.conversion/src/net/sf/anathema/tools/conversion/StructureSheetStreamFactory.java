package net.sf.anathema.tools.conversion;

import java.io.IOException;
import java.io.InputStream;

import net.disy.commons.core.creation.IFactory;

public final class StructureSheetStreamFactory implements IFactory<InputStream, IOException> {
  @Override
  public InputStream createInstance() throws IOException {
    return ClassLoader.getSystemResourceAsStream("net/sf/anathema/tools/conversion/charmStructure.xslt");
  }
}