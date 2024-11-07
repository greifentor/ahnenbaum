package de.ollie.ahnenbaum.backup;

import jakarta.inject.Named;
import java.io.IOException;

@Named
public class BackupFileWriter {

	static final String FILE_NAME_PARAMETER = "file.name";

	public void write(String fileName, String json) throws IOException {
		// TODO
	}
}
