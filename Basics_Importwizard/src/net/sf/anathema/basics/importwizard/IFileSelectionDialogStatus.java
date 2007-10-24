package net.sf.anathema.basics.importwizard;

import org.eclipse.jface.dialogs.IMessageProvider;

public interface IFileSelectionDialogStatus extends IMessageProvider {

  boolean isComplete();
}