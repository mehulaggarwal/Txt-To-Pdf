package com.FileToPdf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "textfile")
public class TextFile {

	@Id
	@GeneratedValue
	private int File_id;

	@Column(length=20000)
	private String textfile;

	public int getFile_id() {
		return File_id;
	}

	public void setFile_id(int file_id) {
		File_id = file_id;
	}

	public String getTextfile() {
		return textfile;
	}

	public void setTextfile(String textfile) {
		this.textfile = textfile;
	}

	

}
