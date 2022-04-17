package jface;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;

public abstract class ColumnViewerComparator extends ViewerComparator {

    public static final int ASC = 1;
    public static final int NONE = 0;
    public static final int DESC = -1;

    private int order = 0;
    private TableViewerColumn column;
    private ColumnViewer viewer;

    public ColumnViewerComparator(ColumnViewer viewer, TableViewerColumn column) {
	this.column = column;
	this.viewer = viewer;
	SelectionAdapter selectionAdapter = createSelectionAdapter();
	this.column.getColumn().addSelectionListener(selectionAdapter);
    }

    private SelectionAdapter createSelectionAdapter() {
	return new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		if (ColumnViewerComparator.this.viewer.getComparator() != null) {
		    if (ColumnViewerComparator.this.viewer.getComparator() == ColumnViewerComparator.this) {
			int tOrder = ColumnViewerComparator.this.order;
			if (tOrder == ASC) {
			    setSorter(ColumnViewerComparator.this, DESC);
			} else if (tOrder == DESC) {
			    setSorter(ColumnViewerComparator.this, NONE);
			}
		    } else {
			setSorter(ColumnViewerComparator.this, ASC);
		    }
		} else {
		    setSorter(ColumnViewerComparator.this, ASC);
		}
	    }
	};
    }

    public void setSorter(ColumnViewerComparator sorter, int order) {
	Table columnParent = column.getColumn().getParent();
	if (order == NONE) {
	    columnParent.setSortColumn(null);
	    columnParent.setSortDirection(SWT.NONE);
	    viewer.setComparator(null);

	} else {
	    columnParent.setSortColumn(column.getColumn());
	    sorter.order = order;
	    columnParent.setSortDirection(order == ASC ? SWT.DOWN : SWT.UP);

	    if (viewer.getComparator() == sorter) {
		viewer.refresh();
	    } else {
		viewer.setComparator(sorter);
	    }
	}
    }

    @Override
    public int compare(Viewer tmpViewer, Object e1, Object e2) {
	return order * doCompare(tmpViewer, e1, e2);
    }

    protected abstract int doCompare(Viewer tmpViewer, Object e1, Object e2);
}
