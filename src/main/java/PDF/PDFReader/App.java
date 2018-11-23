package PDF.PDFReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class App {
	public static void main(String[] args) throws IOException {
		// System.out.println( "Hello World!" );

		try (PDDocument document = PDDocument.load(new File("I:\\Project\\myPdf.pdf"))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);
				System.out.println("Text:" + pdfFileInText);
				System.out.println("-----------------------");
				// split by whitespace
				String lines[] = pdfFileInText.split("\\r?\\n");

				HashMap<String, String> dictionary = new HashMap<String, String>();
				for (String line : lines) {
					System.out.println(line);
					if (line.contains(":")) {
						String key = line.split(":")[0];
						String value = line.split(":")[1];
						dictionary.put(key, value);
					}
				}
				System.out.println("*****************************************************");
				Set<Map.Entry<String, String>> entrySet = dictionary.entrySet();
				for (Entry<String, String> entry : entrySet) {
					System.out.println("------------------------------------------------");
					System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
				}
			}

		}

	}
}
