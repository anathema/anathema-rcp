/**
 * 
 */
package net.sf.anathema.editor.styledtext.jface;

import org.eclipse.jface.text.formatter.IFormattingStrategy;

final class DemoFormattingStrategy implements IFormattingStrategy {
  @Override
  public String format(String content, boolean isLineStart, String indentation, int[] positions) {
    return content.replaceAll("<.?b>", "");
  }

  @Override
  public void formatterStarts(String initialIndentation) {
    // TODO Auto-generated method stub
  }

  @Override
  public void formatterStops() {
    // TODO Auto-generated method stub
  }
}