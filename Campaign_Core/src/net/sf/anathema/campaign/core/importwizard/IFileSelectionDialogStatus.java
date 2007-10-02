package net.sf.anathema.campaign.core.importwizard;

import org.eclipse.jface.dialogs.IMessageProvider;

public interface IFileSelectionDialogStatus extends IMessageProvider {

  boolean isComplete();
}