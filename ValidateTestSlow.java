import java.io.File;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateTestSlow {

	public static void main(String[] args) throws Exception {
		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		File schemaLocation = new File("/somewhere/myschema.xsd");
		Schema schema = factory.newSchema(schemaLocation);
		Validator validator = schema.newValidator();
		Source source = new StreamSource("/somewhere/myxml.xml");

		System.out.println("Validation Starts now!");

		long start = System.currentTimeMillis();
		try {
			validator.validate(source);
			System.out.println(" XML is valid.");
		} catch (SAXException ex) {
			System.out.println(" XML not valid because ");
			System.out.println(ex.getMessage());
		}
		System.out.println("Validation complete!");

		System.out.println("Time (s): " + (System.currentTimeMillis() - start));
	}
}
