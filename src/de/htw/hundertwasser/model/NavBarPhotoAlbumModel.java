package de.htw.hundertwasser.model;

import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import de.htw.hundertwasser.core.PhotoAlbum;
/**
 * The navigationbar use this model to show the current PhotoAlbum
 * @author daniel
 *
 */
public class NavBarPhotoAlbumModel implements TreeModel {

	ArrayList<TreeModelListener> treeModelListener = null;
	ArrayList<PhotoAlbum> photoAlbumList = null;

	public NavBarPhotoAlbumModel() {
		super();
		treeModelListener = new ArrayList<TreeModelListener>();
		photoAlbumList = new ArrayList<PhotoAlbum>();
	}

	@Override
	public void addTreeModelListener(TreeModelListener arg0) {
		treeModelListener.add(arg0);
	}

	@Override
	public Object getChild(Object objParent, int index) {
		if (objParent instanceof PhotoAlbum)
			return null;
		if (objParent instanceof String) {
			if (index > photoAlbumList.size())
				return null;
			return photoAlbumList.get(index);
		}
		return null;
	}

	@Override
	public int getChildCount(Object objParent) {
		if (objParent instanceof String) {
			return photoAlbumList.size();
		}
		return 0;
	}

	@Override
	public int getIndexOfChild(Object objParent, Object objChild) {
		if (objParent instanceof String) {
			if (objChild instanceof PhotoAlbum)
			{
				return getIndexOfChild((PhotoAlbum)objChild);
			}
		}
		return 0;
	}

	private int getIndexOfChild(PhotoAlbum photoAlbumChild) {
		int i = 0;
		for (PhotoAlbum photoAlbumObj : photoAlbumList) {
			if (photoAlbumObj.equals(photoAlbumChild)) {
				return i;
			}
			i++;
		}
		return 0;
	}

	@Override
	public Object getRoot() {
		return "Your photo albums";
	}

	@Override
	public boolean isLeaf(Object objParent) {
		if (objParent instanceof String) {
			return false;
		} else {
			return true;
		}
	}

	public void addPhotoAlbum(PhotoAlbum photoalbum)
	{
		photoAlbumList.add(photoalbum);
		sentListChanged();
	}
	
	public PhotoAlbum getPhotoAlbum(int index)
	{
		return photoAlbumList.get(index);
	}
	
	public void removePhotoAlbum(PhotoAlbum photoalbum)
	{
		photoAlbumList.remove(photoalbum);
		sentListChanged();
	}
	
	public void clear()
	{
		photoAlbumList.clear();
		sentListChanged();
	}
	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {
		treeModelListener.remove(arg0);
		sentListChanged();
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		TreeModelEvent event = new TreeModelEvent(newValue, path);
		for (TreeModelListener listener : treeModelListener) {
	        listener.treeStructureChanged(event);
	    }
	}

	
	private void sentListChanged()
	{
	    for (TreeModelListener listener : treeModelListener) {
	        listener.treeStructureChanged(null);
	    }
	}
}
