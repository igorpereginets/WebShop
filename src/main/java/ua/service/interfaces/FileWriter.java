package ua.service.interfaces;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder {
		GOODS
	}
	
	String save(Folder folder, MultipartFile file, int id);
	
	String update(Folder folder, MultipartFile file, int id);
	
	File getPathToFolder(Folder folder, int id);
}
