package net.sf.anathema.lib.textualdescription;


public abstract class AbstractTextualDescription implements ITextualDescription {

  private boolean dirty = true;

  public boolean isDirty() {
    return dirty;
  }

  public void setDirty(boolean isDirty) {
    this.dirty = isDirty;
    fireChangedEvent();
  }

  protected abstract void fireChangedEvent();
}