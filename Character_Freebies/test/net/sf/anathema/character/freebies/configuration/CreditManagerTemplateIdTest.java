package net.sf.anathema.character.freebies.configuration;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;
import net.sf.anathema.character.freebies.plugin.ICharacterFreebiesPluginConstants;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class CreditManagerTemplateIdTest {
	private static final String CREDIT_ID = "creditId"; //$NON-NLS-1$
	private static final String CREDITS_EXTENSION_POINT = "credits"; //$NON-NLS-1$
	private static final String TEMPLATE_ID = "template"; //$NON-NLS-1$
	private CreditManager manager;
	private IExtensionProvider provider;

	@Before
	public void createManager() throws Exception {
		this.provider = EasyMock.createMock(IExtensionProvider.class);
		EasyMock.expect(
				provider.getExtensions(
						ICharacterFreebiesPluginConstants.PLUGIN_ID,
						CREDITS_EXTENSION_POINT)).andReturn(
								new IPluginExtension[0]);
		EasyMock.replay(provider);
		this.manager = new CreditManager(provider, TemplateProviderObjectMother
				.createTemplateProvider(TemplateProviderObjectMother
						.createCharacterId(TEMPLATE_ID), TEMPLATE_ID));
	}

	@Test
	public void hasNoCreditsIfNoExtensionsAreRegistered() {
		assertFalse(manager.hasCredit(TEMPLATE_ID, CREDIT_ID));
	}
}