package de.htw.hundertwasser.errorsupport;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/*
 * Class for the StringTransferable
 */
public class StringTransferable implements Transferable {

	private String myData;

	/**
	 * Constructor
	 * 
	 * @param data
	 * @throws IllegalArgumentException
	 */
	public StringTransferable(String data) throws IllegalArgumentException {
		super();
		if (data == null)
			throw new IllegalArgumentException(
					"You need to define a source.It can't be null");
		if (data.isEmpty())
			throw new IllegalArgumentException(
					"You need to define a source.It can't be emtpy");
		myData = data;
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (flavor.equals(DataFlavor.stringFlavor)) {
			return myData;
		}
		return null;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor dataFlavor[] = new DataFlavor[1];
		dataFlavor[0] = DataFlavor.stringFlavor;
		return dataFlavor;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		for (DataFlavor fl : getTransferDataFlavors()) {
			if (fl.equals(flavor))
				return true;
		}
		return false;
	}

}
