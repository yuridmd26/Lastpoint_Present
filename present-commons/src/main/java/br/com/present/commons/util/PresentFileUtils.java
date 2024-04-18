package br.com.present.commons.util;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.apache.maven.api.model.Model;
import org.apache.maven.model.v4.MavenStaxReader;

import br.com.present.commons.docs.IDocsMsgConsts;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PresentFileUtils {

	public static String getVersionPom(String pomFilePath) {
		try {
			MavenStaxReader reader = new MavenStaxReader();
            Model model = reader.read(new FileReader(pomFilePath));
            if(model.getVersion() != null && !model.getVersion().isEmpty()) {
            	return model.getVersion();
            }
            return IDocsMsgConsts.DEFAULT_VERSION;
        } catch (IOException | XMLStreamException e) {
            return IDocsMsgConsts.DEFAULT_VERSION;
        }
	}
	
}