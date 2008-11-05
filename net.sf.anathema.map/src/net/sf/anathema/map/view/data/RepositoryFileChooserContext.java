package net.sf.anathema.map.view.data;

import de.disy.cadenza.core.model.ISession;
import de.disy.cadenza.core.navigator.IRawNavigatorTree;
import de.disy.cadenza.navigator.module.NavigatorNodeInfoFactoryRegistry;
import de.disy.cadenza.pro.repository.filechooser.IRepositoryFileChooserContext;

public class RepositoryFileChooserContext implements
		IRepositoryFileChooserContext {

	@Override
	public ISession getCadenzaModel() {
		return null;
	}

	@Override
	public NavigatorNodeInfoFactoryRegistry getNodeInfoFactoryRegistry() {
		return null;
	}

	@Override
	public IRawNavigatorTree getRawNavigatorTree() {
		return null;
	}
}