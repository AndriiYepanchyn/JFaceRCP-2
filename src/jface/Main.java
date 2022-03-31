package jface;

import org.eclipse.swt.widgets.Display;

public class Main {
    public static void main(String[] args) {
	AppWindow win = new AppWindow();
	win.setBlockOnOpen(true);
	win.open();
	Display.getCurrent().dispose();
    }
}
