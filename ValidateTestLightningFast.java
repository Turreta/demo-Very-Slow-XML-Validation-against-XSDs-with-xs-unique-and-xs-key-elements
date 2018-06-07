import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateTestLightningFast {

	public static void main(String[] args) throws Exception {

		String xmlFile = "/somewhere/myxml.xml";

		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		File schemaLocation = new File("/somewhere/my-improved-schema.xsd");
		Schema schema = factory.newSchema(schemaLocation);
		Validator validator = schema.newValidator();
		Source source = new StreamSource(xmlFile);

		System.out.println("Validation Starts now!");

		JAXBContext jc = JAXBContext.newInstance(Names.class);

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		File xml = new File(xmlFile);
		Names names = (Names) unmarshaller.unmarshal(xml);

		long originalSize = names.getName().size();
		long distinctSize = names.getName().stream().distinct().count();

		if (originalSize != distinctSize) {
			throw new Exception("Duplicates detected!");
		}

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
