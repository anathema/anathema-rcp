package net.sf.anathema.lib.textualdescription;

import java.util.List;

public interface IStyledTextualDescription2 extends IStyledTextualDescription {

  ITextPart getPart(int offset);

  int getPartOffset(ITextPart part);

  List<ITextPart> getParts(int offset, int length);
}