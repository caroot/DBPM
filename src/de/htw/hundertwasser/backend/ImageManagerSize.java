package de.htw.hundertwasser.backend;

public enum ImageManagerSize {
BYTE("Byte"),
KILOBYTE("KB"),
MEGABYTE("MB"),
GIGABYTE("GB");

private String name;


private ImageManagerSize(String name)
{
	this.name=name;
}

public String getSizeName()
{
	return name;
}
}
