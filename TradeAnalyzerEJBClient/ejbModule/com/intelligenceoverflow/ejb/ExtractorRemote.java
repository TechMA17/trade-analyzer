package com.intelligenceoverflow.ejb;

import javax.ejb.Remote;

@Remote
public interface ExtractorRemote {
	void extractLines(String path);
}
