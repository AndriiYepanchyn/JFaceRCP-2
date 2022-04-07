package jface;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

public abstract class AbstractEditingSupport extends EditingSupport {
    private TextCellEditor editor;

    public AbstractEditingSupport(TableViewer viewer) {
	super(viewer);
	this.editor = new TextCellEditor(viewer.getTable());
    }

    @Override
    protected boolean canEdit(Object element) {
	return true;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
	return editor;
    }

    @Override
    protected void setValue(Object element, Object value) {
	doSetValue(element, value);
	getViewer().update(element, null);
    }

    protected abstract void doSetValue(Object element, Object value);
}
