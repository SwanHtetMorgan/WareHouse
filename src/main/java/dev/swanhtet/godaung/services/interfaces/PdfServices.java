package dev.swanhtet.godaung.services.interfaces;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface PdfServices {

	ByteArrayInputStream generateSystemUserPDFFIle() throws IOException;

}
