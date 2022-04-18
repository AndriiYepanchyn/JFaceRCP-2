package jface;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class SortedTableEditor {

    public SortedTableEditor(Shell shell) {

	TableViewer viewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
	viewer.setContentProvider(ArrayContentProvider.getInstance());

	TableViewerColumn column = createColumnFor(viewer, "Name");
	column.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
		return ((Entity) element).getName();
	    }
	});

	column.setEditingSupport(new AbstractEditingSupport(viewer) {

	    @Override
	    protected Object getValue(Object element) {
		return ((Entity) element).getName();
	    }

	    @Override
	    protected void doSetValue(Object element, Object value) {
		((Entity) element).setName(value.toString());
	    }
	});
    }

    private TableViewerColumn createColumnFor(TableViewer viewer, String label) {
	TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
	column.getColumn().setWidth(200);
	column.getColumn().setText(label);
	column.getColumn().setMoveable(true);
	return column;
    }
}
