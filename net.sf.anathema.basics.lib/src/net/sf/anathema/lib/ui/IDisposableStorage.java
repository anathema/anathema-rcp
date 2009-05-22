package net.sf.anathema.lib.ui;

public interface IDisposableStorage {

  public <T extends IDisposable> T addDisposable(T disposable);

}