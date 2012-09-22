/**
 * The navigationbar uses this model to show the current PhotoBox
 * @author Dominic Holz (template by Daniel Rhein)
 *
 */

package de.htw.hundertwasser.model;

import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import de.htw.hundertwasser.core.PhotoBox;

/**
 * 
 * 
 *
 */
public class NavBarPhotoBoxModel implements TreeModel {

	ArrayList<TreeModelListener> treeModelListener = null;
	ArrayList<PhotoBox> photoBoxList = null;

	public NavBarPhotoBoxModel() {
		super();
		treeModelListener = new ArrayList<TreeModelListener>();
		photoBoxList = new ArrayList<PhotoBox>();
	}

	/**
	 * 
	 */
	@Override
	public void addTreeModelListener(TreeModelListener arg0) {
		treeModelListener.add(arg0);
	}

	/**
	 * 
	 */
	@Override
	public Object getChild(Object objParent, int index) {
		if (objParent instanceof PhotoBox)
			return null;
		if (objParent instanceof String) {
			if (index > photoBoxList.size())
				return null;
			return photoBoxList.get(index);
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public int getChildCount(Object objParent) {
		if (objParent instanceof String) {
			return photoBoxList.size();
		}
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public int getIndexOfChild(Object objParent, Object objChild) {
		if (objParent instanceof String) {
			if (objChild instanceof PhotoBox) {
				return getIndexOfChild((PhotoBox) objChild);
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param photoBoxChild
	 * @return
	 */
	private int getIndexOfChild(PhotoBox photoBoxChild) {
		int i = 0;
		for (PhotoBox photoBoxObj : photoBoxList) {
			if (photoBoxObj.equals(photoBoxChild)) {
				return i;
			}
			i++;
		}
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public Object getRoot() {
		return "Your photo Boxs";
	}

	/**
	 * 
	 */
	@Override
	public boolean isLeaf(Object objParent) {
		if (objParent instanceof String) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param photoBox
	 */
	public void addPhotoBox(PhotoBox photoBox) {
		photoBoxList.add(photoBox);
		sentListChanged();
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public PhotoBox getPhotoBox(int index) {
		return photoBoxList.get(index);
	}

	/**
	 * 
	 * @param photoBox
	 */
	public void removePhotoBox(PhotoBox photoBox) {
		photoBoxList.remove(photoBox);
		sentListChanged();
	}

	/**
	 * 
	 */
	public void clear() {
		photoBoxList.clear();
		sentListChanged();
	}

	/**
	 * 
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {
		treeModelListener.remove(arg0);
		sentListChanged();
	}

	/**
	 * 
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		TreeModelEvent event = new TreeModelEvent(newValue, path);
		for (TreeModelListener listener : treeModelListener) {
			listener.treeStructureChanged(event);
		}
	}

	/**
	 * 
	 */
	private void sentListChanged() {
		for (TreeModelListener listener : treeModelListener) {
			listener.treeStructureChanged(null);
		}
	}
}
