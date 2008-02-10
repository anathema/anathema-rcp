package net.sf.anathema.basics.importexport;

import org.eclipse.jface.dialogs.IMessageProvider;

public interface IFileSelectionDialogStatus extends IMessageProvider {

  boolean isComplete();
}