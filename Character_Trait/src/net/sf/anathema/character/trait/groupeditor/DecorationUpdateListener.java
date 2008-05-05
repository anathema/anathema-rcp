/**
 * 
 */
package net.sf.anathema.character.trait.groupeditor;


import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public final class DecorationUpdateListener implements IPartListener {

  private final GroupEditor editor;

  public DecorationUpdateListener(GroupEditor editor) {
    this.editor = editor;
  }

  @Override
  public void partActivated(IWorkbenchPart part) {
    // nothing to do
  }

  @Override
  public void partBroughtToTop(IWorkbenchPart part) {
    if (part == editor) {
      editor.updateDecorations();
    }
  }

  @Override
  public void partClosed(IWorkbenchPart part) {
    // nothing to do
  }

  @Override
  public void partDeactivated(IWorkbenchPart part) {
    // nothing to do
  }

  @Override
  public void partOpened(IWorkbenchPart part) {
    // nothing to do
  }
}