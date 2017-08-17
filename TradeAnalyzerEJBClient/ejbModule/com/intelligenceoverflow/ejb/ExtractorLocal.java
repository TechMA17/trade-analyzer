package com.intelligenceoverflow.ejb;

import javax.ejb.Local;

@Local
public interface ExtractorLocal {
	void extractLines(String path);
}
