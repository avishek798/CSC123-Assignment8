package com.usman.csudh.bank.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHook extends CurrencyManager {

	@Override
	protected InputStream getInputStream() throws IOException, ConfigFileErrorException {
		return new FileInputStream(new File(Configuration.getInstance().getFileName()));
	}

}
