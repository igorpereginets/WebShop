package ua.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.service.interfaces.FileWriter;

@Service
public class FileWriterImpl implements FileWriter {

	private static final File PATH_TO_HOME = new File(System.getProperty("catalina.home"));
	private static final String FOLDER_IMAGES = File.separator + "images";
	private static final String DEFAULT_PATH = "/resources/img/no-photo.png";

	@Override
	public String save(Folder folder, MultipartFile file, int id) {
		if (!file.isEmpty()) {
			String fileName = getFileName(id, getExtension(file));
			String rootPath = getRootPath(folder, id);

			File pathToFolder = new File(PATH_TO_HOME, rootPath);
			File pathToFile = new File(pathToFolder, File.separator + fileName);
			createIfNotExists(pathToFolder);

			try {
				file.transferTo(pathToFile);
				return rootPath + File.separator + fileName;
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return DEFAULT_PATH;
	}

	@Override
	public String update(Folder folder, MultipartFile file, int id) {
		if (!file.isEmpty()) {
			String fileName = getFileName(id, getExtension(file));
			String rootPath = getRootPath(folder, id);

			File pathToFolder = new File(PATH_TO_HOME, rootPath);
			File pathToFile = new File(pathToFolder, File.separator + fileName);
			try {
				FileUtils.cleanDirectory(pathToFolder);
				file.transferTo(pathToFile);
				return rootPath + File.separator + fileName;
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return DEFAULT_PATH;
	}

	private String getRootPath(Folder folder, int id) {
		return FOLDER_IMAGES + File.separator + folder.name().toLowerCase() + File.separator + String.valueOf(id);
	}

	private String getFileName(int id, String extension) {
		return String.valueOf(id) + "-" + new Date().getTime() + extension;
	}

	private void createIfNotExists(File pathToFolder) {
		if (!pathToFolder.exists())
			pathToFolder.mkdirs();
	}

	private String getExtension(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		int extensionIndex = fileName.lastIndexOf(".");
		return fileName.substring(extensionIndex);
	}

	@Override
	public File getPathToFolder(Folder folder, int id) {
		return new File(PATH_TO_HOME, getRootPath(folder, id));
	}

}
