package jface;

import java.lang.reflect.Method;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import actions.menuEditActions.CancelAction;
import actions.menuEditActions.DeleteAction;
import actions.menuEditActions.NewRowAction;
import actions.menuEditActions.SaveRowAction;
import actions.menuFileActions.ExitAction;
import actions.menuFileActions.NewFileAction;
import actions.menuFileActions.OpenFileAction;
import actions.menuFileActions.SaveAsFileAction;
import actions.menuFileActions.SaveFileAction;
import actions.menuHelpActions.AboutAction;
import actions.menuHelpActions.HelpAction;

public class AppWindow extends ApplicationWindow {
    Shell mainWindow;
    Font font;
    Text textName;
    Text textGroup;
    Button buttonSWTDone;
    int indexToRemove;
    TableViewer viewer;

    Button buttonNew;
    Button buttonSave;
    Button buttonDelete;
    Button buttonCancel;
    Session session = SessionManager.getSession();
    Entity newEntity;

    public boolean buttonNewStatus = false;
    public boolean buttonSaveStatus = false;
    public boolean buttonDeleteStatus = false;

    private NewRowAction newRowAction = new NewRowAction(this, buttonNewStatus);
    private SaveRowAction newSaveRowAction = new SaveRowAction(this, buttonSaveStatus);
    private DeleteAction newDeleteAction = new DeleteAction(this, buttonDeleteStatus);

    public AppWindow() {
	super(null);
	addMenuBar();
    }

    public AppWindow(Shell parentShell) {
	super(parentShell);
    }

    @Override
    protected void initializeBounds() {
	int xSize = 800;
	int ySize = 270;
	Rectangle sizes = getShell().getDisplay().getBounds();
	int x = sizes.width;
	int y = sizes.height;
	Rectangle mSize = new Rectangle((x - xSize) / 2, (y - ySize) / 2, xSize, ySize);
	getShell().setBounds(mSize);
	getShell().setMinimumSize(xSize, ySize);
	getShell().setMaximumSize(xSize + 400, ySize + 400);
	getShell().setText("JFace application:" + session.fileName);
    }

    @Override
    protected Control createContents(Composite parent) {
	mainWindow = this.getShell();
	Group generalComposite = new Group(mainWindow, SWT.V_SCROLL);
	generalComposite.setLayout(new FillLayout());
	createSash(generalComposite);
	parent.pack();
	return parent;
    }

    @Override
    protected MenuManager createMenuManager() {

	MenuManager mainMenu = new MenuManager();
	MenuManager menuFile = new MenuManager("&File", "1");
	mainMenu.add(menuFile);
// File Menu
	menuFile.add(new NewFileAction(this));
	menuFile.add(new OpenFileAction(this));
	menuFile.add(new SaveAsFileAction(this));
	menuFile.add(new SaveFileAction(this));
	menuFile.add(new ExitAction(this));

// Edit Menu
	MenuManager menuEdit = new MenuManager("&Edit", "2");
	mainMenu.add(menuEdit);

	menuEdit.add(newRowAction);
	menuEdit.add(newSaveRowAction);
	menuEdit.add(newDeleteAction);
	menuEdit.add(new CancelAction(this));
//Help Menu
	MenuManager menuHelp = new MenuManager("&About", "3");
	mainMenu.add(menuHelp);
	menuHelp.add(new HelpAction(this));
	menuHelp.add(new AboutAction(this));

	return mainMenu;
    }

    private void createSash(Composite c) {

	SashForm form = new SashForm(c, SWT.BORDER);
	form.setLayout(new FillLayout());
	form.SASH_WIDTH = 1;

	// Start create Left part
	Composite childLeft = new Composite(form, SWT.BORDER);
	childLeft.setLayout(new FillLayout());
	viewer = createTableViewer(childLeft);

	// Start create right part
	Composite childRight = new Composite(form, SWT.BORDER);
	FillLayout fillLayoutRight = new FillLayout(SWT.VERTICAL);
	childRight.setLayout(fillLayoutRight);

	Composite childFieldsArea = createRightFieldArea(childRight);

	// Labels
	Label labelName = new Label(childFieldsArea, SWT.NONE);
	labelName.setText("Name");
	labelName.setLayoutData(createGridForLabel());

	textName = new Text(childFieldsArea, SWT.BORDER);
	textName.setLayoutData(createGridForText());
	textName.addModifyListener(new ModifyListener() {
	    @Override
	    public void modifyText(ModifyEvent e) {
		moidfyEventMethod();
	    }

	});
	textName.addVerifyListener(AppWindow::ensureTextContainsOnlyTwoWordsWithSpaceAsDelimeter);

	Label labelGroup = new Label(childFieldsArea, SWT.NONE);
	labelGroup.setText("Group");
	labelGroup.setLayoutData(createGridForLabel());

	textGroup = new Text(childFieldsArea, SWT.BORDER);
	textGroup.setLayoutData(createGridForText());
	textGroup.addModifyListener(new ModifyListener() {
	    @Override
	    public void modifyText(ModifyEvent e) {
		moidfyEventMethod();
	    }
	});
	textGroup.addVerifyListener(AppWindow::ensureTextContainsOnlyDigits);

	Label labelSWTDone = new Label(childFieldsArea, SWT.NONE);
	labelSWTDone.setText("SWT task is done?");
	labelSWTDone.setLayoutData(createGridForLabel());

	buttonSWTDone = new Button(childFieldsArea, SWT.CHECK);
	buttonSWTDone.setSelection(false);
	buttonSWTDone.setLayoutData(createGridForText());
	buttonSWTDone.addListener(SWT.Selection, new Listener() {
	    @Override
	    public void handleEvent(Event event) {
		moidfyEventMethod();
	    }
	});

	// Buttons
	buttonNew = createButton(childFieldsArea, "New", createGridForButtonNew());
	buttonNew.addSelectionListener(new SelectionListener() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		newRecordAction();
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
	    }
	});

	buttonSave = createButton(childFieldsArea, "Save", createGridForButton());
	buttonSave.addSelectionListener(new SelectionListener() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		saveRowAction();
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	    }
	});

	buttonDelete = createButton(childFieldsArea, "Delete", createGridForButton());
	buttonDelete.addSelectionListener(new SelectionListener() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		deleteAction();
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
	    }
	});

	buttonCancel = createButton(childFieldsArea, "Cancel", createGridForButton());
	buttonCancel.setEnabled(true);
	buttonCancel.addSelectionListener(new SelectionListener() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		viewer.getTable().deselectAll();
		clearFields();
		changeMenuAndButtonsStatus(false, false, false);
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	    }
	});

	form.setWeights(new int[] { 70, 50 });
    }

    private static void ensureTextContainsOnlyTwoWordsWithSpaceAsDelimeter(VerifyEvent e) {
	String currentChar = e.text;
	String text = ((Text) e.widget).getText() + currentChar;
	e.doit = text.matches("[a-zA-Zà-ÿÀ-ß³²¿¯ºª']+[ ]{0,1}[a-zA-Zà-ÿÀ-ß³²¿¯ºª']*");
    }

    private static void ensureTextContainsOnlyDigits(VerifyEvent e) {
	String string = e.text;
	e.doit = string.matches("\\d*");
	return;
    }

    public static Composite createRightFieldArea(Composite parent) {
	Composite child = new Composite(parent, SWT.BORDER);
	GridLayout gridLayout = new GridLayout(4, false);
	gridLayout.marginHeight = 5;
	gridLayout.marginWidth = 5;
	gridLayout.horizontalSpacing = 10;
	gridLayout.verticalSpacing = 10;
	gridLayout.marginBottom = 10;
	child.setLayout(gridLayout);
	return child;
    }

    public static GridData createGridForLabel() {
	GridData gridForLabel = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	gridForLabel.grabExcessHorizontalSpace = true;
	gridForLabel.horizontalSpan = 1;
	gridForLabel.widthHint = 60;
	gridForLabel.heightHint = 30;
	return gridForLabel;
    }

    public static GridData createGridForText() {
	GridData gridForText = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_END);
	gridForText.grabExcessHorizontalSpace = true;
	gridForText.horizontalSpan = 3;
	gridForText.widthHint = 120;
	gridForText.heightHint = 30;
	return gridForText;
    }

    public static GridData createGridForButton() {
	GridData gridForButton = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
	gridForButton.grabExcessHorizontalSpace = true;
	gridForButton.horizontalSpan = 1;
	gridForButton.widthHint = 60;
	gridForButton.heightHint = 30;
	return gridForButton;
    }

    public static GridData createGridForButtonNew() {
	GridData gridForButtonNew = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
	gridForButtonNew.grabExcessHorizontalSpace = true;
	gridForButtonNew.horizontalSpan = 1;
	gridForButtonNew.horizontalIndent = 20;
	gridForButtonNew.widthHint = 60;
	gridForButtonNew.heightHint = 30;
	return gridForButtonNew;
    }

    public Button createButton(Composite parent, String text, GridData buttonGridData) {
	Button button = new Button(parent, SWT.PUSH);
	button.setLayoutData(buttonGridData);
	button.setText(text);
	button.setEnabled(false);
	return button;
    }

    public TableViewer createTableViewer(Composite parent) {
	TableViewer viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
	viewer.setContentProvider(ArrayContentProvider.getInstance());

	String[] columnNames = Entity.getFieldsNames();
	for (String colName : columnNames) {
	    createColumnName(viewer, colName);
	}

	viewer.getTable().getColumn(2).setWidth(80);
	viewer.setInput(session.getAllRecords());
	viewer.getTable().setSelection(0);
	viewer.getTable().setLinesVisible(true);
	viewer.getTable().setHeaderVisible(true);
	viewer.getTable().setHeaderBackground(new Color(181, 181, 181));

	viewer.addSelectionChangedListener(new ISelectionChangedListener() {
	    @Override
	    public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selection = viewer.getStructuredSelection();
		session.activeRecord = (Entity) selection.getFirstElement();
		if (session.activeRecord == null) {
		    clearFields();
		} else {
		    session.name = session.activeRecord.getName();
		    session.group = session.activeRecord.getGroup();
		    session.swtDone = session.activeRecord.getSwtDone();
		}
		System.out.println(session.activeRecord);
		setFields();
		changeMenuAndButtonsStatus(false, false, true);
	    }
	});
	return viewer;
    }

    private void createColumnName(TableViewer viewer, String columnName) {
	TableViewerColumn column = new TableViewerColumn(viewer, SWT.CHECK);
	column.getColumn().setText(columnName.toUpperCase());
	column.getColumn().setWidth(180);
	column.getColumn().setAlignment(SWT.CENTER);
	column.getColumn().setMoveable(false);
	column.getColumn().setResizable(false);
	column.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
		return getElementValue(columnName, element);
	    }
	});
	ColumnViewerComparator cSorter = new ColumnViewerComparator(viewer, column) {
	    @Override
	    protected int doCompare(Viewer viewer, Object e1, Object e2) {
		String p1 = getElementValue(columnName, e1);
		String p2 = getElementValue(columnName, e2);
		return p1.compareToIgnoreCase(p2);
	    }
	};
    }

    private String getElementValue(String columnName, Object element) {
	String answerString = "";
	try {
	    Method tmpMethod = Entity.getRefferedMethods().get(columnName);
	    answerString = String.valueOf(tmpMethod.invoke((Entity) element));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return answerString;
    }

    private void setFields() {
	textName.setText("");
	textName.setText(session.name);
	textGroup.setText(session.group);
	buttonSWTDone.setSelection(session.swtDone);
	redrawAll();
    }

    public void clearFields() {
	session.name = "";
	session.group = "";
	session.swtDone = false;
	session.activeRecord = null;
	setFields();
    }

    public void newRecordAction() {
	ifNewTableStarted();
	if (session.activeRecord != null) {
	    session.name = textName.getText();
	    session.group = textGroup.getText();
	    session.swtDone = buttonSWTDone.getSelection();
	    boolean answer = (session.activeRecord.getName().equals(session.name)
		    && session.activeRecord.getGroup().equals(session.group)
		    && session.activeRecord.getSwtDone() == session.swtDone);
	    if (!answer) {
		session.addEntity(session.name, session.group, session.swtDone);
	    }
	    viewer.refresh();
	    viewer.getTable().deselectAll();
	    clearFields();
	    changeMenuAndButtonsStatus(false, false, false);
	}
    }

    public void saveRowAction() {
	ifNewTableStarted();
	if (session.activeRecord == null) {
	    return;
	}
	session.name = textName.getText();
	session.group = textGroup.getText();
	session.swtDone = buttonSWTDone.getSelection();
	boolean answer = (session.activeRecord.getName().equals(session.name)
		&& session.activeRecord.getGroup().equals(session.group)
		&& session.activeRecord.getSwtDone() == session.swtDone);
	if (!answer) {
	    session.activeRecord.setName(session.name);
	    session.activeRecord.setGroup(session.group);
	    session.activeRecord.setSwtDone(session.swtDone);
	}
	viewer.getTable().deselectAll();
	clearFields();
	changeMenuAndButtonsStatus(false, false, false);
    }

    public void deleteAction() {
	session.removeCurrentObject();
	viewer.getTable().deselectAll();
	setFields();
	redrawAll();
	changeMenuAndButtonsStatus(false, false, false);
    }

    public void redrawAll() {
	textName.redraw();
	textGroup.redraw();
	buttonSWTDone.redraw();
	viewer.refresh();
    }

    public void reassignTableInput() {
	viewer.setInput(session.unsavedRecords);
	viewer.getTable().deselectAll();
    }

    public void clearSession() {
	viewer.getTable().deselectAll();
	session.clear();
	changeMenuAndButtonsStatus(false, false, false);
    }

    private void ifNewTableStarted() {
	if (session.unsavedRecords.size() == 0) {
	    Entity newEntity = new Entity(textName.getText(), textGroup.getText(), buttonSWTDone.getSelection());
	    session.addEntity(newEntity);
	    session.activeRecord = session.unsavedRecords.get(0);
	    newEntity = null;
	}
    }

    private void moidfyEventMethod() {
	if (session.activeRecord != null || session.unsavedRecords.size() == 0) {
	    changeMenuAndButtonsStatus(true, true, true);
	}
    }

    private void changeMenuAndButtonsStatus(boolean buttonNewStatus, boolean buttonSaveStatus,
	    boolean buttonDeleteStatus) {
	buttonNew.setEnabled(buttonNewStatus);
	buttonSave.setEnabled(buttonSaveStatus);
	buttonDelete.setEnabled(buttonDeleteStatus);

	newRowAction.setEnabled(buttonNewStatus);
	newSaveRowAction.setEnabled(buttonNewStatus);
	newDeleteAction.setEnabled(buttonDeleteStatus);
    }
}
