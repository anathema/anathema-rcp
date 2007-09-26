package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.EclipsePlugin;
import net.sf.anathema.basics.eclipse.extension.internal.ExtensionFacade;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;

public class EclipseExtensionProvider implements IExtensionProvider {

	@Override
	public IPluginExtension[] getExtensions(String pluginId, String pointId) {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(pluginId, pointId);
		if (extensionPoint == null) {
			String message = Messages.EclipseExtensionProvider_ExtensionPointNotFoundMessage;
			EclipsePlugin
					.log(IStatus.WARNING, NLS.bind(message, pointId), null);
			return new IPluginExtension[0];
		}
		IExtension[] extensions = extensionPoint.getExtensions();
		return ArrayUtilities.transform(extensions, IPluginExtension.class,
				new ITransformer<IExtension, IPluginExtension>() {
					@Override
					public IPluginExtension transform(IExtension input) {
						return new ExtensionFacade(input);
					}
				});
	}
}