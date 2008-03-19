/**
 * 
 */
package net.sf.anathema.map.view.data;

import java.io.File;

import de.disy.cadenza.core.resources.UnresolvedPath;
import de.disy.gis.gisterm.imagecatalog.layer.IImageCatalogProperties;
import de.disy.gis.gisterm.map.scale.IScaleRange;

public final class XeriarCreationProperties implements
		IImageCatalogProperties {
	private final File dbfFile;

	public XeriarCreationProperties(File dbfFile) {
		this.dbfFile = dbfFile;
	}

	public String getAbsoluteImagePath() {
		return dbfFile.getAbsolutePath();
	}

	public String getCatalogName() {
		return "Creation Xeriar";
	}

	public double getInitialMaxScale() {
		return IScaleRange.MAX_VALUE;
	}

	public double getInitialMinScale() {
		return IScaleRange.MIN_VALUE;
	}

	@Override
	public UnresolvedPath getMmlSaveString() {
		return new UnresolvedPath("Xeriar Creation");
	}
}