package net.sf.anathema.editor.styledtext.jface;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public final class STE2ViewerConfiguration extends SourceViewerConfiguration {
  private final STE2Partitioner partitioner;

  public STE2ViewerConfiguration(STE2Partitioner partitioner) {
    this.partitioner = partitioner;
  }

  @Override
  public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
    STE2DamagerRepairer repairer = new STE2DamagerRepairer(partitioner);
    PresentationReconciler reconciler = new PresentationReconciler();
    reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
    reconciler.setDamager(repairer, IDocument.DEFAULT_CONTENT_TYPE);
    reconciler.setRepairer(repairer, IDocument.DEFAULT_CONTENT_TYPE);
    for (String type : STE2Partitioner.CONTENT_TYPES) {
      reconciler.setDamager(repairer, type);
      reconciler.setRepairer(repairer, type);
    }
    return reconciler;
  }
}