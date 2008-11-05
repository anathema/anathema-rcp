package net.sf.anathema.map.view.gisterm;

import java.io.IOException;

import com.vividsolutions.jts.geom.Geometry;

import de.disy.gis.gisterm.geometry.validation.GeometryValidationException;
import de.disy.gis.gisterm.map.layer.feature.IFeatureLayer;
import de.disy.gis.gisterm.map.layer.feature.IFeatureLayerAndThemeLayerId;
import de.disy.gis.gisterm.map.layer.feature.IFeatureProxy;
import de.disy.gis.gisterm.map.layer.feature.attribute.IAttributeValuePairList;
import de.disy.gis.gisterm.map.layer.feature.attribute.ILayerAttributeList;
import de.disy.gis.gisterm.pro.edit.layer.AbstractFeatureLayerEditStrategy;
import de.disy.gis.gisterm.pro.edit.layer.FeatureSaveException;
import de.disy.gis.gisterm.pro.edit.layer.TransactionFailedException;
import de.disy.gis.gisterm.pro.mode.edit.EditGeometryType;

public class Db4OEditStrategy extends AbstractFeatureLayerEditStrategy {

  @Override
  public void applyAttributeChanges(IFeatureLayerAndThemeLayerId arg0, IFeatureProxy arg1, IAttributeValuePairList arg2)
      throws IOException,
      FeatureSaveException {
    // TODO Auto-generated method stub
  }

  @Override
  public void applyGeometryChanges(IFeatureLayerAndThemeLayerId arg0, IFeatureProxy arg1, Geometry arg2)
      throws IOException,
      GeometryValidationException {
    // TODO Auto-generated method stub
  }

  @Override
  public void commitTransaction(IFeatureLayer arg0) throws TransactionFailedException {
    // TODO Auto-generated method stub
  }

  @Override
  public EditGeometryType getEditGeometryType(IFeatureLayer arg0) {
    return null;
  }

  @Override
  public ILayerAttributeList getEditableAttributes(IFeatureLayer arg0) throws IOException {
    return null;
  }

  @Override
  public void insertNew(IFeatureLayerAndThemeLayerId arg0, Geometry arg1, IAttributeValuePairList arg2)
      throws IOException,
      GeometryValidationException,
      FeatureSaveException {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean isApplicable(IFeatureLayer arg0) {
    return true;
  }

  @Override
  public void remove(IFeatureLayerAndThemeLayerId arg0, IFeatureProxy[] arg1) throws IOException {
    // TODO Auto-generated method stub
  }

  @Override
  public void rollbackTransaction(IFeatureLayer arg0) throws TransactionFailedException {
    // TODO Auto-generated method stub
  }

  @Override
  public void startTransaction(IFeatureLayer arg0) throws TransactionFailedException {
    // TODO Auto-generated method stub
  }
}